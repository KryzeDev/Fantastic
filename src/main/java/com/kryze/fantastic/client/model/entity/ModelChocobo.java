package com.kryze.fantastic.client.model.entity;

import com.kryze.fantastic.server.entity.chocobo.EntityChocobo;
import net.minecraft.util.ResourceLocation;

import static com.kryze.fantastic.Fantastic.createResourceLocation;

public class ModelChocobo extends AbstractMountModel<EntityChocobo> {

    private static final ResourceLocation MODEL = createResourceLocation("geo/chocobo.geo.json");
    private static final ResourceLocation SADDLED_TEXTURE = createResourceLocation("textures/entity/chocobo_saddle.png");
    private static final ResourceLocation TEXTURE = createResourceLocation("textures/entity/chocobo_yellow.png");
    private static final ResourceLocation ANIMATION = createResourceLocation("animations/chocobo.animation.json");

    @Override
    public ResourceLocation getSaddledTexture(EntityChocobo chocobo) {
        return SADDLED_TEXTURE;
    }

    @Override
    public ResourceLocation getRegularTexture(EntityChocobo chocobo) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getModelLocation(EntityChocobo object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityChocobo animatable) {
        return ANIMATION;
    }
}
