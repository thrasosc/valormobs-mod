package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tiernormal.JaguarWarriorEntity;

public class JaguarWarriorModel extends GeoModel<JaguarWarriorEntity> {
    @Override
    public ResourceLocation getModelResource(JaguarWarriorEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/jaguar_warrior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JaguarWarriorEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/jaguar_warrior.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JaguarWarriorEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/jaguar_warrior.animation.json");
    }
}
