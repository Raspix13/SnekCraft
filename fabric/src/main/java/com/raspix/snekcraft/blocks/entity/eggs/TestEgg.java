package com.raspix.snekcraft.blocks.entity.eggs;

import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestEgg extends BlockWithEntity {
	
	
	public TestEgg(Settings settings) {
		super(settings);
	}
	
	@Override
	public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		if(world.isClient() && hand == Hand.MAIN_HAND && world.getBlockEntity(blockPos) instanceof SnakeEggBlockEntity blockEntity) {
			System.out.println("Hello from test egg");
			NbtCompound nbtCompound = blockEntity.getPersistentData();
			if(nbtCompound != null) {
				int color1 = nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR1);
				int color2 = nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_COLOR2);
				int pattern1 = nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN1);
				int pattern2 = nbtCompound.getInt(SnakeEggBlockEntity.NBT_KEY_PATTERN2);
				System.out.println("The egg has ps: " + pattern1 + ", " + pattern2 + ", and cs: " + color1 + ", " + color2 + " stored");
			}else {
				System.out.println("Compound was null");
			}
		}
		
		return blockState.onUse(world, player, hand, hitResult);
	}
	
	@Override
	@Nullable
	public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new SnakeEggBlockEntity(blockPos, blockState);
	}
	
	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState blockState, BlockEntityType<T> blockEntityType){
		return super.getTicker(world, blockState, blockEntityType);
	}
}
