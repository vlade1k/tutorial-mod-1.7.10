package ru.vlade1k.tutorial.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.block.BlockPinCodeStorage;
import ru.vlade1k.tutorial.common.block.StorageBlock;
import ru.vlade1k.tutorial.common.tile.StorageTile;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;
import ru.vlade1k.tutorial.common.block.BlockPinCodeChest;

public class ModBlocks {

    public static final StorageBlock STORAGE_BLOCK = new StorageBlock();
    public static final BlockPinCodeStorage PIN_CODE_STORAGE = new BlockPinCodeStorage();
    public static final BlockPinCodeChest PIN_CODE_CHEST = new BlockPinCodeChest(0);

    public static void register(){
        GameRegistry.registerBlock(STORAGE_BLOCK, "storage");
        GameRegistry.registerTileEntity(StorageTile.class, TutorialMod.MOD_ID + ":storage");

        GameRegistry.registerBlock(PIN_CODE_STORAGE, "pinCodeStorage");
        GameRegistry.registerTileEntity(TilePinCodeStorage.class, TutorialMod.MOD_ID + ":pinCodeStorage");
    }
}
