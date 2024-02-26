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
import net.pixeldream.valormobs.entity.tiereasy.MummyEntity;
import net.pixeldream.valormobs.entity.tierhard.SeaSerpentEntity;
import net.pixeldream.valormobs.entity.tierhard.SetAnimalEntity;
import net.pixeldream.valormobs.entity.tiernormal.AztecWarriorEntity;
import net.pixeldream.valormobs.entity.tiernormal.EagleWarriorEntity;
import net.pixeldream.valormobs.entity.tiernormal.JaguarWarriorEntity;
import net.pixeldream.valormobs.entity.tierspirit.CrabEntity;
import net.pixeldream.valormobs.entity.tierhard.ExecutionerEntity;
import net.pixeldream.valormobs.entity.tierspirit.SkullHavenSpiritEntity;

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
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, ExecutionerEntity::new).dimensions(EntityDimensions.fixed(1.5f,3.1f)).build()
    );
    public static final EntityType<MummyEntity> MUMMY_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "mummy"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, MummyEntity::new).dimensions(EntityDimensions.fixed(1.0f,2.0f)).build()
    );
    public static final EntityType<AztecWarriorEntity> AZTEC_WARRIOR_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "aztec_warrior"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, AztecWarriorEntity::new).dimensions(EntityDimensions.fixed(1.0f,2.0f)).build()
    );
    public static final EntityType<EagleWarriorEntity> EAGLE_WARRIOR_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "eagle_warrior"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, EagleWarriorEntity::new).dimensions(EntityDimensions.fixed(1.0f,2.0f)).build()
    );
    public static final EntityType<JaguarWarriorEntity> JAGUAR_WARRIOR_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "jaguar_warrior"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, JaguarWarriorEntity::new).dimensions(EntityDimensions.fixed(1.0f,2.0f)).build()
    );
    public static final EntityType<SetAnimalEntity> SET_ANIMAL_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "seth"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, SetAnimalEntity::new).dimensions(EntityDimensions.fixed(1.5f,3.1f)).build()
    );
    public static final EntityType<SeaSerpentEntity> SEA_SERPENT_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(ValorMobs.MOD_ID, "sea_serpent"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, SeaSerpentEntity::new).dimensions(EntityDimensions.fixed(5.0f,1.0f)).build()
    );

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering entities for " + ValorMobs.MOD_NAME);
        FabricDefaultAttributeRegistry.register(SKULLHAVEN_SPIRIT_ENTITY, SkullHavenSpiritEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(CRAB_ENTITY, CrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EXECUTIONER_ENTITY, ExecutionerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(MUMMY_ENTITY, MummyEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(AZTEC_WARRIOR_ENTITY, AztecWarriorEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EAGLE_WARRIOR_ENTITY, EagleWarriorEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(JAGUAR_WARRIOR_ENTITY, JaguarWarriorEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(SET_ANIMAL_ENTITY, SetAnimalEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(SEA_SERPENT_ENTITY, SeaSerpentEntity.setAttributes());
    }
}
