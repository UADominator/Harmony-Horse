package ua.dominator.horses.shared.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ua.dominator.horses.shared.Horses;
import ua.dominator.horses.shared.entity.horses.DefaultOreHorse;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Horses.MODID);

    public static final RegistryObject<EntityType<DefaultOreHorse>> DEFAULT_ORE_HORSE =
            ENTITY_TYPES.register("default_ore_horse", () -> EntityType.Builder.of(DefaultOreHorse::new, MobCategory.CREATURE)
                    .sized(1.4f, 1.4f).build("default_ore_horse"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
