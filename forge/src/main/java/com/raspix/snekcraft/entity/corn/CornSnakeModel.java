package com.raspix.snekcraft.entity.corn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.raspix.snekcraft.SnekCraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class CornSnakeModel<T extends CornSnakeEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(SnekCraft.MOD_ID, "ball_python"), "main");

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


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(70, 50).addBox(-4.0F, -3.1F, -10.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, -16.0F));

        PartDefinition tongue = head.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -6.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.6F, -2.6F));

        PartDefinition prong = tongue.addOrReplaceChild("prong", CubeListBuilder.create().texOffs(52, 6).addBox(-3.0F, -0.8F, -4.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.8F, -6.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(62, 0).addBox(-3.0F, -0.5F, -11.2F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -2.8F));

        PartDefinition mouth = jaw.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -11.2F));

        PartDefinition mouth__r1 = mouth.addOrReplaceChild("mouth__r1", CubeListBuilder.create().texOffs(27, 58).addBox(-2.5F, -1.5F, -5.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition mid = head.addOrReplaceChild("mid", CubeListBuilder.create().texOffs(33, 0).addBox(-3.5F, -8.5F, -38.0F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 24.0F));

        PartDefinition brows = mid.addOrReplaceChild("brows", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition eyes = brows.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition eye_r = eyes.addOrReplaceChild("eye_r", CubeListBuilder.create().texOffs(0, 7).addBox(-4.0F, -7.0F, -38.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.04F, -7.0F, -38.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.5F));

        PartDefinition eye_l = eyes.addOrReplaceChild("eye_l", CubeListBuilder.create().texOffs(9, 7).addBox(2.0F, -7.0F, -38.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(2, 2).addBox(4.04F, -7.0F, -38.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.5F));

        PartDefinition snoot = head.addOrReplaceChild("snoot", CubeListBuilder.create().texOffs(62, 14).addBox(-3.0F, -8.0F, -43.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 24.0F));

        PartDefinition body = head.addOrReplaceChild("body", CubeListBuilder.create().texOffs(60, 32).addBox(-3.0F, -3.5F, 0.0F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.0F));

        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(41, 58).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 11.0F));

        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -2.52F, -1.0F, 6.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 12.0F));

        PartDefinition body4 = body3.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(0, 27).addBox(-3.0F, -2.5F, -1.0F, 6.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 14.0F));

        PartDefinition body5 = body4.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.52F, -1.0F, 6.0F, 6.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 18.0F));

        PartDefinition body6 = body5.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(31, 34).addBox(-2.5F, -1.5F, -1.0F, 5.0F, 5.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 20.0F));

        PartDefinition body7 = body6.addOrReplaceChild("body7", CubeListBuilder.create().texOffs(37, 11).addBox(-2.0F, -1.5F, -1.0F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 18.0F));

        PartDefinition tail = body7.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(66, 65).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 16.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.getAllParts().forEach(ModelPart::resetPose);

        animate(entity.bleleleAnimationState, CornSnakeAnimation.blelele, ageInTicks);
        animate(entity.hideAnimationState, CornSnakeAnimation.hide, ageInTicks);
        animate(entity.shoulderAnimationState, CornSnakeAnimation.shoulder, ageInTicks);
        animate(entity.strikeAnimationState, CornSnakeAnimation.strike, ageInTicks);

        if(limbSwingAmount >= 0.15f){
            animateWalk(CornSnakeAnimation.slither, limbSwing, limbSwingAmount, 1f, 2.5f);
        }else {
            animate(entity.idleAnimationState, CornSnakeAnimation.idle, ageInTicks);
        }

        //animateWalk(CornSnakeAnimation.slither, limbSwing, limbSwingAmount, 1, 2.5f);

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
