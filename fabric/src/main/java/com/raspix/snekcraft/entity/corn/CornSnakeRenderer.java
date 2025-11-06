package com.raspix.snekcraft.entity.corn;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CornSnakeRenderer extends MobEntityRenderer<CornSnakeEntity, CornSnakeModel<CornSnakeEntity>> {

    private static int maxPattern = 2;
    private static int maxColor = 16;

    private static final Identifier NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/normal.png");
    private static final Identifier AMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/amel.png");
    private static final Identifier ANERY_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/anery.png");
    private static final Identifier LAVENDER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/lavender.png");
    private static final Identifier CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/caramel.png");
    private static final Identifier SNOW_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/snow.png");
    private static final Identifier MOONSTONE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/moonstone.png");
    private static final Identifier OPAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/opal.png");
    private static final Identifier ANERY_CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/anery_caramel.png");
    private static final Identifier BUTTER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/butter.png");
    private static final Identifier ALMOND_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/almond.png");
    private static final Identifier LAVENDER_BUTTER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/lavender_butter.png");
    private static final Identifier XANTHIC_SNOW_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/xanthic_snow.png");
    private static final Identifier GLACIER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/glacier.png");
    private static final Identifier MOONSTONE_CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/moonstone_caramel.png");
    private static final Identifier XANTHIC_SNOW_LAVENDER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/normal/xanthic_snow_lavender.png");

    private static final Identifier PALMETTO_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_normal.png");
    private static final Identifier PALMETTO_AMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_amel.png");
    private static final Identifier PALMETTO_ANERY_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_anery.png");
    private static final Identifier PALMETTO_LAVENDER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_lavender.png");
    private static final Identifier PALMETTO_CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_caramel.png");
    private static final Identifier PALMETTO_SNOW_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_snow.png");
    private static final Identifier PALMETTO_MOONSTONE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_moonstone.png");
    private static final Identifier PALMETTO_OPAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_opal.png");
    private static final Identifier PALMETTO_ANERY_CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_anerycaramel.png");
    private static final Identifier PALMETTO_BUTTER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_butter.png");
    private static final Identifier PALMETTO_ALMOND_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_almond.png");
    private static final Identifier PALMETTO_LAVENDER_BUTTER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_lavenderbutter.png");
    private static final Identifier PALMETTO_XANTHIC_SNOW_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_xanthicsnow.png");
    private static final Identifier PALMETTO_GLACIER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_glacier.png");
    private static final Identifier PALMETTO_MOONSTONE_CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_moonstonecaramel.png");
    private static final Identifier PALMETTO_XANTHIC_SNOW_LAVENDER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/palmetto/palmetto_xanthicsnowlavender.png");

    private static final Identifier MOTLEY_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_normal.png");
    private static final Identifier MOTLEY_AMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_amel.png");
    private static final Identifier MOTLEY_ANERY_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_anery.png");
    private static final Identifier MOTLEY_LAVENDER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_lavender.png");
    private static final Identifier MOTLEY_CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_caramel.png");
    private static final Identifier MOTLEY_SNOW_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_snow.png");
    private static final Identifier MOTLEY_MOONSTONE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_moonstone.png");
    private static final Identifier MOTLEY_OPAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_opal.png");
    private static final Identifier MOTLEY_ANERY_CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_anerycaramel.png");
    private static final Identifier MOTLEY_BUTTER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_butter.png");
    private static final Identifier MOTLEY_ALMOND_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_almond.png");
    private static final Identifier MOTLEY_LAVENDER_BUTTER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_lavenderbutter.png");
    private static final Identifier MOTLEY_XANTHIC_SNOW_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_xanthicsnow.png");
    private static final Identifier MOTLEY_GLACIER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_glacier.png");
    private static final Identifier MOTLEY_MOONSTONE_CARAMEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_moonstonecaramel.png");
    private static final Identifier MOTLEY_XANTHIC_SNOW_LAVENDER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/motley/motley_xanthicsnowlavender.png");


    private static final Identifier SPECIAL_TEXTURE =new Identifier(SnekCraft.MOD_ID, "textures/entity/corn_snake/corn_cob.png");

    // 1st is pattern, 2nd is color
    private static final Identifier[][] TEXTURES = new Identifier[][]{
            {NORMAL_TEXTURE, AMEL_TEXTURE, ANERY_TEXTURE, LAVENDER_TEXTURE, CARAMEL_TEXTURE, SNOW_TEXTURE, MOONSTONE_TEXTURE, OPAL_TEXTURE, ANERY_CARAMEL_TEXTURE, BUTTER_TEXTURE, ALMOND_TEXTURE, LAVENDER_BUTTER_TEXTURE, XANTHIC_SNOW_TEXTURE, GLACIER_TEXTURE, MOONSTONE_CARAMEL_TEXTURE, XANTHIC_SNOW_LAVENDER_TEXTURE, SPECIAL_TEXTURE},
            {PALMETTO_TEXTURE, PALMETTO_AMEL_TEXTURE, PALMETTO_ANERY_TEXTURE, PALMETTO_LAVENDER_TEXTURE, PALMETTO_CARAMEL_TEXTURE, PALMETTO_SNOW_TEXTURE, PALMETTO_MOONSTONE_TEXTURE, PALMETTO_OPAL_TEXTURE, PALMETTO_ANERY_CARAMEL_TEXTURE, PALMETTO_BUTTER_TEXTURE, PALMETTO_ALMOND_TEXTURE, PALMETTO_LAVENDER_BUTTER_TEXTURE, PALMETTO_XANTHIC_SNOW_TEXTURE, PALMETTO_GLACIER_TEXTURE, PALMETTO_MOONSTONE_CARAMEL_TEXTURE, PALMETTO_XANTHIC_SNOW_LAVENDER_TEXTURE, SPECIAL_TEXTURE},
            {MOTLEY_TEXTURE, MOTLEY_AMEL_TEXTURE, MOTLEY_ANERY_TEXTURE, MOTLEY_LAVENDER_TEXTURE, MOTLEY_CARAMEL_TEXTURE, MOTLEY_SNOW_TEXTURE, MOTLEY_MOONSTONE_TEXTURE, MOTLEY_OPAL_TEXTURE, MOTLEY_ANERY_CARAMEL_TEXTURE, MOTLEY_BUTTER_TEXTURE, MOTLEY_ALMOND_TEXTURE, MOTLEY_LAVENDER_BUTTER_TEXTURE, MOTLEY_XANTHIC_SNOW_TEXTURE, MOTLEY_GLACIER_TEXTURE, MOTLEY_MOONSTONE_CARAMEL_TEXTURE, MOTLEY_XANTHIC_SNOW_LAVENDER_TEXTURE, SPECIAL_TEXTURE}
    };


    public CornSnakeRenderer(EntityRendererFactory.Context pContext) {
        super(pContext, new CornSnakeModel<>(pContext.getPart(CornSnakeModel.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public Identifier getTexture(CornSnakeEntity entity) {
        int colorNum = entity.getColor();
        int patternNum = entity.getPattern();

        //return NORMAL_TEXTURE;
        return TEXTURES[Math.min(Math.max(patternNum, 0), maxPattern)][Math.min(Math.max(colorNum, 0), maxColor)];
        //return TEST_TEXTURE;
    }


    protected void scale(CornSnakeEntity entity, MatrixStack matrixStack, float pPartialTickTime) {
        if(entity.isBaby()){
            matrixStack.scale(0.2f, 0.2f, 0.2f);
        }else {
            matrixStack.scale(0.4f, 0.4f, 0.4f);
        }
    }
}
