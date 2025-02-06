package com.raspix.snekcraft.entity;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.hognose.HognoseEntity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.SpawnRestriction.Location;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntityTypes {
	public static final EntityType<HognoseEntity> HOGNOSE = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HognoseEntity::new).dimensions(EntityDimensions.changing(0.4f, 0.3f)).build();
	
	public static final EntityType<BallPythonEntity> BALLPYTHON = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BallPythonEntity::new).dimensions(EntityDimensions.changing(0.4f, 0.3f)).build();
	
	public static void init(){
		Registry.register(Registries.ENTITY_TYPE, Identifier.of(SnekCraft.MOD_ID, "hognose"), HOGNOSE);
		Registry.register(Registries.ENTITY_TYPE, Identifier.of(SnekCraft.MOD_ID, "ball_python"), BALLPYTHON);
		
		FabricDefaultAttributeRegistry.register(HOGNOSE, HognoseEntity.createLivingAttributes().build());
		FabricDefaultAttributeRegistry.register(BALLPYTHON, BallPythonEntity.createLivingAttributes().build());
		
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.BADLANDS, BiomeKeys.MEADOW), HOGNOSE.getSpawnGroup(), HOGNOSE, 30, 1, 4);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DESERT), HOGNOSE.getSpawnGroup(), HOGNOSE, 60, 1, 4);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.SAVANNA, BiomeKeys.JUNGLE), BALLPYTHON.getSpawnGroup(), BALLPYTHON, 30, 1, 4);
		
		SpawnRestriction.register(HOGNOSE, Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE, HognoseEntity::canSpawn);
		SpawnRestriction.register(BALLPYTHON, Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE, BallPythonEntity::canSpawn);
	}
}
