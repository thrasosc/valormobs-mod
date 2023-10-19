package net.pixeldream.valormobs.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;

public class ItemRegistry {
    public static final Item GOLLUM_SPAWN_EGG = registerItem("gollum_spawn_egg", new SpawnEggItem(EntityRegistry.GOLLUM_ENTITY, 0x399E6F, 0x1B6E47, new FabricItemSettings().maxCount(64)));
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ValorMobs.MOD_ID, name), item);
    }

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering items for " + ValorMobs.MOD_NAME);
    }
}
