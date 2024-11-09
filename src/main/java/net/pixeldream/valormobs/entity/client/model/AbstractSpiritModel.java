package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.SpiritualEntity;


public class AbstractSpiritModel extends GeoModel<SpiritualEntity> {
    @Override
    public ResourceLocation getModelResource(SpiritualEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/spirit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiritualEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/spirit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritualEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/spirit.animation.json");
    }
}