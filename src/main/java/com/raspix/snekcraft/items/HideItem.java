package com.raspix.snekcraft.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class HideItem extends BlockItem {
    public HideItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 15;//super.getBurnTime(itemStack, recipeType);
    }
}
