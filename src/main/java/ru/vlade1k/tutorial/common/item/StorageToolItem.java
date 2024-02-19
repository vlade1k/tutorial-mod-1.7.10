package ru.vlade1k.tutorial.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.block.BlockPinCodeStorage;
import ru.vlade1k.tutorial.common.handler.ModTab;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;


public class StorageToolItem extends Item {

  public StorageToolItem() {
    setUnlocalizedName(TutorialMod.MOD_ID + ":storageTool");
    setTextureName(TutorialMod.MOD_ID + ":storage-tool");
    setMaxStackSize(1);
    setCreativeTab(ModTab.INSTANCE);
  }

}
