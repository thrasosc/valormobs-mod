package net.pixeldream.valormobs.entity.client.renderer;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.ExecutionerEntity;
import net.pixeldream.valormobs.entity.client.model.ExecutionerModel;

public class ExecutionerRenderer extends GeoEntityRenderer<ExecutionerEntity> {
    public ExecutionerRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ExecutionerModel());
        this.shadowRadius = 1.0f;
    }

    @Override
    public Identifier getTextureLocation(ExecutionerEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/executioner.png");
    }
}