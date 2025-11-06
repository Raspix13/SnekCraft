package com.raspix.snekcraft.entity.corn;

import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.generics.GenePool;
import com.raspix.snekcraft.entity.generics.SnakeBase;
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
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.ParrotEntity;
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
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class CornSnakeEntity extends SnakeBase {

    // NTA: , sounds, aistep(drops),
    private static int maxPattern = 3;
    private static int maxColor = 16;

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
                    new GenePool(new int[]{15, 12, 13, 11, 5, 9, 7, 1}, new int[]{13, 13, 13, 13, 13, 13, 13, 13})},
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
                    new GenePool(new int[]{15, 12, 13, 14, 5, 8, 6, 2}, new int[]{13, 13, 13, 13, 13, 13, 13, 13})},
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
                    new GenePool(new int[]{15, 11, 13, 14, 7, 10, 6, 3}, new int[]{13, 13, 13, 13, 13, 13, 13, 13})},
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
                    new GenePool(new int[]{15, 12, 14, 11, 8, 9, 10, 4}, new int[]{13, 13, 13, 13, 13, 13, 13, 13})},
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
                    new GenePool(new int[]{15, 12, 13, 5}, new int[]{25, 25, 25, 25})},
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
                    new GenePool(new int[]{15, 13, 14, 6}, new int[]{25, 25, 25, 25})},
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
                    new GenePool(new int[]{15, 13, 11, 7}, new int[]{25, 25, 25, 25})},
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
                    new GenePool(new int[]{15, 12, 14, 8}, new int[]{25, 25, 25, 25})},
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
                    new GenePool(new int[]{15, 12, 11, 9}, new int[]{25, 25, 25, 25})},
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
                    new GenePool(new int[]{15, 14, 11, 10}, new int[]{25, 25, 25, 25})},
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
                    new GenePool(new int[]{15, 11}, new int[]{50, 50})},
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
                    new GenePool(new int[]{15, 12}, new int[]{50, 50})},
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
                    new GenePool(new int[]{15, 13}, new int[]{50, 50})},
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
                    new GenePool(new int[]{15, 14}, new int[]{50, 50})},
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
                    new GenePool(new int[]{15}, new int[]{100})}
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


    private static final Ingredient FOOD_ITEMS = Ingredient.ofItems(Items.CHICKEN, Items.RABBIT);

    static final Predicate<Entity> PREY = (p_28498_) -> {
        return p_28498_ instanceof ChickenEntity || p_28498_ instanceof RabbitEntity || p_28498_ instanceof ParrotEntity;
    };

    public CornSnakeEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
        return BlockInit.CORN_SNAKE_EGG;
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

    public static boolean canSpawn(EntityType<CornSnakeEntity> entityType, ServerWorldAccess worldAccess, SpawnReason spawnReason, BlockPos blockPos, Random random) {
        return worldAccess.getBlockState(blockPos.down()).isIn(BlockTags.AZALEA_GROWS_ON) && isLightLevelValidForNaturalSpawn(worldAccess, blockPos);
    }



}
