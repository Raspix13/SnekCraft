package com.raspix.snekcraft.items;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.blocks.entity.eggs.SnakeEggBlock;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class SnekEggItem extends BlockItem {
	public static final String NBT_KEY_COLOR1 = "color";
	public static final String NBT_KEY_COLOR2 = "color_p2";
	public static final String NBT_KEY_PATTERN1 = "pattern";
	public static final String NBT_KEY_PATTERN2 = "pattern_p2";
	
	public SnekEggItem(Block block, Settings settings) {
		super(block, settings);
	}
	
	public void appendTooltip(ItemStack itemStack, @Nullable World world, List<Text> tooltipTexts, TooltipContext context) {
		super.appendTooltip(itemStack, world, tooltipTexts, context);
		NbtCompound nbtCompound = itemStack.getNbt();
		if(nbtCompound != null && nbtCompound.contains(SnakeEggBlock.NBT_KEY_BLOCK_STATE_TAG)) {
			NbtCompound nbtTag = nbtCompound.getCompound(SnakeEggBlock.NBT_KEY_BLOCK_STATE_TAG);
			int color1 = 0;
			int color2 = 0;
			int pattern1 = 0;
			int pattern2 = 0;
			if(nbtTag.contains(NBT_KEY_COLOR1)) {
				color1 = nbtTag.getInt(NBT_KEY_COLOR1);
			}
			if(nbtTag.contains(NBT_KEY_COLOR2)) {
				color2 = nbtTag.getInt(NBT_KEY_COLOR2);
			}
			tooltipTexts.add(Text.translatable("tooltip.snekcraft.snek_egg.tooltip.colors", color1, color2).formatted(Formatting.GRAY));
			if(nbtTag.contains(NBT_KEY_PATTERN1)) {
				pattern1 = nbtTag.getInt(NBT_KEY_PATTERN1);
			}
			if(nbtTag.contains(NBT_KEY_PATTERN2)) {
				pattern2 = nbtTag.getInt(NBT_KEY_PATTERN2);
			}
			tooltipTexts.add(Text.translatable("tooltip.snekcraft.snek_egg.tooltip.patterns", pattern1, pattern2).formatted(Formatting.GRAY));
		}
	}
}
