package com.raspix.snekcraft.entity.ball_python;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.raspix.snekcraft.SnekCraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class BallPythonModel<T extends BallPythonEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(SnekCraft.MOD_ID, "ball_python"), "main");

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
        this.head = root.getChild("head");
        this.tongue = head.getChild("tongue");
        this.prong = tongue.getChild("prong");
        this.jaw = head.getChild("jaw");
        this.body = head.getChild("body");
        this.body2 = body.getChild("body2");
        this.body3 = body2.getChild("body3");
        this.body4 = body3.getChild("body4");
        this.body5 = body4.getChild("body5");
        this.body6 = body5.getChild("body6");
        this.body7 = body6.getChild("body7");
        this.tail = body7.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(45, 65).addBox(-5.0F, -3.1F, -10.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -16.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition tongue = head.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, 0.0F, -6.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.4F, -3.6F));

        tongue.addOrReplaceChild("prong", CubeListBuilder.create().texOffs(31, 65).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(78, 0).addBox(-3.0F, -0.5F, -11.2F, 6.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -2.8F));

        jaw.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(66, 0).addBox(-2.4F, -1.5F, -5.5F, 4.8F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -11.2F));

        PartDefinition mid = head.addOrReplaceChild("mid", CubeListBuilder.create().texOffs(78, 15).addBox(-4.0F, -7.7F, -38.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 24.0F));

        PartDefinition brows = mid.addOrReplaceChild("brows", CubeListBuilder.create().texOffs(0, 9).addBox(2.4F, -8.0F, -38.5F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.4F, -8.0F, -38.5F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition eyes = brows.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        eyes.addOrReplaceChild("eye_r", CubeListBuilder.create().texOffs(11, 0).addBox(-4.6F, -7.0F, -38.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(-4.6F, -7.0F, -37.25F, 0.0F, 2.0F, 0.5F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.62F, -7.0F, -38.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.5F));

        eyes.addOrReplaceChild("eye_l", CubeListBuilder.create().texOffs(11, 9).addBox(2.6F, -7.0F, -38.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(3, 9).addBox(4.62F, -7.0F, -37.25F, 0.0F, 2.0F, 0.5F, new CubeDeformation(0.0F))
                .texOffs(2, 2).addBox(4.64F, -7.0F, -38.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.5F));

        head.addOrReplaceChild("bridge", CubeListBuilder.create().texOffs(25, 78).addBox(-2.0F, -7.2F, -43.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 24.0F));

        head.addOrReplaceChild("snoot", CubeListBuilder.create().texOffs(41, 0).addBox(-3.0F, -6.5F, -44.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 24.0F));

        PartDefinition body = head.addOrReplaceChild("body", CubeListBuilder.create().texOffs(78, 36).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -1.0F));

        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(74, 70).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 10.0F));

        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 56).addBox(-4.0F, -3.52F, 0.0F, 8.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 12.0F));

        PartDefinition body4 = body3.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(39, 38).addBox(-5.0F, -4.5F, 0.0F, 10.0F, 8.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 14.0F));

        PartDefinition body5 = body4.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.52F, 0.0F, 10.0F, 8.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 18.0F));

        PartDefinition body6 = body5.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(0, 29).addBox(-5.0F, -4.5F, 0.0F, 10.0F, 8.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 20.0F));

        PartDefinition body7 = body6.addOrReplaceChild("body7", CubeListBuilder.create().texOffs(45, 13).addBox(-4.0F, -3.5F, 0.0F, 8.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 18.0F));

        body7.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 78).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 16.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.getAllParts().forEach(ModelPart::resetPose);



        animate(entity.bleleleAnimationState, BallPythonAnimation.blelele, ageInTicks);
        animate(entity.hideAnimationState, BallPythonAnimation.hide, ageInTicks);
        animate(entity.shoulderAnimationState, BallPythonAnimation.shoulder, ageInTicks);
        animate(entity.strikeAnimationState, BallPythonAnimation.strike, ageInTicks);

        if(limbSwingAmount >= 0.15f){
            animateWalk(BallPythonAnimation.slither, limbSwing, limbSwingAmount, 1f, 2.5f);
        }else {
            animate(entity.idleAnimationState, BallPythonAnimation.idle, ageInTicks);
        }


        //animateWalk(BallPythonAnimation.slither, limbSwing, limbSwingAmount, 1, 2.5f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}
