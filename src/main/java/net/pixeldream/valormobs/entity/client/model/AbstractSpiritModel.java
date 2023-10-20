package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.AbstractSpiritEntity;

public class AbstractSpiritModel extends GeoModel<AbstractSpiritEntity> {
    @Override
    public Identifier getModelResource(AbstractSpiritEntity object) {
        return new Identifier(ValorMobs.MOD_ID, "geo/entity/spirit.geo.json");
    }

    @Override
    public Identifier getTextureResource(AbstractSpiritEntity object) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/spirit.png");
    }

    @Override
    public Identifier getAnimationResource(AbstractSpiritEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "animations/entity/spirit.animation.json");
    }
}