package com.raspix.snekcraft.entity.ball_python;

import com.raspix.snekcraft.SnekCraft;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BallPythonModel extends AnimatedGeoModel<BallPythonEntity> {
    @Override
    public ResourceLocation getModelLocation(BallPythonEntity object) {
        return new ResourceLocation(SnekCraft.MOD_ID, "geo/ball_python.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BallPythonEntity object) {
        return new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python_rainbow.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BallPythonEntity animatable) {
        //return new ResourceLocation(SnekersMod.MOD_ID, "animations/test.animation.json");
        return new ResourceLocation(SnekCraft.MOD_ID, "animations/ball_python.animation.json");
    }
}
