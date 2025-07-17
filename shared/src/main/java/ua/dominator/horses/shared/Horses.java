package ua.dominator.horses.shared;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ua.dominator.horses.shared.entity.ModEntities;

import java.util.logging.Logger;

@Mod(Horses.MODID)
public class Horses {
    public static final String MODID = "template";
    public static final Logger LOGGER = Logger.getLogger(MODID);

    public Horses(){
        LOGGER.info("Shared loaded");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
