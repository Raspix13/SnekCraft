package com.raspix.snekcraft.entity.ball_python;

import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.entity.generics.GenePool;
import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class BallPythonEntity extends SnakeBase {
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
    
    private static final Ingredient FOOD_ITEMS = Ingredient.ofItems(Items.CHICKEN, Items.EGG, Items.RABBIT);
    
    static final Predicate<Entity> PREY = entity -> entity instanceof ChickenEntity || entity instanceof RabbitEntity;
    
    public BallPythonEntity(EntityType<? extends AnimalEntity> entityType, World world) {
    	super(entityType, world, EntityDimensions.changing(0.4f, 0.3f));
    }
    
    public boolean isBreedingItem(ItemStack itemStack) {
    	return itemStack.isOf(Items.CHICKEN) || itemStack.isOf(Items.RABBIT); 
    }
    
    @Override
    protected void initGoals() {
    	super.initGoals();
    	goalSelector.add(4, new TemptGoal(this, 1.2d, FOOD_ITEMS, false));
    	goalSelector.add(5, new ActiveTargetGoal(this, AnimalEntity.class, 10, false, true, PREY));
    }
    
    @Override
    public void addSpeciesSaveData(NbtCompound nbtCompound) {
    	nbtCompound.putString("Class", getClass().getName()); // Different in HognoseEntity?
    	saveNbt(nbtCompound);
    }
    
    @Override
    public Block getEggType() {
    	return BlockInit.BALL_PYTHON_EGG;
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
    
    public static int getMaxPattern() {
    	return maxPattern;
    }
    
    public static int getMaxColor() {
    	return maxColor;
    }
    
    @Override
    public int getOffspringColor(int p1Color, int p2Color){
        return colorGenetics[p1Color][p2Color].getGene(this.random.nextInt(BREEDING_RANGE));
    }
    
    @Override
    public int getOffspringPattern(int p1Pattern, int p2Pattern){
        return patternGenetics[p1Pattern][p2Pattern].getGene(this.random.nextInt(BREEDING_RANGE));
    }
	
	@Nullable
	@Override
	public EntityData initialize(ServerWorldAccess worldAccess, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound nbt) {
		setColor(0);
		setPattern(0);
		return super.initialize(worldAccess, difficulty, spawnReason, entityData, nbt);
	}
	
	// Only in HognoseEntity?
	/*@Override
	public boolean isInsideWall() {
		return (getControllingPassenger() == null && getWorld().getBlockState(getBlockPos()).isIn(BlockTags.SAND)) || super.isInsideWall();
	}*/
	
	// Unused?
	public static boolean canSpawn(EntityType<BallPythonEntity> entityType, ServerWorldAccess worldAccess, SpawnReason spawnReason, BlockPos blockPos, Random random) {
		return worldAccess.getBlockState(blockPos.down()).isIn(BlockTags.AZALEA_GROWS_ON) && isLightLevelValidForNaturalSpawn(worldAccess, blockPos);
	}
}
