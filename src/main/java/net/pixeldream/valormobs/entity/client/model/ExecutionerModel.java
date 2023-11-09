package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.ExecutionerEntity;

public class ExecutionerModel extends GeoModel<ExecutionerEntity> {
    @Override
    public Identifier getModelResource(ExecutionerEntity object) {
        return new Identifier(ValorMobs.MOD_ID, "geo/entity/executioner.geo.json");
    }

    @Override
    public Identifier getTextureResource(ExecutionerEntity object) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/executioner.png");
    }

    @Override
    public Identifier getAnimationResource(ExecutionerEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "animations/entity/executioner.animation.json");
    }
}