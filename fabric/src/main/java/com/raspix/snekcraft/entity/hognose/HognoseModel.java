package com.raspix.snekcraft.entity.hognose;

import com.raspix.snekcraft.SnekCraft;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HognoseModel<T extends HognoseEntity> extends SinglePartEntityModel<T> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(SnekCraft.MOD_ID, "hognose"), "main");
	private final ModelPart root, head, tongue, jaw, body, body2, body3, body4, body5, body6, body7, tail;
	
	public HognoseModel(ModelPart root) {
		this.root = root;
		head = root.getChild("head");
		tongue = head.getChild("tongue");
		jaw = head.getChild("jaw");
        body = head.getChild("body");
        body2 = body.getChild("body2");
        body3 = body2.getChild("body3");
        body4 = body3.getChild("body4");
        body5 = body4.getChild("body5");
        body6 = body5.getChild("body6");
        body7 = body6.getChild("body7");
        tail = body7.getChild("tail");
	}
	
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.6f, -2.703f, -3.8553f, 5.2f, 3.0f, 4.0f, new Dilation(0.0f)), ModelTransform.pivot(0.0f, 22.203f, -13.3947f));
		
        head.addChild("headjuncture", ModelPartBuilder.create().uv(19, 0).cuboid(-2.0F, -2.25F, -3.5F, 4.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.203F, -1.6053F, 0.0436F, 0.0F, 0.0F));
        
        head.addChild("snoot", ModelPartBuilder.create().uv(32, 0).cuboid(-1.5F, -1.5F, -1.75F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.203F, -4.6053F, 0.2618F, 0.0F, 0.0F));
        
        head.addChild("shovel", ModelPartBuilder.create().uv(45, 0).cuboid(-1.0F, -1.25F, -0.6F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5789F, -6.4131F, 0.3054F, 0.0F, 0.0F));
        
        head.addChild("horn", ModelPartBuilder.create().uv(52, 0).cuboid(-0.5F, -0.25F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.953F, -6.8553F, 0.2618F, 0.0F, 0.0F));
        
        ModelPartData tongue = head.addChild("tongue", ModelPartBuilder.create().uv(32, 7).cuboid(-0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.297F, 1.3947F));
        
        tongue.addChild("prong", ModelPartBuilder.create().uv(32, 11).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.001F, -4.0F));
        
        ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(2, 12).cuboid(-2.0F, -0.8333F, -3.9167F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.6303F, -0.4386F));
        
        ModelPartData mouth = jaw.addChild("mouth", ModelPartBuilder.create().uv(19, 14).cuboid(-1.5F, -0.1683F, -1.8791F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.1667F, -3.9167F, -0.4363F, 0.0F, 0.0F));
        
        mouth.addChild("lip", ModelPartBuilder.create().uv(15, 12).cuboid(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.116F, -2.0F, 1.0472F, 0.0F, 0.0F));
        
        ModelPartData body = head.addChild("body", ModelPartBuilder.create().uv(0, 20).cuboid(-2.5F, -2.0F, -0.75F, 5.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.203F, 0.3947F));
        
        ModelPartData body2 = body.addChild("body2", ModelPartBuilder.create().uv(0, 30).cuboid(-2.6F, -2.1F, 0.125F, 5.2F, 4.1F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 5.125F));
        
        ModelPartData body3 = body2.addChild("body3", ModelPartBuilder.create().uv(0, 40).cuboid(-2.5F, -1.75F, 0.0F, 5.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.25F, 6.125F));
        
        ModelPartData body4 = body3.addChild("body4", ModelPartBuilder.create().uv(0, 50).cuboid(-2.1F, -1.65F, 0.0F, 4.2F, 3.9F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));
        
        ModelPartData body5 = body4.addChild("body5", ModelPartBuilder.create().uv(22, 21).cuboid(-2.0F, -1.75F, 0.0F, 4.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 6.0F));
        
        ModelPartData body6 = body5.addChild("body6", ModelPartBuilder.create().uv(22, 31).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.25F, 7.0F));
        
        ModelPartData body7 = body6.addChild("body7", ModelPartBuilder.create().uv(23, 41).cuboid(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, 8.0F));
        
        ModelPartData tail = body7.addChild("tail", ModelPartBuilder.create().uv(25, 48).cuboid(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 0.0F, 6.0F));
        
        return TexturedModelData.of(modelData, 64, 64);
	}
	
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.traverse().forEach(ModelPart::resetTransform);
		
		updateAnimation(entity.bleleleAnimationState, HognoseAnimation.blelele, ageInTicks);
		updateAnimation(entity.hideAnimationState, HognoseAnimation.hide, ageInTicks);
		updateAnimation(entity.shoulderAnimationState, HognoseAnimation.shoulder, ageInTicks);
		updateAnimation(entity.strikeAnimationState, HognoseAnimation.strike, ageInTicks);
		
		if(limbSwingAmount >= 0.15f) {
			animateMovement(HognoseAnimation.slither, limbSwing, limbSwingAmount, 1.0f, 2.5f);
		}else {
			updateAnimation(entity.idleAnimationState, HognoseAnimation.idle, ageInTicks);
		}
	}
	
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(matrixStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	@Override
	public ModelPart getPart() {
		return root;
	}
}
