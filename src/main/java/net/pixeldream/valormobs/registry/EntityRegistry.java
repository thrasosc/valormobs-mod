package net.pixeldream.valormobs.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.CrabEntity;
import net.pixeldream.valormobs.entity.ExecutionerEntity;
import net.pixeldream.valormobs.entity.SkullHavenSpiritEntity;

public class EntityRegistry {
    public static final EntityType<SkullHavenSpiritEntity> SKULLHAVEN_SPIRIT_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "skullhaven_spirit"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, SkullHavenSpiritEntity::new).dimensions(EntityDimensions.fixed(1.0f,0.8f)).build()
    );
    public static final EntityType<CrabEntity> CRAB_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "crab"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, CrabEntity::new).dimensions(EntityDimensions.fixed(1.0f,0.8f)).build()
    );
    public static final EntityType<ExecutionerEntity> EXECUTIONER_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "executioner"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, ExecutionerEntity::new).dimensions(EntityDimensions.fixed(2.0f,3.0f)).build()
    );

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering entities for " + ValorMobs.MOD_NAME);
        FabricDefaultAttributeRegistry.register(SKULLHAVEN_SPIRIT_ENTITY, SkullHavenSpiritEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(CRAB_ENTITY, CrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EXECUTIONER_ENTITY, ExecutionerEntity.setAttributes());
    }
}
