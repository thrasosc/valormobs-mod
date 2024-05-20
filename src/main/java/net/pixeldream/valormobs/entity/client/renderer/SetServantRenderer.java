package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.SetServantModel;
import net.pixeldream.valormobs.entity.tierhard.SetServantEntity;

public class SetServantRenderer extends GeoEntityRenderer<SetServantEntity> {
    public SetServantRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SetServantModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(SetServantEntity animatable) {
        return new ResourceLocation(ValorMobs.MOD_ID, "textures/entity/seth.png");
    }
}