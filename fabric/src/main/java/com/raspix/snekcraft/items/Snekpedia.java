package com.raspix.snekcraft.items;


import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.minecraft.item.ItemStack;
import vazkii.patchouli.api.PatchouliAPI;
import vazkii.patchouli.common.book.Book;
import vazkii.patchouli.common.book.BookRegistry;

import java.util.List;

public class Snekpedia extends Item {
    public Snekpedia(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, @Nullable World world, List<Text> tooltipTexts, TooltipContext context) {
        super.appendTooltip(itemStack, world, tooltipTexts, context);
        tooltipTexts.add(Text.translatable("tooltip.snekcraft.snekpedia.tooltip").formatted(Formatting.GRAY));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getActiveItem();
        if (FabricLoader.getInstance().isModLoaded("patchouli") && user instanceof ServerPlayerEntity player) {
            Book book = BookRegistry.INSTANCE.books.get(Registries.ITEM.getId(this));
            PatchouliAPI.get().openBookGUI(player, book.id);
        }

        return super.use(world, user, hand);
    }

}
