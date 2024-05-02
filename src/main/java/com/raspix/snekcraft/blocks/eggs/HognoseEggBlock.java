package com.raspix.snekcraft.blocks.eggs;

import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.entity.hognose.HognoseEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class HognoseEggBlock extends SnakeEggBlock{
    /**public static final int MAX_HATCH_LEVEL = 2;
    public static final int MIN_EGGS = 1;
    public static final int MAX_EGGS = 4;

    private static final int normalColor = 0;
    private static final int albinoColor = 1;
    private static final int axanthicColor = 2;
    private static final int arcticColor = 3;
    private static final int snowColor = 4;
    private static final int superArcticColor = 5;
    private static final int subZeroColor = 6;
    private static final int yetiColor = 7;
    private static final int rainbowColor = 8;
    protected final Random random = new Random();
    private static final VoxelShape ONE_EGG_AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);
    private static final VoxelShape MULTIPLE_EGGS_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    public static final IntegerProperty EGGS = BlockStateProperties.EGGS;
    public static final IntegerProperty COLOR = IntegerProperty.create("color", 0, 8);
    public static final IntegerProperty PATTERN = IntegerProperty.create("pattern", 0, 2);
    public static final IntegerProperty COLOR_P2 = IntegerProperty.create("color_p2", 0, 8);
    public static final IntegerProperty PATTERN_P2 = IntegerProperty.create("pattern_p2", 0, 2);*/

    public HognoseEggBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)).setValue(EGGS, Integer.valueOf(1)));
    }

    @Override
    public EntityType GetSnakeType() {
        return ModEntityTypes.HOGNOSE.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
    }


    @Override
    public int getOffspringColor(CompoundTag compoundTag) {
        return HognoseEntity.colorGenetics
                [Math.min(Math.max(compoundTag.getInt("color"), 0), HognoseEntity.GetMaxColor())]
                [Math.min(Math.max(compoundTag.getInt("color_p2"), 0), HognoseEntity.GetMaxColor())]
                .GetGene(this.random.nextInt(SnakeBase.BREEDING_RANGE));
    }

    @Override
    public int getOffspringPattern(CompoundTag compoundTag) {
        return HognoseEntity.patternGenetics
                [Math.min(Math.max(compoundTag.getInt("pattern"), 0), HognoseEntity.GetMaxPattern())]
                [Math.min(Math.max(compoundTag.getInt("pattern_p2"), 0), HognoseEntity.GetMaxPattern())]
                .GetGene(this.random.nextInt(SnakeBase.BREEDING_RANGE));
    }

    /**public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        //this.destroyEgg(pLevel, pState, pPos, pEntity, 100);
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (!(pEntity instanceof Zombie)) {
            //this.destroyEgg(pLevel, pState, pPos, pEntity, 3);
        }

        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }

    private void destroyEgg(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, int pChance) {
        if (this.canDestroyEgg(pLevel, pEntity)) {
            if (!pLevel.isClientSide && pLevel.random.nextInt(pChance) == 0 && pState.is(BlockInit.SNAKE_EGG.get())) {
                this.decreaseEggs(pLevel, pPos, pState);
            }

        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        /**if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
         System.out.println("Parents(c,p): (" + pState.getValue(COLOR) + ", " + pState.getValue(PATTERN) + "), & (" + pState.getValue(COLOR_P2) + ", " + pState.getValue(PATTERN_P2) + ")");
         String genOffspringC = "Colors (";
         String genOffspringP = "Patterns (";
         for(int i = 0; i<10; i++){
         genOffspringC += getOffspringColor(pState) + ", ";
         genOffspringP += getOffspringPattern(pState) + ", ";
         }
         genOffspringC += ")";
         genOffspringP += ")";
         System.out.println("\t" + genOffspringC);
         System.out.println("\t" + genOffspringP);
         }*/
        /**return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    private void decreaseEggs(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
        int i = pState.getValue(EGGS);
        if (i <= 1) {
            pLevel.destroyBlock(pPos, false);
        } else {
            pLevel.setBlock(pPos, pState.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            pLevel.levelEvent(2001, pPos, Block.getId(pState));
        }

    }

    /**
     * Performs a random tick on a block.
     */
    /**public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (this.shouldUpdateHatchLevel(pLevel) && onSand(pLevel, pPos)) {
            int i = pState.getValue(HATCH);
            if (i < 2) {
                pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.setBlock(pPos, pState.setValue(HATCH, Integer.valueOf(i + 1)), 2);
            } else {
                pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.removeBlock(pPos, false);

                for(int j = 0; j < pState.getValue(EGGS); ++j) {
                    pLevel.levelEvent(2001, pPos, Block.getId(pState));
                    HognoseEntity snake = ModEntityTypes.HOGNOSE.get().create(pLevel);
                    snake.setAge(-24000);
                    snake.moveTo((double)pPos.getX() + 0.3D + (double)j * 0.2D, (double)pPos.getY(), (double)pPos.getZ() + 0.3D, 0.0F, 0.0F);
                    snake.setColor(getOffspringColor(pState));
                    snake.setPattern(getOffspringPattern(pState));
                    pLevel.addFreshEntity(snake);
                }
            }
        }
    }

    public static boolean onSand(BlockGetter pLevel, BlockPos pPos) {
        return isSand(pLevel, pPos.below());
    }

    public static boolean isSand(BlockGetter pReader, BlockPos pPos) {
        return pReader.getBlockState(pPos).is(BlockTags.DIRT) || pReader.getBlockState(pPos).is(BlockTags.SAND);
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (onSand(pLevel, pPos) && !pLevel.isClientSide) {
            pLevel.levelEvent(2005, pPos, 0);
        }
    }

    private boolean shouldUpdateHatchLevel(Level pLevel) {
        float f = pLevel.getTimeOfDay(1.0F);
        if ((double)f < 0.69D && (double)f > 0.65D) {
            return true;
        } else {
            return pLevel.random.nextInt(4) == 0;
        }
    }

    /**
     * Called after a player has successfully harvested this block. This method will only be called if the player has
     * used the correct tool and drops should be spawned.
     */
    /**public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
        this.decreaseEggs(pLevel, pPos, pState);
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return !pUseContext.isSecondaryUseActive() && pUseContext.getItemInHand().is(this.asItem()) && pState.getValue(EGGS) < 4 ? true : super.canBeReplaced(pState, pUseContext);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(4, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(pContext);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(EGGS) > 1 ? MULTIPLE_EGGS_AABB : ONE_EGG_AABB;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HATCH, EGGS, COLOR, PATTERN, COLOR_P2, PATTERN_P2);
    }

    private boolean canDestroyEgg(Level pLevel, Entity pEntity) {
        return pEntity instanceof Player;
    }

    public void setParentTraits(int p1Color, int p1Pattern, int p2Color, int p2Pattern, Level level, BlockPos pos){
        System.out.println("setting traits parent 1:" + p1Color + "c & " + p1Pattern + "p and parent 2: " + p2Color + "c & " + p2Pattern);
        level.setBlock(pos, level.getBlockState(pos).setValue(COLOR, p1Color).setValue(PATTERN, p1Pattern).setValue(COLOR_P2, p2Color).setValue(PATTERN_P2, p2Pattern), 3);
    }*/

    //colors: 0=brown, 1=orange, 2=gray, 3=black,
    //colors: 0=normal, 1=ALBINO, 2=arctic/snow, 3=axanthic, 4=rainbow
    /**public int getOffspringColor(BlockState state){
        int color = 0;
        int randColor = this.random.nextInt(20);
        if(!(state.getBlock() instanceof SnakeEggBlock)) {
            //System.out.println("block is not egg");
            return 0;
        }
        int p1Color = state.getValue(COLOR);
        int p2Color = state.getValue(COLOR_P2);


        if(p1Color == rainbowColor || p2Color == rainbowColor){ // if one is rainbow
            if(randColor == 0){
                color = rainbowColor;
            }else {
                color = Math.min(p1Color, p2Color);
            }
        }
        else if(p1Color == normalColor || p2Color == normalColor){ // if one is normal
            if(p1Color == normalColor && p2Color == normalColor){
                if(randColor < 2){
                    color = albinoColor;
                }else if(randColor < 4){
                    color = axanthicColor;
                }else if(randColor < 6){
                    color = arcticColor;
                }else{
                    color = normalColor;
                }
            }else if(p1Color == albinoColor || p2Color == albinoColor){
                if(randColor < 5){
                    color = albinoColor;
                }else {
                    color = normalColor;
                }
            }else if(p1Color == axanthicColor || p2Color == axanthicColor){
                if(randColor < 5){
                    color = axanthicColor;
                }else {
                    color = normalColor;
                }
            }else if(p1Color == snowColor || p2Color == snowColor){
                color = normalColor;
            }else if(p1Color == superArcticColor || p2Color == superArcticColor){
                color = arcticColor;
            }else {
                if(randColor < 10){
                    color = arcticColor;
                }else {
                    color = normalColor;
                }
            }
        }
        else if(p1Color == albinoColor || p2Color == albinoColor){ // if one is albino
            if(p1Color == albinoColor && p2Color == albinoColor){
                color = albinoColor;
            }else if (p1Color == axanthicColor || p2Color == axanthicColor){
                color = snowColor;
            }else if (p1Color == arcticColor || p2Color == arcticColor){
                if(randColor < 10){
                    color = normalColor;
                }else {
                    color = subZeroColor;
                }
            }else if (p1Color == snowColor || p2Color == snowColor){
                if(randColor < 10){
                    color = albinoColor;
                }else {
                    color = snowColor;
                }
            }else if (p1Color == yetiColor || p2Color == yetiColor){
                if(randColor < 10){
                    color = yetiColor;
                }else {
                    color = subZeroColor;
                }
            }else {
                color = subZeroColor;
            }
        }
        else if(p1Color == axanthicColor || p2Color == axanthicColor){ // if one is axanthic
            if(p1Color == axanthicColor && p2Color == axanthicColor) { //one is axanthic
                color = axanthicColor;
            }else if(p1Color == arcticColor || p2Color == arcticColor) {
                if(randColor < 5){
                    color = arcticColor;
                }else if(randColor < 10){
                    color = axanthicColor;
                }else {
                    color = normalColor;
                }
            }else if(p1Color == snowColor || p2Color == snowColor) {
                if(randColor < 10){
                    color = snowColor;
                }else {
                    color = axanthicColor;
                }
            }else if(p1Color == superArcticColor || p2Color == superArcticColor) {
                if(randColor < 10){
                    color = arcticColor;
                }else {
                    color = axanthicColor;
                }
            }else {
                if(randColor < 10){
                    color = snowColor;
                }else {
                    color = yetiColor;
                }
            }
        }
        else if(p1Color == arcticColor || p2Color == arcticColor){ // if one is arctic
            if(p1Color == arcticColor && p2Color == arcticColor){ //one is axanthic
                if(randColor < 5){
                    color = normalColor;
                }else if(randColor < 10){
                    color = superArcticColor;
                }else {
                    color = arcticColor;
                }
            }else if(p1Color == snowColor || p2Color == snowColor){
                if (randColor < 10) {
                    color = normalColor;
                } else {
                    color = yetiColor;
                }
            }else if(p1Color == superArcticColor || p2Color == superArcticColor){
                if (randColor < 10) {
                    color = arcticColor;
                } else {
                    color = superArcticColor;
                }
            }else if(p1Color == subZeroColor || p2Color == subZeroColor){
                if(randColor < 5){
                    color = arcticColor;
                }else if(randColor < 15){
                    color = superArcticColor;
                }else {
                    color = subZeroColor;
                }
            }else {
                if(randColor < 5){
                    color = yetiColor;
                }else if(randColor < 10){
                    color = superArcticColor;
                }else {
                    color = arcticColor;
                }
            }
        }
        else if(p1Color == snowColor || p2Color == snowColor) { // if one is snow
            if(p1Color == snowColor && p2Color == snowColor){
                if (randColor < 5) {
                    color = albinoColor;
                } else if(randColor < 10){
                    color = axanthicColor;
                }else {
                    color = snowColor;
                }
            }else if(p1Color == superArcticColor || p2Color == superArcticColor){
                color = yetiColor;
            }else if(p1Color == subZeroColor || p2Color == subZeroColor){
                if (randColor < 10) {
                    color = yetiColor;
                } else {
                    color = subZeroColor;
                }
            }else {
                if(randColor < 5){
                    color = axanthicColor;
                }else if(randColor < 10){
                    color = subZeroColor;
                }else {
                    color = yetiColor;
                }
            }
        }
        else if(p1Color == superArcticColor || p2Color == superArcticColor) { // if one is superarctic
            if(p1Color == superArcticColor && p2Color == superArcticColor){
                color = superArcticColor;
            }else if(p1Color == subZeroColor || p2Color == subZeroColor){
                color = subZeroColor;
            }else {
                color = yetiColor;
            }
        }
        else if(p1Color == subZeroColor || p2Color == subZeroColor) { // if one is subzero
            if(p1Color == subZeroColor && p2Color == subZeroColor){
                if (randColor < 5) {
                    color = albinoColor;
                } else if(randColor < 15){
                    color = subZeroColor;
                }else {
                    color = superArcticColor;
                }
            }else {
                if(randColor < 10){
                    color = subZeroColor;
                }else {
                    color = yetiColor;
                }
            }
        }
        else { // at least one is axanthic
            if (randColor < 5) {
                color = superArcticColor;
            } else if(randColor < 10){
                color = axanthicColor;
            }else if(randColor < 15){
                color = yetiColor;
            }else {
                color = subZeroColor;
            }
        }
        return color;
    }*/

    //patterns: 0=normal, 1=conda, 2=superconda
    /**public int getOffspringPattern(BlockState state){
        int pattern = 0;
        int randPattern = this.random.nextInt(20);
        if(state.getBlock() != BlockInit.SNAKE_EGG.get()){
            //System.out.println("block is not egg");
            return 0;
        }
        int p1Pattern = state.getValue(PATTERN);
        int p2Pattern = state.getValue(PATTERN_P2);
        if(p1Pattern == 0 || p2Pattern == 0){ //one is normal
            if(p1Pattern == 1 || p2Pattern == 1){ //other is conda
                if(randPattern < 10){
                    pattern = 0;
                }else {
                    pattern = 1;
                }
            }else if(p1Pattern == 2 || p2Pattern == 2){ //other is super
                pattern = 1;
            }else { //both normal
                if(randPattern < 2){
                    pattern = 1;
                }else {
                    pattern = 0;
                }
            }
        }else if(p1Pattern == 1 || p2Pattern == 1){ //one is conda
            if(p1Pattern == 2 || p2Pattern == 2){ //other is super
                if(randPattern < 5){
                    pattern = 1;
                }else {
                    pattern = 2;
                }
            }else{ //both conda
                if(randPattern < 5){
                    pattern = 0;
                }else if(randPattern < 10){
                    pattern = 1;
                }else {
                    pattern = 2;
                }
            }
        }else { // both super
            pattern = 2;
        }
        return pattern;
    }*/
}
