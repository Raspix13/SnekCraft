package com.raspix.snekcraft.datagen;

import com.raspix.snekcraft.SnekCraft;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.core.HolderLookup;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(SnekCraft.MOD_ID));
        System.out.println("HI I AM LOADING MODWORLDGENPROVIDER");
        System.out.println("DEBUG: PackOutput is directed to: " + output.getOutputFolder().toAbsolutePath());
    }
}