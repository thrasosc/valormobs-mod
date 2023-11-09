package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.ExecutionerEntity;
import net.pixeldream.valormobs.entity.client.model.ExecutionerModel;

public class ExecutionerRenderer extends GeoEntityRenderer<ExecutionerEntity> {
    public ExecutionerRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ExecutionerModel());
        this.shadowRadius = 1.0f;
    }

    @Override
    public ResourceLocation getTextureLocation(ExecutionerEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/executioner.png");
    }
}