package com.raspix.snekcraft.blocks.entity.eggs;

import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public abstract class SnakeEggBlock extends BlockWithEntity {
	public static final int MAX_HATCH_LEVEL = 2;
	public static final int MIN_EGGS = 1;
	public static final int MAX_EGGS = 4;
	
	public static final String NBT_KEY_BLOCK_STATE_TAG = "BlockStateTag";
	
	protected final java.util.Random random = new java.util.Random();
	
	private static final VoxelShape ONE_EGG_AABB = Block.createCuboidShape(3.0d, 0.0d, 3.0d, 12.0d, 7.0d, 12.0d);
	private static final VoxelShape MULTIPLE_EGGS_AABB = Block.createCuboidShape(1.0d, 0.01, 1.0d, 15.0d, 7.0d, 15.0d);
	public static final IntProperty HATCH = Properties.HATCH;
	public static final IntProperty EGGS = Properties.EGGS;
	
	public SnakeEggBlock(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(HATCH, Integer.valueOf(0)).with(EGGS, Integer.valueOf(1)));
	}
	
	/*@Override
	public void onSteppedOn(World world, BlockPos blockPos, BlockState blockState, Entity entity) {
		super.onSteppedOn(world, blockPos, blockState, entity);
	}
	
	@Override
	public void onLandedUpon(World world, BlockState blockState, BlockPos blockPos, Entity entity, float fallDistance) {
		super.onLandedUpon(world, blockState, blockPos, entity, fallDistance);
	}
	
	@Override
	public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		if(!world.isClient() && hand == Hand.MAIN_HAND && world.getBlockEntity(blockPos) instanceof SnakeEggBlockEntity blockEntity) {
			NbtCompound nbtCompound = blockEntity.getPersistentData();
			int color1 = nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1);
			int color2 = nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2);
			int pattern1 = nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1);
			int pattern2 = nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2);
			System.out.println("The egg has ps: " + pattern1 + ", " + pattern2 + ", and cs: " + color1 + ", " + color2 + " stored");
		}
		
		return super.onUse(blockState, world, blockPos, player, hand, hitResult)
	}*/
	
	private float nextPitch(Random random) {
		return 0.9f + random.nextFloat() * 0.2f;
	}
	
	private void decreaseEggs(World world, BlockPos blockPos, BlockState blockState) {
		world.playSound(null, blockPos, SoundEvents.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7f, nextPitch(world.random));
		int eggs = blockState.get(EGGS);
		if(eggs <= 1) {
			world.breakBlock(blockPos, false);
		}else {
			NbtCompound oldNbt = ((SnakeEggBlockEntity) world.getBlockEntity(blockPos)).getPersistentData();
			world.setBlockState(blockPos, blockState.with(EGGS, Integer.valueOf(eggs - 1)), 2);
			world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
			SnakeEggBlockEntity blockEntity = (SnakeEggBlockEntity) world.getBlockEntity(blockPos);
			
			blockEntity.setStats(oldNbt.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1),
					oldNbt.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2),
					oldNbt.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1),
					oldNbt.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2));
		}
	}
	
	@Override
	public void randomTick(BlockState blockState, ServerWorld world, BlockPos blockPos, Random random) {
		if(shouldUpdateHatchLevel(world) && onSand(world, blockPos)) {
			int hatchLevel = blockState.get(HATCH);
			if(hatchLevel < 2) {
				world.playSound(null, blockPos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7f, nextPitch(random));
				world.setBlockState(blockPos, blockState.with(HATCH, Integer.valueOf(hatchLevel + 1)), 2);
			}else if(world.getBlockEntity(blockPos) instanceof SnakeEggBlockEntity blockEntity) {
				world.playSound(null, blockPos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7f, nextPitch(random));
				NbtCompound nbtCompound = blockEntity.getPersistentData();
				world.breakBlock(blockPos, false);
				for(int i = 0;i < blockState.get(EGGS);++i) {
					world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
					SnakeBase snake = (SnakeBase) getSnakeType().create(world);
					snake.setBreedingAge(-24000);
					snake.refreshPositionAndAngles((double) blockPos.getX() + 0.3d + i * 0.2d, (double) blockPos.getY(), (double) blockPos.getZ() + 0.3d, 0.0f, 0.0f);
					snake.setColor(getOffspringColor(nbtCompound));
					snake.setPattern(getOffspringPattern(nbtCompound));
					world.spawnEntity(snake);
				}
			}
		}
	}
	
	public abstract EntityType<?> getSnakeType();
	
	public static boolean onSand(BlockView blockView, BlockPos blockPos) {
		return blockView.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)|| blockView.getBlockState(blockPos.down()).isIn(BlockTags.SAND);
	}
	
	@Override
	public void onBlockAdded(BlockState blockState, World world, BlockPos blockPos, BlockState oldState, boolean isMoving) {
		if(onSand(world, blockPos) && !world.isClient()) {
			world.syncWorldEvent(WorldEvents.PLANT_FERTILIZED, blockPos, 0);
		}
	}
	
	private boolean shouldUpdateHatchLevel(World world) {
		double f = world.getSkyAngle(1.0f);
		return (f < 0.69d && f > 0.65d) || world.random.nextInt(4) == 0;
	}
	
	@Override
	public void afterBreak(World world, PlayerEntity player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
		if(!world.isClient() && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, itemStack) != 0 && blockEntity instanceof SnakeEggBlockEntity snakeEggBlockEntity) {
			ItemStack newItemStack = new ItemStack(this);
			
			NbtCompound nbtCompoundBlock = snakeEggBlockEntity.getPersistentData();
			int color1 = nbtCompoundBlock.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1);
			int color2 = nbtCompoundBlock.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2);
			int pattern1 = nbtCompoundBlock.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1);
			int pattern2 = nbtCompoundBlock.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2);
			
			NbtCompound nbtCompound = new NbtCompound();
			if(color1 > 0) {
				nbtCompound.putInt(SnakeEggBlockEntity.NBT_KEY_COLOR1, color1);
			}
			if(color2 > 0) {
				nbtCompound.putInt(SnakeEggBlockEntity.NBT_KEY_COLOR2, color2);
			}
			if(pattern1 > 0) {
				nbtCompound.putInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1, pattern1);
			}
			if(pattern2 > 0) {
				nbtCompound.putInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2, pattern2);
			}
			
			newItemStack.setSubNbt(NBT_KEY_BLOCK_STATE_TAG, nbtCompound);
			ItemEntity itemEntity = new ItemEntity(world, (double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ(), newItemStack);
			itemEntity.setToDefaultPickupDelay();
			world.spawnEntity(itemEntity);
		}
		super.afterBreak(world, player, blockPos, blockState, blockEntity, itemStack);
	}
	
	@Override
	public boolean canReplace(BlockState blockState, ItemPlacementContext context) {
		ItemStack handItem = context.getStack();
		
		NbtCompound nbtCompound = handItem.getNbt();
		boolean isSameEggType = handItem.isOf(asItem());
		boolean areSameValues = false;
		
		BlockPos blockPos = context.getBlockPos();
		if(isSameEggType && nbtCompound != null && !context.getWorld().isClient() && context.getWorld().getBlockEntity(blockPos) instanceof SnakeEggBlockEntity blockEntity) {
			NbtCompound blockNbt = blockEntity.getPersistentData();
			for(String nbtInfo: nbtCompound.getKeys()) {
				if(nbtInfo.contains(NBT_KEY_BLOCK_STATE_TAG)) {
					NbtCompound itemNbt = nbtCompound.getCompound(NBT_KEY_BLOCK_STATE_TAG);
					areSameValues = areGeneticsSame(blockNbt, itemNbt);
				}
			}
		}
		
		return (!context.shouldCancelInteraction() && isSameEggType && areSameValues && blockState.get(EGGS) < 4) || super.canReplace(blockState, context);
	}
	
	public boolean areGeneticsSame(NbtCompound blockNbt, NbtCompound itemNbt) {
		boolean colorMatch = blockNbt.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1) == itemNbt.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1);
		boolean color2Match = blockNbt.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2) == itemNbt.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2);
		boolean patternMatch = blockNbt.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1) == itemNbt.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1);
		boolean pattern2Match = blockNbt.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2) == itemNbt.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2);
		return colorMatch && color2Match && patternMatch && pattern2Match;
	}
	
	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext context) {
		BlockState blockState = context.getWorld().getBlockState(context.getBlockPos());
		return blockState.isOf(this) ? blockState.with(EGGS, Integer.valueOf(Math.min(MAX_EGGS, blockState.get(EGGS) + 1))) : super.getPlacementState(context);
	}
	
	@Override
	@Nullable
	public void onPlaced(World world, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, blockPos, blockState, placer, itemStack);
		NbtCompound nbtCompound = itemStack.getNbt();
		if(nbtCompound != null) {
			NbtCompound itemNbt = nbtCompound.getCompound(NBT_KEY_BLOCK_STATE_TAG);
			if(itemNbt != null) {
				int color1 = itemNbt.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1);
				int color2 = itemNbt.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2);
				int pattern1 = itemNbt.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1);
				int pattern2 = itemNbt.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2);
				SnakeEggBlockEntity blockEntity = (SnakeEggBlockEntity) world.getBlockEntity(blockPos);
				if(blockEntity != null) {
					NbtCompound persistentData = blockEntity.getPersistentData();
					persistentData.putInt(SnakeEggBlockEntity.NBT_KEY_COLOR1, color1);
					persistentData.putInt(SnakeEggBlockEntity.NBT_KEY_COLOR2, color2);
					persistentData.putInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1, pattern1);
					persistentData.putInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2, pattern2);
				}
			}
		}
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		return blockState.get(EGGS) > 1 ? MULTIPLE_EGGS_AABB : ONE_EGG_AABB;
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(HATCH, EGGS);
	}

    public abstract int getOffspringColor(NbtCompound compoundTag);

    public abstract int getOffspringPattern(NbtCompound compoundTag);
    
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
    	return new SnakeEggBlockEntity(blockPos, blockState);
    }
    
    @Override
    public BlockRenderType getRenderType(BlockState blockState){
    	return BlockRenderType.MODEL;
    }
    
    @Override
    public void onStateReplaced(BlockState blockState, World world, BlockPos blockPos, BlockState newState, boolean isMoving) {
    	if(blockState.hasBlockEntity() && (!blockState.isOf(newState.getBlock()) || !newState.hasBlockEntity())) {
    		world.removeBlockEntity(blockPos);
    	}
    }
    
    @Override
    public void onBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
    	super.onBreak(world, blockPos, blockState, player);
    	decreaseEggs(world, blockPos, blockState);
    }
}
