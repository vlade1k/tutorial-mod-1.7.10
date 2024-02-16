package ru.vlade1k.tutorial.common.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

public class GuiHandler implements IGuiHandler {

  @Override
  public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity storageTile = world.getTileEntity(x, y, z);
    if (storageTile instanceof TilePinCodeStorage) {
      if (id == 0) return new ContainerStorage((TilePinCodeStorage) storageTile, player);
    }
    return null;
  }

  @Override
  public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity storageTile = world.getTileEntity(x, y, z);
    if (storageTile instanceof TilePinCodeStorage) {
      if (id == 0) return new GuiStorage((TilePinCodeStorage) storageTile, player);
    }
    return null;
  }
}
