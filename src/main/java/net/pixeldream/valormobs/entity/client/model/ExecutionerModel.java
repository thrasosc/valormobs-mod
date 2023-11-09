package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tierhard.ExecutionerEntity;

public class ExecutionerModel extends GeoModel<ExecutionerEntity> {
    @Override
    public ResourceLocation getModelResource(ExecutionerEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/executioner.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ExecutionerEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/executioner.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ExecutionerEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/executioner.animation.json");
    }
}