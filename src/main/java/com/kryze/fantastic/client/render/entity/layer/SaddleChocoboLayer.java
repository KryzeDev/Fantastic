package com.kryze.fantastic.client.render.entity.layer;

import com.kryze.fantastic.Fantastic;
import com.kryze.fantastic.server.entity.chocobo.EntityChocobo;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class SaddleChocoboLayer extends GeoLayerRenderer<EntityChocobo> {

    private static final ResourceLocation SADDLE_CHOCBO = new ResourceLocation(Fantastic.MOD_ID, "textures/entity/chocobo_saddle.png");

    public SaddleChocoboLayer(IGeoRenderer<EntityChocobo> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityChocobo entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(entityLivingBaseIn.isSaddled()){
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutout(SADDLE_CHOCBO));
        }
    }
}