package ru.vlade1k.tutorial.common;

import static ru.vlade1k.tutorial.common.handler.ModBlocks.PIN_CODE_CHEST;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.gui.GuiHandler;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.vlade1k.tutorial.common.handler.ModBlocks;
import ru.vlade1k.tutorial.common.handler.ModEntity;
import ru.vlade1k.tutorial.common.handler.ModItems;
import ru.vlade1k.tutorial.common.render.RendererPinCodeChest;
import ru.vlade1k.tutorial.common.render.item.ItemRenderPinCodeChest;
import ru.vlade1k.tutorial.common.tile.TileEntityPinCodeChest;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.register();
        ModItems.register();
        ModEntity.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(TutorialMod.INSTANCE, new GuiHandler());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void registerRenderThings() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPinCodeChest.class, new RendererPinCodeChest());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PIN_CODE_CHEST), new ItemRenderPinCodeChest());
    }
}
