package com.raspix.snekcraft.entity.hognose;

import com.raspix.snekcraft.SnekCraft;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HognoseModel extends AnimatedGeoModel<HognoseEntity> {
    @Override
    public ResourceLocation getModelLocation(HognoseEntity object) {
        return new ResourceLocation(SnekCraft.MOD_ID, "geo/hoggie.geo.json");
        /**if(object.isBaby()){

         }else {
         return new ResourceLocation(SnekersMod.MOD_ID, "geo/test.geo.json");
         }*/

    }

    @Override
    public ResourceLocation getTextureLocation(HognoseEntity object) {
        return new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hoggie_rainbow.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HognoseEntity animatable) {
        //return new ResourceLocation(SnekersMod.MOD_ID, "animations/test.animation.json");
        return new ResourceLocation(SnekCraft.MOD_ID, "animations/hoggie.animation.json");
    }

}
