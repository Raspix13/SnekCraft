package com.raspix.snekcraft.blocks;

import org.jetbrains.annotations.NotNull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class CaveHideBlock extends Block {
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
	
	private static final VoxelShape TOP_AABB = Block.createCuboidShape(0f, 8f, 0f, 16f, 12f, 16f);
	private static final VoxelShape NORTH_AABB = Block.createCuboidShape(0f, 0f, 0f, 16f, 8f, 2f);
	private static final VoxelShape WEST_AABB = Block.createCuboidShape(0f, 0f, 0f, 2f, 8f, 16f);
	private static final VoxelShape EAST_AABB = Block.createCuboidShape(14f, 0f, 0f, 16f, 8f, 16f);
	private static final VoxelShape SOUTH_AABB = Block.createCuboidShape(0f, 0f, 14f, 16f, 8f, 16f);
	
	public CaveHideBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public @NotNull VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		VoxelShape shape = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
		shape = VoxelShapes.combineAndSimplify(shape, TOP_AABB, BooleanBiFunction.OR);
		switch(blockState.get(FACING)) {
			case EAST:
				shape = VoxelShapes.combineAndSimplify(shape, SOUTH_AABB, BooleanBiFunction.OR);
				shape = VoxelShapes.combineAndSimplify(shape, WEST_AABB, BooleanBiFunction.OR);
				shape = VoxelShapes.combineAndSimplify(shape, NORTH_AABB, BooleanBiFunction.OR);
				break;
			case SOUTH:
				shape = VoxelShapes.combineAndSimplify(shape, EAST_AABB, BooleanBiFunction.OR);
				shape = VoxelShapes.combineAndSimplify(shape, WEST_AABB, BooleanBiFunction.OR);
				shape = VoxelShapes.combineAndSimplify(shape, NORTH_AABB, BooleanBiFunction.OR);
				break;
			case WEST:
				shape = VoxelShapes.combineAndSimplify(shape, EAST_AABB, BooleanBiFunction.OR);
				shape = VoxelShapes.combineAndSimplify(shape, SOUTH_AABB, BooleanBiFunction.OR);
				shape = VoxelShapes.combineAndSimplify(shape, NORTH_AABB, BooleanBiFunction.OR);
				break;
			case NORTH:
			default:
				shape = VoxelShapes.combineAndSimplify(shape, EAST_AABB, BooleanBiFunction.OR);
				shape = VoxelShapes.combineAndSimplify(shape, SOUTH_AABB, BooleanBiFunction.OR);
				shape = VoxelShapes.combineAndSimplify(shape, WEST_AABB, BooleanBiFunction.OR);
				break;
		}
		
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
	
	@Override
	public BlockState rotate(BlockState blockState, BlockRotation rotation) {
		return blockState.with(FACING, rotation.rotate(blockState.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState blockState, BlockMirror mirror) {
		return blockState.rotate(mirror.getRotation(blockState.get(FACING)));
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
	}
	
	@Override
	public boolean canPathfindThrough(BlockState blockState, BlockView blockView, BlockPos blockPos, NavigationType navigationType) {
		return false;
	}
}
