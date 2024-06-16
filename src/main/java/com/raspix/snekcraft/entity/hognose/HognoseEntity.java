package com.raspix.snekcraft.entity.hognose;

import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.generics.GenePool;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.items.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;

import java.util.Random;
import java.util.function.Predicate;

public class HognoseEntity extends SnakeBase {

    public static GenePool[][] colorGenetics = new GenePool[][]{
            {new GenePool(new int[]{0, 1, 2, 3}, new int[]{70, 10, 10, 10}),
                    new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{3}, new int[]{100})},
            {new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{1}, new int[]{100}),
                    new GenePool(new int[]{4}, new int[]{100}),
                    new GenePool(new int[]{9, 0}, new int[]{50, 50}),
                    new GenePool(new int[]{4, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{9}, new int[]{100}),
                    new GenePool(new int[]{9}, new int[]{100}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{50, 24, 13, 13}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{9, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{50, 24, 13, 13})},
            {new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{4}, new int[]{100}),
                    new GenePool(new int[]{2}, new int[]{100}),
                    new GenePool(new int[]{10, 0}, new int[]{50, 50}),
                    new GenePool(new int[]{4, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{10}, new int[]{100}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{50, 24, 13, 13}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{50, 24, 13, 13}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{10, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{10}, new int[]{100})},
            {new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{9, 0}, new int[]{50, 50}),
                    new GenePool(new int[]{10, 0}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 3, 5}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{0, 10, 9}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{3, 5}, new int[]{50, 50}),
                    new GenePool(new int[]{6, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{7, 9, 10, 6, 11}, new int[]{20, 20, 20, 20, 20}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{6, 9, 1}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{11, 10, 2}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{11, 10}, new int[]{50, 50})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{4, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{4, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 10, 9}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{4}, new int[]{100}),
                    new GenePool(new int[]{9, 10}, new int[]{50, 50}),
                    new GenePool(new int[]{9, 10}, new int[]{75, 25}),
                    new GenePool(new int[]{10, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{9, 4, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{10, 4, 9}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{10, 9}, new int[]{75, 25})},
            {new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{9}, new int[]{100}),
                    new GenePool(new int[]{10}, new int[]{100}),
                    new GenePool(new int[]{3, 5}, new int[]{50, 50}),
                    new GenePool(new int[]{9, 10}, new int[]{50, 50}),
                    new GenePool(new int[]{5}, new int[]{100}),
                    new GenePool(new int[]{6}, new int[]{100}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{9, 6}, new int[]{50, 50}),
                    new GenePool(new int[]{10, 11}, new int[]{50, 50}),
                    new GenePool(new int[]{11}, new int[]{100})},
            {new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{9}, new int[]{100}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{50, 24, 13, 13}),
                    new GenePool(new int[]{6, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{9, 10}, new int[]{75, 25}),
                    new GenePool(new int[]{6}, new int[]{100}),
                    new GenePool(new int[]{6}, new int[]{100}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{6, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{7, 9, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{7}, new int[]{100})},
            {new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{50, 24, 13, 13}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{50, 24, 13, 13}),
                    new GenePool(new int[]{7, 9, 10, 6, 11}, new int[]{20, 20, 20, 20, 20}),
                    new GenePool(new int[]{10, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{7, 8}, new int[]{80, 20}),
                    new GenePool(new int[]{7, 8}, new int[]{50, 50}),
                    new GenePool(new int[]{7, 9, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{7, 9, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{7}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{7, 8}, new int[]{50, 50}),
                    new GenePool(new int[]{8, 0, 1, 2, 4, 9, 3, 10, 5, 11, 6, 7}, new int[]{12, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{9, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{6, 9, 1}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{9, 4, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{9, 6}, new int[]{50, 50}),
                    new GenePool(new int[]{6, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{7, 9, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{6, 9, 1}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{7, 9, 10, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{7, 9, 10}, new int[]{50, 25, 25})},
            {new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{10, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{11, 10, 2}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{10, 4, 9}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{10, 11}, new int[]{50, 50}),
                    new GenePool(new int[]{7, 9, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{7, 9, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{7, 9, 10, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{11, 10, 2}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{10, 11}, new int[]{50, 50})},
            {new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{3, 4, 10, 9}, new int[]{50, 24, 13, 13}),
                    new GenePool(new int[]{10}, new int[]{100}),
                    new GenePool(new int[]{11, 10}, new int[]{50, 50}),
                    new GenePool(new int[]{10, 9}, new int[]{75, 25}),
                    new GenePool(new int[]{11}, new int[]{100}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{7, 9, 10}, new int[]{50, 25, 25}),
                    new GenePool(new int[]{10, 11}, new int[]{50, 50}),
                    new GenePool(new int[]{11}, new int[]{100})}};
    public static GenePool[][] patternGenetics = new GenePool[][]{
            {new GenePool(new int[]{0, 1}, new int[]{90, 10}),
                    new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{1}, new int[]{100})},
            {new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 1, 2}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{1, 2}, new int[]{50, 50})},
            {new GenePool(new int[]{1}, new int[]{100}),
                    new GenePool(new int[]{1, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{2}, new int[]{100})}};


    private static int maxPattern = 2;
    private static int maxColor = 11;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(ItemInit.FROG_LEG.get());


    static final Predicate<Entity> PREY = (p_28498_) -> {
        return p_28498_ instanceof Frog;
    };

    public HognoseEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, EntityDimensions.scalable(0.4f, 0.3f));
    }

    //colors: 0=normal, 1=ALBINO, 2=arctic, 3=axanthic, 4=rainbow

    //patterns: 0=normal, 1=conda, 2=superconda

    private static boolean isTemptingItem(ItemStack pStack) {
        return pStack.is(ItemInit.FROG_LEG.get());
    }

    public boolean isFood(ItemStack pStack) {
        return isTemptingItem(pStack);
    }


    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal(this, Animal.class, 10, false, true, PREY));

    }

    @Override
    public void addSpeciesSaveData(CompoundTag compound) {
        compound.putString("Class", getType().getBaseClass().getName());
        this.save(compound);
    }

    @Override
    public Block GetEggType() {
        return BlockInit.SNAKE_EGG.get();
    }

    @Override
    public String getSpeciesName(int color, int pattern) {
        String snakeType = "";
        switch (color){
            case 0:
                break;
            case 1:
                snakeType += "Albino ";
                break;
            case 2:
                snakeType += "Axanthic ";
                break;
            case 3:
                snakeType += "Arctic ";
                break;
            case 4:
                snakeType += "Snow ";
                break;
            case 5:
                snakeType += "Super Arctic ";
                break;
            case 6:
                snakeType += "Subzero ";
                break;
            case 7:
                snakeType += "Yeti ";
                break;
            case 8:
                snakeType += "Rainbow ";
                break;
            case 9:
                snakeType += "Albino Arctic ";
                break;
            case 10:
                snakeType += "Axarctic ";
                break;
            case 11:
                snakeType += "Super Axarctic ";
                break;
            default:
                snakeType += "Normal ";
                break;

        }
        switch (pattern){
            case 1:
                snakeType += "Conda ";
                break;
            case 2:
                snakeType += "Superconda ";
                break;
            default:
                snakeType += "Normal ";
                break;

        }
        snakeType += "Hognose";
        return snakeType;
    }

    public static int GetMaxPattern(){
        return maxPattern;
    }

    public static int GetMaxColor(){
        return maxColor;
    }

    @Override
    public int getOffspringColor(int p1Color, int p2Color){
        return colorGenetics[p1Color][p2Color].GetGene(this.random.nextInt(BREEDING_RANGE));
    }

    @Override
    public int getOffspringPattern(int p1Pattern, int p2Pattern){
        return patternGenetics[p1Pattern][p2Pattern].GetGene(this.random.nextInt(BREEDING_RANGE));
    }


    //TODO figure out what this is
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
    }

    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        this.setColor(0);//this.getRandom().nextInt(5));
        this.setPattern(0);//this.getRandom().nextInt(4)); //not inclusive
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    /**public static boolean canSpawn(BallPythonEntity entity, LevelAccessor levelAccess, MobSpawnType spawnType, BlockPos pos, Random random){
        return checkSnakeSpawnRules(entity, levelAccess, spawnType, pos, random);
    }*/

    public static boolean checkSnakeSpawnRules(EntityType<HognoseEntity> entity, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return (pLevel.getBlockState(pPos.below()).is(BlockTags.AZALEA_GROWS_ON)) && isBrightEnoughToSpawn(pLevel, pPos);
    }


    @Override
    public boolean isInWall() {
        if (this.isInSand()) {
            return false;
        } else {
            return super.isInWall();
        }
    }

    public boolean isInSand() {
        return this.getControllingPassenger() == null && level().getBlockState(blockPosition()).is(BlockTags.SAND);
    }


    public static boolean canSpawn(EntityType<HognoseEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return checkSnakeSpawnRules(entityType, level, spawnType, pos, randomSource);
    }
}
