package com.raspix.snekcraft.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.Nullable;

public class FakeFluid extends ForgeFlowingFluid {//WaterFluid{//}

    protected FakeFluid(Properties properties) {
        super(properties);
    }

    /**public static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOW = new ResourceLocation("block/water_overlay");
    public static final ResourceLocation WATER_OVERLAY = new ResourceLocation("block/water_overlay");

    @Override
    protected FluidAttributes createAttributes() {
        System.out.println("Here it is the water over write \n\n\n\n\n\n\n\n\n\n\n");
        FluidAttributes.Builder attri = FluidAttributes.Water.builder(WATER_STILL, WATER_FLOW).density(5).luminosity(0).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY);
        return attri.build(this);
    }*/

    @Override
    public boolean isSource(FluidState pState) {
        return true;
    }

    @Override
    public int getAmount(FluidState pState) {
        return 8;
    }

    @Override
    public boolean isEntityInside(FluidState state, LevelReader level, BlockPos pos, Entity entity, double yToTest, HolderSet<Fluid> tag, boolean testingHead) {
        return super.isEntityInside(state, level, pos, entity, yToTest, tag, testingHead);
    }

    @Nullable
    @Override
    public Boolean isAABBInsideMaterial(FluidState state, LevelReader level, BlockPos pos, AABB boundingBox, Material materialIn) {
        return super.isAABBInsideMaterial(state, level, pos, boundingBox, materialIn);
    }

    @Nullable
    @Override
    public Boolean isAABBInsideLiquid(FluidState state, LevelReader level, BlockPos pos, AABB boundingBox) {
        return super.isAABBInsideLiquid(state, level, pos, boundingBox);
    }

    @Override
    public float getExplosionResistance(FluidState state, BlockGetter level, BlockPos pos, Explosion explosion) {
        return super.getExplosionResistance(state, level, pos, explosion);
    }

    @Override
    public boolean isSame(Fluid pFluid) {
        return true;//super.isSame(pFluid) || pFluid == Fluids.WATER || pFluid == Fluids.FLOWING_WATER;
    }

    @Override
    public boolean is(TagKey<Fluid> pTag) {
        return true;
    }


    @Override
    protected FluidAttributes createAttributes()
    {
        System.out.println("fluid attributes are being made for fake fluid");
        return super.createAttributes();
    }

}
