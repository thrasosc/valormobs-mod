package net.pixeldream.valormobs.entity.client.renderer.skullhaven;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.client.model.skullhaven.ExecutionerModel;
import net.pixeldream.valormobs.entity.enemy.skullhaven.ExecutionerEntity;

public class ExecutionerRenderer extends GeoEntityRenderer<ExecutionerEntity> {
    public ExecutionerRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ExecutionerModel());
        this.shadowRadius = 1.0f;
    }

    @Override
    public Identifier getTextureLocation(ExecutionerEntity animatable) {
        return new Identifier(ValorMobs.MOD_ID, "textures/entity/skullhaven/executioner.png");
    }
}