package com.raspix.snekcraft;

import com.mojang.logging.LogUtils;
import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.blocks.entity.BlockEntityInit;
import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.ball_python.BallPythonRenderer;
import com.raspix.snekcraft.entity.hognose.HognoseEntity;
import com.raspix.snekcraft.entity.hognose.HognoseRenderer;
import com.raspix.snekcraft.fluid.FluidInit;
import com.raspix.snekcraft.items.ItemInit;
import com.raspix.snekcraft.items.SnakeBagItem;
import com.raspix.snekcraft.packets.PacketHandler;
import com.raspix.snekcraft.sounds.SoundInit;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SnekCraft.MOD_ID)
public class SnekCraft
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "snekcraft";

    public SnekCraft() {
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::setup);
        ModEntityTypes.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        FluidInit.VANILLA_FLUIDS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        SoundInit.SOUNDS.register(modEventBus);
        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);

        GeckoLib.initialize();
    }

    @Mod.EventBusSubscriber(modid = SnekCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ClientEventBusSubscriber {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer((BlockInit.WIRE_CAGE.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.AQUARIUM.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.TERRARIUM.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.LAVAQUARIUM.get()), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer((FluidInit.FAKE_FLUID.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((FluidInit.FAKE_FLOWING.get()), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer((BlockInit.FAKE_FLUID_BLOCK.get()), RenderType.translucent());
        }
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.HOGNOSE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, HognoseEntity::canSpawn);
            SpawnPlacements.register(ModEntityTypes.BALLPYTHON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, BallPythonEntity::canSpawn);
        });


    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetUp(FMLClientSetupEvent event){
            EntityRenderers.register(ModEntityTypes.HOGNOSE.get(), HognoseRenderer::new);
            EntityRenderers.register(ModEntityTypes.BALLPYTHON.get(), BallPythonRenderer::new);
            ItemProperties.register(ItemInit.SNAKE_BAG.get(), new ResourceLocation("snake_count"), (stack, p_239428_1_, p_239428_2_, other)-> {
                return Math.min(1, SnakeBagItem.getSnakesInStack(stack));
            }); // adds predicate value thing for item model
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }

    @Mod.EventBusSubscriber(modid = SnekCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CommonModEvents {

        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event){
            event.enqueueWork(PacketHandler::init);

        }

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.HOGNOSE.get(), HognoseEntity.setAttributes());
            event.put(ModEntityTypes.BALLPYTHON.get(), BallPythonEntity.setAttributes());
        }
    }

    public static final CreativeModeTab SNEKERS_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return ItemInit.SNAKE_SKIN.get().getDefaultInstance();
        }
    };
}
