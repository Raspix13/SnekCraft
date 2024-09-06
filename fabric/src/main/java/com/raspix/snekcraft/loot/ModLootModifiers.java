package com.raspix.snekcraft.loot;

import com.raspix.snekcraft.items.ItemInit;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootModifiers {
	private static final Identifier FROG_ID = Identifier.of("minecraft", "entities/frog");
	
	public static void init() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if(source.isBuiltin() && FROG_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(RandomChanceLootCondition.builder(1))
						.with(ItemEntry.builder(ItemInit.FROG_LEG))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 2)).build());
				tableBuilder.pool(poolBuilder);
			}
		});
	}
}
