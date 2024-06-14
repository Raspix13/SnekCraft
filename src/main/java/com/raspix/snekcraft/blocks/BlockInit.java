package com.raspix.snekcraft.blocks;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.eggs.BallPythonEggBlock;
import com.raspix.snekcraft.blocks.eggs.HognoseEggBlock;
import com.raspix.snekcraft.blocks.terrariums.AquariumBlock;
import com.raspix.snekcraft.blocks.terrariums.CageBlock;
import com.raspix.snekcraft.blocks.terrariums.LavaquariumBlock;
import com.raspix.snekcraft.blocks.terrariums.TerrariumBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SnekCraft.MOD_ID);


    public static final RegistryObject<Block> TERRARIUM = BLOCKS.register("terrarium", () -> new TerrariumBlock(BlockBehaviour.Properties.of().strength(0.3F).sound(SoundType.GLASS).noOcclusion().dynamicShape().isValidSpawn(BlockInit::never).isRedstoneConductor(BlockInit::never).isSuffocating(BlockInit::never)));
    public static final RegistryObject<Block> AQUARIUM = BLOCKS.register("aquarium", () -> new AquariumBlock(BlockBehaviour.Properties.of().strength(0.3F).sound(SoundType.GLASS).noOcclusion().dynamicShape().isValidSpawn(BlockInit::never).isRedstoneConductor(BlockInit::never).isSuffocating(BlockInit::never)));
    public static final RegistryObject<Block> WIRE_CAGE = BLOCKS.register("wire_cage", () -> new CageBlock(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().dynamicShape().isValidSpawn(BlockInit::never).isRedstoneConductor(BlockInit::never).isSuffocating(BlockInit::never)));//.lightLevel((state)-> state.getValue(CageBlock.CLICKED)?15:0)
    public static final RegistryObject<Block> LAVAQUARIUM = BLOCKS.register("lavaquarium", () -> new LavaquariumBlock(BlockBehaviour.Properties.of().strength(0.3F).sound(SoundType.GLASS).noOcclusion().dynamicShape().isValidSpawn(BlockInit::never).isRedstoneConductor(BlockInit::never).isSuffocating(BlockInit::never).lightLevel((p_50755_) -> {
        return 15;
    })));

    //<editor-fold desc="Small Hide and Tunnel Block RegistryObject">

    public static final RegistryObject<Block> DIRT_HIDE = BLOCKS.register("dirt_hide", () -> new CaveHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> STONE_HIDE = BLOCKS.register("stone_hide", () -> new CaveHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> STONE_BRICK_HIDE = BLOCKS.register("stone_brick_hide", () -> new CaveHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> MOSSY_BRICK_HIDE = BLOCKS.register("mossy_brick_hide", () -> new CaveHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SANDSTONE_HIDE = BLOCKS.register("sandstone_hide", () -> new CaveHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<Block> SPRUCE_HIDE = BLOCKS.register("spruce_hide", () -> new TunnelHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_HIDE = BLOCKS.register("acacia_hide", () -> new TunnelHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_HIDE = BLOCKS.register("birch_hide", () -> new TunnelHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_HIDE = BLOCKS.register("dark_oak_hide", () -> new TunnelHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_HIDE = BLOCKS.register("jungle_hide", () -> new TunnelHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MANGROVE_HIDE = BLOCKS.register("mangrove_hide", () -> new TunnelHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> OAK_HIDE = BLOCKS.register("oak_hide", () -> new TunnelHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));

    //</editor-fold>

    //<editor-fold desc="Large Hide and Tunnel Block RegistryObject">
    public static final RegistryObject<Block> DIRT_MEDIUM_HIDE = BLOCKS.register("dirt_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> STONE_MEDIUM_HIDE = BLOCKS.register("stone_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> STONE_BRICK_MEDIUM_HIDE = BLOCKS.register("stone_brick_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> MOSSY_BRICK_MEDIUM_HIDE = BLOCKS.register("mossy_brick_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SANDSTONE_MEDIUM_HIDE = BLOCKS.register("sandstone_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)));

    public static final RegistryObject<Block> SPRUCE_MEDIUM_HIDE = BLOCKS.register("spruce_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_MEDIUM_HIDE = BLOCKS.register("acacia_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_MEDIUM_HIDE = BLOCKS.register("birch_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_MEDIUM_HIDE = BLOCKS.register("dark_oak_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_MEDIUM_HIDE = BLOCKS.register("jungle_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MANGROVE_MEDIUM_HIDE = BLOCKS.register("mangrove_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> OAK_MEDIUM_HIDE = BLOCKS.register("oak_medium_hide", () -> new MediumHideBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));

    //</editor-fold>


    //public static final RegistryObject<LiquidBlock> TEST_FLUID_BLOCK = BLOCKS.register("test_fluid", () -> new LiquidBlock(() -> FluidInit.WATER_TEST.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    //TODO: When Adding new snake egg block remember to add it to the SNAKE_EGG in BlockEntityInit


    public static final RegistryObject<Block> HEAT_LAMP = BLOCKS.register("heat_lamp", () -> new HeatLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL).lightLevel((p_50755_) -> { return 10;})));
    public static final RegistryObject<Block> LAMP_POST = BLOCKS.register("lamp_post", () -> new LampPostBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> SNAKE_EGG = BLOCKS.register("hoggie_egg", () -> new HognoseEggBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> BALL_PYTHON_EGG = BLOCKS.register("ball_python_egg", () -> new BallPythonEggBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }
    private static boolean always(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return true;
    }
}
