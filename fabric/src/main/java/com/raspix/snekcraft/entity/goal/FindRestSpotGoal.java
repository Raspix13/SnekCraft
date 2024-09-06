package com.raspix.snekcraft.entity.goal;

import com.raspix.snekcraft.blocks.CaveHideBlock;
import com.raspix.snekcraft.blocks.HeatLampBlock;
import com.raspix.snekcraft.blocks.MediumHideBlock;
import com.raspix.snekcraft.blocks.TunnelHideBlock;
import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class FindRestSpotGoal extends MoveToTargetPosGoal {
	private final SnakeBase snake;
	private static final int REST_CHANCE = 10;
	
	public FindRestSpotGoal(SnakeBase snake, double speedModifier) {
		super(snake, speedModifier, 8);
		this.snake = snake;
	}
	
	@Override
	public boolean canStart() {
		return !snake.isResting() && snake.getRandom().nextInt(REST_CHANCE) == 1 && super.canStart();
	}
	
	@Override
	public boolean shouldContinue() {
		return !snake.isResting() && super.shouldContinue();
	}
	
	@Override
	protected boolean isTargetPos(WorldView worldView, BlockPos blockPos) {
		Block block = worldView.getBlockState(blockPos).getBlock();
		if(block instanceof CaveHideBlock || block instanceof TunnelHideBlock || block instanceof MediumHideBlock) {
			return true;
		}else if(!worldView.isSkyVisible(blockPos)) {
			BlockPos abovePos = blockPos.up();
			for(int i = 0;i < 5;++i) {
				if(worldView.getBlockState(abovePos).getBlock() instanceof HeatLampBlock) {
					return true;
				}
				abovePos = abovePos.up();
			}
		}
		
		return false;
	}
	
	@Override
	public void start() {
		super.start();
		snake.setResting(false);
	}
	
	@Override
	public void stop() {
		super.stop();
		//snake.setResting(false);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(hasReached()) {
			snake.setResting(true);
		}
	}
	
	@Override
	public double getDesiredDistanceToTarget() {
		return 2.0d;
	}
}
