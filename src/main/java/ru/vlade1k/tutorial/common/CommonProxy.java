package ru.vlade1k.tutorial.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.gui.GuiHandler;
import ru.vlade1k.tutorial.common.handler.ModBlocks;
import ru.vlade1k.tutorial.common.handler.ModEntity;
import ru.vlade1k.tutorial.common.handler.ModItems;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event){
        ModBlocks.register();
        ModItems.register();
        ModEntity.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(TutorialMod.INSTANCE, new GuiHandler());
    }

    public void init(FMLInitializationEvent event){

    }

    public void postInit(FMLPostInitializationEvent event){

    }
}
