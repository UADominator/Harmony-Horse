package ua.dominator.horses.shared.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import ua.dominator.horses.shared.entity.animations.OreHorseAnimationsDefinitions;
import ua.dominator.horses.shared.entity.horses.DefaultOreHorse;

public class DefaultOreHorseModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart bodyAll;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart legs;
    private final ModelPart legRL;
    private final ModelPart legRR;
    private final ModelPart legFL;
    private final ModelPart legFR;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart ears;
    private final ModelPart earR;
    private final ModelPart earL;

    public DefaultOreHorseModel(ModelPart root) {
        this.bodyAll = root.getChild("bodyAll");
        this.body = this.bodyAll.getChild("body");
        this.tail = this.body.getChild("tail");
        this.legs = this.body.getChild("legs");
        this.legRL = this.legs.getChild("legRL");
        this.legRR = this.legs.getChild("legRR");
        this.legFL = this.legs.getChild("legFL");
        this.legFR = this.legs.getChild("legFR");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.ears = this.head.getChild("ears");
        this.earR = this.ears.getChild("earR");
        this.earL = this.ears.getChild("earL");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bodyAll = partdefinition.addOrReplaceChild("bodyAll", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 9.0F));

        PartDefinition body = bodyAll.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -9.0F, -20.0F, 10.0F, 10.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(42, 36).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(3.0F, 1.0F, 0.0F));

        PartDefinition legRL = legs.addOrReplaceChild("legRL", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legRR = legs.addOrReplaceChild("legRR", CubeListBuilder.create().texOffs(48, 21).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 0.0F, 0.0F));

        PartDefinition legFL = legs.addOrReplaceChild("legFL", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, -18.0F));

        PartDefinition legFR = legs.addOrReplaceChild("legFR", CubeListBuilder.create().texOffs(48, 21).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 0.0F, -18.0F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 35).addBox(-2.0F, -11.0F, -3.0F, 4.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(56, 36).addBox(-1.0F, -16.0F, 4.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -17.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -5.0F, -6.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).addBox(-2.0F, -5.0F, -11.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 3.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -1.0F));

        PartDefinition earR = ears.addOrReplaceChild("earR", CubeListBuilder.create().texOffs(19, 16).mirror().addBox(0.8073F, -3.059F, -1.0174F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0873F));

        PartDefinition earL = ears.addOrReplaceChild("earL", CubeListBuilder.create().texOffs(19, 16).addBox(-2.8073F, -3.059F, -1.0174F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, -0.0873F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(OreHorseAnimationsDefinitions.WALKING, limbSwing, limbSwingAmount, 2f, 2f);
        this.animate(((DefaultOreHorse) entity).idleAnimationState, OreHorseAnimationsDefinitions.IDLE, ageInTicks, 1f);
        this.animate(((DefaultOreHorse) entity).damagedAnimationState, OreHorseAnimationsDefinitions.DAMAGE, ageInTicks, 1f);
        this.animate(((DefaultOreHorse) entity).attackAnimationState, OreHorseAnimationsDefinitions.ATACK, ageInTicks, 1f);
        this.animate(((DefaultOreHorse) entity).poopAnimationState, OreHorseAnimationsDefinitions.POOP, ageInTicks, 1f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bodyAll.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return bodyAll;
    }
}