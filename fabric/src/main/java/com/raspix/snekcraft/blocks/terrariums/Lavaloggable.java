package com.raspix.snekcraft.blocks.terrariums;

import java.util.Optional;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public interface Lavaloggable extends FluidDrainable, FluidFillable {
	
	@Override
	default boolean canFillWithFluid(BlockView blockView, BlockPos blockPos, BlockState blockState, Fluid fluid) {
		return !blockState.get(Properties.WATERLOGGED) && fluid == Fluids.LAVA;
	}
	
	@Override
	default boolean tryFillWithFluid(WorldAccess worldAccess, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
		if(!blockState.get(Properties.WATERLOGGED) && fluidState.getFluid() == Fluids.LAVA) {
			if(!worldAccess.isClient()) {
				worldAccess.setBlockState(blockPos, blockState.with(Properties.WATERLOGGED, Boolean.valueOf(true)), 3);
				worldAccess.scheduleFluidTick(blockPos, fluidState.getFluid(), fluidState.getFluid().getTickRate(worldAccess));
			}
			
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	default ItemStack tryDrainFluid(WorldAccess worldAccess, BlockPos blockPos, BlockState blockState) {
		if(blockState.get(Properties.WATERLOGGED)) {
			worldAccess.setBlockState(blockPos, blockState.with(Properties.WATERLOGGED, Boolean.valueOf(false)), 3);
			if(!blockState.canPlaceAt(worldAccess, blockPos)) {
				worldAccess.breakBlock(blockPos, true);
			}
			
			return new ItemStack(Items.LAVA_BUCKET);
		}else {
			return ItemStack.EMPTY;
		}
	}
	
	@Override
	default Optional<SoundEvent> getBucketFillSound(){
		return Fluids.LAVA.getBucketFillSound();
	}
}
