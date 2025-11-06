package com.raspix.snekcraft.blocks.entity.eggs;

import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.corn.CornSnakeEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;

public class CornSnakeEggBlock extends SnakeEggBlock {
	
	
	public CornSnakeEggBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public EntityType<?> getSnakeType() {
		return ModEntityTypes.CORNSNAKE;
	}
	
	@Override
	public int getOffspringColor(NbtCompound nbtCompound) {
		return CornSnakeEntity.colorGenetics
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1), 0), CornSnakeEntity.getMaxColor())]
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2), 0), CornSnakeEntity.getMaxColor())]
				.getGene(random.nextInt(SnakeBase.BREEDING_RANGE));
	}
	
	@Override
	public int getOffspringPattern(NbtCompound nbtCompound) {
		return CornSnakeEntity.colorGenetics
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1), 0), CornSnakeEntity.getMaxPattern())]
				[Math.min(Math.max(nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2), 0), CornSnakeEntity.getMaxPattern())]
				.getGene(random.nextInt(SnakeBase.BREEDING_RANGE));
	}
}
