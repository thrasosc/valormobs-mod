package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tiereasy.MummyEntity;
import net.pixeldream.valormobs.entity.tiernormal.AztecWarriorEntity;

public class AztecWarriorModel extends GeoModel<AztecWarriorEntity> {
    @Override
    public ResourceLocation getModelResource(AztecWarriorEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/aztec_warrior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AztecWarriorEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/aztec_warrior.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AztecWarriorEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/aztec_warrior.animation.json");
    }
}