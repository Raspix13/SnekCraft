package com.raspix.snekcraft.entity.ball_python;

import com.raspix.snekcraft.SnekCraft;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BallPythonRenderer extends MobEntityRenderer<BallPythonEntity, BallPythonModel<BallPythonEntity>> {
	private static int maxPattern = 4;
    private static int maxColor = 10;
    
    private static final Identifier NORMAL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/normal.png");
    private static final Identifier FIRE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/fire.png");
    private static final Identifier PASTEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/pastel.png");
    private static final Identifier B_PASTEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpastel.png");
    private static final Identifier SUPER_FIRE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/superfire.png");
    private static final Identifier SUPER_PASTEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/superpastel.png");
    private static final Identifier SUPER_B_PASTEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/superblackpastel.png");
    private static final Identifier FIREFLY_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/firefly.png");
    private static final Identifier BLACK_PEWTER_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpewter.png");
    private static final Identifier BLACK_FIRE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfire.png");
    private static final Identifier BLACK_FIRE_PASTEL_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepastel.png");
    
    private static final Identifier NORMAL_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/normalpied.png");
    private static final Identifier FIRE_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/firepied.png");
    private static final Identifier PASTEL_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/pastelpied.png");
    private static final Identifier B_PASTEL_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpastelpied.png");
    private static final Identifier SUPER_PASTEL_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/superpastelpied.png");
    private static final Identifier SUPER_B_PASTEL_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/superblackpastelpied.png");
    private static final Identifier FIREFLY_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/fireflypied.png");
    private static final Identifier BLACK_PEWTER_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpewterpied.png");
    private static final Identifier BLACK_FIRE_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepied.png");
    private static final Identifier BLACK_FIRE_PASTEL_PIED_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepastelpied.png");
    
    private static final Identifier NORMAL_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/normalpinstripe.png");
    private static final Identifier FIRE_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/firepinstripe.png");
    private static final Identifier PASTEL_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/pastelpinstripe.png");
    private static final Identifier B_PASTEL_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpastelpinstripe.png");
    private static final Identifier SUPER_PASTEL_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/superpastelpinstripe.png");
    private static final Identifier FIREFLY_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/fireflypinstripe.png");
    private static final Identifier BLACK_PEWTER_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackpewterpinstripe.png");
    private static final Identifier BLACK_FIRE_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepinstripe.png");
    private static final Identifier BLACK_FIRE_PASTEL_PINSTRIPE_TEXTURE = new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/blackfirepastelpinstripe.png");
    
    private static final Identifier SPECIAL_TEXTURE =new Identifier(SnekCraft.MOD_ID, "textures/entity/ball_python/smile.png");
    
    private static final Identifier[][] TEXTURES = new Identifier[][]{
            {NORMAL_TEXTURE, FIRE_TEXTURE, PASTEL_TEXTURE, B_PASTEL_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_TEXTURE, BLACK_PEWTER_TEXTURE, BLACK_FIRE_TEXTURE, BLACK_FIRE_PASTEL_TEXTURE},
            {NORMAL_PIED_TEXTURE, FIRE_PIED_TEXTURE, PASTEL_PIED_TEXTURE, B_PASTEL_PIED_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_PIED_TEXTURE, SUPER_B_PASTEL_PIED_TEXTURE, FIREFLY_PIED_TEXTURE, BLACK_PEWTER_PIED_TEXTURE, BLACK_FIRE_PIED_TEXTURE, BLACK_FIRE_PASTEL_PIED_TEXTURE},
            {NORMAL_PINSTRIPE_TEXTURE, FIRE_PINSTRIPE_TEXTURE, PASTEL_PINSTRIPE_TEXTURE, B_PASTEL_PINSTRIPE_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_PINSTRIPE_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_PINSTRIPE_TEXTURE, BLACK_PEWTER_PINSTRIPE_TEXTURE, BLACK_FIRE_PINSTRIPE_TEXTURE, BLACK_FIRE_PASTEL_PINSTRIPE_TEXTURE},
            {NORMAL_PINSTRIPE_TEXTURE, FIRE_PINSTRIPE_TEXTURE, PASTEL_PINSTRIPE_TEXTURE, B_PASTEL_PINSTRIPE_TEXTURE, SUPER_FIRE_TEXTURE, SUPER_PASTEL_PINSTRIPE_TEXTURE, SUPER_B_PASTEL_TEXTURE, FIREFLY_PINSTRIPE_TEXTURE, BLACK_PEWTER_PINSTRIPE_TEXTURE, BLACK_FIRE_PINSTRIPE_TEXTURE, BLACK_FIRE_PASTEL_PINSTRIPE_TEXTURE},
            {SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE, SPECIAL_TEXTURE}
    };
    
    public BallPythonRenderer(EntityRendererFactory.Context pContext) {
        super(pContext, new BallPythonModel<>(pContext.getPart(BallPythonModel.LAYER_LOCATION)), 0.4f);
    }
    
    @Override
    public Identifier getTexture(BallPythonEntity entity) {
        int colorNum = entity.getColor();
        int patternNum = entity.getPattern();
        return TEXTURES[Math.min(Math.max(patternNum, 0), maxPattern)][Math.min(Math.max(colorNum, 0), maxColor)];
    }
    
    protected void scale(BallPythonEntity entity, MatrixStack matrixStack, float pPartialTickTime) {
        if(entity.isBaby()){
        	matrixStack.scale(0.2f, 0.2f, 0.2f);
        }else {
        	matrixStack.scale(0.4f, 0.4f, 0.4f);
        }
    }
}
