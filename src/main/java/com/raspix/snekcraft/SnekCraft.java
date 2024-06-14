package com.raspix.snekcraft;

import com.mojang.logging.LogUtils;
import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.blocks.entity.BlockEntityInit;
import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.ball_python.BallPythonModel;
import com.raspix.snekcraft.entity.ball_python.BallPythonRenderer;
import com.raspix.snekcraft.entity.hognose.HognoseEntity;
import com.raspix.snekcraft.entity.hognose.HognoseModel;
import com.raspix.snekcraft.entity.hognose.HognoseRenderer;
import com.raspix.snekcraft.items.ItemInit;
import com.raspix.snekcraft.items.SnakeBagItem;
import com.raspix.snekcraft.loot.ModLootModifiers;
import com.raspix.snekcraft.packets.PacketHandler;
import com.raspix.snekcraft.sounds.SoundInit;
import com.raspix.snekcraft.util.KeyInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
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
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SnekCraft.MOD_ID)
public class SnekCraft {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "snekcraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "snekcraft" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public SnekCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        SoundInit.SOUNDS.register(modEventBus);

        ModEntityTypes.ENTITIES.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);

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
        /**if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);*/
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = SnekCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ClientEventBusSubscriber {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer((BlockInit.WIRE_CAGE.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.AQUARIUM.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.TERRARIUM.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.LAVAQUARIUM.get()), RenderType.translucent());

        }
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

            ItemProperties.register(ItemInit.SNAKE_BAG.get(), new ResourceLocation("snake_count"), (stack, p_239428_1_, p_239428_2_, other)-> {
                return Math.min(1, SnakeBagItem.getSnakesInStack(stack));
            });
        }

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event){
            event.register(KeyInit.shoulderKey);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
            event.registerEntityRenderer(ModEntityTypes.HOGNOSE.get(), HognoseRenderer::new);
            event.registerEntityRenderer(ModEntityTypes.BALLPYTHON.get(), BallPythonRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(HognoseModel.LAYER_LOCATION, HognoseModel::createBodyLayer);
            event.registerLayerDefinition(BallPythonModel.LAYER_LOCATION, BallPythonModel::createBodyLayer);
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CommonModEvents
    {
        @SubscribeEvent
        public static void entityAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.HOGNOSE.get(), HognoseEntity.createLivingAttributes().build());
            event.put(ModEntityTypes.BALLPYTHON.get(), BallPythonEntity.createLivingAttributes().build());
        }

        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event){
            event.enqueueWork(PacketHandler::init);

        }

        @SubscribeEvent
        public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event){
            event.register(ModEntityTypes.HOGNOSE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,
                    HognoseEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);

            event.register(ModEntityTypes.BALLPYTHON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,
                    BallPythonEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        }
    }

}
