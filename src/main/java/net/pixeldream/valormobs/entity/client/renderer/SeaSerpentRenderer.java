package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.SeaSerpentModel;
import net.pixeldream.valormobs.entity.tierhard.SeaSerpentEntity;

public class SeaSerpentRenderer extends GeoEntityRenderer<SeaSerpentEntity> {
    public SeaSerpentRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SeaSerpentModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(SeaSerpentEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/sea_serpent.png");
    }
}