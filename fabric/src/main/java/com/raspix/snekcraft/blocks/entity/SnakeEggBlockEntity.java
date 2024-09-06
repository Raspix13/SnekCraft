package com.raspix.snekcraft.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class SnakeEggBlockEntity extends BlockEntity {
	public static final String NBT_KEY_COLOR1 = "color";
	public static final String NBT_KEY_COLOR2 = "color_p2";
	public static final String NBT_KEY_PATTERN1 = "pattern";
	public static final String NBT_KEY_PATTERN2 = "pattern_p2";
	public static final String NBT_KEY_PERSISTENT_DATA = "ForgeData";
	
	private int color, pattern, colorP2, patternP2;
	
	private NbtCompound persistentData;
	
	public SnakeEggBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}
	
	public SnakeEggBlockEntity(BlockPos blockPos, BlockState blockState, int color) {
		super(BlockEntityInit.SNAKE_EGG, blockPos, blockState);
		//this.color = color;
	}
	
	public SnakeEggBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(BlockEntityInit.SNAKE_EGG, blockPos, blockState);
	}
	
	public SnakeEggBlockEntity(BlockPos blockPos, BlockState blockState, int color, int color2, int pattern, int pattern2) {
		super(BlockEntityInit.SNAKE_EGG, blockPos, blockState);
		/*this.color = color;
		colorP2 = color2;
		this.pattern = pattern; 
		patternP2 = pattern2;*/
	}
	
	@Override
	public void readNbt(NbtCompound nbtCompound) {
		super.readNbt(nbtCompound);
		if(nbtCompound.contains(NBT_KEY_PERSISTENT_DATA)) {
			persistentData = nbtCompound.getCompound(NBT_KEY_PERSISTENT_DATA);
		}
		color = nbtCompound.getInt(NBT_KEY_COLOR1);
		colorP2 = nbtCompound.getInt(NBT_KEY_COLOR2);
		pattern = nbtCompound.getInt(NBT_KEY_PATTERN1);
		patternP2 = nbtCompound.getInt(NBT_KEY_PATTERN2);
	}
	
	@Override
	protected void writeNbt(NbtCompound nbtCompound) {
		super.writeNbt(nbtCompound);
		if(persistentData != null) {
			nbtCompound.put(NBT_KEY_PERSISTENT_DATA, persistentData.copy());
		}
		nbtCompound.putInt(NBT_KEY_COLOR1, color);
		nbtCompound.putInt(NBT_KEY_COLOR2, colorP2);
		nbtCompound.putInt(NBT_KEY_PATTERN1, pattern);
		nbtCompound.putInt(NBT_KEY_PATTERN2, patternP2);
	}
	
	public int getColor() {
		return color;
	}
	
	public int getColorP2() {
		return colorP2;
	}
	
	public int getPattern() {
		System.out.println("Pattern was set in EggBlockEntity");
		return pattern;
	}
	
	public int getPatternP2() {
		return patternP2;
	}
	
	public void setColor(int color) {
		toInitialChunkDataNbt().putInt(NBT_KEY_COLOR1, color);
		System.out.println("Color was set to " + color + " in EggBlockEntity");
		this.color = color;
	}
	
	public void setColorP2(int color) {
		System.out.println("Color was set to " + color + " in EggBlockEntity");
		colorP2 = color;
	}
	
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	
	public void setPatternP2(int pattern) {
		patternP2 = pattern;
	}
	
	public void setStats(int color, int colorP2, int pattern, int patternP2) {
		this.color = color;
		this.colorP2 = colorP2;
		this.pattern = pattern;
		this.patternP2 = patternP2;
	}
	
	public void setStats(NbtCompound nbtCompound) {
		color = nbtCompound.getInt(NBT_KEY_COLOR1);
		colorP2 = nbtCompound.getInt(NBT_KEY_COLOR2);
		pattern = nbtCompound.getInt(NBT_KEY_PATTERN1);
		patternP2 = nbtCompound.getInt(NBT_KEY_PATTERN2);
	}
	
	public void printOutStats() {
		System.out.println("SnakeBlockEntity Stats: c=" + color + ", c2=" + colorP2 + ", p=" + pattern + ", p2=" + patternP2);
	}
	
	public NbtCompound getPersistentData() {
		return persistentData != null ? persistentData : new NbtCompound();
	}
}
