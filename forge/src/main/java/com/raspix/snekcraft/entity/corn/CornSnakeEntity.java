package com.raspix.snekcraft.entity.corn;

import com.github.alexthe666.rats.registry.RatsItemRegistry;
import com.raspix.snekcraft.blocks.BlockInit;
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
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;

import java.util.function.Predicate;

public class CornSnakeEntity extends SnakeBase {

    // NTA: , sounds, aistep(drops),
    private static int maxPattern = 2;
    private static int maxColor = 16;

    //<editor-fold desc="Color & Pattern Genetics Gene Pools">
    // Color Genetics: [0: normal, 1: piebald, 2: pinstripe, 3: pinpied]
    public static GenePool[][] colorGenetics = new GenePool[][]{
            {new GenePool(new int[]{0, 1, 2, 3, 4}, new int[]{60, 10, 10, 10, 10}),
                    new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{1}, new int[]{100}),
                    new GenePool(new int[]{5}, new int[]{100}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{9}, new int[]{100}),
                    new GenePool(new int[]{5, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{13, 6, 5, 7, 3, 2, 1, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{7, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{12, 5, 9, 8, 1, 2, 4, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{9, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{11, 9, 7, 10, 1, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{11, 7, 9, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{12, 5, 9, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{13, 5, 7, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{5}, new int[]{100}),
                    new GenePool(new int[]{2}, new int[]{100}),
                    new GenePool(new int[]{6}, new int[]{100}),
                    new GenePool(new int[]{8}, new int[]{100}),
                    new GenePool(new int[]{5, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{6, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{13, 6, 5, 7, 3, 2, 1, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{8, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{12, 5, 9, 8, 1, 2, 4, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{14, 8, 6, 10, 2, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{12, 5, 8, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{13, 5, 6, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14, 8, 6, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{6}, new int[]{100}),
                    new GenePool(new int[]{3}, new int[]{100}),
                    new GenePool(new int[]{10}, new int[]{100}),
                    new GenePool(new int[]{13, 6, 5, 7, 3, 2, 1, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{6, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{7, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{14, 8, 6, 10, 2, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{11, 9, 7, 10, 1, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{10, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{11, 7, 10, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{13, 7, 6, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14, 10, 6, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 11, 13, 14, 7, 10, 6, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{9}, new int[]{100}),
                    new GenePool(new int[]{8}, new int[]{100}),
                    new GenePool(new int[]{10}, new int[]{100}),
                    new GenePool(new int[]{4}, new int[]{100}),
                    new GenePool(new int[]{12, 5, 9, 8, 1, 2, 4, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{14, 8, 6, 10, 2, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{11, 9, 7, 10, 1, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{8, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{9, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{10, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{11, 10, 9, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{12, 9, 8, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{14, 10, 8, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 11, 8, 9, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{5, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{5, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{13, 6, 5, 7, 3, 2, 1, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{12, 5, 9, 8, 1, 2, 4, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{5}, new int[]{100}),
                    new GenePool(new int[]{13, 6, 5, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{13, 7, 5, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{12, 8, 5, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{12, 9, 5, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{12, 5}, new int[]{50, 50}),
                    new GenePool(new int[]{13, 5}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 13, 5}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{13, 6, 5, 7, 3, 2, 1, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{6, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{6, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{14, 8, 6, 10, 2, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{13, 6, 5, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{6}, new int[]{100}),
                    new GenePool(new int[]{13, 7, 6, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14, 8, 6, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{14, 10, 6, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 14, 13, 11, 7, 6, 10, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{13, 6}, new int[]{50, 50}),
                    new GenePool(new int[]{14, 6}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 13, 14, 6}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{7, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{13, 6, 5, 7, 3, 2, 1, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{7, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{11, 9, 7, 10, 1, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{13, 7, 5, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{13, 7, 6, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{7}, new int[]{100}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{11, 7, 9, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{11, 7, 10, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{11, 7}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{13, 7}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 14, 13, 11, 7, 6, 10, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 13, 11, 7}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{12, 5, 9, 8, 1, 2, 4, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{8, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{14, 8, 6, 10, 2, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{8, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{12, 8, 5, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14, 8, 6, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{8}, new int[]{100}),
                    new GenePool(new int[]{12, 8, 9, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14, 8, 10, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 11, 8, 9, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{12, 8}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{14, 8}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 14, 8}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{9, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{12, 5, 9, 8, 1, 2, 4, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{11, 9, 7, 10, 1, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{9, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{12, 9, 5, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{11, 7, 9, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{12, 8, 9, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{9}, new int[]{100}),
                    new GenePool(new int[]{11, 10, 9, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{11, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{12, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 14, 11, 8, 9, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 11, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{11, 9, 7, 10, 1, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{14, 8, 6, 10, 2, 4, 3, 0}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{10, 3}, new int[]{50, 50}),
                    new GenePool(new int[]{10, 4}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{14, 10, 6, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{11, 7, 10, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14, 8, 10, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{11, 10, 9, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{10}, new int[]{100}),
                    new GenePool(new int[]{11, 10}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 11, 14, 9, 8, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 11, 13, 14, 7, 10, 6, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{14, 10}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 14, 11, 10}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{11, 7, 9, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{11, 7, 10, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{11, 10, 9, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 14, 13, 11, 7, 6, 10, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{11, 7}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 14, 11, 8, 9, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{11, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{11, 10}, new int[]{50, 50}),
                    new GenePool(new int[]{11}, new int[]{100}),
                    new GenePool(new int[]{15, 12, 11, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 13, 11, 7}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 14, 11, 10}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 11}, new int[]{50, 50}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{12, 5, 9, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{12, 5, 8, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{12, 9, 8, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{12, 5}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{12, 8}, new int[]{50, 50}),
                    new GenePool(new int[]{12, 9}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 11, 14, 9, 8, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 11, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{12}, new int[]{100}),
                    new GenePool(new int[]{15, 12, 13, 5}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 8}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12}, new int[]{50, 50}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{13, 5, 7, 1}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{13, 5, 6, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{13, 7, 6, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{13, 5}, new int[]{50, 50}),
                    new GenePool(new int[]{13, 6}, new int[]{50, 50}),
                    new GenePool(new int[]{13, 7}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 11, 13, 14, 7, 10, 6, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 13, 11, 7}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 13, 5}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{13}, new int[]{100}),
                    new GenePool(new int[]{15, 13, 14, 6}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 13}, new int[]{50, 50}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{15, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}),
                    new GenePool(new int[]{14, 8, 6, 2}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14, 10, 6, 3}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14, 10, 8, 4}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{14, 6}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 14, 13, 11, 7, 6, 10, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{14, 8}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12, 14, 11, 8, 9, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{14, 10}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 14, 11, 10}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 8}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 13, 14, 6}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{14}, new int[]{100}),
                    new GenePool(new int[]{15, 14}, new int[]{50, 50}),
                    new GenePool(new int[]{0}, new int[]{100})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 11, 13, 14, 7, 10, 6, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 14, 11, 8, 9, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13}),
                    new GenePool(new int[]{15, 12, 13, 5}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 13, 14, 6}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 13, 11, 7}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 14, 8}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 12, 11, 9}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 14, 11, 10}, new int[]{25, 25, 25, 25}),
                    new GenePool(new int[]{15, 11}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 12}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 13}, new int[]{50, 50}),
                    new GenePool(new int[]{15, 14}, new int[]{50, 50}),
                    new GenePool(new int[]{15}, new int[]{100}),
                    new GenePool(new int[]{15, 16}, new int[]{50, 50})},
            {new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{0}, new int[]{100}),
                    new GenePool(new int[]{15, 16}, new int[]{50, 50}),
                    new GenePool(new int[]{16, 0, 2, 1, 5, 4, 3, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new int[]{20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}),}
    };

    // Pattern Genetics
    public static GenePool[][] patternGenetics = new GenePool[][]{
            {new GenePool(new int[]{0, 1, 2}, new int[]{80, 10, 10}),
                    new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{0, 2}, new int[]{50, 50})},
            {new GenePool(new int[]{0, 1}, new int[]{50, 50}),
                    new GenePool(new int[]{1}, new int[]{100}),
                    new GenePool(new int[]{1, 2}, new int[]{50, 50})},
            {new GenePool(new int[]{0, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{1, 2}, new int[]{50, 50}),
                    new GenePool(new int[]{2}, new int[]{100})}
    };
    //</editor-fold>

    private static final Ingredient FOOD_ITEMS = getFoodItems();//Ingredient.of(Items.CHICKEN, Items.EGG, Items.RABBIT);

    static final Predicate<Entity> PREY = (p_28498_) -> {
        return p_28498_ instanceof Chicken || p_28498_ instanceof Rabbit || p_28498_ instanceof Parrot;
    };

    public CornSnakeEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, EntityDimensions.scalable(0.4f, 0.3f));
    }

    private static boolean isTemptingItem(ItemStack pStack) {
        return pStack.is(Items.CHICKEN) || pStack.is(Items.RABBIT) || (ModList.get().isLoaded("rats") && pStack.is(RatsItemRegistry.RAW_RAT.get()));
    }

    public boolean isFood(ItemStack pStack) {
        return isTemptingItem(pStack);
    }

    private static Ingredient getFoodItems(){
        return Ingredient.of(
                Items.CHICKEN,
                Items.RABBIT,
                (ModList.get().isLoaded("rats")? RatsItemRegistry.RAW_RAT.get(): Items.CHICKEN)
        );
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
        return BlockInit.CORN_SNAKE_EGG.get();
    }

    @Override
    public String getSpeciesName(int color, int pattern) {
        String snakeType = "";
        switch (color){
            case 0:
                break;
            case 1:
                snakeType += "Amel ";
                break;
            case 2:
                snakeType += "Anery ";
                break;
            case 3:
                snakeType += "Lavender ";
                break;
            case 4:
                snakeType += "Caramel ";
                break;
            case 5:
                snakeType += "Snow ";
                break;
            case 6:
                snakeType += "Moonstone ";
                break;
            case 7:
                snakeType += "Opal ";
                break;
            case 8:
                snakeType += "Anery Caramel ";
                break;
            case 9:
                snakeType += "Butter ";
                break;
            case 10:
                snakeType += "Almond ";
                break;
            case 11:
                snakeType += "Lavender Butter ";
                break;
            case 12:
                snakeType += "Xanthic Snow ";
                break;
            case 13:
                snakeType += "Glacier ";
                break;
            case 14:
                snakeType += "Moonstone Caramel ";
                break;
            case 15:
                snakeType += "Xanthic Snow Lavender ";
                break;
            default:
                snakeType += "Corn Cob ";
                break;

        }
        switch (pattern){
            case 0:
                snakeType += "Normal ";
                break;
            case 1:
                snakeType += "Palmetto ";
                break;
            default:
                snakeType += "Motley ";
                break;

        }
        snakeType += "Corn Snake";
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
        this.setColor(0);
        this.setPattern(0);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public static boolean canSpawn(EntityType<CornSnakeEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return checkSnakeSpawnRules(entityType, level, spawnType, pos, randomSource);
    }

    public static boolean checkSnakeSpawnRules(EntityType<CornSnakeEntity> entity, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return (pLevel.getBlockState(pPos.below()).is(BlockTags.AZALEA_GROWS_ON)) && isBrightEnoughToSpawn(pLevel, pPos);
    }


}
