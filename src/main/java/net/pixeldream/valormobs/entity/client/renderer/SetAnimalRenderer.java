package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.SetAnimalModel;
import net.pixeldream.valormobs.entity.tierhard.SetAnimalEntity;

public class SetAnimalRenderer extends GeoEntityRenderer<SetAnimalEntity> {
    public SetAnimalRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SetAnimalModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(SetAnimalEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/seth.png");
    }
}