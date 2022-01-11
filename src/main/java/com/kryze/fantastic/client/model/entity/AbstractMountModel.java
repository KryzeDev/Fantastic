package com.kryze.fantastic.client.model.entity;

import com.kryze.fantastic.server.entity.chocobo.EntityChocobo;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class AbstractMountModel<T extends EntityChocobo> extends AnimatedGeoModel<T> {

    @Override
    public ResourceLocation getTextureLocation(T object) {
        return object.isSaddled() ? getSaddledTexture(object) : getRegularTexture(object);
    }

    public abstract @NotNull
    ResourceLocation getSaddledTexture(T t);

    public abstract @NotNull
    ResourceLocation getRegularTexture(T t);
}
