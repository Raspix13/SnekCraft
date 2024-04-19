package com.raspix.snekcraft.blocks.entity;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.BlockInit;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SnekCraft.MOD_ID);

    /**public static final RegistryObject<BlockEntityType<SnakeEggBlockEntity>> SNAKE_EGG =
            BLOCK_ENTITIES.register("snake_egg", () -> BlockEntityType.Builder.of
                    (SnakeEggBlockEntity::new,
                            BlockInit.BALL_PYTHON_EGG.get(),
                            BlockInit.SNAKE_EGG.get())
                    .build(null));*/

    public static final RegistryObject<BlockEntityType<SnakeEggBlockEntity>> SNAKE_EGG =
            BLOCK_ENTITIES.register("snake_egg", () -> BlockEntityType.Builder.of
                            (SnakeEggBlockEntity::new,
                                    BlockInit.TEST_EGG.get())
                    .build(null));

}
