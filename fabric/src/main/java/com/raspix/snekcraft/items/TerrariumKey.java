package com.raspix.snekcraft.items;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class TerrariumKey extends Item {
	
	
	public TerrariumKey(Settings settings) {
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, @Nullable World world, List<Text> tooltipTexts, TooltipContext context) {
		super.appendTooltip(itemStack, world, tooltipTexts, context);
		tooltipTexts.add(Text.translatable("toooltip.snekcraft.terrarium_key.tooltip").formatted(Formatting.GRAY));
	}
}
