package com.raspix.snekcraft.items;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.entity.ModEntityTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    private ItemInit(){}
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SnekCraft.MOD_ID);
    public static CreativeModeTab modTab = SnekCraft.SNEKERS_TAB;

    public static final RegistryObject<Item> SNAKE_SKIN = ITEMS.register("snake_skin", () -> new Item(new Item.Properties().tab(modTab)));
    public static final RegistryObject<Item> SNAKE_TOOTH = ITEMS.register("snake_tooth", () -> new Item(new Item.Properties().tab(modTab)));
    public static final RegistryObject<SnakeBagItem> SNAKE_BAG = ITEMS.register("snake_bag", () -> new SnakeBagItem(new Item.Properties().tab(modTab).stacksTo(1)));
    public static final RegistryObject<ForgeSpawnEggItem> HOGNOSE_SPAWN_EGG = ITEMS.register("hognose_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.HOGNOSE, 0xd6bf96, 0x40351c, new Item.Properties().tab(modTab)));
    public static final RegistryObject<ForgeSpawnEggItem> BALL_PYTHON_SPAWN_EGG = ITEMS.register("ball_python_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.BALLPYTHON, 0x352213, 0xb17c3b, new Item.Properties().tab(modTab)));

    public static final RegistryObject<Item> TERRARIUM_KEY = ITEMS.register("terrarium_key", () -> new TerrariumKey(new Item.Properties().tab(modTab)));
//    public static final RegistryObject<Item> SNAKE_GUIDE = ITEMS.register("snake_guide", () -> new TerrariumKey(new Item.Properties().tab(modTab)));

    public static final RegistryObject<BlockItem> TERRARIUM_ITEM = ITEMS.register("terrarium", () -> new BlockItem(BlockInit.TERRARIUM.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> AQUARIUM_ITEM = ITEMS.register("aquarium", () -> new BlockItem(BlockInit.AQUARIUM.get(), new Item.Properties().craftRemainder(TERRARIUM_ITEM.get()).tab(modTab)));
    public static final RegistryObject<BlockItem> WIRE_CAGE_ITEM = ITEMS.register("wire_cage", () -> new BlockItem(BlockInit.WIRE_CAGE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> LAVAQUARIUM_ITEM = ITEMS.register("lavaquarium", () -> new BlockItem(BlockInit.LAVAQUARIUM.get(), new Item.Properties().craftRemainder(TERRARIUM_ITEM.get()).tab(modTab)));

    public static final RegistryObject<BlockItem> HOGGIE_EGG = ITEMS.register("hoggie_egg", () -> new SnekEggItem(BlockInit.SNAKE_EGG.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> BALL_PYTHON_EGG = ITEMS.register("ball_python_egg", () -> new SnekEggItem(BlockInit.BALL_PYTHON_EGG.get(), new Item.Properties().tab(modTab)));
    //public static final RegistryObject<BlockItem> TEST_EGG = ITEMS.register("test_egg", () -> new BlockItem(BlockInit.TEST_EGG.get(), new Item.Properties().tab(modTab)));

    public static final RegistryObject<BlockItem> DIRT_HIDE = ITEMS.register("dirt_hide", () -> new BlockItem(BlockInit.DIRT_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> STONE_HIDE = ITEMS.register("stone_hide", () -> new BlockItem(BlockInit.STONE_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> STONE_BRICK_HIDE = ITEMS.register("stone_brick_hide", () -> new BlockItem(BlockInit.STONE_BRICK_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> MOSSY_BRICK_HIDE = ITEMS.register("mossy_brick_hide", () -> new BlockItem(BlockInit.MOSSY_BRICK_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> SANDSTONE_HIDE = ITEMS.register("sandstone_hide", () -> new BlockItem(BlockInit.SANDSTONE_HIDE.get(), new Item.Properties().tab(modTab)));

    public static final RegistryObject<BlockItem> SPRUCE_HIDE = ITEMS.register("spruce_hide", () -> new HideItem(BlockInit.SPRUCE_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> ACACIA_HIDE = ITEMS.register("acacia_hide", () -> new HideItem(BlockInit.ACACIA_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> BIRCH_HIDE = ITEMS.register("birch_hide", () -> new HideItem(BlockInit.BIRCH_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> DARK_OAK_HIDE = ITEMS.register("dark_oak_hide", () -> new HideItem(BlockInit.DARK_OAK_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> JUNGLE_HIDE = ITEMS.register("jungle_hide", () -> new HideItem(BlockInit.JUNGLE_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> MANGROVE_HIDE = ITEMS.register("mangrove_hide", () -> new HideItem(BlockInit.MANGROVE_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> OAK_HIDE = ITEMS.register("oak_hide", () -> new HideItem(BlockInit.OAK_HIDE.get(), new Item.Properties().tab(modTab)));

    public static final RegistryObject<BlockItem> DIRT_MEDIUM_HIDE = ITEMS.register("dirt_medium_hide", () -> new BlockItem(BlockInit.DIRT_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> STONE_MEDIUM_HIDE = ITEMS.register("stone_medium_hide", () -> new BlockItem(BlockInit.STONE_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> STONE_BRICK_MEDIUM_HIDE = ITEMS.register("stone_brick_medium_hide", () -> new BlockItem(BlockInit.STONE_BRICK_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> MOSSY_BRICK_MEDIUM_HIDE = ITEMS.register("mossy_brick_medium_hide", () -> new BlockItem(BlockInit.MOSSY_BRICK_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> SANDSTONE_MEDIUM_HIDE = ITEMS.register("sandstone_medium_hide", () -> new BlockItem(BlockInit.SANDSTONE_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));

    public static final RegistryObject<BlockItem> SPRUCE_MEDIUM_HIDE = ITEMS.register("spruce_medium_hide", () -> new HideItem(BlockInit.SPRUCE_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> ACACIA_MEDIUM_HIDE = ITEMS.register("acacia_medium_hide", () -> new HideItem(BlockInit.ACACIA_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> BIRCH_MEDIUM_HIDE = ITEMS.register("birch_medium_hide", () -> new HideItem(BlockInit.BIRCH_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> DARK_OAK_MEDIUM_HIDE = ITEMS.register("dark_oak_medium_hide", () -> new HideItem(BlockInit.DARK_OAK_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> JUNGLE_MEDIUM_HIDE = ITEMS.register("jungle_medium_hide", () -> new HideItem(BlockInit.JUNGLE_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> MANGROVE_MEDIUM_HIDE = ITEMS.register("mangrove_medium_hide", () -> new HideItem(BlockInit.MANGROVE_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> OAK_MEDIUM_HIDE = ITEMS.register("oak_medium_hide", () -> new BlockItem(BlockInit.OAK_MEDIUM_HIDE.get(), new Item.Properties().tab(modTab)));

    public static final RegistryObject<BlockItem> HEAT_LAMP = ITEMS.register("heat_lamp", () -> new BlockItem(BlockInit.HEAT_LAMP.get(), new Item.Properties().tab(modTab)));
    public static final RegistryObject<BlockItem> LAMP_POST = ITEMS.register("lamp_post", () -> new BlockItem(BlockInit.LAMP_POST.get(), new Item.Properties().tab(modTab)));


}
