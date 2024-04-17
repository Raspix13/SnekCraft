package com.raspix.snekcraft.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.fluids.FluidAttributes;

public class FakeWater extends WaterFluid {

    public FakeWater(){
        getAttributes();
        //createAttributes();
        //this.bucket = () -> ItemInit.TEST_BUCKET.get();
    }

    /**@Override
    public Fluid getFlowing() {
        return FluidInit.FAKE_FLOWING.get();
    }

    @Override
    public Fluid getSource() {
        return FluidInit.WATER_TEST.get();
    }

    @Override
    public BlockState createLegacyBlock(FluidState pState) {
        //return Blocks.WATER.defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(pState)));
        return BlockInit.TEST_FLUID_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(pState)));
    }

    @Nullable
    private final Supplier<? extends Item> bucket;


    @Override
    public Item getBucket() {
        return bucket != null ? bucket.get() : Items.AIR;
    }

    public static class Properties
    {
        private Supplier<? extends Item> bucket;

        public void bucket(Supplier<? extends Item> bucket)
        {
            this.bucket = bucket;
        }
    }
*/
    public int getAmount(FluidState pState) {
        return 8;
    }

    public boolean isSource(FluidState pState) {
        return true;
    }

    @Override
    protected FluidAttributes createAttributes() {
        //super.createAttributes();
        System.out.println("HERE \n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        return FluidAttributes.Water.builder(
                        new ResourceLocation("block/water_overlay"),
                        new ResourceLocation("block/water_overlay"))
                .overlay(new ResourceLocation("block/water_overlay"))
                .color(0xFFa8325e)
                .sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY)
                .build(this);
    }



    /**@Override
    protected FluidAttributes createAttributes() {
        System.out.println("\n\n\n\n\n\n\nfluid attributes are being made for fake fluid \n\n\n\n\n\n\n");
        return super.createAttributes();
    }*/


    @Override
    public boolean isEntityInside(FluidState state, LevelReader level, BlockPos pos, Entity entity, double yToTest, HolderSet<Fluid> tag, boolean testingHead) {
        System.out.println(getAttributes().getFlowingTexture());
        System.out.println("hello there ladies and gentlemen");
        return super.isEntityInside(state, level, pos, entity, yToTest, tag, testingHead);
    }

}
