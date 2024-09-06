package com.raspix.snekcraft.entity.goal;

import java.util.EnumSet;

import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.entity.ai.goal.Goal;

public class CurlGoal extends Goal {
	private final SnakeBase snake;
	
	private static final int REST_TIME = toGoalTicks(300);
	private int countdown;
	
	public CurlGoal(SnakeBase snake) {
		super();
		this.snake = snake;
		setControls(EnumSet.of(Control.JUMP, Control.MOVE, Control.LOOK));
	}
	
	@Override
	public boolean canStart() {
		return snake.isResting();
	}
	
	@Override
	public boolean shouldContinue() {
		return snake.isResting() && super.shouldContinue();
	}
	
	@Override
	public void start() {
		countdown = snake.getRandom().nextInt(REST_TIME) + 300;
		super.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		snake.setResting(false);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(countdown > 0) {
			--countdown;
		}else {
			snake.setResting(false);
		}
	}
}
