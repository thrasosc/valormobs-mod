package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.tierhard.SetAnimalEntity;

public class SetAnimalModel extends GeoModel<SetAnimalEntity> {
    @Override
    public ResourceLocation getModelResource(SetAnimalEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "geo/entity/seth.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SetAnimalEntity object) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/seth.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SetAnimalEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "animations/entity/seth.animation.json");
    }
}