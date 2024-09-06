package com.raspix.snekcraft.entity.goal;

import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;

public class SnakeBreedGoal extends AnimalMateGoal {
	private final SnakeBase snake;
	
	public SnakeBreedGoal(SnakeBase snake, double speedModifier) {
		super(snake, speedModifier);
		this.snake = snake;
	}
	
	@Override
	public boolean canStart() {
		return super.canStart() && snake.hasEgg();
	}
	
	@Override
	protected void breed() {
		ServerPlayerEntity serverPlayer = animal.getLovingPlayer();
		if(serverPlayer == null && mate.getLovingPlayer() != null) {
			serverPlayer = mate.getLovingPlayer();
		}
		
		if(serverPlayer != null) {
			serverPlayer.increaseStat(Stats.ANIMALS_BRED, 1);
			Criteria.BRED_ANIMALS.trigger(serverPlayer, animal, mate, null);
		}
		snake.setHasEgg(true);
		animal.setBreedingAge(6000);
		mate.setBreedingAge(6000);
		animal.resetLoveTicks();
		mate.resetLoveTicks();
		((SnakeBase) animal).partnerColor = ((SnakeBase) mate).getColor();
		((SnakeBase) animal).partnerPattern = ((SnakeBase) mate).getPattern();
		Random random = animal.getRandom();
		if(world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
			world.spawnEntity(new ExperienceOrbEntity(world, animal.getX(), animal.getY(), animal.getZ(), random.nextInt(7) + 1));
		}
	}
}
