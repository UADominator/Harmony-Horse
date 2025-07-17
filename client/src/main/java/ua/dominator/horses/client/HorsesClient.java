package ua.dominator.horses.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import ua.dominator.horses.client.render.DefaultOreHorseModel;
import ua.dominator.horses.client.render.ModModelLayers;
import ua.dominator.horses.shared.Horses;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ua.dominator.horses.shared.entity.ModEntities;
import ua.dominator.horses.client.render.DefaultOreHorseRenderer;
import ua.dominator.horses.shared.entity.horses.DefaultOreHorse;

import java.util.logging.Logger;


@Mod.EventBusSubscriber(modid = Horses.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorsesClient {
    public static final Logger LOGGER = Logger.getLogger(Horses.MODID);

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event){
        LOGGER.info("Client loaded");
        EntityRenderers.register(ModEntities.DEFAULT_ORE_HORSE.get(), DefaultOreHorseRenderer::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.DEFAULT_ORE_HORSE.get(), DefaultOreHorse.createAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DEFAULT_ORE_HORSE_LAYER, DefaultOreHorseModel::createBodyLayer);
    }

}
