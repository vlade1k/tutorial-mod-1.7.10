package ru.vlade1k.tutorial.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.vlade1k.tutorial.common.handler.ModBlocks;
import ru.vlade1k.tutorial.common.handler.ModEntity;
import ru.vlade1k.tutorial.common.handler.ModItems;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event){
        ModBlocks.register();
        ModItems.register();
        ModEntity.register();
    }

    public void init(FMLInitializationEvent event){

    }

    public void postInit(FMLPostInitializationEvent event){

    }
}
