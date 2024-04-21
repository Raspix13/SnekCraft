package com.raspix.snekcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TunnelHideBlock extends Block {


    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape TOP_AABB = Block.box(0F, 8F, 0F, 16F, 12F, 16F);
    private static final VoxelShape NORTH_AABB = Block.box(0F, 0F, 0F, 16F, 8F, 2F);
    private static final VoxelShape WEST_AABB = Block.box(0F, 0F, 0F, 2F, 8F, 16F);
    private static final VoxelShape EAST_AABB = Block.box(14F, 0F, 0F, 16F, 8F, 16F);
    private static final VoxelShape SOUTH_AABB = Block.box(0F, 0F, 14F, 16F, 8F, 16F);

    public TunnelHideBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Block.box(0, 0, 0, 0, 0, 0);
        shape = Shapes.join(shape, TOP_AABB, BooleanOp.OR);
        switch (state.getValue(FACING)){
            case EAST:
            case WEST:
                shape = Shapes.join(shape, SOUTH_AABB, BooleanOp.OR);
                shape = Shapes.join(shape, NORTH_AABB, BooleanOp.OR);
                break;
            case SOUTH:
            default: //NORTH
                shape = Shapes.join(shape, EAST_AABB, BooleanOp.OR);
                shape = Shapes.join(shape, WEST_AABB, BooleanOp.OR);
                break;
        }

        return shape;
    }

    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = Block.box(0, 0, 0, 0, 0, 0);
        return getCollisionShape(state, pLevel, pPos, pContext);
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return false;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.PUSH_ONLY;
    }

}
