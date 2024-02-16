package ru.vlade1k.tutorial;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import ru.vlade1k.tutorial.common.CommonProxy;
import ru.vlade1k.tutorial.common.entities.EntityThrowableItem;

@Mod(modid=TutorialMod.MOD_ID)
public class TutorialMod {

    @Mod.Instance
    public static TutorialMod INSTANCE;
    public static final String MOD_ID = "tutorial";
    public static final String VERSION = "1.0.0";

    @SidedProxy(
            clientSide = "ru.vlade1k.tutorial.common.ClientProxy",
            serverSide = "ru.vlade1k.tutorial.common.CommonProxy"
    )
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        EntityRegistry.registerModEntity(EntityThrowableItem.class, "throwable", 4, this, 80, 3, true);
        proxy.init(event);
        INSTANCE = this;
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
