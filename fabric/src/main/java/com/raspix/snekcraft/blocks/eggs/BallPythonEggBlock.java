package com.raspix.snekcraft.blocks.eggs;

import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;

public class BallPythonEggBlock extends SnakeEggBlock {
	
	
	public BallPythonEggBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public EntityType<?> getSnakeType() {
		return ModEntityTypes.BALLPYTHON;
	}
	
	@Override
	public int getOffspringColor(NbtCompound nbtCompound) {
		return BallPythonEntity.colorGenetics
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1), 0), BallPythonEntity.getMaxColor())]
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2), 0), BallPythonEntity.getMaxColor())]
				.getGene(random.nextInt(SnakeBase.BREEDING_RANGE));
	}
	
	@Override
	public int getOffspringPattern(NbtCompound nbtCompound) {
		return BallPythonEntity.colorGenetics
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1), 0), BallPythonEntity.getMaxPattern())]
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2), 0), BallPythonEntity.getMaxPattern())]
				.getGene(random.nextInt(SnakeBase.BREEDING_RANGE));
	}
}
