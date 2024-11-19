package com.raspix.snekcraft.blocks.terrariums;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.SnekBlockStateProperties;
import com.raspix.snekcraft.items.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class TerrariumBlock extends GlassBlock {
	public static final IntProperty UP = SnekBlockStateProperties.UP;
	public static final IntProperty DOWN = SnekBlockStateProperties.DOWN;
	public static final IntProperty NORTH = SnekBlockStateProperties.NORTH;
	public static final IntProperty EAST = SnekBlockStateProperties.EAST;
	public static final IntProperty SOUTH = SnekBlockStateProperties.SOUTH;
	public static final IntProperty WEST = SnekBlockStateProperties.WEST;
	
	public static final BooleanProperty PATHFIND = BooleanProperty.of("pathfind");
	
	private static final VoxelShape TOP_AABB = Block.createCuboidShape(0.0f, 15.0f, 0.0f, 16.0f, 16.0f, 16.0f);
	private static final VoxelShape BOTTOM_AABB = Block.createCuboidShape(0.0f, 0.0f, 0.0f, 16.0f, 2.0f, 16.0f);
	private static final VoxelShape NORTH_AABB = Block.createCuboidShape(0.0f, 0.0f, 0.0f, 16.0f, 16.0f, 1.0f);
	private static final VoxelShape SOUTH_AABB = Block.createCuboidShape(0.0f, 0.0f, 15.0f, 16.0f, 16.0f, 16.0f);
	private static final VoxelShape EAST_AABB = Block.createCuboidShape(15.0f, 0.0f, 0.0f, 16.0f, 16.0f, 16.0f);
	private static final VoxelShape WEST_AABB = Block.createCuboidShape(0.0f, 0.0f, 0.0f, 1.0f, 16.0f, 16.0f);
	
	public TerrariumBlock(Settings settings) {
		super(settings);
		setDefaultState(stateManager.getDefaultState()
				.with(NORTH, 0)
				.with(EAST, 0)
				.with(SOUTH, 0)
				.with(WEST, 0)
				.with(UP, 0)
				.with(DOWN, 0));
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, PATHFIND);
	}
	
	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext context) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockState blockState = world.getBlockState(blockPos);
		return Objects.requireNonNull(super.getPlacementState(context))
				.with(NORTH, canFenceConnectTo(world.getBlockState(blockPos.north()), blockState, Direction.SOUTH, null))
				.with(EAST, canFenceConnectTo(world.getBlockState(blockPos.east()), blockState, Direction.WEST, null))
				.with(SOUTH, canFenceConnectTo(world.getBlockState(blockPos.south()), blockState, Direction.NORTH, null))
				.with(WEST, canFenceConnectTo(world.getBlockState(blockPos.west()), blockState, Direction.EAST, null));
	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState blockState, Direction facing, BlockState facingState, WorldAccess worldAccess, BlockPos blockPos, BlockPos facingPos) {
		return updateBlockShape(blockState, facing, facingState, worldAccess, blockPos, facingPos).with(PATHFIND, updatePathfinding(blockState, worldAccess, blockPos));
	}
	
	public boolean updatePathfinding(BlockState blockState, WorldAccess worldAccess, BlockPos blockPos) {
		return !(blockState.get(NORTH) == 0 || blockState.get(EAST) == 0 || blockState.get(SOUTH) == 0 || blockState.get(WEST) == 0);
	}
	
	public BlockState updateBlockShape(BlockState blockState, Direction facing, BlockState facingState, WorldAccess worldAccess, BlockPos blockPos, BlockPos facingPos) {
		switch(facing) {
			case NORTH:
				return blockState.with(NORTH, isWallConnectedTo(facingState, blockState, NORTH, worldAccess, Direction.NORTH, blockPos))
						.with(WEST, isWallConnectedTo(worldAccess.getBlockState(blockPos.west()), blockState, WEST, worldAccess, Direction.WEST, blockPos));
			case SOUTH:
				return blockState.with(SOUTH, isWallConnectedTo(facingState, blockState, SOUTH, worldAccess, Direction.SOUTH, blockPos))
						.with(EAST, isWallConnectedTo(worldAccess.getBlockState(blockPos.east()), blockState, EAST, worldAccess, Direction.EAST, blockPos));
			case EAST:
				return blockState.with(EAST, isWallConnectedTo(facingState, blockState, EAST, worldAccess, Direction.EAST, blockPos))
						.with(NORTH, isWallConnectedTo(worldAccess.getBlockState(blockPos.north()), blockState, NORTH, worldAccess, Direction.NORTH, blockPos));
			case WEST:
				return blockState.with(WEST, isWallConnectedTo(facingState, blockState, WEST, worldAccess, Direction.WEST, blockPos))
						.with(SOUTH, isWallConnectedTo(worldAccess.getBlockState(blockPos.south()), blockState, SOUTH, worldAccess, Direction.SOUTH, blockPos));
			case DOWN:
				return blockState.with(DOWN, canFenceConnectTo(facingState, blockState, facing.getOpposite(), DOWN));
			case UP:
			default:
				return blockState.with(UP, canFenceConnectTo(facingState, blockState, facing.getOpposite(), UP));
		}
	}
	
	@Override
	public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		if(!world.isClient() && hand == Hand.MAIN_HAND) {
			ItemStack heldItem = player.getStackInHand(hand);
			if(heldItem.isOf(Items.STICK)) {
				//System.out.println("pathfinding" + blockState.get(PATHFIND));
			}
			if(heldItem.isOf(ItemInit.TERRARIUM_KEY)) {
				setSideEntrance(blockState, world, blockPos, player, hand, hitResult);
			}else {
				return ActionResult.FAIL;
			}
			return ActionResult.SUCCESS;
		}
		return ActionResult.FAIL;
	}
	
	public void setSideEntrance(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		Direction hitWall = hitResult.getSide();
		
		int sideValue = blockState.get(getDirStateFromDir(hitWall));
		if(sideValue == 1 || sideValue == 3) { // if the wall is invisible get the visible one
			hitWall = hitWall.getOpposite();
		}
		
		if(hitWall == null) {
			return;
		}else if(hitWall.equals(Direction.NORTH) && blockState.get(NORTH) != 1) {
			world.setBlockState(blockPos, blockState.with(NORTH, blockState.get(NORTH) == 2 ? 0 : 2).with(PATHFIND, updatePathfinding(blockState, world, blockPos)), 3);
		}else if(hitWall.equals(Direction.SOUTH) && blockState.get(SOUTH) != 1) {
			world.setBlockState(blockPos, blockState.with(SOUTH, blockState.get(SOUTH) == 2 ? 0 : 2).with(PATHFIND, updatePathfinding(blockState, world, blockPos)), 3);
		}else if(hitWall.equals(Direction.EAST) && blockState.get(EAST) != 1) {
			world.setBlockState(blockPos, blockState.with(EAST, blockState.get(EAST) == 2 ? 0 : 2).with(PATHFIND, updatePathfinding(blockState, world, blockPos)), 3);
		}else if(hitWall.equals(Direction.WEST) && blockState.get(WEST) != 1) {
			world.setBlockState(blockPos, blockState.with(WEST, blockState.get(WEST) == 2 ? 0 : 2).with(PATHFIND, updatePathfinding(blockState, world, blockPos)), 3);
		}else if(hitWall.equals(Direction.UP) && blockState.get(UP) != 1) {
			world.setBlockState(blockPos, blockState.with(UP, blockState.get(UP) == 2 ? 0 : 2), 3);
		}else if(hitWall.equals(Direction.DOWN) && blockState.get(DOWN) != 1) {
			world.setBlockState(blockPos, blockState.with(DOWN, blockState.get(DOWN) == 2 ? 0 : 2), 3);
		}
	}
	
	public void updateCornerOnChangeDelete(BlockState blockState, World world, Direction wallChanged, BlockPos blockPos) {
		if(wallChanged == Direction.NORTH) {
			BlockState eastState = world.getBlockState(blockPos.east());
			world.setBlockState(blockPos.east(), eastState.with(SOUTH, isWallConnectedTo(world.getBlockState(blockPos.east().south()), eastState, SOUTH, world, Direction.SOUTH, blockPos.east())), 3);
		}
	}
	
	public IntProperty getDirStateFromDir(Direction direction) {
		switch(direction) {
			case SOUTH:
				return SOUTH;
	        case EAST:
	            return EAST;
	        case WEST:
	            return WEST;
	        case UP:
	            return UP;
	        case DOWN:
	            return DOWN;
	        default:
	        	SnekCraft.logger.error("something went wrong");
	        case NORTH:
	        	return NORTH;
		}
	}
	
	public int canFenceConnectTo(BlockState sideState, BlockState thisState, Direction facing, IntProperty property) {
		if(sideState.getBlock() instanceof TerrariumBlock) {
			return 1;
		}else {
			if(thisState != null && property != null && thisState.get(property) == 2) {
				return 2;
			}else {
				return 0;
			}
		}
	}
	
	public int isWallConnectedTo(BlockState facingState, BlockState thisState, IntProperty property, WorldAccess worldAccess, Direction facing, BlockPos blockPos) {
		if(facingState.getBlock() instanceof TerrariumBlock) {
			if(areSiliconeSidesSolid(worldAccess, facing, blockPos)) {
				return 3; // Silicone
			}
			return 1; // Empty
		}else {
			if(thisState != null && property != null && thisState.get(property) == 2) {
				return 2; // Invisible
			}else {
				return 0; // There
			}
		}
	}
	
	public boolean areSiliconeSidesSolid(WorldAccess worldAccess, Direction facing, BlockPos blockPos) {
		BlockState facingState, clockwiseState, diagonalState;
		IntProperty facingWallDir, clockwiseWallDir;
		switch(facing) {
			case NORTH:
				facingState = worldAccess.getBlockState(blockPos.north());
				clockwiseState = worldAccess.getBlockState(blockPos.east());
				diagonalState = worldAccess.getBlockState(blockPos.north().east());
				facingWallDir = EAST;
				clockwiseWallDir = NORTH;
				break;
			case EAST:
				facingState = worldAccess.getBlockState(blockPos.east());
				clockwiseState = worldAccess.getBlockState(blockPos.south());
				diagonalState = worldAccess.getBlockState(blockPos.east().south());
				facingWallDir = SOUTH;
				clockwiseWallDir = EAST;
				break;
			case SOUTH:
				facingState = worldAccess.getBlockState(blockPos.south());
				clockwiseState = worldAccess.getBlockState(blockPos.west());
				diagonalState = worldAccess.getBlockState(blockPos.south().west());
				facingWallDir = WEST;
				clockwiseWallDir = SOUTH;
				break;
			case WEST:
				facingState = worldAccess.getBlockState(blockPos.west());
				clockwiseState = worldAccess.getBlockState(blockPos.north());
				diagonalState = worldAccess.getBlockState(blockPos.west().north());
				facingWallDir = NORTH;
				clockwiseWallDir = WEST;
				break;
			default:
				return false;
		}
		
		return facingState.getBlock() instanceof TerrariumBlock && clockwiseState.getBlock() instanceof TerrariumBlock && 
				!(diagonalState.getBlock() instanceof TerrariumBlock) && !(facingState.get(facingWallDir) == 2 && clockwiseState.get(clockwiseWallDir) == 2);
	}
	
	public int hasInnerCorner(BlockState diagonalState, BlockState sideState1, BlockState sideState2) {
		if(diagonalState.getBlock() instanceof TerrariumBlock || !(sideState1.getBlock() instanceof TerrariumBlock) || !(sideState2.getBlock() instanceof TerrariumBlock)) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public @NotNull VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		VoxelShape shape = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
		if(blockState.getBlock() instanceof TerrariumBlock) {
			if(blockState.get(UP) == 0) {
				shape = VoxelShapes.combineAndSimplify(shape, TOP_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(DOWN) == 0) {
				shape = VoxelShapes.combineAndSimplify(shape, BOTTOM_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(NORTH) == 0) {
				shape = VoxelShapes.combineAndSimplify(shape, NORTH_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(SOUTH) == 0) {
				shape = VoxelShapes.combineAndSimplify(shape, SOUTH_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(EAST) == 0) {
				shape = VoxelShapes.combineAndSimplify(shape, EAST_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(WEST) == 0) {
				shape = VoxelShapes.combineAndSimplify(shape, WEST_AABB, BooleanBiFunction.OR);
			}
		}
		return shape;
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		VoxelShape shape = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
		boolean hasItem = shapeContext.isHolding(ItemInit.TERRARIUM_KEY);
		if(blockState.getBlock() instanceof TerrariumBlock) {
			if(blockState.get(UP) == 0 || (blockState.get(UP) == 2 && hasItem)) {
				shape = VoxelShapes.combineAndSimplify(shape, TOP_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(DOWN) == 0 || (blockState.get(DOWN) == 2 && hasItem)) {
				shape = VoxelShapes.combineAndSimplify(shape, BOTTOM_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(NORTH) == 0 || (blockState.get(NORTH) == 2 && hasItem)) {
				shape = VoxelShapes.combineAndSimplify(shape, NORTH_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(SOUTH) == 0 || (blockState.get(SOUTH) == 2 && hasItem)) {
				shape = VoxelShapes.combineAndSimplify(shape, SOUTH_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(WEST) == 0 || (blockState.get(WEST) == 2 && hasItem)) {
				shape = VoxelShapes.combineAndSimplify(shape, WEST_AABB, BooleanBiFunction.OR);
			}
			if(blockState.get(EAST) == 0 || (blockState.get(EAST) == 2 && hasItem)) {
				shape = VoxelShapes.combineAndSimplify(shape, EAST_AABB, BooleanBiFunction.OR);
			}
		}
		return shape;
	}
	
	@Override
	public @NotNull BlockRenderType getRenderType(BlockState blockState) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	public boolean isShapeFullCube(BlockState blockState, BlockView blockView, BlockPos blockPos) {
		return false;
	}
	
	protected boolean isEntityInsideContent(BlockState blockState, BlockPos blockPos, Entity entity) {
		return entity.getY() < (double) blockPos.getY() + getContentHeight(blockState) && entity.getBoundingBox().maxY > (double) blockPos.getY() + 0.25d;
	}
	
	protected double getContentHeight(BlockState blockState) {
		return 0.0d;
	}
	
	@Override
	public boolean canPathfindThrough(BlockState blockState, BlockView blockView, BlockPos blockPos, NavigationType navigationType) {
		if(navigationType == NavigationType.WATER) {
			return blockState.canPathfindThrough(blockView, blockPos, navigationType);
		}
		return blockState.get(PATHFIND);
	}
}
