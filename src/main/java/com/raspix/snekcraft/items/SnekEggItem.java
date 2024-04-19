package com.raspix.snekcraft.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.List;

import javax.annotation.Nullable;
import javax.swing.text.html.parser.TagElement;

public class SnekEggItem extends BlockItem{


    public SnekEggItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        CompoundTag com = pStack.getTag();
        if (com != null && com.contains("BlockStateTag")) {
            CompoundTag compoundtag = com.getCompound("BlockStateTag");
            int c1 = 0;
            int c2 = 0;
            int p1 = 0;
            int p2 = 0;
            if(compoundtag.contains("color")){
                c1 = compoundtag.getInt("color");
            }
            if(compoundtag.contains("color_p2")){
                c2 = compoundtag.getInt("color_p2");
            }
            pTooltip.add((new TranslatableComponent("tooltip.snekcraft.snek_egg.tooltip.colors", c1, c2)).withStyle(ChatFormatting.GRAY));
            if(compoundtag.contains("pattern")){
                p1 = compoundtag.getInt("pattern");
            }
            if(compoundtag.contains("pattern_p2")){
                p2 = compoundtag.getInt("pattern_p2");
            }
            pTooltip.add((new TranslatableComponent("tooltip.snekcraft.snek_egg.tooltip.patterns", p1, p2)).withStyle(ChatFormatting.GRAY));

        }
    }
}
