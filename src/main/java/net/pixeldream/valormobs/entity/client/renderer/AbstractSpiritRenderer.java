package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.SpiritEntity;
import net.pixeldream.valormobs.entity.client.model.AbstractSpiritModel;

public class AbstractSpiritRenderer extends GeoEntityRenderer<SpiritEntity> {
    public AbstractSpiritRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new AbstractSpiritModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(SpiritEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/spirit.png");
    }
}