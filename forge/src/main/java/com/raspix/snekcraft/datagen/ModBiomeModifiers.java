package com.raspix.snekcraft.datagen;

import com.raspix.snekcraft.Config;
import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.ModEntityTypes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {

    // file saved to /forge/src/generated[main]/resources/data/snekcraft/forge/biome_modifier
    public static final ResourceKey<BiomeModifier> SPAWN_HOGNOSE = registerKey("spawn_hognose");
    public static final ResourceKey<BiomeModifier> SPAWN_DESERT_HOGNOSE = registerKey("spawn_desert_hognose");
    public static final ResourceKey<BiomeModifier> SPAWN_BALL_PYTHON = registerKey("spawn_ball_python");
    public static final ResourceKey<BiomeModifier> SPAWN_CORN_SNAKE = registerKey("spawn_corn_snake");


    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        System.out.println("HI I AM DOING THE SPAWN DATA");
        var biomes = context.lookup(Registries.BIOME);

        context.register(SPAWN_HOGNOSE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS), biomes.getOrThrow(Biomes.BADLANDS), biomes.getOrThrow(Biomes.MEADOW)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntityTypes.HOGNOSE.get(), Config.hognoseSpawnWeight, 1, 4))));

        context.register(SPAWN_DESERT_HOGNOSE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DESERT)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntityTypes.HOGNOSE.get(), Config.hognoseDesertSpawnWeight, 1, 4))));

        context.register(SPAWN_BALL_PYTHON, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SAVANNA), biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.JUNGLE)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntityTypes.BALLPYTHON.get(), Config.ballpythonSpawnWeight, 1, 4))));

        context.register(SPAWN_CORN_SNAKE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntityTypes.CORNSNAKE.get(), Config.cornSnakeSpawnWeight, 1, 4))));


        //System.out.println("WEIGHT DATA: " + Config.hognoseSpawnWeight + ", " + Config.ballpythonSpawnWeight);
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(SnekCraft.MOD_ID, name));
    }
}