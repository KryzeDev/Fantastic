package com.kryze.fantastic.client.render.entity;

import com.kryze.fantastic.client.model.entity.ModelChocobo;
import com.kryze.fantastic.server.entity.chocobo.EntityChocobo;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderChocobo extends GeoEntityRenderer<EntityChocobo>{

    public RenderChocobo(EntityRendererManager renderManager) {
        super(renderManager, new ModelChocobo());
    }

    @Override
    public ResourceLocation getEntityTexture(EntityChocobo entity) {
        return this.getGeoModelProvider().getTextureLocation(entity);
    }

    @Override
    public RenderType getRenderType(EntityChocobo animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.getEntityTranslucent(getTextureLocation(animatable));
    }
}
