package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.AztecWarriorModel;
import net.pixeldream.valormobs.entity.client.model.EagleWarriorModel;
import net.pixeldream.valormobs.entity.tiernormal.AztecWarriorEntity;
import net.pixeldream.valormobs.entity.tiernormal.EagleWarriorEntity;

public class EagleWarriorRenderer extends GeoEntityRenderer<EagleWarriorEntity> {
    public EagleWarriorRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new EagleWarriorModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(EagleWarriorEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/eagle_warrior.png");
    }
}