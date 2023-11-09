package net.pixeldream.valormobs.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.pixeldream.valormobs.ValorMobs;

public class ItemRegistry {
    public static final Item SKULLHAVEN_SPIRIT_SPAWN_EGG = registerItem("skullhaven_spirit_spawn_egg", new SpawnEggItem(EntityRegistry.SKULLHAVEN_SPIRIT_ENTITY, 0xE3E3E3, 0xB8B8B8, new FabricItemSettings().maxCount(64)));
    public static final Item EXECUTIONER_SPAWN_EGG = registerItem("executioner_spawn_egg", new SpawnEggItem(EntityRegistry.EXECUTIONER_ENTITY, 0xDCDCDC, 0x424242, new FabricItemSettings().maxCount(64)));
    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(ValorMobs.MOD_ID, name), item);
    }

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering items for " + ValorMobs.MOD_NAME);
    }
}
