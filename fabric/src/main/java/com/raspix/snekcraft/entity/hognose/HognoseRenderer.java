package com.raspix.snekcraft.entity.hognose;

import com.raspix.snekcraft.SnekCraft;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HognoseRenderer extends MobEntityRenderer<HognoseEntity, HognoseModel<HognoseEntity>> {
	private static int maxPattern = 2;
	private static int maxColor = 11;
	
    private static final Identifier NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/normal.png");
    private static final Identifier CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/conda.png");
    private static final Identifier SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/superconda.png");
    private static final Identifier RAINBOW_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/rainbow.png");
    
    private static final Identifier ALBINO_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/albino_normal.png");
    private static final Identifier ALBINO_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/albino_conda.png");
    private static final Identifier ALBINO_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/albino_superconda.png");
    
    private static final Identifier AXANTHIC_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/axanthic_normal.png");
    private static final Identifier AXANTHIC_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/axanthic_conda.png");
    private static final Identifier AXANTHIC_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/axanthic_superconda.png");
    
    private static final Identifier ARCTIC_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/arctic_normal.png");
    private static final Identifier ARCTIC_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/arctic_conda.png");
    private static final Identifier ARCTIC_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/arctic_superconda.png");
    
    private static final Identifier SNOW_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/snow_normal.png");
    private static final Identifier SNOW_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/snow_conda.png");
    private static final Identifier SNOW_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/snow_superconda.png");
    
    private static final Identifier SUPER_ARCTIC_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/superarctic_normal.png");
    private static final Identifier SUPER_ARCTIC_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/superarctic_conda.png");
    private static final Identifier SUPER_ARCTIC_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/superarctic_superconda.png");
    
    private static final Identifier SUBZERO_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/subzero_normal.png");
    private static final Identifier SUBZERO_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/subzero_conda.png");
    private static final Identifier SUBZERO_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/subzero_superconda.png");
    
    private static final Identifier YETI_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/yeti_normal.png");
    private static final Identifier YETI_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/yeti_conda.png");
    private static final Identifier YETI_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/yeti_superconda.png");
    
    private static final Identifier ALBINO_ARCTIC_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/albino_arctic_normal.png");
    private static final Identifier ALBINO_ARCTIC_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/albino_arctic_conda.png");
    private static final Identifier ALBINO_ARCTIC_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/albino_arctic_superconda.png");
    
    private static final Identifier AXARCTIC_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/axarctic_normal.png");
    private static final Identifier AXARCTIC_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/axarctic_conda.png");
    private static final Identifier AXARCTIC_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/axarctic_superconda.png");
    
    private static final Identifier SUPER_AXARCTIC_NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/superaxarctic_normal.png");
    private static final Identifier SUPER_AXARCTIC_CONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/superaxarctic_conda.png");
    private static final Identifier SUPER_AXARCTIC_SUPERCONDA_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/hognose/superaxarctic_superconda.png");
    
    private static final Identifier[][] TEXTURES = new Identifier[][]{
            {NORMAL_TEXTURE, ALBINO_NORMAL_TEXTURE, AXANTHIC_NORMAL_TEXTURE, ARCTIC_NORMAL_TEXTURE, SNOW_NORMAL_TEXTURE, SUPER_ARCTIC_NORMAL_TEXTURE, SUBZERO_NORMAL_TEXTURE, YETI_NORMAL_TEXTURE, RAINBOW_TEXTURE, ALBINO_ARCTIC_NORMAL_TEXTURE, AXARCTIC_NORMAL_TEXTURE, SUPER_AXARCTIC_NORMAL_TEXTURE},
            {CONDA_TEXTURE, ALBINO_CONDA_TEXTURE, AXANTHIC_CONDA_TEXTURE, ARCTIC_CONDA_TEXTURE, SNOW_CONDA_TEXTURE, SUPER_ARCTIC_CONDA_TEXTURE, SUBZERO_CONDA_TEXTURE, YETI_CONDA_TEXTURE, RAINBOW_TEXTURE, ALBINO_ARCTIC_CONDA_TEXTURE, AXARCTIC_CONDA_TEXTURE, SUPER_AXARCTIC_CONDA_TEXTURE},
            {SUPERCONDA_TEXTURE, ALBINO_SUPERCONDA_TEXTURE, AXANTHIC_SUPERCONDA_TEXTURE, ARCTIC_SUPERCONDA_TEXTURE, SNOW_SUPERCONDA_TEXTURE, SUPER_ARCTIC_SUPERCONDA_TEXTURE, SUBZERO_SUPERCONDA_TEXTURE, YETI_SUPERCONDA_TEXTURE, RAINBOW_TEXTURE, ALBINO_ARCTIC_SUPERCONDA_TEXTURE, AXARCTIC_SUPERCONDA_TEXTURE, SUPER_AXARCTIC_SUPERCONDA_TEXTURE}
    };
    
    public HognoseRenderer(EntityRendererFactory.Context context) {
    	super(context, new HognoseModel<>(context.getPart(HognoseModel.LAYER_LOCATION)), 0.3f);
    }
    
    @Override
    public Identifier getTexture(HognoseEntity entity) {
    	int colorNum = entity.getColor();
    	int patternNum = entity.getPattern();
    	return TEXTURES[Math.min(Math.max(patternNum, 0), maxPattern)][Math.min(Math.max(colorNum, 0), maxColor)];
    }
    
    @Override
    protected void scale(HognoseEntity entity, MatrixStack matrixStack, float partialTickTime) {
    	if(entity.isBaby()) {
    		matrixStack.scale(0.4f, 0.4f, 0.4f);
    	}else {
    		matrixStack.scale(0.6f, 0.6f, 0.6f);
    	}
    }
}
