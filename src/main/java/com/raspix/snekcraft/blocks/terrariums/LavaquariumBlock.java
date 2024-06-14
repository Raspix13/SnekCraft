package com.raspix.snekcraft.blocks.terrariums;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class LavaquariumBlock extends TerrariumBlock implements SimpleLavaLoggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public LavaquariumBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, Boolean.valueOf(true)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    // returns the fluid block type to be logged in the block (ie what the liquid the block is rendered in)
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.LAVA.getSource(false) : super.getFluidState(pState);
    }
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public void entityInside(BlockState p_153506_, Level p_153507_, BlockPos p_153508_, Entity p_153509_) {
        if (this.isEntityInsideContent(p_153506_, p_153508_, p_153509_)) {
            p_153509_.lavaHurt();
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(WATERLOGGED, true);//Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    protected double getContentHeight(BlockState p_153500_) {
        return 0.9375D;
    }

    public boolean isFull(BlockState p_153511_) {
        return true;
    }

    @Override
    public void destroy(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        if (!pLevel.isClientSide() && pState.getValue(BlockStateProperties.WATERLOGGED)) { // also destroys the water
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        }
        super.destroy(pLevel, pPos, pState);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return pFluid == Fluids.LAVA;
    }


    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if (!pLevel.isClientSide() && pFluidState.getType() == Fluids.LAVA) {
            pLevel.setBlock(pPos, pState.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ItemStack pickupBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        if (pState.getValue(BlockStateProperties.WATERLOGGED)) {
            return new ItemStack(Items.LAVA_BUCKET);
        } else {
            return ItemStack.EMPTY;
        }
    }
}
