package com.raspix.snekcraft.blocks;

import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.SnekCraft;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MediumHideBlock extends HorizontalFacingBlock {
	public static final EnumProperty<HidePart> PART = EnumProperty.of("part", HidePart.class);
	
	protected static final VoxelShape WALL_NORTH = Block.createCuboidShape(0.0d, 0.0d, 12.0d, 16.0d, 8.0d, 16.0d);
	protected static final VoxelShape WALL_SOUTH = Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 8.0d, 4.0d);
	protected static final VoxelShape WALL_EAST = Block.createCuboidShape(10.0d, 0.0d, 0.0d, 16.0d, 8.0d, 16.0d);
	protected static final VoxelShape WALL_WEST = Block.createCuboidShape(0.0d, 0.0d, 0.0d, 4.0d, 8.0d, 16.0d);
	protected static final VoxelShape BASE = Block.createCuboidShape(0.0d, 8.0d, 0.0d, 16.0d, 12.0d, 16.0d);
	
	public MediumHideBlock(Settings settings) {
		super(settings);
		setDefaultState(stateManager.getDefaultState().with(PART, HidePart.ENTRANCE));
	}
	
	@Nullable
	public static Direction getBedOrientation(BlockView blockView, BlockPos blockPos) {
		BlockState blockState = blockView.getBlockState(blockPos);
		return blockState.getBlock() instanceof BedBlock ? blockState.get(FACING) : null;
	}
	
	@Override
	public void onBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
		if(!world.isClient()) {
			HidePart hidePart = blockState.get(PART);
			if(hidePart != HidePart.ENTRANCE) {
				originDestroyHelper(world, blockPos, blockState, player, 0);
			}else {
				Direction direction = blockState.get(FACING);
				BlockPos partPos = blockPos.offset(direction.rotateYClockwise());
				BlockPos partPos2 = blockPos.offset(direction);
				BlockPos partPos3 = partPos.offset(direction);
				removePartBlock(world, partPos, player);
				removePartBlock(world, partPos2, player);
				removePartBlock(world, partPos3, player);
			}
		}
		
		super.onBreak(world, blockPos, blockState, player);
	}
	
	public void originDestroyHelper(World world, BlockPos blockPos, BlockState blockState, PlayerEntity player, int iteration) {
		if(blockState.isOf(this)) {
			HidePart hidePart = blockState.get(PART);
			if(hidePart != HidePart.ENTRANCE && iteration <= 2) {
				BlockPos newPos = blockPos.offset(blockState.get(FACING));
				BlockState newState = world.getBlockState(newPos);
				originDestroyHelper(world, newPos, newState, player, iteration + 1);
			}else if(hidePart == HidePart.ENTRANCE) {
				playerWillDestroyFinal(world, blockPos, blockState, player);
			}
		}else {
			SnekCraft.logger.error("originDestroyHelper ran into a non-MedHide block, you suck at coding");
		}
	}
	
	public void playerWillDestroyFinal(World world, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
		if(!world.isClient()) {
			HidePart hidePart = blockState.get(PART);
			if(hidePart == HidePart.ENTRANCE) {
				Direction direction = blockState.get(FACING);
				BlockPos partPos = blockPos.offset(direction.rotateYClockwise());
				BlockPos partPos2 = blockPos.offset(direction);
				BlockPos partPos3 = partPos.offset(direction);
				removePartBlock(world, partPos, player);
				removePartBlock(world, partPos2, player);
				removePartBlock(world, partPos3, player);
				Item tempItem = world.getBlockState(blockPos).getBlock().asItem();
				removePartBlock(world, blockPos, player);
				System.out.println("Item is: " + tempItem);
				ItemStack itemStack = new ItemStack(this);
				ItemEntity itemEntity = new ItemEntity(world, (double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ(), itemStack);
				itemEntity.setToDefaultPickupDelay();
				world.spawnEntity(itemEntity);
			}
		}
	}
	
	private void removePartBlock(World world, BlockPos blockPos, PlayerEntity player) {
		BlockState blockState = world.getBlockState(blockPos);
		if(blockState.isOf(this)) {
			world.removeBlock(blockPos, false);
		}
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		Direction direction = blockState.get(FACING);
		VoxelShape shape = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
		shape = VoxelShapes.combineAndSimplify(shape, BASE, BooleanBiFunction.OR);
		HidePart hidePart = blockState.get(PART);
		if(hidePart == HidePart.ENTRANCE) {
			switch(direction) {
				case NORTH:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_WEST, BooleanBiFunction.OR);
					break;
				case SOUTH:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_EAST, BooleanBiFunction.OR);
					break;
				case WEST:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_NORTH, BooleanBiFunction.OR);
					break;
				case EAST:
				default:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_SOUTH, BooleanBiFunction.OR);
					break;
			}
		}else if(hidePart == HidePart.ENTRANCE2) {
			switch(direction) {
				case NORTH:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_EAST, BooleanBiFunction.OR);
					break;
				case SOUTH:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_WEST, BooleanBiFunction.OR);
					break;
				case WEST:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_SOUTH, BooleanBiFunction.OR);
					break;
				case EAST:
				default:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_NORTH, BooleanBiFunction.OR);
					break;
			}
		}else if(hidePart == HidePart.SIDE || hidePart == HidePart.SIDE2) {
			switch(direction) {
				case NORTH:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_NORTH, BooleanBiFunction.OR);
					break;
				case SOUTH:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_SOUTH, BooleanBiFunction.OR);
					break;
				case WEST:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_EAST, BooleanBiFunction.OR);
					break;
				case EAST:
				default:
					shape = VoxelShapes.combineAndSimplify(shape, WALL_WEST, BooleanBiFunction.OR);
					break;
			}
		}
		
		return shape;
	}
	
	// Unused?
	public static Direction getConnectedDirection(BlockState blockState) {
		Direction direction = blockState.get(FACING);
		return blockState.get(PART) != HidePart.ENTRANCE ? direction.getOpposite() : direction;
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART);
	}
	
	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext context) {
		Direction direction = context.getHorizontalPlayerFacing();
		BlockPos blockPos = context.getBlockPos();
		BlockPos partPos = blockPos.offset(direction.rotateYClockwise());
		BlockPos partPos2 = blockPos.offset(direction);
		BlockPos partPos3 = partPos.offset(direction);
		World world = context.getWorld();
		return world.getBlockState(partPos).canReplace(context) && world.getWorldBorder().contains(partPos) &&
				world.getBlockState(partPos2).canReplace(context) && world.getWorldBorder().contains(partPos2) &&
				world.getBlockState(partPos3).canReplace(context) && world.getWorldBorder().contains(partPos3)
				? getDefaultState().with(FACING, direction) : null;
	}
	
	@Override
	public void onPlaced(World world, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, blockPos, blockState, placer, itemStack);
		if(!world.isClient() && blockState.get(PART) == HidePart.ENTRANCE) {
			Direction direction = blockState.get(FACING);
			BlockPos partPos = blockPos.offset(direction.rotateYClockwise());
			BlockPos partPos2 = blockPos.offset(direction);
			BlockPos partPos3 = partPos.offset(direction);
			addPartBlock(world, partPos, blockState, direction.rotateYClockwise().getOpposite(), HidePart.SIDE);
			addPartBlock(world, partPos2, blockState, direction.getOpposite(), HidePart.ENTRANCE2);
			addPartBlock(world, partPos3, blockState, direction.rotateYClockwise().getOpposite(), HidePart.SIDE2);
		}
	}
	
	private void addPartBlock(World world, BlockPos blockPos, BlockState blockState, Direction direction, HidePart type) {
		world.setBlockState(blockPos, blockState.with(PART, type).with(FACING, direction), 3);
		world.updateNeighbors(blockPos, Blocks.AIR);
		blockState.updateNeighbors(world, blockPos, 3);
	}
	
	@Override
	public boolean canPathfindThrough(BlockState blockState, BlockView blockView, BlockPos blockPos, NavigationType navigationType) {
		return false;
	}
}
