package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.JaguarWarriorModel;
import net.pixeldream.valormobs.entity.tiernormal.JaguarWarriorEntity;

public class JaguarWarriorRenderer extends GeoEntityRenderer<JaguarWarriorEntity> {
    public JaguarWarriorRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new JaguarWarriorModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(JaguarWarriorEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/jaguar_warrior.png");
    }
}
