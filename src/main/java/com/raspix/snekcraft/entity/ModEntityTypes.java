package com.raspix.snekcraft.entity;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.hognose.HognoseEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.raspix.snekcraft.SnekCraft.MOD_ID;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SnekCraft.MOD_ID);

    public static final RegistryObject<EntityType<HognoseEntity>> HOGNOSE = ENTITIES.register("hognose",
            () -> EntityType.Builder.of(HognoseEntity::new, MobCategory.CREATURE).sized(0.4f, 0.3f)
                    .build(new ResourceLocation(SnekCraft.MOD_ID, "hognose").toString()));

    public static final RegistryObject<EntityType<BallPythonEntity>> BALLPYTHON = ENTITIES.register("ball_python",
            () -> EntityType.Builder.of(BallPythonEntity::new, MobCategory.CREATURE).sized(0.4f, 0.3f)
                    .build(new ResourceLocation(SnekCraft.MOD_ID, "ball_python").toString()));

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }


}
