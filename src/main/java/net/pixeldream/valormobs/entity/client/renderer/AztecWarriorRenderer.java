package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.AztecWarriorModel;
import net.pixeldream.valormobs.entity.client.model.MummyModel;
import net.pixeldream.valormobs.entity.tiereasy.MummyEntity;
import net.pixeldream.valormobs.entity.tiernormal.AztecWarriorEntity;

public class AztecWarriorRenderer extends GeoEntityRenderer<AztecWarriorEntity> {
    public AztecWarriorRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new AztecWarriorModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(AztecWarriorEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/aztec_warrior.png");
    }
}