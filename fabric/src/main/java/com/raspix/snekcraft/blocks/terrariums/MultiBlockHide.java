package com.raspix.snekcraft.blocks.terrariums;

import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.blocks.SnekBlockStateProperties;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class MultiBlockHide extends GlassBlock {
	public final EnumProperty<SnekBlockStateProperties.MergeBlockState> FORM = SnekBlockStateProperties.FORM;
	public final DirectionProperty FACING = Properties.FACING;
	public static final BooleanProperty PATHFIND = BooleanProperty.of("pathfind");
	
	public MultiBlockHide(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(FORM, SnekBlockStateProperties.MergeBlockState.ALONE).with(FACING, Direction.NORTH));
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FORM, FACING, PATHFIND);
	}
	
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext context) {
		return null;
	}
}
