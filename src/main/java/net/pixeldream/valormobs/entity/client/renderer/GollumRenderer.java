package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.GollumEntity;
import net.pixeldream.valormobs.entity.client.model.GollumModel;

public class GollumRenderer extends GeoEntityRenderer<GollumEntity> {
    public GollumRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new GollumModel());
        this.shadowRadius = 0.85f;
    }

    @Override
    public Identifier getTextureLocation(GollumEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/gollum.png");
    }
}