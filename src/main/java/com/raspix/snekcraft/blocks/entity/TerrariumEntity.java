package com.raspix.snekcraft.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class TerrariumEntity extends BlockEntity {

    public static final BooleanProperty NE_CORNER= BooleanProperty.create("ne_corner");
    public static final BooleanProperty NW_CORNER= BooleanProperty.create("nw_corner");
    public static final BooleanProperty SE_CORNER= BooleanProperty.create("se_corner");
    public static final BooleanProperty SW_CORNER= BooleanProperty.create("sw_corner");

    public TerrariumEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }
}
