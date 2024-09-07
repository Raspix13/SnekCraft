package com.raspix.snekcraft.blocks;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.entity.eggs.BallPythonEggBlock;
import com.raspix.snekcraft.blocks.entity.eggs.HognoseEggBlock;
import com.raspix.snekcraft.blocks.terrariums.AquariumBlock;
import com.raspix.snekcraft.blocks.terrariums.CageBlock;
import com.raspix.snekcraft.blocks.terrariums.LavaquariumBlock;
import com.raspix.snekcraft.blocks.terrariums.TerrariumBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockInit {
	public static final Block TERRARIUM = new TerrariumBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().dynamicBounds().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never));
	public static final Block AQUARIUM = new AquariumBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().dynamicBounds().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never));
	public static final Block WIRE_CAGE = new CageBlock(AbstractBlock.Settings.create().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).nonOpaque().dynamicBounds().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never));
	public static final Block LAVAQUARIUM = new LavaquariumBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().dynamicBounds().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).luminance(blockState -> 15));
	
	public static final Block DIRT_HIDE = new CaveHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).strength(0.5f).sounds(BlockSoundGroup.GRAVEL).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block STONE_HIDE = new CaveHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).strength(1.5f, 6.0f).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block STONE_BRICK_HIDE = new CaveHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).strength(1.5f, 6.0f).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block MOSSY_BRICK_HIDE = new CaveHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).strength(1.5f, 6.0f).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block SANDSTONE_HIDE = new CaveHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.5f).sounds(BlockSoundGroup.SAND).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block SPRUCE_HIDE = new TunnelHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block ACACIA_HIDE = new TunnelHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block BIRCH_HIDE = new TunnelHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block DARK_OAK_HIDE = new TunnelHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block JUNGLE_HIDE = new TunnelHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block MANGROVE_HIDE = new TunnelHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.PUSH_ONLY));
	public static final Block OAK_HIDE = new TunnelHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.PUSH_ONLY));
	
	public static final Block DIRT_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).strength(0.5f).sounds(BlockSoundGroup.GRAVEL));
	public static final Block STONE_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).strength(1.5f, 6.0f));
	public static final Block STONE_BRICK_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).strength(1.5f, 6.0f));
	public static final Block MOSSY_BRICK_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).strength(1.5f, 6.0f));
	public static final Block SANDSTONE_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.5f).sounds(BlockSoundGroup.SAND));
	public static final Block SPRUCE_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD));
	public static final Block ACACIA_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD));
	public static final Block BIRCH_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD));
	public static final Block DARK_OAK_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNGLE_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD));
	public static final Block MANGROVE_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD));
	public static final Block OAK_MEDIUM_HIDE = new MediumHideBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f).sounds(BlockSoundGroup.WOOD));
	
	public static final Block HEAT_LAMP = new HeatLampBlock(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).luminance(blockState -> 10));
	public static final Block LAMP_POST = new LampPostBlock(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	
	public static final Block SNAKE_EGG = new HognoseEggBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.5f).sounds(BlockSoundGroup.METAL).ticksRandomly().nonOpaque());
	public static final Block BALL_PYTHON_EGG = new BallPythonEggBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.5f).sounds(BlockSoundGroup.METAL).ticksRandomly().nonOpaque());
	
	public static void init() {
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "terrarium"), TERRARIUM);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "aquarium"), AQUARIUM);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "wire_cage"), WIRE_CAGE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "lavaquarium"), LAVAQUARIUM);
		
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "dirt_hide"), DIRT_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "stone_hide"), STONE_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "stone_brick_hide"), STONE_BRICK_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "mossy_brick_hide"), MOSSY_BRICK_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "sandstone_hide"), SANDSTONE_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "spruce_hide"), SPRUCE_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "acacia_hide"), ACACIA_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "birch_hide"), BIRCH_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "dark_oak_hide"), DARK_OAK_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "jungle_hide"), JUNGLE_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "mangrove_hide"), MANGROVE_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "oak_hide"), OAK_HIDE);
		
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "dirt_medium_hide"), DIRT_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "stone_medium_hide"), STONE_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "stone_brick_medium_hide"), STONE_BRICK_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "mossy_brick_medium_hide"), MOSSY_BRICK_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "sandstone_medium_hide"), SANDSTONE_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "spruce_medium_hide"), SPRUCE_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "acacia_medium_hide"), ACACIA_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "birch_medium_hide"), BIRCH_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "dark_oak_medium_hide"), DARK_OAK_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "jungle_medium_hide"), JUNGLE_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "mangrove_medium_hide"), MANGROVE_MEDIUM_HIDE);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "oak_medium_hide"), OAK_MEDIUM_HIDE);
		
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "heat_lamp"), HEAT_LAMP);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "lamp_post"), LAMP_POST);
		
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "hoggie_egg"), SNAKE_EGG);
		Registry.register(Registries.BLOCK, Identifier.of(SnekCraft.MOD_ID, "ball_python_egg"), BALL_PYTHON_EGG);
	}
}
