package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.MummyModel;
import net.pixeldream.valormobs.entity.tiereasy.MummyEntity;

public class MummyRenderer extends GeoEntityRenderer<MummyEntity> {
    public MummyRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new MummyModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(MummyEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/mummy.png");
    }
}