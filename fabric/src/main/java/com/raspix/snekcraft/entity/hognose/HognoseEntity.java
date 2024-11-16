package com.raspix.snekcraft.entity.hognose;

import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.entity.generics.GenePool;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.items.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
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
	
	private static final int maxPattern = 2;
	private static final int maxColor = 11;
	
	private static final Ingredient FOOD_ITEMS = Ingredient.ofItems(ItemInit.FROG_LEG, Items.RABBIT); // Rats mod not available on Fabric, so omitted here
	
	static final Predicate<Entity> PREY = entity -> entity instanceof FrogEntity || entity instanceof RabbitEntity;
	
	public HognoseEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world, EntityDimensions.changing(0.4f, 0.3f));
	}
	
	@Override
	public boolean isBreedingItem(ItemStack itemStack) {
		return itemStack.isOf(ItemInit.FROG_LEG) || itemStack.isOf(Items.RABBIT); // Rats mod not available on Fabric, so omitted here
	}
	
	@Override
	protected void initGoals() {
		super.initGoals();
		goalSelector.add(4, new TemptGoal(this, 1.2d, FOOD_ITEMS, false));
		goalSelector.add(5, new ActiveTargetGoal(this, AnimalEntity.class, 10, false, true, PREY));
	}
	
	@Override
	public void addSpeciesSaveData(NbtCompound nbtCompound) {
		nbtCompound.putString("Class", getType().getBaseClass().getName());
		saveNbt(nbtCompound);
	}
	
	@Override
	public Block getEggType() {
		return BlockInit.SNAKE_EGG;
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
	
    public static int getMaxPattern(){
        return maxPattern;
    }
    
    public static int getMaxColor(){
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
	
	@Override
	public boolean isInsideWall() {
		return (getControllingPassenger() == null && getWorld().getBlockState(getBlockPos()).isIn(BlockTags.SAND)) || super.isInsideWall();
	}
	
	// Unused?
	public static boolean canSpawn(EntityType<HognoseEntity> entityType, ServerWorldAccess worldAccess, SpawnReason spawnReason, BlockPos blockPos, Random random) {
		return worldAccess.getBlockState(blockPos.down()).isIn(BlockTags.AZALEA_GROWS_ON) && isLightLevelValidForNaturalSpawn(worldAccess, blockPos);
	}
}
