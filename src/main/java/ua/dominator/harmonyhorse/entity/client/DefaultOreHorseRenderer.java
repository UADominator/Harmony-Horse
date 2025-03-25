package ua.dominator.harmonyhorse.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import ua.dominator.harmonyhorse.HarmonyHorse;
import ua.dominator.harmonyhorse.entity.horses.DefaultOreHorse;

public class DefaultOreHorseRenderer extends MobRenderer<DefaultOreHorse, DefaultOreHorseModel<DefaultOreHorse>> {
    public DefaultOreHorseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DefaultOreHorseModel<>(pContext.bakeLayer(ModModelLayers.DEFAULT_ORE_HORSE_LAYER)), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(DefaultOreHorse defaultOreHorse) {
        return new ResourceLocation(HarmonyHorse.MODID, "textures/entity/default_ore_horse.png");
    }

    @Override
    public void render(DefaultOreHorse pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
