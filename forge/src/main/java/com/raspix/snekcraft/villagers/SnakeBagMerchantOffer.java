package com.raspix.snekcraft.villagers;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;

/**
 * Pretty sure this is no longer needed since separating the snake bags by type
 */

public class SnakeBagMerchantOffer extends MerchantOffer {
    public SnakeBagMerchantOffer(ItemStack pBaseCostA, ItemStack pResult, int pMaxUses, int pXp, float pPriceMultiplier) {
        super(pBaseCostA, pResult, pMaxUses, pXp, pPriceMultiplier);
    }

    @Override
    public boolean satisfiedBy(ItemStack playerOfferA, ItemStack playerOfferB) {
        if (!ItemStack.isSameItem(playerOfferA, this.getBaseCostA())) { // is this needed?
            return false;
        }
        if (playerOfferA.hasTag()) {
            CompoundTag nbt = playerOfferA.getTag();
            if (nbt != null && nbt.contains("Filled")) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean take(ItemStack playerOfferA, ItemStack playerOfferB) {
        if (!this.satisfiedBy(playerOfferA, playerOfferB)) {
            return false;
        } else {
            //playerOfferA.shrink(this.getCostA().getCount());
            playerOfferA.setTag(new CompoundTag());
            //if (!this.getCostB().isEmpty()) {
            //    playerOfferB.shrink(this.getCostB().getCount());
            //]}

            return true;
        }
    }

}
