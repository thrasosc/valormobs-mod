package net.pixeldream.valormobs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.pixeldream.valormobs.entity.client.renderer.GollumRenderer;
import net.pixeldream.valormobs.registry.EntityRegistry;

public class ValorMobsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.GOLLUM_ENTITY, GollumRenderer::new);
    }
}
