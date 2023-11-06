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
import net.pixeldream.valormobs.entity.enemy.skullhaven.ExecutionerEntity;
import net.pixeldream.valormobs.entity.spirit.CrabEntity;
import net.pixeldream.valormobs.entity.spirit.SkullHavenSpiritEntity;

public class EntityRegistry {
    public static final EntityType<SkullHavenSpiritEntity> SKULLHAVEN_SPIRIT_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(ValorMobs.MOD_ID, "skullhaven_spirit"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SkullHavenSpiritEntity::new).dimensions(EntityDimensions.fixed(1.0f,0.8f)).build()
    );
    public static final EntityType<CrabEntity> CRAB_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(ValorMobs.MOD_ID, "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrabEntity::new).dimensions(EntityDimensions.fixed(1.0f,0.8f)).build()
    );
    public static final EntityType<ExecutionerEntity> EXECUTIONER_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(ValorMobs.MOD_ID, "executioner"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ExecutionerEntity::new).dimensions(EntityDimensions.fixed(2.0f,3.0f)).build()
    );

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering entities for " + ValorMobs.MOD_NAME);
        FabricDefaultAttributeRegistry.register(SKULLHAVEN_SPIRIT_ENTITY, SkullHavenSpiritEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(CRAB_ENTITY, CrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EXECUTIONER_ENTITY, ExecutionerEntity.setAttributes());
    }
}
