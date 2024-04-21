package com.raspix.snekcraft.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MediumHideBlock extends HorizontalDirectionalBlock {

    public static final EnumProperty<HidePart> PART = EnumProperty.create("part", HidePart.class);

    protected static final VoxelShape WALL_N = Block.box(0.0D, 0.0D, 12.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape WALL_S = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 4.0D);
    protected static final VoxelShape WALL_E = Block.box(12.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape WALL_W = Block.box(0.0D, 0.0D, 0.0D, 4.0D, 8.0D, 16.0D);
    protected static final VoxelShape BASE = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public MediumHideBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PART, HidePart.ENTERANCE));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }


    @Nullable
    public static Direction getBedOrientation(BlockGetter pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return blockstate.getBlock() instanceof BedBlock ? blockstate.getValue(FACING) : null;
    }


    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    /**public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pFacing == getNeighbourDirection(pState.getValue(PART), pState.getValue(FACING))) {
            return pFacingState.is(this) && pFacingState.getValue(PART) != pState.getValue(PART) ? pState.setValue(OCCUPIED, pFacingState.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }
    }*/

    /**
     * Given a bed part and the direction it's facing, find the direction to move to get the other bed part
     */
    private static Direction getNeighbourDirection(HidePart pPart, Direction pDirection) {
        return pPart == HidePart.ENTERANCE ? pDirection : pDirection.getOpposite();
    }

    /**private static Direction getNeighbourDirection(HidePart pPart, Direction pDirection) {
        return pPart == HidePart.ENTERANCE ? pDirection : pDirection.getOpposite();
    }*/

    /**
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
     * this block
     *
     * @return
     */
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide) {
            HidePart hidepart = pState.getValue(PART);
            if (hidepart != HidePart.ENTERANCE){
                originDestroyHelper(pLevel, pPos, pState, pPlayer, 0);
            } else if (hidepart == HidePart.ENTERANCE){
                Direction dir = pState.getValue(FACING);//getNeighbourDirection(hidepart, pState.getValue(FACING));
                BlockPos blockpos = pPos.relative(dir.getClockWise());
                BlockPos blockpos2 = pPos.relative(dir);
                BlockPos blockpos3 = blockpos.relative(dir);
                removePartBlock(pLevel, blockpos, pPlayer);
                removePartBlock(pLevel, blockpos2, pPlayer);
                removePartBlock(pLevel, blockpos3, pPlayer);
                /**BlockState blockstate = pLevel.getBlockState(blockpos);
                if (blockstate.is(this) && blockstate.getValue(PART) == HidePart.EXTRA) {
                    pLevel.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
                }*/
            }
        }

        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
        return pState;
    }

    public void originDestroyHelper(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer, int iteration) {
        if(pState.is(this)) {
            HidePart hidepart = pState.getValue(PART);
            if (hidepart != HidePart.ENTERANCE && iteration <= 2) { // make sure loop is not infinite
                BlockPos newPos = pPos.relative(pState.getValue(FACING));
                BlockState newState = pLevel.getBlockState(newPos);
                System.out.println("Checked for destroy failed: " + pPos.toShortString() + ", moving " + pState.getValue(FACING) + " to " + newPos.toShortString());
                originDestroyHelper(pLevel, newPos, newState, pPlayer, iteration + 1);

            } else if (hidepart == HidePart.ENTERANCE) {
                System.out.println("Found Entrance");
                playerWillDestroyFinal(pLevel, pPos, pState, pPlayer);
            }
        }else {
            System.out.println("originDestroyHelper ran into a non-MedHide block, you suck at coding");
        }
    }

    public void playerWillDestroyFinal(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide) {
            HidePart hidepart = pState.getValue(PART);
            if (hidepart == HidePart.ENTERANCE){
                Direction dir = pState.getValue(FACING);
                BlockPos blockpos = pPos.relative(dir.getClockWise());
                BlockPos blockpos2 = pPos.relative(dir);
                BlockPos blockpos3 = blockpos.relative(dir);
                removePartBlock(pLevel, blockpos, pPlayer);
                removePartBlock(pLevel, blockpos2, pPlayer);
                removePartBlock(pLevel, blockpos3, pPlayer);
                removePartBlock(pLevel, pPos, pPlayer);
                /**BlockState blockstate = pLevel.getBlockState(blockpos);
                 if (blockstate.is(this) && blockstate.getValue(PART) == HidePart.EXTRA) {
                 pLevel.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                 pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
                 }*/
            }
        }
    }

    private void removePartBlock(Level pLevel, BlockPos blockpos, Player pPlayer){
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (blockstate.is(this)) {
            pLevel.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
            pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
        }
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);;//getConnectedDirection(pState).getOpposite();
        VoxelShape shape = Block.box(0, 0, 0, 0, 0, 0);
        shape = Shapes.join(shape, BASE, BooleanOp.OR);
        HidePart hidepart = pState.getValue(PART);
        if(hidepart == HidePart.ENTERANCE) {
            switch (direction) {
                case NORTH:
                    shape = Shapes.join(shape, WALL_W, BooleanOp.OR);
                    break;
                case SOUTH:
                    shape = Shapes.join(shape, WALL_E, BooleanOp.OR);
                    break;
                case WEST:
                    shape = Shapes.join(shape, WALL_N, BooleanOp.OR);
                    break;
                default:
                    shape = Shapes.join(shape, WALL_S, BooleanOp.OR);
            }
        }else if(hidepart == HidePart.ENTERANCE2){
            switch(direction) {
                case NORTH:
                    shape = Shapes.join(shape, WALL_E, BooleanOp.OR);
                    break;
                case SOUTH:
                    shape = Shapes.join(shape, WALL_W, BooleanOp.OR);
                    break;
                case WEST:
                    shape = Shapes.join(shape, WALL_S, BooleanOp.OR);
                    break;
                default:
                    shape = Shapes.join(shape, WALL_N, BooleanOp.OR);
            }
        }else if(hidepart == HidePart.SIDE || hidepart == HidePart.SIDE2){
            switch(direction) {
                case NORTH:
                    shape = Shapes.join(shape, WALL_N, BooleanOp.OR);
                    break;
                case SOUTH:
                    shape = Shapes.join(shape, WALL_S, BooleanOp.OR);
                    break;
                case WEST:
                    shape = Shapes.join(shape, WALL_E, BooleanOp.OR);
                    break;
                default:
                    shape = Shapes.join(shape, WALL_W, BooleanOp.OR);
            }
        }
        return shape;
    }

    public static Direction getConnectedDirection(BlockState pState) {
        Direction direction = pState.getValue(FACING);
        return pState.getValue(PART) != HidePart.ENTERANCE ? direction.getOpposite() : direction;
    }

    /**public static DoubleBlockCombiner.BlockType getBlockType(BlockState pState) {
        HidePart hidepart = pState.getValue(PART);
        return hidepart == HidePart.EXTRA ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
    }*/

    /**public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }*/

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, PART);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getHorizontalDirection();
        BlockPos blockpos = pContext.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction.getClockWise());
        BlockPos blockpos2 = blockpos.relative(direction);
        BlockPos blockpos3 = blockpos1.relative(direction);
        Level level = pContext.getLevel();
        return level.getBlockState(blockpos1).canBeReplaced(pContext) && level.getWorldBorder().isWithinBounds(blockpos1) &&
                level.getBlockState(blockpos2).canBeReplaced(pContext) && level.getWorldBorder().isWithinBounds(blockpos2) &&
                level.getBlockState(blockpos3).canBeReplaced(pContext) && level.getWorldBorder().isWithinBounds(blockpos3)
                ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    /**
     * Called by BlockItem after this block has been placed.
     */
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!pLevel.isClientSide && pState.getValue(PART) == HidePart.ENTERANCE) {
            BlockPos blockpos = pPos.relative(pState.getValue(FACING).getClockWise());
            BlockPos blockpos2 = pPos.relative(pState.getValue(FACING));
            BlockPos blockpos3 = blockpos.relative(pState.getValue(FACING));
            Direction dir = pState.getValue(FACING);
            /**pLevel.setBlock(blockpos, pState.setValue(PART, HidePart.EXTRA), 3);
            pLevel.blockUpdated(pPos, Blocks.AIR);
            pState.updateNeighbourShapes(pLevel, pPos, 3);*/
            addPartBlock(pLevel, blockpos, pState, dir.getClockWise().getOpposite(), HidePart.SIDE);
            addPartBlock(pLevel, blockpos2, pState, dir.getOpposite(), HidePart.ENTERANCE2);
            addPartBlock(pLevel, blockpos3, pState, dir.getClockWise().getOpposite(), HidePart.SIDE2);
            /**System.out.println("Testing directions: " + pState.getValue(FACING).toString() + ", " +
                    (pLevel.getBlockState(blockpos)).getValue(FACING).toString() + ", " +
                    (pLevel.getBlockState(blockpos2)).getValue(FACING).toString() + ", " +
                    (pLevel.getBlockState(blockpos3)).getValue(FACING).toString());*/

        }

    }

    private void addPartBlock(Level pLevel, BlockPos blockpos, BlockState pState, Direction dir, HidePart type){
        pLevel.setBlock(blockpos, pState.setValue(PART, type).setValue(FACING, dir), 3);
        pLevel.blockUpdated(blockpos, Blocks.AIR);
        pState.updateNeighbourShapes(pLevel, blockpos, 3);

    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    //check to make sure all sections are checked on place
    //check to make sure all parts fully destroy
    //

}
