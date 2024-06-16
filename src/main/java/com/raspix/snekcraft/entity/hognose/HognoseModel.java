package com.raspix.snekcraft.entity.hognose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.raspix.snekcraft.SnekCraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HognoseModel<T extends HognoseEntity> extends HierarchicalModel<T> {


    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(SnekCraft.MOD_ID, "hognose"), "main");
    private final ModelPart root;
    private final ModelPart head;
    //private final ModelPart headjuncture;
    //private final ModelPart snoot;
    //private final ModelPart shovel;
    //private final ModelPart horn;
    private final ModelPart tongue;
    //private final ModelPart prong;
    private final ModelPart jaw;
    //private final ModelPart mouth;
    //private final ModelPart lip;
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart body5;
    private final ModelPart body6;
    private final ModelPart body7;
    private final ModelPart tail;

    public HognoseModel(ModelPart root) {
        //super(true, 10.0F, 4.0F);
        this.root = root;
        this.head = root.getChild("head");
        //this.headjuncture = root.getChild("headjuncture");
        //this.snoot = root.getChild("snoot");
        //this.shovel = root.getChild("shovel");
        //this.horn = root.getChild("horn");
        this.tongue = head.getChild("tongue");
        //this.prong = root.getChild("prong");
        this.jaw = head.getChild("jaw");
        //this.mouth = root.getChild("mouth");
        //this.lip = root.getChild("lip");
        this.body = head.getChild("body");
        this.body2 = body.getChild("body2");
        this.body3 = body2.getChild("body3");
        this.body4 = body3.getChild("body4");
        this.body5 = body4.getChild("body5");
        this.body6 = body5.getChild("body6");
        this.body7 = body6.getChild("body7");
        this.tail = body7.getChild("tail");
        //this.steveref = root.getChild("steveref");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.6F, -2.703F, -3.8553F, 5.2F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.203F, -13.3947F));

        head.addOrReplaceChild("headjuncture", CubeListBuilder.create().texOffs(19, 0).addBox(-2.0F, -2.25F, -3.5F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.203F, -1.6053F, 0.0436F, 0.0F, 0.0F));

        head.addOrReplaceChild("snoot", CubeListBuilder.create().texOffs(32, 0).addBox(-1.5F, -1.5F, -1.75F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.203F, -4.6053F, 0.2618F, 0.0F, 0.0F));

        head.addOrReplaceChild("shovel", CubeListBuilder.create().texOffs(45, 0).addBox(-1.0F, -1.25F, -0.6F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5789F, -6.4131F, 0.3054F, 0.0F, 0.0F));

        head.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(52, 0).addBox(-0.5F, -0.25F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.953F, -6.8553F, 0.2618F, 0.0F, 0.0F));

        PartDefinition tongue = head.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(32, 7).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.297F, 1.3947F));

        tongue.addOrReplaceChild("prong", CubeListBuilder.create().texOffs(32, 11).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.001F, -4.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(2, 12).addBox(-2.0F, -0.8333F, -3.9167F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.6303F, -0.4386F));

        PartDefinition mouth = jaw.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(19, 14).addBox(-1.5F, -0.1683F, -1.8791F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1667F, -3.9167F, -0.4363F, 0.0F, 0.0F));

        mouth.addOrReplaceChild("lip", CubeListBuilder.create().texOffs(15, 12).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.116F, -2.0F, 1.0472F, 0.0F, 0.0F));

        PartDefinition body = head.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-2.5F, -2.0F, -0.75F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.203F, 0.3947F));

        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 30).addBox(-2.6F, -2.1F, 0.125F, 5.2F, 4.1F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.125F));

        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 40).addBox(-2.5F, -1.75F, 0.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.25F, 6.125F));

        PartDefinition body4 = body3.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(0, 50).addBox(-2.1F, -1.65F, 0.0F, 4.2F, 3.9F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

        PartDefinition body5 = body4.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(22, 21).addBox(-2.0F, -1.75F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 6.0F));

        PartDefinition body6 = body5.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(22, 31).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 7.0F));

        PartDefinition body7 = body6.addOrReplaceChild("body7", CubeListBuilder.create().texOffs(23, 41).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 8.0F));

        PartDefinition tail = body7.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(25, 48).addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 6.0F));


        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.getAllParts().forEach(ModelPart::resetPose);



        animate(entity.bleleleAnimationState, HognoseAnimation.blelele, ageInTicks);
        animate(entity.hideAnimationState, HognoseAnimation.hide, ageInTicks);
        animate(entity.shoulderAnimationState, HognoseAnimation.shoulder, ageInTicks);
        animate(entity.strikeAnimationState, HognoseAnimation.strike, ageInTicks);

        if(limbSwingAmount >= 0.15f){
            animateWalk(HognoseAnimation.slither, limbSwing, limbSwingAmount, 1f, 2.5f);
        }else {
            animate(entity.idleAnimationState, HognoseAnimation.idle, ageInTicks);
        }


        //animateWalk(HognoseAnimation.slither, limbSwing, limbSwingAmount, 1, 2.5f);

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
