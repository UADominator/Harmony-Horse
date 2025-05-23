package ua.dominator.harmonyhorse.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ua.dominator.harmonyhorse.HarmonyHorse;
import ua.dominator.harmonyhorse.entity.client.DefaultOreHorseModel;
import ua.dominator.harmonyhorse.entity.client.ModModelLayers;

@Mod.EventBusSubscriber(modid = HarmonyHorse.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.DEFAULT_ORE_HORSE_LAYER, DefaultOreHorseModel::createBodyLayer);
    }
}
