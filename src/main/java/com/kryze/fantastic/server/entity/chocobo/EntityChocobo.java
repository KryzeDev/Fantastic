package com.kryze.fantastic.server.entity.chocobo;

import com.kryze.fantastic.server.item.ItemHandler;
import com.kryze.fantastic.server.sound.SoundHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class EntityChocobo extends TameableEntity implements IAnimatable {

    private static final DataParameter<Boolean> SADDLED = EntityDataManager.createKey(EntityChocobo.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(EntityChocobo.class, DataSerializers.BOOLEAN);
    public boolean forcedSit = false;

    private AnimationFactory factory = new AnimationFactory(this);

    public EntityChocobo(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.setTamed(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.5D));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.23F)
                .createMutableAttribute(Attributes.MAX_HEALTH, 30);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SADDLED, Boolean.valueOf(false));
        this.dataManager.register(SITTING, Boolean.valueOf(false));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Saddled", this.isSaddled());
        compound.putBoolean("ChocoboSitting", this.isSitting());
        compound.putBoolean("ForcedToSit", this.forcedSit);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSaddled(compound.getBoolean("Saddled"));
        this.setSitting(compound.getBoolean("ChocoboSitting"));
        this.forcedSit = compound.getBoolean("ForcedToSit");
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundHandler.CHOCOBO_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundHandler.CHOCOBO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundHandler.CHOCOBO_DEATH.get();
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    public boolean isSaddled() {
        return this.dataManager.get(SADDLED).booleanValue();
    }

    public void setSaddled(boolean saddled) {
        this.dataManager.set(SADDLED, Boolean.valueOf(saddled));
    }

    public void setSitting(boolean sit) {
        this.dataManager.set(SITTING, Boolean.valueOf(sit));
    }

    public boolean isSitting() {
        return this.dataManager.get(SITTING).booleanValue();
    }

    public ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        if(this.world.isRemote) {
            boolean flag = this.isOwner(playerIn) || this.isTamed() || item == ItemHandler.GYSAHL.get() && !this.isTamed();
            return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
        }else {
            if(this.isTamed()) {
                if(!this.isSaddled() && itemstack.getItem() instanceof SaddleItem){
                    itemstack.shrink(1);
                    this.setSaddled(true);
                    world.playSound(null, getPosX(), getPosY(), getPosZ(), SoundEvents.ENTITY_HORSE_SADDLE, getSoundCategory(), 1, 1);
                    return ActionResultType.SUCCESS;
                }else if(this.isSaddled() && !playerIn.isSneaking()) {
                    playerIn.startRiding(this);
                }else {
                    if(this.isSitting()){
                        this.forcedSit = false;
                        this.setSitting(false);
                        return ActionResultType.SUCCESS;
                    }else{
                        this.forcedSit = true;
                        this.setSitting(true);
                        return ActionResultType.SUCCESS;
                    }
                }
            }else if(item == ItemHandler.GYSAHL.get()) {
                if (!playerIn.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, playerIn)) {
                    this.setTamedBy(playerIn);
                    this.navigator.clearPath();
                    this.world.setEntityState(this, (byte)7);
                } else {
                    this.world.setEntityState(this, (byte)6);
                }

                return ActionResultType.SUCCESS;
            }
        }
        return super.getEntityInteractionResult(playerIn, hand);
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }else if(isSitting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sitting", true));
            return PlayState.CONTINUE;
        }else if(!isOnGround()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
            return PlayState.CONTINUE;
        }else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public double getMountedYOffset() {
        return 1.0D;
    }

    protected void dropInventory() {
        super.dropInventory();
        if (this.isSaddled()) {
            if (!this.world.isRemote) {
                this.entityDropItem(Items.SADDLE);
            }
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }
}
