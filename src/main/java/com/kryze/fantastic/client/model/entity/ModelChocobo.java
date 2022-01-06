package com.kryze.fantastic.client.model.entity;

import com.kryze.fantastic.Fantastic;
import com.kryze.fantastic.server.entity.chocobo.EntityChocobo;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelChocobo extends AnimatedGeoModel<EntityChocobo> {

    @Override
    public ResourceLocation getModelLocation(EntityChocobo object) {
        return new ResourceLocation(Fantastic.MOD_ID, "geo/chocobo.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityChocobo object) {
        return new ResourceLocation(Fantastic.MOD_ID, "textures/entity/chocobo_yellow.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityChocobo animatable) {
        return new ResourceLocation(Fantastic.MOD_ID, "animations/chocobo.animation.json");
    }
}
