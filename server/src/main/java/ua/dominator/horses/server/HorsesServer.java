package ua.dominator.horses.server;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.targets.CommonServerLaunchHandler;
import ua.dominator.horses.shared.Horses;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Logger;

@Mod.EventBusSubscriber(modid = Horses.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HorsesServer {
    public static final Logger LOGGER = Logger.getLogger(Horses.MODID);

    @SubscribeEvent
    public static void onServerStartingEvent(FMLCommonSetupEvent event){
        LOGGER.info("Server loaded");
    }

}
