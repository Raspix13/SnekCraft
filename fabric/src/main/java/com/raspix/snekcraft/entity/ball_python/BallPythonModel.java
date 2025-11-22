package com.raspix.snekcraft.entity.ball_python;

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
import org.joml.Vector3f;

public class BallPythonModel<T extends BallPythonEntity> extends SinglePartEntityModel<T> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(SnekCraft.MOD_ID, "ball_python"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart tongue;
    private final ModelPart prong;
    private final ModelPart jaw;
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart body5;
    private final ModelPart body6;
    private final ModelPart body7;
    private final ModelPart tail;

    public BallPythonModel(ModelPart root) {
        this.root = root;
        head = root.getChild("head");
        tongue = head.getChild("tongue");
        prong = tongue.getChild("prong");
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
        
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(45, 65).cuboid(-5.0F, -3.1F, -10.0F, 10.0F, 6.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 20.0F, -16.0F, 0.0F, 0.0F, 0.0F));
        
        ModelPartData tongue = head.addChild("tongue", ModelPartBuilder.create().uv(0, 29).cuboid(-1.0F, 0.0F, -6.0F, 2.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.4F, -3.6F));
        
        tongue.addChild("prong", ModelPartBuilder.create().uv(31, 65).cuboid(-3.0F, 0.0F, -4.0F, 6.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -6.0F));
        
        ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(78, 0).cuboid(-3.0F, -0.5F, -11.2F, 6.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, -2.8F));
        
        jaw.addChild("mouth", ModelPartBuilder.create().uv(66, 0).cuboid(-2.4F, -1.5F, -5.5F, 4.8F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -11.2F));
        
        ModelPartData mid = head.addChild("mid", ModelPartBuilder.create().uv(78, 15).cuboid(-4.0F, -7.7F, -38.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 24.0F));
        
        ModelPartData brows = mid.addChild("brows", ModelPartBuilder.create().uv(0, 9).cuboid(2.4F, -8.0F, -38.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.4F, -8.0F, -38.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        
        ModelPartData eyes = brows.addChild("eyes", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        
        eyes.addChild("eye_r", ModelPartBuilder.create().uv(11, 0).cuboid(-4.6F, -7.0F, -38.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 9).cuboid(-4.6F, -7.0F, -37.25F, 0.0F, 2.0F, 0.5F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.62F, -7.0F, -38.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.5F));
        
        eyes.addChild("eye_l", ModelPartBuilder.create().uv(11, 9).cuboid(2.6F, -7.0F, -38.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(3, 9).cuboid(4.62F, -7.0F, -37.25F, 0.0F, 2.0F, 0.5F, new Dilation(0.0F))
                .uv(2, 2).cuboid(4.64F, -7.0F, -38.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.5F));

        head.addChild("bridge", ModelPartBuilder.create().uv(25, 78).cuboid(-2.0F, -7.2F, -43.0F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 24.0F));
        
        head.addChild("snoot", ModelPartBuilder.create().uv(41, 0).cuboid(-3.0F, -6.5F, -44.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 24.0F));
        
        ModelPartData body = head.addChild("body", ModelPartBuilder.create().uv(78, 36).cuboid(-2.5F, -2.0F, 0.0F, 5.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -1.0F));
        
        ModelPartData body2 = body.addChild("body2", ModelPartBuilder.create().uv(74, 70).cuboid(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 10.0F));
        
        ModelPartData body3 = body2.addChild("body3", ModelPartBuilder.create().uv(0, 56).cuboid(-4.0F, -3.52F, 0.0F, 8.0F, 7.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 12.0F));
        
        ModelPartData body4 = body3.addChild("body4", ModelPartBuilder.create().uv(39, 38).cuboid(-5.0F, -4.5F, 0.0F, 10.0F, 8.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 14.0F));
        
        ModelPartData body5 = body4.addChild("body5", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -4.52F, 0.0F, 10.0F, 8.0F, 20.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 18.0F));
        
        ModelPartData body6 = body5.addChild("body6", ModelPartBuilder.create().uv(0, 29).cuboid(-5.0F, -4.5F, 0.0F, 10.0F, 8.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 20.0F));
        
        ModelPartData body7 = body6.addChild("body7", ModelPartBuilder.create().uv(45, 13).cuboid(-4.0F, -3.5F, 0.0F, 8.0F, 6.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 18.0F));
        
        body7.addChild("tail", ModelPartBuilder.create().uv(0, 78).cuboid(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 16.0F));
        
        return TexturedModelData.of(modelData, 128, 128);
    }
    
    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.traverse().forEach(ModelPart::resetTransform);

        if(ageInTicks == 1.0){
            poseForBook();
        }

        updateAnimation(entity.bleleleAnimationState, BallPythonAnimation.blelele, ageInTicks);
        updateAnimation(entity.periscopeAnimationState, BallPythonAnimation.periscope, ageInTicks);
        updateAnimation(entity.hideAnimationState, BallPythonAnimation.hide, ageInTicks);
        updateAnimation(entity.shoulderAnimationState, BallPythonAnimation.shoulder, ageInTicks);
        updateAnimation(entity.strikeAnimationState, BallPythonAnimation.strike, ageInTicks);
        
        if(limbSwingAmount >= 0.15f){
        	animateMovement(BallPythonAnimation.slither, limbSwing, limbSwingAmount, 1f, 2.5f);
        }else {
            updateAnimation(entity.idleAnimationState, BallPythonAnimation.idle, ageInTicks);
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

    public void poseForBook() {
        head.rotate(formatRotationVector(-30.0F + 20.0F, -5.0F, 0.0F));
        head.translate(new Vector3f(-3.0F, -6.0F, 15.0F));
        body.rotate(formatRotationVector(43.0F, 52.0F, 37.0F));
        body2.rotate(formatRotationVector(0.0F, 75.0F, 0.0F));
        body3.rotate(formatRotationVector(0.0F, 60.0F, 0.0F));
        body4.rotate(formatRotationVector(0.0F, 60.0F, 0.0F));
        body5.rotate(formatRotationVector(0.0F, 50.0F, 0.0F));
        body6.rotate(formatRotationVector(0.0F, 57.5F, 0.0F));
        body7.rotate(formatRotationVector(0.0F, 45.0F, 0.0F));
        tail.rotate(formatRotationVector(0.0F, 45.0F, 0.0F));
    }

    public Vector3f formatRotationVector(float x, float y, float z){
        return new Vector3f((float) Math.toRadians(x), (float) -Math.toRadians(y), (float) -Math.toRadians(z)); // vert, side, tilt
    }
    
}
