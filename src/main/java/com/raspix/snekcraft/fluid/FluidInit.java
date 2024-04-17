package com.raspix.snekcraft.fluid;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.BlockInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidInit {

    public static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOW = new ResourceLocation("block/water_flowing");
    public static final ResourceLocation WATER_OVERLAY = new ResourceLocation("block/water_overlay");

    public static DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SnekCraft.MOD_ID);
    public static final DeferredRegister<Fluid> VANILLA_FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, "minecraft");

    public static final RegistryObject<FlowingFluid> FAKE_FLUID = FLUIDS.register("fake_fluid", () -> new FakeFluid.Source(FluidInit.FAKE_PROPS));
    public static final RegistryObject<FlowingFluid> FAKE_FLOWING = FLUIDS.register("fake_flowing", () -> new FakeFluid.Flowing(FluidInit.FAKE_PROPS));

    //public static final RegistryObject<FlowingFluid> WATER_TEST = FLUIDS.register("water_test", () -> new FakeWater());

    public static final ForgeFlowingFluid.Properties FAKE_PROPS = new ForgeFlowingFluid.Properties(
            () -> FAKE_FLUID.get(), ()-> FAKE_FLOWING.get(), FluidAttributes.Water.builder(WATER_STILL, WATER_OVERLAY).density(5).luminosity(0).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY)
            ).slopeFindDistance(2).levelDecreasePerBlock(2).canMultiply().block(()-> BlockInit.FAKE_FLUID_BLOCK.get())
            ;//.bucket(() -> ItemInit.FAKE_BUCKET.get());

    //return BiomeColors.getAverageWaterColor(level, pos) | 0xFF000000;.color(0xFF3F76E4).bucket(() -> ItemInit.BLOOD_BUCKET.get())





    //public static final RegistryObject<FlowingFluid> GRASS_BLOCK = registerBlock("grass_block", () -> new SiltBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    /**public static final RegistryObject<FlowingFluid> FLOWING_WATER = VANILLA_FLUIDS.register("flowing_water", () -> new FakeFluid.Flowing(FluidInit.WATER_PROPS));
    public static final RegistryObject<FlowingFluid> WATER = VANILLA_FLUIDS.register("water", () -> new FakeFluid.Source(FluidInit.WATER_PROPS));
    public static final ForgeFlowingFluid.Properties WATER_PROPS = new ForgeFlowingFluid.Properties(
            () -> WATER.get(), ()-> FLOWING_WATER.get(), FluidAttributes.Water.builder(WATER_STILL, WATER_FLOW).density(5).luminosity(0).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY));*/
}
