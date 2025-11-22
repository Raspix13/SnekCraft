package com.raspix.snekcraft.util;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.items.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.ModList;
import vazkii.patchouli.api.PatchouliAPI;

public class PatchouliHandler {

    public static void openBookGUI(ServerPlayer player) {
        PatchouliAPI.get().openBookGUI(player, new ResourceLocation(SnekCraft.MOD_ID, "snekpedia"));
    }

    public static void openBookClient() {
        PatchouliAPI.get().openBookGUI(BuiltInRegistries.ITEM.getKey(ItemInit.SNEKPEDIA.get()));
    }

    public static boolean isPatchouliWorld() {
        if (!(ModList.get().isLoaded("patchouli"))) {
            return false;
        }
        return true;//Minecraft.getInstance().screen instanceof GuiBookEntry;
    }

}