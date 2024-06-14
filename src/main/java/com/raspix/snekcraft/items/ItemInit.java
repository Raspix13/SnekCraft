package com.raspix.snekcraft.items;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.entity.ModEntityTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.checker.units.qual.A;

public class ItemInit {

    private ItemInit(){}
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SnekCraft.MOD_ID);
;
    //public static CreativeModeTab modTab = SnekCraft.SNEKERS_TAB;

    public static final RegistryObject<Item> SNAKE_SKIN = ITEMS.register("snake_skin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SNAKE_TOOTH = ITEMS.register("snake_tooth", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FROG_LEG = ITEMS.register("frog_leg", () -> new Item(new Item.Properties().food(Foods.PORKCHOP)));
    public static final RegistryObject<Item> COOKED_FROG_LEG = ITEMS.register("cooked_frog_leg", () -> new Item(new Item.Properties().food(Foods.COOKED_PORKCHOP)));
    public static final RegistryObject<SnakeBagItem> SNAKE_BAG = ITEMS.register("snake_bag", () -> new SnakeBagItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<ForgeSpawnEggItem> HOGNOSE_SPAWN_EGG = ITEMS.register("hognose_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.HOGNOSE, 0xd6bf96, 0x40351c, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> BALL_PYTHON_SPAWN_EGG = ITEMS.register("ball_python_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.BALLPYTHON, 0x352213, 0xb17c3b, new Item.Properties()));
    public static final RegistryObject<BlockItem> HOGGIE_EGG = ITEMS.register("hoggie_egg", () -> new SnekEggItem(BlockInit.SNAKE_EGG.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BALL_PYTHON_EGG = ITEMS.register("ball_python_egg", () -> new SnekEggItem(BlockInit.BALL_PYTHON_EGG.get(), new Item.Properties()));

    public static final RegistryObject<Item> TERRARIUM_KEY = ITEMS.register("terrarium_key", () -> new TerrariumKey(new Item.Properties()));
    public static final RegistryObject<BlockItem> TERRARIUM_ITEM = ITEMS.register("terrarium", () -> new BlockItem(BlockInit.TERRARIUM.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> AQUARIUM_ITEM = ITEMS.register("aquarium", () -> new BlockItem(BlockInit.AQUARIUM.get(), new Item.Properties().craftRemainder(TERRARIUM_ITEM.get())));
    public static final RegistryObject<BlockItem> WIRE_CAGE_ITEM = ITEMS.register("wire_cage", () -> new BlockItem(BlockInit.WIRE_CAGE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> LAVAQUARIUM_ITEM = ITEMS.register("lavaquarium", () -> new BlockItem(BlockInit.LAVAQUARIUM.get(), new Item.Properties().craftRemainder(TERRARIUM_ITEM.get())));


    //<editor-fold desc="Small Hide and Tunnel Block Item RegistryObject">
    public static final RegistryObject<BlockItem> DIRT_HIDE = ITEMS.register("dirt_hide", () -> new BlockItem(BlockInit.DIRT_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> STONE_HIDE = ITEMS.register("stone_hide", () -> new BlockItem(BlockInit.STONE_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> STONE_BRICK_HIDE = ITEMS.register("stone_brick_hide", () -> new BlockItem(BlockInit.STONE_BRICK_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOSSY_BRICK_HIDE = ITEMS.register("mossy_brick_hide", () -> new BlockItem(BlockInit.MOSSY_BRICK_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SANDSTONE_HIDE = ITEMS.register("sandstone_hide", () -> new BlockItem(BlockInit.SANDSTONE_HIDE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> SPRUCE_HIDE = ITEMS.register("spruce_hide", () -> new HideItem(BlockInit.SPRUCE_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ACACIA_HIDE = ITEMS.register("acacia_hide", () -> new HideItem(BlockInit.ACACIA_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BIRCH_HIDE = ITEMS.register("birch_hide", () -> new HideItem(BlockInit.BIRCH_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DARK_OAK_HIDE = ITEMS.register("dark_oak_hide", () -> new HideItem(BlockInit.DARK_OAK_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> JUNGLE_HIDE = ITEMS.register("jungle_hide", () -> new HideItem(BlockInit.JUNGLE_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MANGROVE_HIDE = ITEMS.register("mangrove_hide", () -> new HideItem(BlockInit.MANGROVE_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> OAK_HIDE = ITEMS.register("oak_hide", () -> new HideItem(BlockInit.OAK_HIDE.get(), new Item.Properties()));

    //</editor-fold>


    //<editor-fold desc="Large Hide and Tunnel Block RegistryObject">
    public static final RegistryObject<BlockItem> DIRT_MEDIUM_HIDE = ITEMS.register("dirt_medium_hide", () -> new BlockItem(BlockInit.DIRT_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> STONE_MEDIUM_HIDE = ITEMS.register("stone_medium_hide", () -> new BlockItem(BlockInit.STONE_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> STONE_BRICK_MEDIUM_HIDE = ITEMS.register("stone_brick_medium_hide", () -> new BlockItem(BlockInit.STONE_BRICK_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOSSY_BRICK_MEDIUM_HIDE = ITEMS.register("mossy_brick_medium_hide", () -> new BlockItem(BlockInit.MOSSY_BRICK_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SANDSTONE_MEDIUM_HIDE = ITEMS.register("sandstone_medium_hide", () -> new BlockItem(BlockInit.SANDSTONE_MEDIUM_HIDE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> SPRUCE_MEDIUM_HIDE = ITEMS.register("spruce_medium_hide", () -> new HideItem(BlockInit.SPRUCE_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ACACIA_MEDIUM_HIDE = ITEMS.register("acacia_medium_hide", () -> new HideItem(BlockInit.ACACIA_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BIRCH_MEDIUM_HIDE = ITEMS.register("birch_medium_hide", () -> new HideItem(BlockInit.BIRCH_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DARK_OAK_MEDIUM_HIDE = ITEMS.register("dark_oak_medium_hide", () -> new HideItem(BlockInit.DARK_OAK_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> JUNGLE_MEDIUM_HIDE = ITEMS.register("jungle_medium_hide", () -> new HideItem(BlockInit.JUNGLE_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MANGROVE_MEDIUM_HIDE = ITEMS.register("mangrove_medium_hide", () -> new HideItem(BlockInit.MANGROVE_MEDIUM_HIDE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> OAK_MEDIUM_HIDE = ITEMS.register("oak_medium_hide", () -> new BlockItem(BlockInit.OAK_MEDIUM_HIDE.get(), new Item.Properties()));
    //</editor-fold>
    
    
    public static final RegistryObject<BlockItem> HEAT_LAMP = ITEMS.register("heat_lamp", () -> new BlockItem(BlockInit.HEAT_LAMP.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> LAMP_POST = ITEMS.register("lamp_post", () -> new BlockItem(BlockInit.LAMP_POST.get(), new Item.Properties()));


    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = SnekCraft.CREATIVE_MODE_TABS.register("snekcraft_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("creativetab.snake_tab"))
            .icon(() -> SNAKE_SKIN.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(SNAKE_SKIN.get());
                output.accept(SNAKE_TOOTH.get());
                output.accept(FROG_LEG.get());
                output.accept(COOKED_FROG_LEG.get());
                output.accept(SNAKE_BAG.get());
                output.accept(HOGNOSE_SPAWN_EGG.get());
                output.accept(BALL_PYTHON_SPAWN_EGG.get());

                output.accept(TERRARIUM_KEY.get());
                output.accept(TERRARIUM_ITEM.get());
                output.accept(WIRE_CAGE_ITEM.get());
                output.accept(AQUARIUM_ITEM.get());
                output.accept(LAVAQUARIUM_ITEM.get());

                output.accept(HOGGIE_EGG.get());
                output.accept(BALL_PYTHON_EGG.get());
                output.accept(HEAT_LAMP.get());
                output.accept(LAMP_POST.get());

                output.accept(DIRT_HIDE.get());
                output.accept(DIRT_MEDIUM_HIDE.get());
                output.accept(STONE_HIDE.get());
                output.accept(STONE_MEDIUM_HIDE.get());
                output.accept(STONE_BRICK_HIDE.get());
                output.accept(STONE_BRICK_MEDIUM_HIDE.get());
                output.accept(MOSSY_BRICK_HIDE.get());
                output.accept(MOSSY_BRICK_MEDIUM_HIDE.get());
                output.accept(SANDSTONE_HIDE.get());
                output.accept(SANDSTONE_MEDIUM_HIDE.get());
                output.accept(SPRUCE_HIDE.get());
                output.accept(SPRUCE_MEDIUM_HIDE.get());
                output.accept(ACACIA_HIDE.get());
                output.accept(ACACIA_MEDIUM_HIDE.get());
                output.accept(BIRCH_HIDE.get());
                output.accept(BIRCH_MEDIUM_HIDE.get());
                output.accept(DARK_OAK_HIDE.get());
                output.accept(DARK_OAK_MEDIUM_HIDE.get());
                output.accept(JUNGLE_HIDE.get());
                output.accept(JUNGLE_MEDIUM_HIDE.get());
                output.accept(MANGROVE_HIDE.get());
                output.accept(MANGROVE_MEDIUM_HIDE.get());
                output.accept(OAK_HIDE.get());
                output.accept(OAK_MEDIUM_HIDE.get());

            }).build());
}
