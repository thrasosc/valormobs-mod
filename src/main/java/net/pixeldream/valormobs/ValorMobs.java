package net.pixeldream.valormobs;

import mod.azure.azurelib.AzureLib;
import net.fabricmc.api.ModInitializer;

import net.pixeldream.valormobs.registry.EntityRegistry;
import net.pixeldream.valormobs.registry.ItemGroupRegistry;
import net.pixeldream.valormobs.registry.ItemRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValorMobs implements ModInitializer {
	public static final String MOD_ID = "valormobs";
	public static final String MOD_NAME = "Valor Mobs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AzureLib.initialize();
		EntityRegistry.initialize();
		ItemRegistry.initialize();
		ItemGroupRegistry.initialize();
	}
}