package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.SphinxModel;
import net.pixeldream.valormobs.entity.tierhard.SphinxEntity;

public class SphinxRenderer extends GeoEntityRenderer<SphinxEntity> {
    public SphinxRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SphinxModel());
        this.shadowRadius = 1.25f;
    }

    @Override
    public ResourceLocation getTextureLocation(SphinxEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/sphinx.png");
    }
}