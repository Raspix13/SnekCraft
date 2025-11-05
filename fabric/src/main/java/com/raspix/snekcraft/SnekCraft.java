package com.raspix.snekcraft;

import static com.raspix.snekcraft.items.ItemInit.SNAKE_BAG;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.blocks.entity.BlockEntityInit;
import com.raspix.snekcraft.entity.ModEntityTypeRenderers;
import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.event.ClientEvents;
import com.raspix.snekcraft.items.ItemInit;
import com.raspix.snekcraft.items.SnakeBagItem;
import com.raspix.snekcraft.loot.ModLootModifiers;
import com.raspix.snekcraft.packet.PacketHandler;
import com.raspix.snekcraft.sounds.SoundInit;
import com.raspix.snekcraft.util.KeyInit;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class SnekCraft implements ClientModInitializer, ModInitializer {
	public static final String MOD_ID = "snekcraft";
	public static final Logger logger = LogManager.getLogger();
	
	public void onInitialize() {
		SnekCraftConfig.loadConfig();

		BlockInit.init();
		ItemInit.init();
		SoundInit.init();
		ModEntityTypes.init();
		ModLootModifiers.init();
		BlockEntityInit.init();
		PacketHandler.init();
	}

	
	public void onInitializeClient() {
		KeyInit.init();
		ClientEvents.init();
		BlockInit.initClient();
		ModEntityTypeRenderers.init();

		ModelPredicateProviderRegistry.register(SNAKE_BAG, new Identifier("snake_count"), (stack, world, entity, seed) -> {
			return Math.min(1, SnakeBagItem.getSnakesInStack(stack));
		});
	}
}
