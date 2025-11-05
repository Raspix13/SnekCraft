package com.raspix.snekcraft.entity.corn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.raspix.snekcraft.SnekCraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CornSnakeRenderer extends MobRenderer<CornSnakeEntity, CornSnakeModel<CornSnakeEntity>> {

    private static int maxPattern = 3;
    private static int maxColor = 16;

    private static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/normal.png");
    private static final ResourceLocation AMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/amel.png");
    private static final ResourceLocation ANERY_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/anery.png");
    private static final ResourceLocation LAVENDER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/lavender.png");
    private static final ResourceLocation CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/caramel.png");
    private static final ResourceLocation SNOW_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/snow.png");
    private static final ResourceLocation MOONSTONE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/moonstone.png");
    private static final ResourceLocation OPAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/opal.png");
    private static final ResourceLocation ANERY_CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/anery_caramel.png");
    private static final ResourceLocation BUTTER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/butter.png");
    private static final ResourceLocation ALMOND_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/almond.png");
    private static final ResourceLocation LAVENDER_BUTTER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/lavender_butter.png");
    private static final ResourceLocation XANTHIC_SNOW_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/xanthic_snow.png");
    private static final ResourceLocation GLACIER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/glacier.png");
    private static final ResourceLocation MOONSTONE_CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/moonstone_caramel.png");
    private static final ResourceLocation XANTHIC_SNOW_LAVENDER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/xanthic_snow_lavender.png");

    private static final ResourceLocation PALMETTO_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto.png");
    private static final ResourceLocation PALMETTO_AMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_amel.png");
    private static final ResourceLocation PALMETTO_ANERY_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_anery.png");
    private static final ResourceLocation PALMETTO_LAVENDER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_lavender.png");
    private static final ResourceLocation PALMETTO_CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_caramel.png");
    private static final ResourceLocation PALMETTO_SNOW_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_snow.png");
    private static final ResourceLocation PALMETTO_MOONSTONE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_moonstone.png");
    private static final ResourceLocation PALMETTO_OPAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_opal.png");
    private static final ResourceLocation PALMETTO_ANERY_CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_anery_caramel.png");
    private static final ResourceLocation PALMETTO_BUTTER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_butter.png");
    private static final ResourceLocation PALMETTO_ALMOND_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_almond.png");
    private static final ResourceLocation PALMETTO_LAVENDER_BUTTER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_lavender_butter.png");
    private static final ResourceLocation PALMETTO_XANTHIC_SNOW_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_xanthic_snow.png");
    private static final ResourceLocation PALMETTO_GLACIER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_glacier.png");
    private static final ResourceLocation PALMETTO_MOONSTONE_CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_moonstone_caramel.png");
    private static final ResourceLocation PALMETTO_XANTHIC_SNOW_LAVENDER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_xanthic_snow_lavender.png");

    private static final ResourceLocation MOTLEY_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley.png");
    private static final ResourceLocation MOTLEY_AMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_amel.png");
    private static final ResourceLocation MOTLEY_ANERY_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_anery.png");
    private static final ResourceLocation MOTLEY_LAVENDER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_lavender.png");
    private static final ResourceLocation MOTLEY_CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_caramel.png");
    private static final ResourceLocation MOTLEY_SNOW_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_snow.png");
    private static final ResourceLocation MOTLEY_MOONSTONE_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_moonstone.png");
    private static final ResourceLocation MOTLEY_OPAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_opal.png");
    private static final ResourceLocation MOTLEY_ANERY_CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_anery_caramel.png");
    private static final ResourceLocation MOTLEY_BUTTER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_butter.png");
    private static final ResourceLocation MOTLEY_ALMOND_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_almond.png");
    private static final ResourceLocation MOTLEY_LAVENDER_BUTTER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_lavender_butter.png");
    private static final ResourceLocation MOTLEY_XANTHIC_SNOW_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_xanthic_snow.png");
    private static final ResourceLocation MOTLEY_GLACIER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_glacier.png");
    private static final ResourceLocation MOTLEY_MOONSTONE_CARAMEL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_moonstone_caramel.png");
    private static final ResourceLocation MOTLEY_XANTHIC_SNOW_LAVENDER_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_xanthic_snow_lavender.png");


    private static final ResourceLocation SPECIAL_TEXTURE =new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/corn_snake/corn_cob.png");

    // 1st is pattern, 2nd is color
    private static final ResourceLocation[][] TEXTURES = new ResourceLocation[][]{
            {NORMAL_TEXTURE, AMEL_TEXTURE, ANERY_TEXTURE, LAVENDER_TEXTURE, CARAMEL_TEXTURE, SNOW_TEXTURE, MOONSTONE_TEXTURE, OPAL_TEXTURE, ANERY_CARAMEL_TEXTURE, BUTTER_TEXTURE, ALMOND_TEXTURE, LAVENDER_BUTTER_TEXTURE, XANTHIC_SNOW_TEXTURE, GLACIER_TEXTURE, MOONSTONE_CARAMEL_TEXTURE, XANTHIC_SNOW_LAVENDER_TEXTURE, SPECIAL_TEXTURE},
            {PALMETTO_TEXTURE, PALMETTO_AMEL_TEXTURE, PALMETTO_ANERY_TEXTURE, PALMETTO_LAVENDER_TEXTURE, PALMETTO_CARAMEL_TEXTURE, PALMETTO_SNOW_TEXTURE, PALMETTO_MOONSTONE_TEXTURE, PALMETTO_OPAL_TEXTURE, PALMETTO_ANERY_CARAMEL_TEXTURE, PALMETTO_BUTTER_TEXTURE, PALMETTO_ALMOND_TEXTURE, PALMETTO_LAVENDER_BUTTER_TEXTURE, PALMETTO_XANTHIC_SNOW_TEXTURE, PALMETTO_GLACIER_TEXTURE, PALMETTO_MOONSTONE_CARAMEL_TEXTURE, PALMETTO_XANTHIC_SNOW_LAVENDER_TEXTURE, SPECIAL_TEXTURE},
            {MOTLEY_TEXTURE, MOTLEY_AMEL_TEXTURE, MOTLEY_ANERY_TEXTURE, MOTLEY_LAVENDER_TEXTURE, MOTLEY_CARAMEL_TEXTURE, MOTLEY_SNOW_TEXTURE, MOTLEY_MOONSTONE_TEXTURE, MOTLEY_OPAL_TEXTURE, MOTLEY_ANERY_CARAMEL_TEXTURE, MOTLEY_BUTTER_TEXTURE, MOTLEY_ALMOND_TEXTURE, MOTLEY_LAVENDER_BUTTER_TEXTURE, MOTLEY_XANTHIC_SNOW_TEXTURE, MOTLEY_GLACIER_TEXTURE, MOTLEY_MOONSTONE_CARAMEL_TEXTURE, MOTLEY_XANTHIC_SNOW_LAVENDER_TEXTURE, SPECIAL_TEXTURE}
    };


    public CornSnakeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CornSnakeModel<>(pContext.bakeLayer(CornSnakeModel.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(CornSnakeEntity entity) {
        int colorNum = entity.getColor();
        int patternNum = entity.getPattern();

        //return NORMAL_TEXTURE;
        return TEXTURES[Math.min(Math.max(patternNum, 0), maxPattern)][Math.min(Math.max(colorNum, 0), maxColor)];
        //return TEST_TEXTURE;
    }


    protected void scale(CornSnakeEntity entity, PoseStack poseStack, float pPartialTickTime) {
        if(entity.isBaby()){
            poseStack.scale(0.2f, 0.2f, 0.2f); //changes scale
        }else {
            poseStack.scale(0.4f, 0.4f, 0.4f); //changes scale
        }

    }
}
