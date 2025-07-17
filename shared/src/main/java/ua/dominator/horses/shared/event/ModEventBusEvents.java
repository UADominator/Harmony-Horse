package ua.dominator.horses.shared.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ua.dominator.horses.shared.Horses;
import ua.dominator.horses.shared.entity.ModEntities;
import ua.dominator.horses.shared.entity.horses.DefaultOreHorse;

@Mod.EventBusSubscriber(modid = Horses.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.DEFAULT_ORE_HORSE.get(), DefaultOreHorse.createAttributes().build());
    }
}
