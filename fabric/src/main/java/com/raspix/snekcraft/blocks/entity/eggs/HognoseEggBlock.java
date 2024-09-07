package com.raspix.snekcraft.blocks.entity.eggs;

import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.entity.hognose.HognoseEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;

public class HognoseEggBlock extends SnakeEggBlock {
	
	
	public HognoseEggBlock(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(HATCH, Integer.valueOf(0)).with(EGGS, Integer.valueOf(1)));
	}
	
	@Override
	public EntityType<?> getSnakeType(){
		return ModEntityTypes.HOGNOSE;
	}
	
	@Override
	public int getOffspringColor(NbtCompound nbtCompound) {
		return HognoseEntity.colorGenetics
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1), 0), HognoseEntity.getMaxColor())]
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2), 0), HognoseEntity.getMaxColor())]
				.getGene(random.nextInt(SnakeBase.BREEDING_RANGE));
	}
	
	@Override
	public int getOffspringPattern(NbtCompound nbtCompound) {
		return HognoseEntity.colorGenetics
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1), 0), HognoseEntity.getMaxPattern())]
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2), 0), HognoseEntity.getMaxPattern())]
				.getGene(random.nextInt(SnakeBase.BREEDING_RANGE));
	}
}
