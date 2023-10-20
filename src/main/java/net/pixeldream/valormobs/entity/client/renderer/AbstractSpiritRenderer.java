package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.AbstractSpiritEntity;
import net.pixeldream.valormobs.entity.client.model.AbstractSpiritModel;

public class AbstractSpiritRenderer extends GeoEntityRenderer<AbstractSpiritEntity> {
    public AbstractSpiritRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new AbstractSpiritModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public Identifier getTextureLocation(AbstractSpiritEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/spirit.png");
    }
}