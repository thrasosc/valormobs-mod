package net.pixeldream.valormobs.entity.client.model;

import mod.azure.azurelib.constant.DataTickets;
import mod.azure.azurelib.core.animatable.model.CoreGeoBone;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.model.GeoModel;
import mod.azure.azurelib.model.data.EntityModelData;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
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

    @Override
    public void setCustomAnimations(ExecutionerEntity animatable, long instanceId, AnimationState<ExecutionerEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}