package com.raspix.snekcraft.entity.ball_python;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.raspix.snekcraft.SnekCraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BallPythonRenderer extends GeoEntityRenderer<BallPythonEntity> {
    private static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/normal.png");
    private static final ResourceLocation FIRE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/fire.png");
    private static final ResourceLocation PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/pastel.png");
    private static final ResourceLocation B_PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/bpastel.png");
    private static final ResourceLocation SUPER_FIRE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superfire.png");
    private static final ResourceLocation SUPER_PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superpastel.png");
    private static final ResourceLocation SUPER_B_PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/superbpastel.png");
    private static final ResourceLocation FIREFLY_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/firefly.png");
    private static final ResourceLocation BLACK_PEWTER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpewter.png");
    private static final ResourceLocation BLACK_FIRE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfire.png");
    private static final ResourceLocation BLACK_FIRE_PASTEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepastel.png");


    private static final ResourceLocation NORMAL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piednormal.png");
    private static final ResourceLocation FIRE_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedfire.png");
    private static final ResourceLocation PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedpastel.png");
    private static final ResourceLocation B_PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedbpastel.png");
    private static final ResourceLocation SUPER_FIRE_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedsuperfire.png");
    private static final ResourceLocation SUPER_PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedsuperpastel.png");
    private static final ResourceLocation SUPER_B_PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedsuperbpastel.png");
    private static final ResourceLocation FIREFLY_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedfirefly.png");
    private static final ResourceLocation BLACK_PEWTER_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedblackpewter.png");
    private static final ResourceLocation BLACK_FIRE_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedblackfire.png");
    private static final ResourceLocation BLACK_FIRE_PASTEL_PIED_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/piedblackfirepastel.png");


    private static final ResourceLocation TEST_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/test.png");

    private static final ResourceLocation BAD_TEXTURE =new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/normal2.png");
    private static final ResourceLocation SPECIAL_TEXTURE =new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/ball_python/smile.png");


    // 1st is pattern, 2nd is color
    private static final ResourceLocation[][] TEXTURES = new ResourceLocation[][]{
            {NORMAL_TEXTURE, FIRE_TEXTURE, PASTEL_TEXTURE, B_PASTEL_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_TEXTURE, BLACK_PEWTER_TEXTURE, BLACK_FIRE_TEXTURE, BLACK_FIRE_PASTEL_TEXTURE},
            {NORMAL_PIED_TEXTURE, FIRE_PIED_TEXTURE, PASTEL_PIED_TEXTURE, B_PASTEL_PIED_TEXTURE, SUPER_FIRE_PIED_TEXTURE, SUPER_PASTEL_PIED_TEXTURE, SUPER_B_PASTEL_PIED_TEXTURE, FIREFLY_PIED_TEXTURE, BLACK_PEWTER_PIED_TEXTURE, BLACK_FIRE_PIED_TEXTURE, BLACK_FIRE_PASTEL_PIED_TEXTURE},
            {NORMAL_TEXTURE, FIRE_TEXTURE, PASTEL_TEXTURE, B_PASTEL_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_TEXTURE, BLACK_PEWTER_TEXTURE, BLACK_FIRE_TEXTURE, BLACK_FIRE_PASTEL_TEXTURE},
            {NORMAL_TEXTURE, FIRE_TEXTURE, PASTEL_TEXTURE, B_PASTEL_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_TEXTURE, BLACK_PEWTER_TEXTURE, BLACK_FIRE_TEXTURE, BLACK_FIRE_PASTEL_TEXTURE},
            {SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE}
    };


    public BallPythonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BallPythonModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public ResourceLocation getTextureLocation(BallPythonEntity entity) {
        int colorNum = entity.getColor();
        int patternNum = entity.getPattern();
        /**if(colorNum == 0){
            return NORMAL_TEXTURE;
        }*/
        return TEXTURES[patternNum][colorNum];
        //return TEST_TEXTURE;
    }

    @Override
    public RenderType getRenderType(BallPythonEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer,
                                    int packedLight, ResourceLocation texture) {
        if(animatable.isBaby()){
            poseStack.scale(0.2f, 0.2f, 0.2f); //changes scale
        }else {
            poseStack.scale(0.4f, 0.4f, 0.4f); //changes scale
        }
        //poseStack.scale(0.8f, 0.8f, 0.8f); //changes scale
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
