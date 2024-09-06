package com.raspix.snekcraft.entity.goal;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.eggs.SnakeEggBlock;
import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class SnakeLayEggGoal extends MoveToTargetPosGoal {
	private final SnakeBase snake;
	
	public SnakeLayEggGoal(SnakeBase snake, double speedModifier) {
		super(snake, speedModifier, 16);
		this.snake = snake;
	}
	
	@Override
	public boolean canStart() {
		return snake.hasEgg() && super.canStart();
	}
	
	@Override
	public boolean shouldContinue() {
		return super.shouldContinue() && snake.hasEgg();
	}
	
	@Override
	public void tick() {
		super.tick();
		BlockPos blockPos = snake.getBlockPos();
		if(!snake.isTouchingWater() && !hasReached()) {
			if(snake.layEggCounter < 1) {
				snake.setLayingEgg(true);
			}else if(snake.layEggCounter > getTickCount(200)) {
				World world = snake.getWorld();
				world.playSound(null, blockPos, SoundEvents.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3f, 0.9f + world.random.nextFloat() * 0.2f);
				
				createEgg(world);
				snake.setHasEgg(false);
				snake.setLayingEgg(false);
			}
			
			if(snake.isLayingEgg()) {
				++snake.layEggCounter;
			}
		}
	}
	
	public void createEgg(World world) {
		world.setBlockState(targetPos.up(), snake.getEggType().getDefaultState().with(SnakeEggBlock.EGGS, Integer.valueOf(snake.getRandom().nextInt(4) + 1)), 3);
		SnakeEggBlockEntity snakeEggBlockEntity = (SnakeEggBlockEntity) world.getBlockEntity(targetPos.up());
		NbtCompound nbtCompound = snakeEggBlockEntity.getPersistentData();
		if(nbtCompound != null) {
			nbtCompound.putInt(SnakeEggBlockEntity.NBT_KEY_COLOR1, ((SnakeBase) mob).getColor());
			nbtCompound.putInt(SnakeEggBlockEntity.NBT_KEY_COLOR2, ((SnakeBase) mob).partnerColor);
			nbtCompound.putInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1, ((SnakeBase) mob).getPattern());
			nbtCompound.putInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2, ((SnakeBase) mob).partnerPattern);
			
			snakeEggBlockEntity.setStats(((SnakeBase) mob).getColor(), ((SnakeBase) mob).partnerColor, ((SnakeBase) mob).getPattern(), ((SnakeBase) mob).partnerPattern);
		}else {
			SnekCraft.logger.error("NbtCompound not found");
		}
	}
	
	protected boolean isTargetPos(WorldView worldView, BlockPos blockPos) {
		if(worldView.isAir(blockPos.up())) {
			BlockState blockState = worldView.getBlockState(blockPos);
			return blockState.isIn(BlockTags.DIRT) || blockState.isIn(BlockTags.SAND);
		}
		return false;
	}
}
