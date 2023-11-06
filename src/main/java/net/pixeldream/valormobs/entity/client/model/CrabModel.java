package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.spirit.CrabEntity;

public class CrabModel extends GeoModel<CrabEntity> {
    @Override
    public Identifier getModelResource(CrabEntity object) {
        return new Identifier(ValorMobs.MOD_ID, "geo/entity/crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrabEntity object) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/crab.png");
    }

    @Override
    public Identifier getAnimationResource(CrabEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "animations/entity/crab.animation.json");
    }
}