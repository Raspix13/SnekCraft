package com.raspix.snekcraft.blocks.entity;

import com.raspix.snekcraft.blocks.BlockInit;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BlockEntityInit {
	public static final BlockEntityType<SnakeEggBlockEntity> SNAKE_EGG = BlockEntityType.Builder.create(SnakeEggBlockEntity::new, BlockInit.SNAKE_EGG).build(null);
	
	public static void init() {
		Registry.register(Registries.BLOCK_ENTITY_TYPE, "snake_egg", SNAKE_EGG);
	}
}
