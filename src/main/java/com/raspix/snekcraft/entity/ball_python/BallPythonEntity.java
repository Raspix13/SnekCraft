package com.raspix.snekcraft.entity.ball_python;

import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.entity.generics.GenePool;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.entity.hognose.HognoseEntity;
import com.raspix.snekcraft.sounds.SoundInit;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import java.util.Random;
import java.util.function.Predicate;

public class BallPythonEntity extends SnakeBase {

    // NTA: , sounds, aistep(drops),
    private static int maxPattern = 4;
    private static int maxColor = 10;

    // 0: normal, 1: piebald, 2: pinstripe, 3: pinpied
    public static GenePool[][] colorGenetics = new GenePool[][]{
            {new GenePool(new int[]{0, 1, 2, 3}, new int[]{70, 10, 10, 10}),
                    new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{1}, new int[]{100}),
                    new GenePool(new int[]{2}, new int[]{100}),
                    new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{0, 1, 2, 7}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0, 3, 2, 8}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0, 9, 1, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0, 9, 8, 7, 3, 1, 2, 10}, new int[]{13, 13, 13, 13, 13, 13, 13, 13})},
            {new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 1, 4}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{0, 1, 2, 7}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0, 1, 3, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{1, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{2, 7}, new int[]{50, 50}),
                    new GenePool(new int[]{3, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 2, 7, 4, 0}, new int[]{25, 16, 25, 16, 16}),
                    new GenePool(new int[]{3, 2, 1, 0, 9, 7, 8, 10}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{1, 3, 9, 4, 0}, new int[]{25, 16, 16, 16, 16}),
                    new GenePool(new int[]{0, 2, 3, 8, 4, 1, 7, 9, 10}, new int[]{8, 8, 8, 8, 8, 15, 15, 15, 15})},
            {new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 1, 2, 7}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0, 2, 5}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{0, 2, 3, 8}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{1, 7}, new int[]{50, 50}),
                    new GenePool(new int[]{2, 5}, new int[]{50, 50}),
                    new GenePool(new int[]{3, 8}, new int[]{50, 50}),
                    new GenePool(new int[]{2, 7, 5, 0, 1}, new int[]{25, 25, 16, 16, 16}),
                    new GenePool(new int[]{2, 8, 0, 3, 5}, new int[]{25, 25, 16, 16, 16}),
                    new GenePool(new int[]{0, 1, 2, 3, 7, 9, 8, 10}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{0, 1, 3, 5, 9, 2, 7, 8, 10}, new int[]{8, 8, 8, 8, 8, 15, 15, 15, 15})},
            {new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 1, 3, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0, 2, 3, 8}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0, 3, 6}, new int[]{25, 50, 25}),
                    new GenePool(new int[]{9, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{8, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{3, 6}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 1, 3, 2, 8, 7, 9, 10}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{3, 8, 0, 2, 6}, new int[]{25, 25, 16, 16, 16}),
                    new GenePool(new int[]{3, 9, 1, 0, 6}, new int[]{25, 25, 16, 16, 16}),
                    new GenePool(new int[]{0, 2, 1, 6, 7, 3, 8, 9, 10}, new int[]{8, 8, 8, 8, 8, 15, 15, 15, 15})},
            {new GenePool(new int[]{1}, new int[]{100}),
                    new GenePool(new int[]{1, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 7}, new int[]{50, 50}),
                    new GenePool(new int[]{9, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{4}, new int[]{100}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{9}, new int[]{100}),
                    new GenePool(new int[]{4, 1, 7}, new int[]{33, 33, 33}),
                    new GenePool(new int[]{9, 1, 7, 10}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{9, 1, 4}, new int[]{33, 33, 33}),
                    new GenePool(new int[]{10, 9, 7, 4, 1}, new int[]{20, 20, 20, 20, 20})},
            {new GenePool(new int[]{2}, new int[]{100}), new GenePool(new int[]{2, 7}, new int[]{50, 50}), new GenePool(new int[]{2, 5}, new int[]{50, 50}), new GenePool(new int[]{8, 2}, new int[]{50, 50}), new GenePool(new int[]{7}, new int[]{100}), new GenePool(new int[]{5}, new int[]{100}), new GenePool(new int[]{8}, new int[]{100}), new GenePool(new int[]{5, 2, 7}, new int[]{33, 33, 33}), new GenePool(new int[]{8, 5, 2}, new int[]{33, 33, 33}), new GenePool(new int[]{10, 8, 7, 2}, new int[]{25, 25, 25, 25}), new GenePool(new int[]{10, 8, 7, 2, 5}, new int[]{20, 20, 20, 20, 20})},
            {new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{3, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{3, 8}, new int[]{50, 50}),
                    new GenePool(new int[]{3, 6}, new int[]{50, 50}),
                    new GenePool(new int[]{9}, new int[]{100}), new GenePool(new int[]{8}, new int[]{100}), new GenePool(new int[]{6}, new int[]{100}), new GenePool(new int[]{10, 9, 8, 3}, new int[]{25, 25, 25, 25}), new GenePool(new int[]{8, 6, 3}, new int[]{33, 33, 33}), new GenePool(new int[]{9, 6, 3}, new int[]{33, 33, 33}), new GenePool(new int[]{10, 9, 8, 3, 6}, new int[]{20, 20, 20, 20, 20})}, {new GenePool(new int[]{0, 1, 2, 7}, new int[]{25, 25, 25, 25}), new GenePool(new int[]{1, 2, 7, 4, 0}, new int[]{25, 16, 25, 16, 16}), new GenePool(new int[]{2, 7, 5, 0, 1}, new int[]{25, 25, 16, 16, 16}), new GenePool(new int[]{0, 1, 3, 2, 8, 7, 9, 10}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}), new GenePool(new int[]{4, 1, 7}, new int[]{33, 33, 33}), new GenePool(new int[]{5, 2, 7}, new int[]{33, 33, 33}), new GenePool(new int[]{10, 9, 8, 3}, new int[]{25, 25, 25, 25}), new GenePool(new int[]{7, 4, 5, 1, 2, 0}, new int[]{30, 11, 11, 18, 18, 11}), new GenePool(new int[]{10, 8, 7, 9, 5, 2, 3, 1, 0}, new int[]{15, 15, 15, 8, 8, 15, 8, 8, 8}), new GenePool(new int[]{10, 8, 7, 9, 4, 2, 3, 1, 0}, new int[]{15, 8, 15, 15, 8, 8, 8, 15, 8}), new GenePool(new int[]{10, 8, 7, 9, 4, 2, 3, 1, 0, 5}, new int[]{16, 10, 16, 10, 7, 10, 7, 10, 7, 7})},
            {new GenePool(new int[]{0, 3, 2, 8}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{3, 2, 1, 0, 9, 7, 8, 10}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}), new GenePool(new int[]{2, 8, 0, 3, 5}, new int[]{25, 25, 16, 16, 16}), new GenePool(new int[]{3, 8, 0, 2, 6}, new int[]{25, 25, 16, 16, 16}), new GenePool(new int[]{9, 1, 7, 10}, new int[]{25, 25, 25, 25}), new GenePool(new int[]{8, 5, 2}, new int[]{33, 33, 33}), new GenePool(new int[]{8, 6, 3}, new int[]{33, 33, 33}), new GenePool(new int[]{10, 8, 7, 9, 5, 2, 3, 1, 0}, new int[]{15, 15, 15, 8, 8, 15, 8, 8, 8}), new GenePool(new int[]{8, 6, 3, 2, 0, 5}, new int[]{30, 11, 18, 18, 11, 11}), new GenePool(new int[]{10, 8, 7, 9, 6, 2, 3, 1, 0}, new int[]{15, 15, 8, 15, 8, 8, 15, 8, 8}), new GenePool(new int[]{10, 8, 7, 9, 6, 2, 3, 1, 0, 5}, new int[]{16, 16, 10, 10, 7, 10, 10, 7, 7, 7})}, {new GenePool(new int[]{0, 9, 1, 3}, new int[]{25, 25, 25, 25}), new GenePool(new int[]{1, 3, 9, 4, 0}, new int[]{25, 16, 16, 16, 16}), new GenePool(new int[]{0, 1, 2, 3, 7, 9, 8, 10}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}), new GenePool(new int[]{3, 9, 1, 0, 6}, new int[]{25, 25, 16, 16, 16}), new GenePool(new int[]{9, 1, 4}, new int[]{33, 33, 33}), new GenePool(new int[]{10, 8, 7, 2}, new int[]{25, 25, 25, 25}), new GenePool(new int[]{9, 6, 3}, new int[]{33, 33, 33}), new GenePool(new int[]{10, 8, 7, 9, 4, 2, 3, 1, 0}, new int[]{15, 8, 15, 15, 8, 8, 8, 15, 8}), new GenePool(new int[]{10, 8, 7, 9, 6, 2, 3, 1, 0}, new int[]{15, 15, 8, 15, 8, 8, 15, 8, 8}), new GenePool(new int[]{9, 6, 4, 3, 1, 0}, new int[]{30, 11, 11, 18, 18, 11}), new GenePool(new int[]{10, 8, 7, 9, 6, 2, 3, 1, 0, 4}, new int[]{16, 10, 10, 16, 7, 7, 10, 10, 7, 7})}, {new GenePool(new int[]{0, 9, 8, 7, 3, 1, 2, 10}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}), new GenePool(new int[]{0, 2, 3, 8, 4, 1, 7, 9, 10}, new int[]{8, 8, 8, 8, 8, 15, 15, 15, 15}), new GenePool(new int[]{0, 1, 3, 5, 9, 2, 7, 8, 10}, new int[]{8, 8, 8, 8, 8, 15, 15, 15, 15}), new GenePool(new int[]{0, 2, 1, 6, 7, 3, 8, 9, 10}, new int[]{8, 8, 8, 8, 8, 15, 15, 15, 15}), new GenePool(new int[]{10, 9, 7, 4, 1}, new int[]{20, 20, 20, 20, 20}), new GenePool(new int[]{10, 8, 7, 2, 5}, new int[]{20, 20, 20, 20, 20}), new GenePool(new int[]{10, 9, 8, 3, 6}, new int[]{20, 20, 20, 20, 20}), new GenePool(new int[]{10, 8, 7, 9, 4, 2, 3, 1, 0, 5}, new int[]{16, 10, 16, 10, 7, 10, 7, 10, 7, 7}), new GenePool(new int[]{10, 8, 7, 9, 6, 2, 3, 1, 0, 5}, new int[]{16, 16, 10, 10, 7, 10, 10, 7, 7, 7}), new GenePool(new int[]{10, 8, 7, 9, 6, 2, 3, 1, 0, 4}, new int[]{16, 10, 10, 16, 7, 7, 10, 10, 7, 7}), new GenePool(new int[]{10, 9, 8, 7, 6, 4, 5, 3, 1, 2, 0}, new int[]{18, 11, 11, 11, 7, 7, 7, 7, 7, 7, 7})}};

    public static GenePool[][] patternGenetics = new GenePool[][]{
            {new GenePool(new int[]{0, 1, 2}, new int[]{80, 10, 10}),
                    new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 2, 3}, new int[]{25, 25, 50}),
                    new GenePool(new int[]{0, 1}, new int[]{50, 50})},
            {new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 4}, new int[]{90, 10}),
                    new GenePool(new int[]{1, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 4}, new int[]{75, 25})},
            {new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{2}, new int[]{100}),
                    new GenePool(new int[]{2, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 2}, new int[]{50, 50})},
            {new GenePool(new int[]{1, 2, 3}, new int[]{25, 25, 50}),
                    new GenePool(new int[]{1, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{2, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 2, 3}, new int[]{25, 25, 50}),
                    new GenePool(new int[]{1, 3}, new int[]{50, 50})},
            {new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 4}, new int[]{75, 25}),
                    new GenePool(new int[]{1, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{1}, new int[]{50})}};



    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CHICKEN, Items.EGG, Items.RABBIT);

    static final Predicate<Entity> PREY = (p_28498_) -> {
        return p_28498_ instanceof Chicken || p_28498_ instanceof Rabbit;
    };

    public BallPythonEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, EntityDimensions.scalable(0.4f, 0.3f));
    }

    private static boolean isTemptingItem(ItemStack pStack) {
        return pStack.is(Items.CHICKEN) || pStack.is(Items.RABBIT);
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

    public void addSpeciesSaveData(CompoundTag compound) {
        compound.putString("Class", getClass().getName());
        this.save(compound);
    }

    @Override
    public Block GetEggType() {
        return BlockInit.BALL_PYTHON_EGG.get();
    }

    @Override
    public String getSpeciesName(int color, int pattern) {
        String snakeType = "";
        switch (color){
            case 0:
                break;
            case 1:
                snakeType += "Fire ";
                break;
            case 2:
                snakeType += "Pastel ";
                break;
            case 3:
                snakeType += "Black Pastel ";
                break;
            case 4:
                snakeType += "Super Fire ";
                break;
            case 5:
                snakeType += "Super Pastel ";
                break;
            case 6:
                snakeType += "Super Black Pastel ";
                break;
            case 7:
                snakeType += "Firefly ";
                break;
            case 8:
                snakeType += "Black Pewter ";
                break;
            case 9:
                snakeType += "Black Fire ";
                break;
            case 10:
                snakeType += "Black Fire Pastel ";
                break;
            default:
                snakeType += "Super Axarctic ";
                break;

        }
        switch (pattern){
            case 0:
                snakeType += "Normal ";
                break;
            case 1:
                snakeType += "Pied ";
                break;
            case 2:
            case 3:
                snakeType += "Pinstripe ";
                break;
            default:
                snakeType += "Smiley ";
                break;

        }
        snakeType += "Ball Python";
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

    public static boolean canSpawn(EntityType<BallPythonEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return checkSnakeSpawnRules(entityType, level, spawnType, pos, randomSource);
    }

    public static boolean checkSnakeSpawnRules(EntityType<BallPythonEntity> entity, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return (pLevel.getBlockState(pPos.below()).is(BlockTags.AZALEA_GROWS_ON)) && isBrightEnoughToSpawn(pLevel, pPos);
    }


}
