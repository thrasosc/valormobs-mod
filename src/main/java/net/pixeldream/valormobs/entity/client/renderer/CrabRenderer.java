package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tierspirit.CrabEntity;
import net.pixeldream.valormobs.entity.client.model.CrabModel;

public class CrabRenderer extends GeoEntityRenderer<CrabEntity> {
    public CrabRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new CrabModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(CrabEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/crab.png");
    }
}