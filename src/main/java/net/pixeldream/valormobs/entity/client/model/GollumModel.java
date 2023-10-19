package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.GollumEntity;

public class GollumModel extends GeoModel<GollumEntity> {
    @Override
    public Identifier getModelResource(GollumEntity object) {
        return new Identifier(ValorMobs.MOD_ID, "geo/entity/gollum.geo.json");
    }

    @Override
    public Identifier getTextureResource(GollumEntity object) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/gollum.png");
    }

    @Override
    public Identifier getAnimationResource(GollumEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "animations/entity/gollum.animation.json");
    }
}