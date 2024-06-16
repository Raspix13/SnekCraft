package com.raspix.snekcraft.entity.ball_python;

import com.mojang.blaze3d.vertex.PoseStack;
import com.raspix.snekcraft.SnekCraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BallPythonRenderer extends MobRenderer<BallPythonEntity, BallPythonModel<BallPythonEntity>> {

    private static int maxPattern = 4;
    private static int maxColor = 10;

    private static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/normal.png");
    private static final ResourceLocation FIRE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/fire.png");
    private static final ResourceLocation PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/pastel.png");
    private static final ResourceLocation B_PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpastel.png");
    private static final ResourceLocation SUPER_FIRE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superfire.png");
    private static final ResourceLocation SUPER_PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superpastel.png");
    private static final ResourceLocation SUPER_B_PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superblackpastel.png");
    private static final ResourceLocation FIREFLY_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/firefly.png");
    private static final ResourceLocation BLACK_PEWTER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpewter.png");
    private static final ResourceLocation BLACK_FIRE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfire.png");
    private static final ResourceLocation BLACK_FIRE_PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepastel.png");


    private static final ResourceLocation NORMAL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/normalpied.png");
    private static final ResourceLocation FIRE_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/firepied.png");
    private static final ResourceLocation PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/pastelpied.png");
    private static final ResourceLocation B_PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpastelpied.png");
    private static final ResourceLocation SUPER_PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superpastelpied.png");
    private static final ResourceLocation SUPER_B_PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superblackpastelpied.png");
    private static final ResourceLocation FIREFLY_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/fireflypied.png");
    private static final ResourceLocation BLACK_PEWTER_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpewterpied.png");
    private static final ResourceLocation BLACK_FIRE_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepied.png");
    private static final ResourceLocation BLACK_FIRE_PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepastelpied.png");

    private static final ResourceLocation NORMAL_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/normalpinstripe.png");
    private static final ResourceLocation FIRE_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/firepinstripe.png");
    private static final ResourceLocation PASTEL_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/pastelpinstripe.png");
    private static final ResourceLocation B_PASTEL_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpastelpinstripe.png");
    private static final ResourceLocation SUPER_PASTEL_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superpastelpinstripe.png");
    private static final ResourceLocation FIREFLY_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/fireflypinstripe.png");
    private static final ResourceLocation BLACK_PEWTER_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpewterpinstripe.png");
    private static final ResourceLocation BLACK_FIRE_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepinstripe.png");
    private static final ResourceLocation BLACK_FIRE_PASTEL_PINSTRIPE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepastelpinstripe.png");


    private static final ResourceLocation TEST_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/test.png"); //keeping for testing

    private static final ResourceLocation BAD_TEXTURE =new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/normal2.png"); //keeping for testing
    private static final ResourceLocation SPECIAL_TEXTURE =new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/smile.png");


    // 1st is pattern, 2nd is color
    private static final ResourceLocation[][] TEXTURES = new ResourceLocation[][]{
            {NORMAL_TEXTURE, FIRE_TEXTURE, PASTEL_TEXTURE, B_PASTEL_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_TEXTURE, BLACK_PEWTER_TEXTURE, BLACK_FIRE_TEXTURE, BLACK_FIRE_PASTEL_TEXTURE},
            {NORMAL_PIED_TEXTURE, FIRE_PIED_TEXTURE, PASTEL_PIED_TEXTURE, B_PASTEL_PIED_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_PIED_TEXTURE, SUPER_B_PASTEL_PIED_TEXTURE, FIREFLY_PIED_TEXTURE, BLACK_PEWTER_PIED_TEXTURE, BLACK_FIRE_PIED_TEXTURE, BLACK_FIRE_PASTEL_PIED_TEXTURE},
            {NORMAL_PINSTRIPE_TEXTURE, FIRE_PINSTRIPE_TEXTURE, PASTEL_PINSTRIPE_TEXTURE, B_PASTEL_PINSTRIPE_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_PINSTRIPE_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_PINSTRIPE_TEXTURE, BLACK_PEWTER_PINSTRIPE_TEXTURE, BLACK_FIRE_PINSTRIPE_TEXTURE, BLACK_FIRE_PASTEL_PINSTRIPE_TEXTURE},
            {NORMAL_PINSTRIPE_TEXTURE, FIRE_PINSTRIPE_TEXTURE, PASTEL_PINSTRIPE_TEXTURE, B_PASTEL_PINSTRIPE_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_PINSTRIPE_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_PINSTRIPE_TEXTURE, BLACK_PEWTER_PINSTRIPE_TEXTURE, BLACK_FIRE_PINSTRIPE_TEXTURE, BLACK_FIRE_PASTEL_PINSTRIPE_TEXTURE},
            {SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE}
    };


    public BallPythonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BallPythonModel<>(pContext.bakeLayer(BallPythonModel.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(BallPythonEntity entity) {
        int colorNum = entity.getColor();
        int patternNum = entity.getPattern();

        //return NORMAL_TEXTURE;
        return TEXTURES[Math.min(Math.max(patternNum, 0), maxPattern)][Math.min(Math.max(colorNum, 0), maxColor)];
        //return TEST_TEXTURE;
    }


    protected void scale(BallPythonEntity entity, PoseStack poseStack, float pPartialTickTime) {
        if(entity.isBaby()){
            poseStack.scale(0.2f, 0.2f, 0.2f); //changes scale
        }else {
            poseStack.scale(0.4f, 0.4f, 0.4f); //changes scale
        }

    }
}
