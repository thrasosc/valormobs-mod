package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tierspirit.CrabEntity;

public class CrabModel extends GeoModel<CrabEntity> {
    @Override
    public ResourceLocation getModelResource(CrabEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CrabEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/crab.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CrabEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/crab.animation.json");
    }
}