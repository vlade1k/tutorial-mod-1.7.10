package ru.vlade1k.tutorial.common.handler;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ru.vlade1k.tutorial.common.item.StickItem;
import ru.vlade1k.tutorial.common.item.StorageToolItem;
import ru.vlade1k.tutorial.common.item.ThrowableItem;

public class ModItems {

    public static final ThrowableItem THROWABLE_ITEM = new ThrowableItem();
    public static final StickItem STICK_ITEM = new StickItem();
    public static final StorageToolItem STORAGE_TOOL_ITEM = new StorageToolItem();

    public static void register(){
        GameRegistry.registerItem(THROWABLE_ITEM, "throwableItem");
        GameRegistry.registerItem(STICK_ITEM, "stickItem");
        GameRegistry.registerItem(STORAGE_TOOL_ITEM, "storageTool");
    }
}
