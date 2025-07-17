package ua.dominator.horses.server;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ua.dominator.horses.shared.Horses;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Logger;

@Mod.EventBusSubscriber(modid = Horses.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorsesServer {
    public static final Logger LOGGER = Logger.getLogger(Horses.MODID);

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event){
        LOGGER.info("Server loaded");
    }

}
