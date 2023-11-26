package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tiereasy.MummyEntity;

public class MummyModel extends GeoModel<MummyEntity> {
    @Override
    public ResourceLocation getModelResource(MummyEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/mummy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MummyEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/mummy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MummyEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/mummy.animation.json");
    }
}