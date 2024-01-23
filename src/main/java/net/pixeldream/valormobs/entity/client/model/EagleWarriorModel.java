package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tiernormal.EagleWarriorEntity;

public class EagleWarriorModel extends GeoModel<EagleWarriorEntity> {
    @Override
    public ResourceLocation getModelResource(EagleWarriorEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/eagle_warrior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EagleWarriorEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/eagle_warrior.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EagleWarriorEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/eagle_warrior.animation.json");
    }
}