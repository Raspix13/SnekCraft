package com.raspix.snekcraft.world;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.ModEntityTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnekCraft.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {

        //Spawning for entities
        if(event.getName().equals(new ResourceLocation("minecraft:desert"))) { //use biome tags here
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HOGNOSE.get(), 60, 2, 4));
        }
        if(event.getName().equals(new ResourceLocation("minecraft:plains"))) { //use biome tags here
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HOGNOSE.get(), 30, 2, 4));
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.BALLPYTHON.get(), 30, 2, 4));
        }
        if(event.getName().equals(new ResourceLocation("minecraft:sunflower_plains"))) { //use biome tags here
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HOGNOSE.get(), 30, 2, 4));
        }
        if(event.getName().equals(new ResourceLocation("minecraft:meadow"))) { //use biome tags here
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HOGNOSE.get(), 30, 2, 4));
        }
        if(event.getName().equals(new ResourceLocation("minecraft:badlands")) || event.getName().equals(new ResourceLocation("minecraft:eroded_badlands")) || event.getName().equals(new ResourceLocation("minecraft:wooded_badlands"))) { //use biome tags here
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HOGNOSE.get(), 30, 2, 4));
        }
        if(event.getName().equals(new ResourceLocation("minecraft:savanna"))) { //use biome tags here
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.BALLPYTHON.get(), 30, 2, 4));
        }
        if(event.getName().equals(new ResourceLocation("minecraft:jungle")) || event.getName().equals(new ResourceLocation("minecraft:sparse_jungle"))) { //use biome tags here
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.BALLPYTHON.get(), 30, 2, 4));
        }

    }
}
