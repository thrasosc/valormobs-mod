package net.pixeldream.valormobs.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.GollumEntity;

public class EntityRegistry {
    public static final EntityType<GollumEntity> GOLLUM_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(ValorMobs.MOD_ID, "gollum"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GollumEntity::new).dimensions(EntityDimensions.fixed(1.0f,0.8f)).build()
    );

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering entities for " + ValorMobs.MOD_NAME);
        FabricDefaultAttributeRegistry.register(GOLLUM_ENTITY, GollumEntity.setAttributes());
    }
}
