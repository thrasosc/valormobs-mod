package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.SpiritEntity;


public class AbstractSpiritModel extends GeoModel<SpiritEntity> {
    @Override
    public ResourceLocation getModelResource(SpiritEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/spirit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiritEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/spirit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/spirit.animation.json");
    }
}