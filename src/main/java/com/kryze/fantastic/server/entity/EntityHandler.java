package com.kryze.fantastic.server.entity;

import com.kryze.fantastic.Fantastic;
import com.kryze.fantastic.server.entity.chocobo.EntityChocobo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;


@Mod.EventBusSubscriber(modid = Fantastic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityHandler {

    public static EntityType<EntityChocobo> CHOCOBO;

    public static void register() {
        CHOCOBO = register("chocobo", EntityType.Builder.create(EntityChocobo::new, EntityClassification.CREATURE).size(1.4f, 3.4f));

        ForgeRegistries.ENTITIES.registerAll(
                CHOCOBO
        );
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceLocation regName = new ResourceLocation(Fantastic.MOD_ID,name );
        return (EntityType<T>) builder.build(name).setRegistryName(regName);
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void registerEntity(RegistryEvent.Register<EntityType<?>> event) {
        GlobalEntityTypeAttributes.put(CHOCOBO, EntityChocobo.createAttributes().create());
    }
}

