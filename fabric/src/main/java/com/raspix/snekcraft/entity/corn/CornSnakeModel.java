package com.raspix.snekcraft.entity.corn;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.ball_python.BallPythonAnimation;
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

public class CornSnakeModel<T extends CornSnakeEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(SnekCraft.MOD_ID, "ball_python"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart tongue;
    private final ModelPart prong;
    private final ModelPart jaw;
    private final ModelPart mouth;
    private final ModelPart mid;
    private final ModelPart brows;
    private final ModelPart eyes;
    private final ModelPart eye_r;
    private final ModelPart eye_l;
    private final ModelPart snoot;
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart body5;
    private final ModelPart body6;
    private final ModelPart body7;
    private final ModelPart tail;

    public CornSnakeModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.tongue = this.head.getChild("tongue");
        this.prong = this.tongue.getChild("prong");
        this.jaw = this.head.getChild("jaw");
        this.mouth = this.jaw.getChild("mouth");
        this.mid = this.head.getChild("mid");
        this.brows = this.mid.getChild("brows");
        this.eyes = this.brows.getChild("eyes");
        this.eye_r = this.eyes.getChild("eye_r");
        this.eye_l = this.eyes.getChild("eye_l");
        this.snoot = this.head.getChild("snoot");
        this.body = this.head.getChild("body");
        this.body2 = this.body.getChild("body2");
        this.body3 = this.body2.getChild("body3");
        this.body4 = this.body3.getChild("body4");
        this.body5 = this.body4.getChild("body5");
        this.body6 = this.body5.getChild("body6");
        this.body7 = this.body6.getChild("body7");
        this.tail = this.body7.getChild("tail");
    }


    public static TexturedModelData createBodyLayer() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(70, 50).cuboid(-4.0F, -3.1F, -10.0F, 8.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, -16.0F));

        ModelPartData tongue = head.addChild("tongue", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -6.0F, 2.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.6F, -2.6F));

        ModelPartData prong = tongue.addChild("prong", ModelPartBuilder.create().uv(52, 6).cuboid(-3.0F, -0.8F, -4.0F, 6.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.8F, -6.0F));

        ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(62, 0).cuboid(-3.0F, -0.5F, -11.2F, 6.0F, 2.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, -2.8F));

        ModelPartData mouth = jaw.addChild("mouth", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, -11.2F));

        ModelPartData mouth__r1 = mouth.addChild("mouth__r1", ModelPartBuilder.create().uv(27, 58).cuboid(-2.5F, -1.5F, -5.5F, 5.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 1.0F, -0.1309F, 0.0F, 0.0F));

        ModelPartData mid = head.addChild("mid", ModelPartBuilder.create().uv(33, 0).cuboid(-3.5F, -8.5F, -38.0F, 7.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 24.0F));

        ModelPartData brows = mid.addChild("brows", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData eyes = brows.addChild("eyes", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData eye_r = eyes.addChild("eye_r", ModelPartBuilder.create().uv(0, 7).cuboid(-4.0F, -7.0F, -38.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.04F, -7.0F, -38.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.5F));

        ModelPartData eye_l = eyes.addChild("eye_l", ModelPartBuilder.create().uv(9, 7).cuboid(2.0F, -7.0F, -38.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 2).cuboid(4.04F, -7.0F, -38.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.5F));

        ModelPartData snoot = head.addChild("snoot", ModelPartBuilder.create().uv(62, 14).cuboid(-3.0F, -8.0F, -43.0F, 6.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 24.0F));

        ModelPartData body = head.addChild("body", ModelPartBuilder.create().uv(60, 32).cuboid(-3.0F, -3.5F, 0.0F, 6.0F, 6.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -2.0F));

        ModelPartData body2 = body.addChild("body2", ModelPartBuilder.create().uv(41, 58).cuboid(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 11.0F));

        ModelPartData body3 = body2.addChild("body3", ModelPartBuilder.create().uv(0, 52).cuboid(-3.0F, -2.52F, -1.0F, 6.0F, 6.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 12.0F));

        ModelPartData body4 = body3.addChild("body4", ModelPartBuilder.create().uv(0, 27).cuboid(-3.0F, -2.5F, -1.0F, 6.0F, 6.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 14.0F));

        ModelPartData body5 = body4.addChild("body5", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -2.52F, -1.0F, 6.0F, 6.0F, 20.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 18.0F));

        ModelPartData body6 = body5.addChild("body6", ModelPartBuilder.create().uv(31, 34).cuboid(-2.5F, -1.5F, -1.0F, 5.0F, 5.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 20.0F));

        ModelPartData body7 = body6.addChild("body7", ModelPartBuilder.create().uv(37, 11).cuboid(-2.0F, -1.5F, -1.0F, 4.0F, 4.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 18.0F));

        ModelPartData tail = body7.addChild("tail", ModelPartBuilder.create().uv(66, 65).cuboid(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 16.0F));

        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.traverse().forEach(ModelPart::resetTransform);

        updateAnimation(entity.bleleleAnimationState, BallPythonAnimation.blelele, ageInTicks);
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
}
