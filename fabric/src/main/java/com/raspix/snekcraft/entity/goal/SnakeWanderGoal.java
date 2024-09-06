package com.raspix.snekcraft.entity.goal;

import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.entity.ai.goal.WanderAroundFarGoal;

// Unused?
public class SnakeWanderGoal extends WanderAroundFarGoal {
	private final SnakeBase snake;
	
	public SnakeWanderGoal(SnakeBase snake, double speedModifier) {
		super(snake, speedModifier);
		this.snake = snake;
	}
	
	@Override
	public boolean canStart() {
		return !snake.isResting() && super.canStart();
	}
	
	@Override
	public boolean shouldContinue() {
		return !snake.isResting() && super.shouldContinue();
	}
}
