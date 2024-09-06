package com.raspix.snekcraft.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;

public class TerrariumEntity extends BlockEntity {
	public static final BooleanProperty NORTH_EAST_CORNER = BooleanProperty.of("ne_corner");
	public static final BooleanProperty NORTH_WEST_CORNER = BooleanProperty.of("nw_corner");
	public static final BooleanProperty SOUTH_EAST_CORNER = BooleanProperty.of("se_corner");
	public static final BooleanProperty SOUTH_WEST_CORNER = BooleanProperty.of("sw_corner");
	
	public TerrariumEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}
}
