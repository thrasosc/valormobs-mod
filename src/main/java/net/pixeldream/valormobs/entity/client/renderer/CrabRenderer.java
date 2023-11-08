package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.CrabEntity;
import net.pixeldream.valormobs.entity.client.model.CrabModel;

public class CrabRenderer extends GeoEntityRenderer<CrabEntity> {
    public CrabRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CrabModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public Identifier getTextureLocation(CrabEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/crab.png");
    }
}