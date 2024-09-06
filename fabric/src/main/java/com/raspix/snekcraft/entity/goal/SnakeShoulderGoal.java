package com.raspix.snekcraft.entity.goal;

import java.util.EnumSet;

import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.entity.ai.goal.Goal;

// Unused?
public class SnakeShoulderGoal extends Goal {
	private final SnakeBase snake;
	
	public SnakeShoulderGoal(SnakeBase snake) {
		this.snake = snake;
		setControls(EnumSet.of(Control.JUMP, Control.MOVE, Control.LOOK));
	}
	
	@Override
	public boolean canStart() {
		return snake.isSittingOnShoulder;
	}
	
	@Override
	public boolean shouldContinue() {
		return super.shouldContinue() && canStart();
	}
}
