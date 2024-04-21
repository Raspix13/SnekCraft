package com.raspix.snekcraft.blocks.terrariums;

import com.raspix.snekcraft.blocks.SnekBlockStateProperties;
import com.raspix.snekcraft.items.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class TerrariumBlock extends TransparentBlock {

    //public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    //public static final IntegerProperty GROUND_TYPE = IntegerProperty.create("ground_type", 0, 5);
    public static final IntegerProperty UP = SnekBlockStateProperties.UP;
    public static final IntegerProperty DOWN = SnekBlockStateProperties.DOWN;
    public static final IntegerProperty NORTH = SnekBlockStateProperties.NORTH;
    public static final IntegerProperty EAST = SnekBlockStateProperties.EAST;
    public static final IntegerProperty SOUTH = SnekBlockStateProperties.SOUTH;
    public static final IntegerProperty WEST = SnekBlockStateProperties.WEST;

    public static final BooleanProperty PATHFIND = BooleanProperty.create("pathfind");
    //public boolean isPathFindable;

    //public static DirectionProperty CORN_DIRS = DirectionProperty.create();
    /**public static final BooleanProperty NE_CORNER= BooleanProperty.create("ne_corner");
    public static final BooleanProperty NW_CORNER= BooleanProperty.create("nw_corner");
    public static final BooleanProperty SE_CORNER= BooleanProperty.create("se_corner");
    public static final BooleanProperty SW_CORNER= BooleanProperty.create("sw_corner");*/
    //public static final IntegerProperty NORTH_WEST = IntegerProperty.create("nw_corner", 0, 2);
   // public static final IntegerProperty SOUTH_EAST = IntegerProperty.create("se_corner", 0, 2);
    //public static final IntegerProperty SOUTH_WEST = IntegerProperty.create("sw_corner", 0, 2);

    private static final VoxelShape TOP_AABB = Block.box(0F, 15F, 0F, 16F, 16F, 16F);
    private static final VoxelShape BOTTOM_AABB = Block.box(0F, 0F, 0F, 16F, 2F, 16F);
    private static final VoxelShape NORTH_AABB = Block.box(0F, 0F, 0F, 16F, 16F, 1F);
    private static final VoxelShape SOUTH_AABB = Block.box(0F, 0F, 15F, 16F, 16F, 16F);
    private static final VoxelShape EAST_AABB = Block.box(15F, 0F, 0F, 16F, 16F, 16F);
    private static final VoxelShape WEST_AABB = Block.box(0F, 0F, 0F, 1F, 16F, 16F);

    public TerrariumBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, 0)
                .setValue(EAST, 0)
                .setValue(SOUTH, 0)
                .setValue(WEST, 0)
                .setValue(UP, 0)
                .setValue(DOWN, 0)
                //.setValue(GROUND_TYPE, 0)
                //.setValue(NORTH_EAST, 0)
                 /**.setValue(SOUTH_EAST, 0)
                .setValue(NORTH_WEST, 0)
                .setValue(SOUTH_WEST, 0)*/
        );
        //isPathFindable = true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, PATHFIND);//, GROUND_TYPE);//, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);//);//
        //builder.add(NE_CORNER, NW_CORNER, SE_CORNER, SW_CORNER);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level iblockreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState thisState = iblockreader.getBlockState(blockpos);
        FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos blockposN = blockpos.north();
        BlockPos blockposE = blockpos.east();
        BlockPos blockposS = blockpos.south();
        BlockPos blockposW = blockpos.west();
        BlockState blockstateN = iblockreader.getBlockState(blockposN);
        BlockState blockstateE = iblockreader.getBlockState(blockposE);
        BlockState blockstateS = iblockreader.getBlockState(blockposS);
        BlockState blockstateW = iblockreader.getBlockState(blockposW);
        BlockState blockstateNE = iblockreader.getBlockState(blockposN.east());
        BlockState blockstateNW = iblockreader.getBlockState(blockposN.west());
        BlockState blockstateSE = iblockreader.getBlockState(blockposS.east());
        BlockState blockstateSW = iblockreader.getBlockState(blockposS.west());
        return Objects.requireNonNull(super.getStateForPlacement(context))
                .setValue(NORTH, this.canFenceConnectTo(blockstateN, thisState, Direction.SOUTH, null))
                .setValue(EAST, this.canFenceConnectTo(blockstateE, thisState, Direction.WEST, null))
                .setValue(SOUTH, this.canFenceConnectTo(blockstateS, thisState, Direction.NORTH, null))
                .setValue(WEST, this.canFenceConnectTo(blockstateW, thisState, Direction.EAST, null))
                ;/**.setValue(NORTH_EAST, this.hasInnerCorner(blockstateNE, blockstateN, blockstateE))
                .setValue(NORTH_WEST, this.hasInnerCorner(blockstateNW, blockstateN, blockstateW))
                .setValue(SOUTH_EAST, this.hasInnerCorner(blockstateSE, blockstateS, blockstateE))
                .setValue(SOUTH_WEST, this.hasInnerCorner(blockstateSW, blockstateS, blockstateW));//*/
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState ans = updateBlockShape(state, facing, facingState, level, currentPos, facingPos).setValue(PATHFIND, updatePathfinding(state, level, currentPos));
        return ans;
        /**IntegerProperty connect = null;
        switch (facing) {
            case NORTH:
                connect = NORTH;
                break;
            case SOUTH:
                connect = SOUTH;
                break;
            case EAST:
                connect = EAST;
                break;
            case WEST:
                connect = WEST;
                break;
            case DOWN:
                connect = DOWN;
                break;
            default:
                connect = UP;
                break;
        }
        return state.setValue(connect, this.canFenceConnectTo(facingState, state, facing.getOpposite(), connect));*/
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
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            ItemStack heldItem = pPlayer.getItemInHand(pHand);
            if (heldItem.is(Items.STICK)) {
            //    System.out.println("pathfinding" + pState.getValue(PATHFIND));
            }
            if (heldItem.is(ItemInit.TERRARIUM_KEY.get())) {
                //System.out.println(pState);
                setSideEntrance(pState, pLevel, pPos, pPlayer, pHand, pHit);
                //updatePathfinding(pState, pLevel, pPos);
            }else {
                return InteractionResult.FAIL;
            }
            //boolean currentState = pState.getValue(CLICKED);
            //pLevel.setBlock(pPos, pState.setValue(CLICKED, !currentState), 3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    // determines the solidness of walls
    public void setSideEntrance(BlockState state, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit){
        Direction hitWall = pHit.getDirection();
        //System.out.println("hit dir is " + pHit.getDirection().getOpposite() + " player dir is: " + pPlayer.getDirection().toString());

        //if hit side is invisible (not open or closed) then get opposite direction?
        int sideValue = state.getValue(getDirStateFromDir(hitWall));
        if(sideValue == 1 || sideValue == 3){ //if the wall is invisible get the visible one
            hitWall = hitWall.getOpposite();
        }

        if(hitWall == null) {
            return;
        }else if (hitWall.equals(Direction.NORTH) && state.getValue(NORTH)!=1) {
            pLevel.setBlock(pPos, state.setValue(NORTH, state.getValue(NORTH)==2?0:2).setValue(PATHFIND, updatePathfinding(state, pLevel, pPos)), 3);
            //get sides
            //updateCornerOnChange(state, pLevel, hitWall, pPos);
        }else if(hitWall.equals(Direction.SOUTH) && state.getValue(SOUTH)!=1){
            pLevel.setBlock(pPos, state.setValue(SOUTH, state.getValue(SOUTH)==2?0:2).setValue(PATHFIND, updatePathfinding(state, pLevel, pPos)), 3);
        }else if(hitWall.equals(Direction.EAST) && state.getValue(EAST)!=1){
            pLevel.setBlock(pPos, state.setValue(EAST, state.getValue(EAST)==2?0:2).setValue(PATHFIND, updatePathfinding(state, pLevel, pPos)), 3);
        }else if(hitWall.equals(Direction.WEST) && state.getValue(WEST)!=1){
            pLevel.setBlock(pPos, state.setValue(WEST, state.getValue(WEST)==2?0:2).setValue(PATHFIND, updatePathfinding(state, pLevel, pPos)), 3);
        }else if(hitWall.equals(Direction.UP)&& state.getValue(UP)!=1){
            pLevel.setBlock(pPos, state.setValue(UP, state.getValue(UP)==2?0:2), 3);
        }else if(hitWall.equals(Direction.DOWN) && state.getValue(DOWN)!=1){
            pLevel.setBlock(pPos, state.setValue(DOWN, state.getValue(DOWN)==2?0:2), 3);
        }
        /**IntegerProperty tapDIr = SOUTH;
        pLevel.setBlock(pPos, state.setValue(tapDIr, state.getValue(tapDIr)==2?1:2), 3);*/
    }

    public void updateCornerOnChangeDelete(BlockState pState, Level pLevel, Direction wallChanged, BlockPos pPos){
        switch (wallChanged){
            // X U X
            // B A B
            // X O X
            case NORTH:
                //east and west
                BlockState eastState = pLevel.getBlockState(pPos.east());
                BlockState westPos = pLevel.getBlockState(pPos.west());
                //eastState.getBlock().updateShape(eastState, wallChanged.getOpposite(), null, pLevel, pPos.east(), );

                pLevel.setBlock(pPos.east(),
                        eastState.setValue(SOUTH, isWallConnectedTo(pLevel.getBlockState(pPos.east().south()), eastState, SOUTH, pLevel, Direction.SOUTH, pPos.east())), 3);
                break;
            case EAST:
                break;
            case SOUTH:
                break;
            case WEST:
                break;
            default:

        }
    }

    public IntegerProperty getDirStateFromDir(Direction dir){
        switch (dir){
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case EAST:
                return EAST;
            case WEST:
                return WEST;
            case UP:
                return UP;
            case DOWN:
                return DOWN;
            default:
                System.out.println("something went wrong");
                return NORTH;
        }
    }

    //for the 2 vertical directions states
    public int canFenceConnectTo(BlockState sideState, BlockState thisState, Direction facing, IntegerProperty prop) {
        if(sideState.getBlock() instanceof TerrariumBlock){ //if the block this side is facing is of same type

            //if stateRight is also instanceof this and statediag is not terrarium then is 3
            return 1;
        }else {
            if(thisState != null && prop != null && thisState.getValue(prop) == 2){ //keeps as 2 if already 2
                return 2;
            }else {
                return 0;
            }
        }
        //return state.getBlock() instanceof TerrariumBlock?1:0;
    }

    /**
     * For determining the wall states of the horizontal block directions
     * @param facingState the blockstate of the block adjacent to the main block in the wall direction
     * @param thisState the main block
     * //@param stateRight the blockstate of the block that is to the right of the direction of the wall being evaluated
     * //@param stateDiag the block that is diagonal to the main block and directly to the right of the sideState
     * @param prop the property of the direction
     * @return the state for the wall direction
     */
    public int isWallConnectedTo(BlockState facingState, BlockState thisState, IntegerProperty prop, LevelAccessor level, Direction facing, BlockPos currentPos){//BlockState stateRight, BlockState stateDiag, ){
        if(facingState.getBlock() instanceof TerrariumBlock){ //if the block this side is facing is of same type
            if(areSiliconeSidesSolid(level, facing, currentPos)){
                return 3; //silicone
            }
            /**if(stateRight.getBlock() instanceof TerrariumBlock && !(stateDiag.getBlock() instanceof TerrariumBlock) ){
                return 3;
            }*/
            return 1; //empty
        }else {
            if(thisState != null && prop != null && thisState.getValue(prop) == 2){ //keeps as 2 if already 2
                return 2; //invisible
            }else {
                return 0; //there
            }
        }
    }

    public boolean areSiliconeSidesSolid(LevelAccessor level, Direction facing, BlockPos currentPos){
        BlockState facingState;
        BlockState clockwiseState;
        BlockState diagState;
        IntegerProperty facingWallDir;
        IntegerProperty clockwiseWallDir;
        switch (facing){
            case NORTH:
                facingState = level.getBlockState(currentPos.north());
                clockwiseState = level.getBlockState(currentPos.east());
                diagState = level.getBlockState(currentPos.north().east());
                facingWallDir = EAST;
                clockwiseWallDir = NORTH;
                break;
            case EAST:
                facingState = level.getBlockState(currentPos.east());
                clockwiseState = level.getBlockState(currentPos.south());
                diagState = level.getBlockState(currentPos.east().south());
                facingWallDir = SOUTH;
                clockwiseWallDir = EAST;
                break;
            case SOUTH:
                facingState = level.getBlockState(currentPos.south());
                clockwiseState = level.getBlockState(currentPos.west());
                diagState = level.getBlockState(currentPos.south().west());
                facingWallDir = WEST;
                clockwiseWallDir = SOUTH;
                break;
            case WEST:
                facingState = level.getBlockState(currentPos.west());
                clockwiseState = level.getBlockState(currentPos.north());
                diagState = level.getBlockState(currentPos.west().north());
                facingWallDir = NORTH;
                clockwiseWallDir = WEST;
                break;
            default:
                return false;
        }
        /**if(level.isClientSide()){
            System.out.println("Updating " + currentPos.toString() + " facing " + facingWallDir.toString() + " at " + facingState.getBlock().toString());
        }*/

        if(facingState.getBlock() instanceof TerrariumBlock && clockwiseState.getBlock() instanceof TerrariumBlock &&
        !(diagState.getBlock() instanceof TerrariumBlock) && !(facingState.getValue(facingWallDir) == 2 && clockwiseState.getValue(clockwiseWallDir) == 2)){
            return true;
        }
        return false;
    }

    public int hasInnerCorner(BlockState stateDiag, BlockState stateSide1, BlockState stateSide2){
        if(stateDiag.getBlock() instanceof TerrariumBlock || !(stateSide1.getBlock() instanceof TerrariumBlock) || !(stateSide2.getBlock() instanceof TerrariumBlock)){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        //boolean pathFindable = true;
        VoxelShape shape1 = Block.box(0, 0, 0, 0, 0, 0);
        if (state.getBlock() instanceof TerrariumBlock) {
            if (state.getValue(UP) == 0) {
                shape1 = Shapes.join(shape1, TOP_AABB, BooleanOp.OR);
            }
            if (state.getValue(DOWN) == 0) {
                shape1 = Shapes.join(shape1, BOTTOM_AABB, BooleanOp.OR);
            }
            if (state.getValue(NORTH) == 0) {
                shape1 = Shapes.join(shape1, NORTH_AABB, BooleanOp.OR);
                //pathFindable = false;
            }
            if (state.getValue(SOUTH) == 0) {
                shape1 = Shapes.join(shape1, SOUTH_AABB, BooleanOp.OR);
                //pathFindable = false;
            }
            if (state.getValue(EAST) == 0) {
                shape1 = Shapes.join(shape1, EAST_AABB, BooleanOp.OR);
                //pathFindable = false;
            }
            if (state.getValue(WEST) == 0) {
                shape1 = Shapes.join(shape1, WEST_AABB, BooleanOp.OR);
                //pathFindable = false;
            }
        }
        //this.isPathFindable = pathFindable;
        return shape1;
    }

    // This is the black shape outline that determines where you can click
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape1 = Block.box(0, 0, 0, 0, 0, 0);
        boolean hasItem = pContext.isHoldingItem(ItemInit.TERRARIUM_KEY.get());
        if (state.getBlock() instanceof TerrariumBlock) {
            if (state.getValue(UP) == 0 || (state.getValue(UP) == 2 && hasItem)) {
                shape1 = Shapes.join(shape1, TOP_AABB, BooleanOp.OR);
            }
            if (state.getValue(DOWN) == 0 || (state.getValue(DOWN) == 2 && hasItem)) {
                shape1 = Shapes.join(shape1, BOTTOM_AABB, BooleanOp.OR);
            }
            if (state.getValue(NORTH) == 0 || (state.getValue(NORTH) == 2 && hasItem)) {
                shape1 = Shapes.join(shape1, NORTH_AABB, BooleanOp.OR);
            }
            if (state.getValue(SOUTH) == 0 || (state.getValue(SOUTH) == 2 && hasItem)) {
                shape1 = Shapes.join(shape1, SOUTH_AABB, BooleanOp.OR);
            }
            if (state.getValue(WEST) == 0 || (state.getValue(WEST) == 2 && hasItem)) {
                shape1 = Shapes.join(shape1, WEST_AABB, BooleanOp.OR);
            }
            if (state.getValue(EAST) == 0 || (state.getValue(EAST) == 2 && hasItem)) {
                shape1 = Shapes.join(shape1, EAST_AABB, BooleanOp.OR);
            }
        }
        return shape1;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return false;
    }

    protected boolean isEntityInsideContent(BlockState pState, BlockPos pPos, Entity pEntity) {
        return pEntity.getY() < (double)pPos.getY() + this.getContentHeight(pState) && pEntity.getBoundingBox().maxY > (double)pPos.getY() + 0.25D;
    }

    protected double getContentHeight(BlockState pState) {
        return 0.0D;
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        if (pType == PathComputationType.WATER){
            return super.isPathfindable(pState, pLevel, pPos, pType);//pLevel.getFluidState(pPos).is(FluidTags.WATER);
        }
        return pState.getValue(PATHFIND);
    }

}
