package com.raspix.snekcraft.blocks;

import org.jetbrains.annotations.NotNull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class LampPostBlock extends Block {
	private static final VoxelShape WIRE_AABB = Block.createCuboidShape(7.0f, 0.0f, 7.0f, 9.0f, 16.0f, 9.0f);
	
	public LampPostBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public @NotNull VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		return VoxelShapes.combineAndSimplify(Block.createCuboidShape(0, 0, 0, 0, 0, 0), WIRE_AABB, BooleanBiFunction.OR);
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		return getCollisionShape(blockState, blockView, blockPos, shapeContext);
	}
	
	@Override
	public boolean isShapeFullCube(BlockState blockState, BlockView blockView, BlockPos blockPos) {
		return false;
	}
	
	@Override
	public @NotNull BlockRenderType getRenderType(BlockState blockState) {
		return BlockRenderType.MODEL;
	}
}
