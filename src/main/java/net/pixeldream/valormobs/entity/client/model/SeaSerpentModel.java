package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tierhard.SeaSerpentEntity;

public class SeaSerpentModel extends GeoModel<SeaSerpentEntity> {
    @Override
    public ResourceLocation getModelResource(SeaSerpentEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/sea_serpent.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeaSerpentEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/sea_serpent.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeaSerpentEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/sea_serpent.animation.json");
    }
}