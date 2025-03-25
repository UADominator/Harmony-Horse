package ua.dominator.harmonyhorse.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ua.dominator.harmonyhorse.HarmonyHorse;
import ua.dominator.harmonyhorse.entity.ModEntities;
import ua.dominator.harmonyhorse.entity.horses.DefaultOreHorse;

@Mod.EventBusSubscriber(modid = HarmonyHorse.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.DEFAULT_ORE_HORSE.get(), DefaultOreHorse.createAttributes().build());
    }
}
