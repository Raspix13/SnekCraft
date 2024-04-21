package com.raspix.snekcraft;

import com.mojang.logging.LogUtils;
import com.raspix.snekcraft.blocks.BlockInit;

import com.raspix.snekcraft.items.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SnekCraft.MOD_ID)
public class SnekCraft
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "snekcraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> SNEKERS_TAB = CREATIVE_MODE_TABS.register("snekers_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemInit.SNAKE_SKIN.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemInit.SNAKE_SKIN.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(ItemInit.SNAKE_TOOTH.get());
  //              output.accept(ItemInit.SNAKE_BAG.get());
                //output.accept(ItemInit.HOGNOSE_SPAWN_EGG.get());
                //output.accept(ItemInit.BALL_PYTHON_SPAWN_EGG.get());
                output.accept(ItemInit.TERRARIUM_KEY.get());
                output.accept(ItemInit.TERRARIUM_ITEM.get());
                //output.accept(ItemInit.AQUARIUM_ITEM.get());
                output.accept(ItemInit.WIRE_CAGE_ITEM.get());
                output.accept(ItemInit.LAVAQUARIUM_ITEM.get());
 //               output.accept(ItemInit.HOGGIE_EGG.get());
 //               output.accept(ItemInit.BALL_PYTHON_EGG.get());
                output.accept(ItemInit.DIRT_HIDE.get());
                output.accept(ItemInit.STONE_HIDE.get());
                output.accept(ItemInit.STONE_BRICK_HIDE.get());
                output.accept(ItemInit.MOSSY_BRICK_HIDE.get());
                output.accept(ItemInit.SANDSTONE_HIDE.get());
                output.accept(ItemInit.SPRUCE_HIDE.get());
                output.accept(ItemInit.ACACIA_HIDE.get());
                output.accept(ItemInit.BIRCH_HIDE.get());
                output.accept(ItemInit.DARK_OAK_HIDE.get());
                output.accept(ItemInit.JUNGLE_HIDE.get());
                output.accept(ItemInit.MANGROVE_HIDE.get());
                output.accept(ItemInit.OAK_HIDE.get());
                output.accept(ItemInit.DIRT_MEDIUM_HIDE.get());
                output.accept(ItemInit.STONE_MEDIUM_HIDE.get());
                output.accept(ItemInit.STONE_BRICK_MEDIUM_HIDE.get());
                output.accept(ItemInit.MOSSY_BRICK_MEDIUM_HIDE.get());
                output.accept(ItemInit.SANDSTONE_MEDIUM_HIDE.get());
                output.accept(ItemInit.SPRUCE_MEDIUM_HIDE.get());
                output.accept(ItemInit.ACACIA_MEDIUM_HIDE.get());
                output.accept(ItemInit.BIRCH_MEDIUM_HIDE.get());
                output.accept(ItemInit.DARK_OAK_MEDIUM_HIDE.get());
                output.accept(ItemInit.JUNGLE_MEDIUM_HIDE.get());
                output.accept(ItemInit.MANGROVE_MEDIUM_HIDE.get());
                output.accept(ItemInit.OAK_MEDIUM_HIDE.get());
                output.accept(ItemInit.HEAT_LAMP.get());
                output.accept(ItemInit.LAMP_POST.get());
                output.accept(ItemInit.SNAKE_SKIN.get());
                output.accept(ItemInit.SNAKE_SKIN.get());
            }).build());

    public SnekCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
//        FluidInit.FLUIDS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ItemInit.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
  //      if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
 //           event.accept(EXAMPLE_BLOCK_ITEM);

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            /**ItemProperties.register(ItemInit.SNAKE_BAG.get(), new ResourceLocation("snake_count"), (stack, p_239428_1_, p_239428_2_, other)-> {
                return Math.min(1, SnakeBagItem.getSnakesInStack(stack));
            });*/

            ItemBlockRenderTypes.setRenderLayer((BlockInit.WIRE_CAGE.get()), RenderType.translucent());
            //ItemBlockRenderTypes.setRenderLayer((BlockInit.AQUARIUM.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.TERRARIUM.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.LAVAQUARIUM.get()), RenderType.translucent());

            //ItemBlockRenderTypes.setRenderLayer((FluidInit.FAKE_FLUID.get()), RenderType.translucent());
            //ItemBlockRenderTypes.setRenderLayer((FluidInit.FAKE_FLOWING.get()), RenderType.translucent());
            //ItemBlockRenderTypes.setRenderLayer((BlockInit.FAKE_FLUID_BLOCK.get()), RenderType.translucent());
        }
    }
}
