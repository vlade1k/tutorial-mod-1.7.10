package ru.vlade1k.tutorial.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.block.BlockPinCodeChest;
import ru.vlade1k.tutorial.common.block.StorageBlock;
import ru.vlade1k.tutorial.common.tile.StorageTile;
import ru.vlade1k.tutorial.common.tile.TileEntityPinCodeChest;

public class ModBlocks {

    public static final StorageBlock STORAGE_BLOCK = new StorageBlock();
    public static final BlockPinCodeChest PIN_CODE_CHEST = new BlockPinCodeChest(0);

    public static void register(){
        GameRegistry.registerBlock(STORAGE_BLOCK, "storage");
        GameRegistry.registerBlock(PIN_CODE_CHEST, "pincodechest");
        GameRegistry.registerTileEntity(StorageTile.class, TutorialMod.MOD_ID + ":storage");
        GameRegistry.registerTileEntity(TileEntityPinCodeChest.class, TutorialMod.MOD_ID + ":pincodechest");
    }
}
