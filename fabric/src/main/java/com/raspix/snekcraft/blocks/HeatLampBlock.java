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

public class HeatLampBlock extends Block {
	private static final VoxelShape WIRE_AABB = Block.createCuboidShape(7.0f, 4.0f, 7.0f, 9.0f, 16.0f, 9.0f);
	private static final VoxelShape LAMP_AABB = Block.createCuboidShape(3.0f, 1.0f, 3.0f, 13.0f, 4.0f, 13.0f);
	
	public HeatLampBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public @NotNull VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		VoxelShape shape = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
		shape = VoxelShapes.combineAndSimplify(shape, WIRE_AABB, BooleanBiFunction.OR);
		shape = VoxelShapes.combineAndSimplify(shape, LAMP_AABB, BooleanBiFunction.OR);
		
		return shape;
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
