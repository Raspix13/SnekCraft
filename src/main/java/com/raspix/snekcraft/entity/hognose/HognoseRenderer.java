package com.raspix.snekcraft.entity.hognose;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.raspix.snekcraft.SnekCraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Strider;
import org.jetbrains.annotations.Nullable;

public class HognoseRenderer extends MobRenderer<HognoseEntity, HognoseModel<HognoseEntity>> {

    private static int maxPattern = 2;
    private static int maxColor = 11;

    private static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/normal.png");
    private static final ResourceLocation CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/conda.png");
    private static final ResourceLocation SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/superconda.png");
    private static final ResourceLocation RAINBOW_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/rainbow.png");

    private static final ResourceLocation ALBINO_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/albino_normal.png");
    private static final ResourceLocation ALBINO_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/albino_conda.png");
    private static final ResourceLocation ALBINO_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/albino_superconda.png");

    private static final ResourceLocation AXANTHIC_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/axanthic_normal.png");
    private static final ResourceLocation AXANTHIC_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/axanthic_conda.png");
    private static final ResourceLocation AXANTHIC_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/axanthic_superconda.png");

    private static final ResourceLocation ARCTIC_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/arctic_normal.png");
    private static final ResourceLocation ARCTIC_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/arctic_conda.png");
    private static final ResourceLocation ARCTIC_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/arctic_superconda.png");

    private static final ResourceLocation SNOW_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/snow_normal.png");
    private static final ResourceLocation SNOW_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/snow_conda.png");
    private static final ResourceLocation SNOW_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/snow_superconda.png");

    private static final ResourceLocation SUPER_ARCTIC_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/superarctic_normal.png");
    private static final ResourceLocation SUPER_ARCTIC_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/superarctic_conda.png");
    private static final ResourceLocation SUPER_ARCTIC_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/superarctic_superconda.png");

    private static final ResourceLocation SUBZERO_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/subzero_normal.png");
    private static final ResourceLocation SUBZERO_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/subzero_conda.png");
    private static final ResourceLocation SUBZERO_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/subzero_superconda.png");

    private static final ResourceLocation YETI_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/yeti_normal.png");
    private static final ResourceLocation YETI_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/yeti_conda.png");
    private static final ResourceLocation YETI_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/yeti_superconda.png");

    private static final ResourceLocation ALBINO_ARCTIC_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/albino_arctic_normal.png");
    private static final ResourceLocation ALBINO_ARCTIC_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/albino_arctic_conda.png");
    private static final ResourceLocation ALBINO_ARCTIC_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/albino_arctic_superconda.png");

    private static final ResourceLocation AXARCTIC_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/axarctic_normal.png");
    private static final ResourceLocation AXARCTIC_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/axarctic_conda.png");
    private static final ResourceLocation AXARCTIC_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/axarctic_superconda.png");

    private static final ResourceLocation SUPER_AXARCTIC_NORMAL_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/superaxarctic_normal.png");
    private static final ResourceLocation SUPER_AXARCTIC_CONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/superaxarctic_conda.png");
    private static final ResourceLocation SUPER_AXARCTIC_SUPERCONDA_TEXTURE = new ResourceLocation(SnekCraft.MOD_ID, "textures/entity/hognose/superaxarctic_superconda.png");


    private static final ResourceLocation[][] TEXTURES = new ResourceLocation[][]{
            {NORMAL_TEXTURE, ALBINO_NORMAL_TEXTURE, AXANTHIC_NORMAL_TEXTURE, ARCTIC_NORMAL_TEXTURE, SNOW_NORMAL_TEXTURE, SUPER_ARCTIC_NORMAL_TEXTURE, SUBZERO_NORMAL_TEXTURE, YETI_NORMAL_TEXTURE, RAINBOW_TEXTURE, ALBINO_ARCTIC_NORMAL_TEXTURE, AXARCTIC_NORMAL_TEXTURE, SUPER_AXARCTIC_NORMAL_TEXTURE},
            {CONDA_TEXTURE, ALBINO_CONDA_TEXTURE, AXANTHIC_CONDA_TEXTURE, ARCTIC_CONDA_TEXTURE, SNOW_CONDA_TEXTURE, SUPER_ARCTIC_CONDA_TEXTURE, SUBZERO_CONDA_TEXTURE, YETI_CONDA_TEXTURE, RAINBOW_TEXTURE, ALBINO_ARCTIC_CONDA_TEXTURE, AXARCTIC_CONDA_TEXTURE, SUPER_AXARCTIC_CONDA_TEXTURE},
            {SUPERCONDA_TEXTURE, ALBINO_SUPERCONDA_TEXTURE, AXANTHIC_SUPERCONDA_TEXTURE, ARCTIC_SUPERCONDA_TEXTURE, SNOW_SUPERCONDA_TEXTURE, SUPER_ARCTIC_SUPERCONDA_TEXTURE, SUBZERO_SUPERCONDA_TEXTURE, YETI_SUPERCONDA_TEXTURE, RAINBOW_TEXTURE, ALBINO_ARCTIC_SUPERCONDA_TEXTURE, AXARCTIC_SUPERCONDA_TEXTURE, SUPER_AXARCTIC_SUPERCONDA_TEXTURE}
    };

    public HognoseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HognoseModel<>(pContext.bakeLayer(HognoseModel.LAYER_LOCATION)), 0.3f);
    }


    @Override
    public ResourceLocation getTextureLocation(HognoseEntity entity) {
        int colorNum = entity.getColor();
        int patternNum = entity.getPattern();
        return TEXTURES[Math.min(Math.max(patternNum, 0), maxPattern)][Math.min(Math.max(colorNum, 0), maxColor)];
    }



    protected void scale(HognoseEntity entity, PoseStack pPoseStack, float pPartialTickTime) {
        if(entity.isBaby()){
            pPoseStack.scale(0.4f, 0.4f, 0.4f); //changes scale
        }else {
            pPoseStack.scale(0.6f, 0.6f, 0.6f); //changes scale
        }

    }
}