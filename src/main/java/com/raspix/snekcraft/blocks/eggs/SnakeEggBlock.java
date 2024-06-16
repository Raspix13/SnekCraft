package com.raspix.snekcraft.blocks.eggs;

import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class SnakeEggBlock extends BaseEntityBlock {
    public static final int MAX_HATCH_LEVEL = 2;
    public static final int MIN_EGGS = 1;
    public static final int MAX_EGGS = 4;


    protected final Random random = new Random();

    //public SnakeEggBlockEntity eggEntity;
    private static final VoxelShape ONE_EGG_AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);
    private static final VoxelShape MULTIPLE_EGGS_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    public static final IntegerProperty EGGS = BlockStateProperties.EGGS;

    public SnakeEggBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)).setValue(EGGS, Integer.valueOf(1)));
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (!(pEntity instanceof Zombie)) {
        }
        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }

    /**private void destroyEgg(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, int pChance) {
        if (this.canDestroyEgg(pLevel, pEntity)) {
            if (!pLevel.isClientSide && pLevel.random.nextInt(pChance) == 0 && pState.is(BlockInit.SNAKE_EGG.get())) {
                this.decreaseEggs(pLevel, pPos, pState);
            }

        }
    }*/

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

        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            CompoundTag compoundTag = pLevel.getBlockEntity(pPos).getPersistentData();
            if(compoundTag != null){
                int p1 = compoundTag.getInt("pattern");
                int p2 = compoundTag.getInt("pattern_p2");
                int c1 = compoundTag.getInt("color");
                int c2 = compoundTag.getInt("color_p2");
                //((SnakeEggBlockEntity)pLevel.getBlockEntity(pPos)).PrintOutStats();
                //System.out.println("The egg has ps:" + p1 + ", " + p2 + ", and cs: " + c1 + ", " + c2 + " stored");
            }

        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    private void decreaseEggs(Level pLevel, BlockPos pPos, BlockState pState, CompoundTag tag) {
        pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
        int i = pState.getValue(EGGS);
        if (i <= 1) {
            pLevel.destroyBlock(pPos, false);
        } else {
            pLevel.setBlock(pPos, pState.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            pLevel.levelEvent(2001, pPos, Block.getId(pState));
            SnakeEggBlockEntity tile = (SnakeEggBlockEntity)pLevel.getBlockEntity(pPos);
            tile.setStats(tag.getInt("color"),
                    tag.getInt("color_p2"),
                    tag.getInt("pattern"),
                    tag.getInt("pattern_p2"));
        }

    }

    private void decreaseEggs(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
        int i = pState.getValue(EGGS);
        if (i <= 1) {
            pLevel.destroyBlock(pPos, false);
        } else {
            CompoundTag oldTag = ((SnakeEggBlockEntity)pLevel.getBlockEntity(pPos)).getPersistentData();
            pLevel.setBlock(pPos, pState.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            pLevel.levelEvent(2001, pPos, Block.getId(pState));
            SnakeEggBlockEntity tile = (SnakeEggBlockEntity)pLevel.getBlockEntity(pPos);

            tile.setStats(oldTag.getInt("color"),
                    oldTag.getInt("color_p2"),
                    oldTag.getInt("pattern"),
                    oldTag.getInt("pattern_p2"));
        }

    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (this.shouldUpdateHatchLevel(pLevel) && onSand(pLevel, pPos)) {
            int i = pState.getValue(HATCH);
            if (i < 2) {
                //System.out.println("Aging with colors " + eggEntity.getCOLOR() + " and " + eggEntity.getCOLOR_P2());
                pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.setBlock(pPos, pState.setValue(HATCH, Integer.valueOf(i + 1)), 2);
            } else {
                pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                CompoundTag compoundTag = pLevel.getBlockEntity(pPos).getPersistentData();
                pLevel.removeBlock(pPos, false);
                for(int j = 0; j < pState.getValue(EGGS); ++j) {
                    pLevel.levelEvent(2001, pPos, Block.getId(pState));
                    SnakeBase snake = (SnakeBase) GetSnakeType().create(pLevel);
                    snake.setAge(-24000);
                    snake.moveTo((double)pPos.getX() + 0.3D + (double)j * 0.2D, (double)pPos.getY(), (double)pPos.getZ() + 0.3D, 0.0F, 0.0F);
                    snake.setColor(getOffspringColor(compoundTag));
                    snake.setPattern(getOffspringPattern(compoundTag));
                    pLevel.addFreshEntity(snake);
                }
            }
        }
    }

    public abstract EntityType GetSnakeType(); //ModEntityTypes.HOGNOSE.get()

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
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
        if (!pLevel.isClientSide && pLevel.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) ) {
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, pStack) != 0) {
                ItemStack itemstack = new ItemStack(this);

                CompoundTag compoundTagBlock = pTe.getPersistentData();
                int c1 = compoundTagBlock.getInt("color");
                int c2 = compoundTagBlock.getInt("color_p2");
                int p1 = compoundTagBlock.getInt("pattern");
                int p2 = compoundTagBlock.getInt("pattern_p2");

                CompoundTag compoundtag = new CompoundTag();
                if(c1 > 0){
                    compoundtag.putInt("color", c1);
                }
                if(c2 > 0){
                    compoundtag.putInt("color_p2", c2);
                }
                if(p1 > 0){
                    compoundtag.putInt("pattern", p1);
                }
                if(p2 > 0){
                    compoundtag.putInt("pattern_p2", p2);
                }

                itemstack.addTagElement("BlockStateTag", compoundtag);
                ItemEntity itementity = new ItemEntity(pLevel, (double)pPos.getX(), (double)pPos.getY(), (double)pPos.getZ(), itemstack);
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            }

        }
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
        //this.decreaseEggs(pLevel, pPos, pState, pTe.getTileData());
    }



    // For telling if an item can be placed on a block, like candles
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        ItemStack handItem = pUseContext.getItemInHand();

        CompoundTag tag = handItem.getTag();
        boolean isSameEggType = handItem.is(this.asItem());
        boolean areSameValues = false;

        if(isSameEggType && handItem.getTag() != null && !pUseContext.getLevel().isClientSide) {
            BlockPos pos = pUseContext.getClickedPos();
            CompoundTag blockTag = pUseContext.getLevel().getBlockEntity(pos).getPersistentData();
            /**if(pUseContext.getLevel().getBlockState(pos) == pState){
                System.out.println("should be same egg");
            }*/
            for (String tagInfo : tag.getAllKeys()) {
                if (tagInfo.contains("BlockStateTag")) {
                    CompoundTag compoundTag = tag.getCompound("BlockStateTag"); //the item's data
                    areSameValues = areGeneticsSame(blockTag, compoundTag);
                }
            }
        }

        return !pUseContext.isSecondaryUseActive() && isSameEggType && areSameValues && pState.getValue(EGGS) < 4 ? true : super.canBeReplaced(pState, pUseContext);
    }

    public boolean areGeneticsSame(CompoundTag blockTag, CompoundTag itemTag){
        boolean colorMatch = blockTag.getInt("color") == itemTag.getInt("color");
        boolean colorMatch2 = blockTag.getInt("color_p2") == itemTag.getInt("color_p2");
        boolean patternMatch = blockTag.getInt("pattern") == itemTag.getInt("pattern");
        boolean patternMatch2 = blockTag.getInt("pattern_p2") == itemTag.getInt("pattern_p2");
        return colorMatch && colorMatch2 && patternMatch && patternMatch2;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(4, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(pContext);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @org.jetbrains.annotations.Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        CompoundTag tag1 = pStack.getTag();
        if(tag1 != null){
            CompoundTag compoundTag = tag1.getCompound("BlockStateTag");
            if (compoundTag!=null){
                int c1 = compoundTag.getInt("color");
                int c2 = compoundTag.getInt("color_p2");
                int p1 = compoundTag.getInt("pattern");
                int p2 = compoundTag.getInt("pattern_p2");
                SnakeEggBlockEntity sebe = (SnakeEggBlockEntity) pLevel.getBlockEntity(pPos);
                if(sebe != null){
                    CompoundTag tag = sebe.getPersistentData();
                    tag.putInt("color", c1);
                    tag.putInt("color_p2", c2);
                    tag.putInt("pattern", p1);
                    tag.putInt("pattern_p2", p2);
                }
            }
        }

    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(EGGS) > 1 ? MULTIPLE_EGGS_AABB : ONE_EGG_AABB;
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HATCH, EGGS);
    }

    private boolean canDestroyEgg(Level pLevel, Entity pEntity) {
        return pEntity instanceof Player;
    }

    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        /**if (!pLevel.isClientSide && pLevel.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            ItemStack itemstack = new ItemStack(this);

            CompoundTag compoundTagBlock = blockentity.getTileData();
            int c1 = compoundTagBlock.getInt("color");
            int c2 = compoundTagBlock.getInt("color_p2");
            int p1 = compoundTagBlock.getInt("pattern");
            int p2 = compoundTagBlock.getInt("pattern_p2");

            CompoundTag compoundtag = new CompoundTag();
            if(c1 > 0){
                compoundtag.putInt("color", c1);
            }
            if(c2 > 0){
                compoundtag.putInt("color_p2", c2);
            }
            if(p1 > 0){
                compoundtag.putInt("pattern", p1);
            }
            if(p2 > 0){
                compoundtag.putInt("pattern_p2", p2);
            }

            itemstack.addTagElement("BlockStateTag", compoundtag);
            ItemEntity itementity = new ItemEntity(pLevel, (double)pPos.getX(), (double)pPos.getY(), (double)pPos.getZ(), itemstack);
            itementity.setDefaultPickUpDelay();
            pLevel.addFreshEntity(itementity);

        }*/

        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }



    /**public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        BlockEntity blockentity = pBuilder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof ShulkerBoxBlockEntity) {
            ShulkerBoxBlockEntity shulkerboxblockentity = (ShulkerBoxBlockEntity)blockentity;
            pBuilder = pBuilder.withDynamicDrop(CONTENTS, (p_56218_, p_56219_) -> {
                for(int i = 0; i < shulkerboxblockentity.getContainerSize(); ++i) {
                    p_56219_.accept(shulkerboxblockentity.getItem(i));
                }

            });
        }

        return super.getDrops(pState, pBuilder);
    }*/

    //public abstract void setParentTraits(int p1Color, int p1Pattern, int p2Color, int p2Pattern, Level level, BlockPos pos);

    //colors: 0=brown, 1=orange, 2=gray, 3=black,
    //colors: 0=normal, 1=ALBINO, 2=arctic/snow, 3=axanthic, 4=rainbow
    //public abstract int getOffspringColor(BlockState state);

    //patterns: 0=normal, 1=conda, 2=superconda
    //public abstract int getOffspringPattern(BlockState state);


    // Block Entity Things

    public abstract int getOffspringColor(CompoundTag compoundTag);

    public abstract int getOffspringPattern(CompoundTag compoundTag);

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SnakeEggBlockEntity(pPos, pState);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
        //return pLevel.isClientSide ? null : createTickerHelper(pBlockEntityType, BlockEntityInit.SNAKE_EGG.get(), SnakeEggBlockEntity::serverTick);
    }


    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.hasBlockEntity() && (!pState.is(pNewState.getBlock()) || !pNewState.hasBlockEntity())) {
            pLevel.removeBlockEntity(pPos);
        }

    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        this.playerWillDestroy(level, pos, state, player);
        decreaseEggs(level, pos, state);
        return true;//super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    /**@org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        eggEntity = new SnakeEggBlockEntity(pPos, pState);
        return eggEntity;
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState, int color, int color_2, int pattern, int pattern_2) {
        eggEntity = new SnakeEggBlockEntity(pPos, pState, color, color_2, pattern, pattern_2);
        return eggEntity;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    public void setParentTraits(int p1Color, int p1Pattern, int p2Color, int p2Pattern, Level level, BlockPos pos){
        //System.out.println("setting traits parent 1:" + p1Color + "c & " + p1Pattern + "p and parent 2: " + p2Color + "c & " + p2Pattern);
        this.eggEntity.setCOLOR(p1Color);
        this.eggEntity.setCOLOR_P2(p2Color);
        this.eggEntity.setPATTERN(p1Pattern);
        this.eggEntity.setPATTERN_P2(p2Pattern);

        //level.setBlock(pos, level.getBlockState(pos).setValue(COLOR, p1Color).setValue(PATTERN, p1Pattern).setValue(COLOR_P2, p2Color).setValue(PATTERN_P2, p2Pattern), 3);
    }

    //@Override
    public int getOffspringColor(BlockState state) {
        System.out.println("Hatching with colors " + eggEntity.getCOLOR() + " and " + eggEntity.getCOLOR_P2());
        return BallPythonEntity.colorGenetics[eggEntity.getCOLOR()][eggEntity.getCOLOR_P2()].GetGene(this.random.nextInt(SnakeBase.BREEDING_RANGE));
    }

    //@Override
    public int getOffspringPattern(BlockState state) {
        return BallPythonEntity.patternGenetics[eggEntity.getPATTERN()][eggEntity.getPATTERN_P2()].GetGene(this.random.nextInt(SnakeBase.BREEDING_RANGE));
    }*/

    /**@org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        //return pLevel.isClientSide() ? null: ($0, pos, )
        return createTickerHelper(pBlockEntityType, BlockEntityInit.SNAKE_EGG.get(), SnakeEggBlockEntity::tick);
        //return super.getTicker(pLevel, pState, pBlockEntityType);
    }*/
}
