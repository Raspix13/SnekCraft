package com.raspix.snekcraft.blocks.terrariums;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class AquariumBlock extends TerrariumBlock implements Waterloggable {
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED; // Why is WATERLOGGED and Properties.WATERLOGGED used interchangeably?
	
	public AquariumBlock(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(WATERLOGGED, Boolean.valueOf(true)));
	}
	
	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos blockPos, Entity entity) {
		if(!world.isClient() && entity.isOnFire() && isEntityInsideContent(blockState, blockPos, entity)) {
			entity.extinguish();
		}
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
		super.appendProperties(builder);
		builder.add(Properties.WATERLOGGED);
	}
	
	@Override
	public FluidState getFluidState(BlockState blockState) {
		return blockState.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : blockState.getFluidState();
	}
	
	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext context) {
		return super.getPlacementState(context).with(WATERLOGGED, true);
	}
	
	@Override
	public void onBroken(WorldAccess worldAccess, BlockPos blockPos, BlockState blockState) {
		if(!worldAccess.isClient() && blockState.get(Properties.WATERLOGGED)) {
			worldAccess.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 3);
		}
		super.onBroken(worldAccess, blockPos, blockState);
	}
	
	@Override
	public boolean tryFillWithFluid(WorldAccess worldAccess, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
		if(!worldAccess.isClient() && fluidState.getFluid() == Fluids.WATER) {
			worldAccess.setBlockState(blockPos, blockState.with(WATERLOGGED, Boolean.valueOf(true)), 3);
			return true;
		}
		return false;
	}
	
	@Override
	public ItemStack tryDrainFluid(WorldAccess worldAcces, BlockPos blockPos, BlockState blockState) {
		if(blockState.get(Properties.WATERLOGGED)) {
			return new ItemStack(Items.WATER_BUCKET);
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean canFillWithFluid(BlockView blockView, BlockPos blockPos, BlockState blockState, Fluid fluid) {
		return fluid == Fluids.WATER;
	}
}
