package com.raspix.snekcraft.blocks.terrariums;

import com.raspix.snekcraft.blocks.SnekBlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import javax.annotation.Nullable;

public class MultiBlockHide extends GlassBlock {

    public final EnumProperty<SnekBlockStateProperties.MergeBlockState> FORM = SnekBlockStateProperties.FORM;
    public final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty PATHFIND = BooleanProperty.create("pathfind");


    public MultiBlockHide(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                        .setValue(FORM, SnekBlockStateProperties.MergeBlockState.ALONE)
                        .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(FORM, FACING, PATHFIND);//, GROUND_TYPE);//, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);//);//
        //builder.add(NE_CORNER, NW_CORNER, SE_CORNER, SW_CORNER);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return null;
    }

    /**public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState ans = updateBlockShape(state, facing, facingState, level, currentPos, facingPos).setValue(PATHFIND, updatePathfinding(state, level, currentPos));
        return ans;
    }

    public boolean updatePathfinding(BlockState state, LevelAccessor level, BlockPos pos){
        if (state.getValue(NORTH) == 0 || state.getValue(EAST) == 0 || state.getValue(SOUTH) == 0 || state.getValue(WEST) == 0) {
            //  System.out.println("set (" + pos.toShortString() + ") to false");
            //level.setBlock(pos, state.setValue(PATHFIND, false), 3);
            return false;
            //this.isPathFindable = true;
        }else {
            //  System.out.println("set (" + pos.toShortString() + ") to true");
            //level.setBlock(pos, state.setValue(PATHFIND, true), 3);
            return true;
            //this.isPathFindable = false;
        }
    }

    public BlockState updateBlockShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos){
        switch (facing){
            case NORTH:
                return state.setValue(NORTH, this.isWallConnectedTo(facingState, state, NORTH, level, Direction.NORTH, currentPos))
                        .setValue(WEST, this.isWallConnectedTo(level.getBlockState(currentPos.west()), state, WEST, level, Direction.WEST, currentPos));
            case SOUTH:
                return state.setValue(SOUTH, this.isWallConnectedTo(facingState, state, SOUTH, level, Direction.SOUTH, currentPos))
                        .setValue(EAST, this.isWallConnectedTo(level.getBlockState(currentPos.east()), state, EAST, level, Direction.EAST, currentPos));
            case EAST:
                return state.setValue(EAST, this.isWallConnectedTo(facingState, state, EAST, level, Direction.EAST, currentPos))
                        .setValue(NORTH, this.isWallConnectedTo(level.getBlockState(currentPos.north()), state, NORTH, level, Direction.NORTH, currentPos));
            case WEST:
                return state.setValue(WEST, this.isWallConnectedTo(facingState, state, WEST, level, Direction.WEST, currentPos))
                        .setValue(SOUTH, this.isWallConnectedTo(level.getBlockState(currentPos.south()), state, SOUTH, level, Direction.SOUTH, currentPos ));
            //return state.setValue(WEST, this.isWallConnectedTo(facingState, state, level.getBlockState(currentPos.north()), level.getBlockState(facingPos.north()), WEST))
            //      .setValue(SOUTH, this.isWallConnectedTo(level.getBlockState(currentPos.south()), state, level.getBlockState(currentPos.west()), level.getBlockState(currentPos.south().west()), SOUTH));
            case DOWN:
                return state.setValue(DOWN, this.canFenceConnectTo(facingState, state, facing.getOpposite(), DOWN));
            default: //up
                return state.setValue(UP, this.canFenceConnectTo(facingState, state, facing.getOpposite(), UP));
        }
    }*/
}
