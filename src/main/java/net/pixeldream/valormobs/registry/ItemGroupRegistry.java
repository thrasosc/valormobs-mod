package net.pixeldream.valormobs.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;

public class ItemGroupRegistry {
    public static final ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(ValorMobs.MOD_ID, ValorMobs.MOD_ID), FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + ValorMobs.MOD_ID)).icon(() -> new ItemStack(Items.BROWN_MUSHROOM)).entries((displayContext, entries) -> {
        entries.add(ItemRegistry.GOLLUM_SPAWN_EGG);
    }).build());

    public static void initialize() {
        ValorMobs.LOGGER.info("Registering item group for " + ValorMobs.MOD_NAME);
    }
}
