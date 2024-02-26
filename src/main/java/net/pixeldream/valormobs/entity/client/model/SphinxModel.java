package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tierhard.SphinxEntity;

public class SphinxModel extends GeoModel<SphinxEntity> {
    @Override
    public ResourceLocation getModelResource(SphinxEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/sphinx.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SphinxEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/sphinx.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SphinxEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/sphinx.animation.json");
    }
}