package net.pixeldream.valormobs.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;

public class ItemRegistry {
    public static final Item SKULLHAVEN_SPIRIT_SPAWN_EGG = registerItem("skullhaven_spirit_spawn_egg", new SpawnEggItem(EntityRegistry.SKULLHAVEN_SPIRIT_ENTITY, 0xE3E3E3, 0xB8B8B8, new FabricItemSettings().maxCount(64)));
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ValorMobs.MOD_ID, name), item);
    }

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering items for " + ValorMobs.MOD_NAME);
    }
}
