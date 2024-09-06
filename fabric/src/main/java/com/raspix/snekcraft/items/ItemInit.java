package com.raspix.snekcraft.items;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.entity.ModEntityTypes;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemInit {
	public static final Item SNAKE_SKIN = new Item(new Item.Settings());
	public static final Item SNAKE_TOOTH = new Item(new Item.Settings());
	public static final Item FROG_LEG = new Item(new Item.Settings().food(FoodComponents.PORKCHOP));
	public static final Item COOKED_FROG_LEG = new Item(new Item.Settings().food(FoodComponents.COOKED_PORKCHOP));
	public static final Item SNAKE_BAG = new SnakeBagItem(new Item.Settings().maxCount(1));
	public static final Item HOGNOSE_SPAWN_EGG = new SpawnEggItem(ModEntityTypes.HOGNOSE, 0xd6bf96, 0x40351c, new Item.Settings());
	public static final Item BALL_PYTHON_SPAWN_EGG = new SpawnEggItem(ModEntityTypes.BALLPYTHON, 0xd6bf96, 0x40351c, new Item.Settings());
	public static final Item HOGGIE_EGG = new SnekEggItem(BlockInit.SNAKE_EGG, new Item.Settings());
	public static final Item BALL_PYTHON_EGG = new SnekEggItem(BlockInit.BALL_PYTHON_EGG, new Item.Settings());
	
	public static final Item TERRARIUM_KEY = new TerrariumKey(new Item.Settings());
	public static final Item TERRARIUM_ITEM = new BlockItem(BlockInit.TERRARIUM, new Item.Settings());
	public static final Item AQUARIUM_ITEM = new BlockItem(BlockInit.AQUARIUM, new Item.Settings().recipeRemainder(TERRARIUM_ITEM));
	public static final Item WIRE_CAGE_ITEM = new BlockItem(BlockInit.WIRE_CAGE, new Item.Settings());
	public static final Item LAVAQUARIUM_ITEM = new BlockItem(BlockInit.LAVAQUARIUM, new Item.Settings().recipeRemainder(TERRARIUM_ITEM));
	
	public static final Item DIRT_HIDE = new HideItem(BlockInit.DIRT_HIDE, new Item.Settings());
	public static final Item STONE_HIDE = new HideItem(BlockInit.STONE_HIDE, new Item.Settings());
	public static final Item STONE_BRICK_HIDE = new HideItem(BlockInit.STONE_BRICK_HIDE, new Item.Settings());
	public static final Item MOSSY_BRICK_HIDE = new HideItem(BlockInit.MOSSY_BRICK_HIDE, new Item.Settings());
	public static final Item SANDSTONE_HIDE = new HideItem(BlockInit.SANDSTONE_HIDE, new Item.Settings());
	
	public static final Item SPRUCE_HIDE = new HideItem(BlockInit.SPRUCE_HIDE, new Item.Settings());
	public static final Item ACACIA_HIDE = new HideItem(BlockInit.ACACIA_HIDE, new Item.Settings());
	public static final Item BIRCH_HIDE = new HideItem(BlockInit.BIRCH_HIDE, new Item.Settings());
	public static final Item DARK_OAK_HIDE = new HideItem(BlockInit.DARK_OAK_HIDE, new Item.Settings());
	public static final Item JUNGLE_HIDE = new HideItem(BlockInit.JUNGLE_HIDE, new Item.Settings());
	public static final Item MANGROVE_HIDE = new HideItem(BlockInit.MANGROVE_HIDE, new Item.Settings());
	public static final Item OAK_HIDE = new HideItem(BlockInit.OAK_HIDE, new Item.Settings());
	
	public static final Item DIRT_MEDIUM_HIDE = new HideItem(BlockInit.DIRT_MEDIUM_HIDE, new Item.Settings());
	public static final Item STONE_MEDIUM_HIDE = new HideItem(BlockInit.STONE_MEDIUM_HIDE, new Item.Settings());
	public static final Item STONE_BRICK_MEDIUM_HIDE = new HideItem(BlockInit.STONE_BRICK_MEDIUM_HIDE, new Item.Settings());
	public static final Item MOSSY_BRICK_MEDIUM_HIDE = new HideItem(BlockInit.MOSSY_BRICK_MEDIUM_HIDE, new Item.Settings());
	public static final Item SANDSTONE_MEDIUM_HIDE = new HideItem(BlockInit.SANDSTONE_MEDIUM_HIDE, new Item.Settings());
	
	public static final Item SPRUCE_MEDIUM_HIDE = new HideItem(BlockInit.SPRUCE_MEDIUM_HIDE, new Item.Settings());
	public static final Item ACACIA_MEDIUM_HIDE = new HideItem(BlockInit.ACACIA_MEDIUM_HIDE, new Item.Settings());
	public static final Item BIRCH_MEDIUM_HIDE = new HideItem(BlockInit.BIRCH_MEDIUM_HIDE, new Item.Settings());
	public static final Item DARK_OAK_MEDIUM_HIDE = new HideItem(BlockInit.DARK_OAK_MEDIUM_HIDE, new Item.Settings());
	public static final Item JUNGLE_MEDIUM_HIDE = new HideItem(BlockInit.JUNGLE_MEDIUM_HIDE, new Item.Settings());
	public static final Item MANGROVE_MEDIUM_HIDE = new HideItem(BlockInit.MANGROVE_MEDIUM_HIDE, new Item.Settings());
	public static final Item OAK_MEDIUM_HIDE = new HideItem(BlockInit.OAK_MEDIUM_HIDE, new Item.Settings());
	
	public static final Item HEAT_LAMP = new BlockItem(BlockInit.HEAT_LAMP, new Item.Settings());
	public static final Item LAMP_POST = new BlockItem(BlockInit.LAMP_POST, new Item.Settings());
	
	public static final int HIDE_BURN_TIME = 15;
	
	public static final ItemGroup SNEKCRAFT_TAB = FabricItemGroup.builder()
			//.withTabsBefore(ItemGroups.COMBAT) // Doesn't exist
			.displayName(Text.translatable("creativetab.snake_tab"))
			.icon(() -> SNAKE_SKIN.getDefaultStack())
			.entries((context, entries) -> {
				entries.add(SNAKE_SKIN);
				entries.add(SNAKE_TOOTH);
				entries.add(FROG_LEG);
				entries.add(COOKED_FROG_LEG);
				entries.add(SNAKE_BAG);
				entries.add(HOGNOSE_SPAWN_EGG);
				entries.add(BALL_PYTHON_SPAWN_EGG);
				
				entries.add(TERRARIUM_KEY);
				entries.add(TERRARIUM_ITEM);
				entries.add(AQUARIUM_ITEM);
				entries.add(WIRE_CAGE_ITEM);
				entries.add(LAVAQUARIUM_ITEM);
				
				entries.add(HOGGIE_EGG);
				entries.add(BALL_PYTHON_EGG);
				entries.add(HEAT_LAMP);
				entries.add(LAMP_POST);
				
				entries.add(DIRT_HIDE);
				entries.add(DIRT_MEDIUM_HIDE);
				entries.add(STONE_HIDE);
				entries.add(STONE_MEDIUM_HIDE);
				entries.add(STONE_BRICK_HIDE);
				entries.add(STONE_BRICK_MEDIUM_HIDE);
				entries.add(MOSSY_BRICK_HIDE);
				entries.add(MOSSY_BRICK_MEDIUM_HIDE);
				entries.add(SANDSTONE_HIDE);
				entries.add(SANDSTONE_MEDIUM_HIDE);
				entries.add(SPRUCE_HIDE);
				entries.add(SPRUCE_MEDIUM_HIDE);
				entries.add(ACACIA_HIDE);
				entries.add(ACACIA_MEDIUM_HIDE);
				entries.add(BIRCH_HIDE);
				entries.add(BIRCH_MEDIUM_HIDE);
				entries.add(DARK_OAK_HIDE);
				entries.add(DARK_OAK_MEDIUM_HIDE);
				entries.add(JUNGLE_HIDE);
				entries.add(JUNGLE_MEDIUM_HIDE);
				entries.add(MANGROVE_HIDE);
				entries.add(MANGROVE_MEDIUM_HIDE);
				entries.add(OAK_HIDE);
				entries.add(OAK_MEDIUM_HIDE);
			})
			.build();
	
	public static void init() {
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "snake_skin"), SNAKE_SKIN);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "snake_tooth"), SNAKE_TOOTH);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "frog_leg"), FROG_LEG);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "cooked_frog_leg"), COOKED_FROG_LEG);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "snake_bag"), SNAKE_BAG);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "hognose_spawn_egg"), HOGNOSE_SPAWN_EGG);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "ball_python_spawn_egg"), BALL_PYTHON_SPAWN_EGG);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "hoggie_egg"), HOGGIE_EGG);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "ball_python_egg"), BALL_PYTHON_EGG);
		
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "terrarium_key"), TERRARIUM_KEY);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "terrarium"), TERRARIUM_ITEM);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "aquarium"), AQUARIUM_ITEM);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "wire_cage"), WIRE_CAGE_ITEM);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "lavaquarium"), LAVAQUARIUM_ITEM);
		
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "dirt_hide"), DIRT_HIDE);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "stone_hide"), STONE_HIDE);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "stone_brick_hide"), STONE_BRICK_HIDE);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "mossy_brick_hide"), MOSSY_BRICK_HIDE);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "sandstone_hide"), SANDSTONE_HIDE);
		
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "spruce_hide"), SPRUCE_HIDE);
		FuelRegistry.INSTANCE.add(SPRUCE_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "acacia_hide"), ACACIA_HIDE);
		FuelRegistry.INSTANCE.add(ACACIA_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "birch_hide"), BIRCH_HIDE);
		FuelRegistry.INSTANCE.add(BIRCH_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "dark_oak_hide"), DARK_OAK_HIDE);
		FuelRegistry.INSTANCE.add(DARK_OAK_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "jungle_hide"), JUNGLE_HIDE);
		FuelRegistry.INSTANCE.add(JUNGLE_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "mangrove_hide"), MANGROVE_HIDE);
		FuelRegistry.INSTANCE.add(MANGROVE_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "oak_hide"), OAK_HIDE);
		FuelRegistry.INSTANCE.add(OAK_HIDE, HIDE_BURN_TIME);
		
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "dirt_medium_hide"), DIRT_MEDIUM_HIDE);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "stone_medium_hide"), STONE_MEDIUM_HIDE);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "stone_brick_medium_hide"), STONE_BRICK_MEDIUM_HIDE);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "mossy_brick_medium_hide"), MOSSY_BRICK_MEDIUM_HIDE);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "sandstone_medium_hide"), SANDSTONE_MEDIUM_HIDE);
		
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "spruce_medium_hide"), SPRUCE_MEDIUM_HIDE);
		FuelRegistry.INSTANCE.add(SPRUCE_MEDIUM_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "acacia_medium_hide"), ACACIA_MEDIUM_HIDE);
		FuelRegistry.INSTANCE.add(ACACIA_MEDIUM_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "birch_medium_hide"), BIRCH_MEDIUM_HIDE);
		FuelRegistry.INSTANCE.add(BIRCH_MEDIUM_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "dark_oak_medium_hide"), DARK_OAK_MEDIUM_HIDE);
		FuelRegistry.INSTANCE.add(DARK_OAK_MEDIUM_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "jungle_medium_hide"), JUNGLE_MEDIUM_HIDE);
		FuelRegistry.INSTANCE.add(JUNGLE_MEDIUM_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "mangrove_medium_hide"), MANGROVE_MEDIUM_HIDE);
		FuelRegistry.INSTANCE.add(MANGROVE_MEDIUM_HIDE, HIDE_BURN_TIME);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "oak_medium_hide"), OAK_MEDIUM_HIDE);
		FuelRegistry.INSTANCE.add(OAK_MEDIUM_HIDE, HIDE_BURN_TIME);
		
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "heat_lamp"), HEAT_LAMP);
		Registry.register(Registries.ITEM, Identifier.of(SnekCraft.MOD_ID, "lamp_post"), LAMP_POST);
		
		Registry.register(Registries.ITEM_GROUP, Identifier.of(SnekCraft.MOD_ID, "snekcraft_tab"), SNEKCRAFT_TAB);
	}
}
