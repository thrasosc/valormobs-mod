package net.pixeldream.valormobs.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.pixeldream.valormobs.ValorMobs;

public class ItemGroupRegistry {
    public static final CreativeModeTab ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(ValorMobs.MOD_ID, ValorMobs.MOD_ID), FabricItemGroup.builder().title(Component.translatable("itemgroup." + ValorMobs.MOD_ID)).icon(() -> new ItemStack(Items.BROWN_MUSHROOM)).displayItems((displayContext, entries) -> {
        entries.accept(ItemRegistry.SKULLHAVEN_SPIRIT_SPAWN_EGG);
        entries.accept(ItemRegistry.EXECUTIONER_SPAWN_EGG);
        entries.accept(ItemRegistry.MUMMY_SPAWN_EGG);
        entries.accept(ItemRegistry.AZTEC_WARRIOR_SPAWN_EGG);
        entries.accept(ItemRegistry.EAGLE_WARRIOR_SPAWN_EGG);
        entries.accept(ItemRegistry.JAGUAR_WARRIOR_SPAWN_EGG);
        entries.accept(ItemRegistry.SET_ANIMAL_SPAWN_EGG);
        entries.accept(ItemRegistry.SEA_SERPENT_SPAWN_EGG);
    }).build());

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering item group for " + ValorMobs.MOD_NAME);
    }
}
