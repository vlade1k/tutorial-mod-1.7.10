package ru.vlade1k.tutorial.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.block.StorageBlock;
import ru.vlade1k.tutorial.common.tile.StorageTile;

public class ModBlocks {

    public static final StorageBlock STORAGE_BLOCK = new StorageBlock();
    public static void register(){
        GameRegistry.registerBlock(STORAGE_BLOCK, "storage");
        GameRegistry.registerTileEntity(StorageTile.class, TutorialMod.MOD_ID + ":storage");
    }
}
