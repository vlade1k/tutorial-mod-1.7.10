package ru.vlade1k.tutorial.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.handler.ModTab;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

import java.util.Random;

public class BlockPinCodeStorage extends BlockContainer {

  private final Random rand = new Random();
  public BlockPinCodeStorage() {
    super(Material.wood);
    setBlockName("storage");
    setCreativeTab(ModTab.INSTANCE);
  }

  @Override
  public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer activator,
      int side, float hitX, float hitY, float hitZ) {

    if (!world.isRemote) {
      TileEntity tile = world.getTileEntity(xCoord, yCoord, zCoord);
      if (tile instanceof TilePinCodeStorage) {
        TilePinCodeStorage storageTile = (TilePinCodeStorage) tile;
        activator.openGui(TutorialMod.INSTANCE, 0, world, xCoord, yCoord, zCoord);
      }
    }
    return true;
  }

  @Override
  public void breakBlock(World world, int x, int y, int z, Block block, int wtf) {
    if (!world.isRemote) {
      TileEntity tile = world.getTileEntity(x, y, z);
      if (tile instanceof TilePinCodeStorage) {
        TilePinCodeStorage storageTile = (TilePinCodeStorage) tile;
        ItemStack[] storageItems = storageTile.getStorageItems();
        for (int i = 0; i < storageTile.getStorageSize(); i++) {
          if (storageItems[i] != null) {
            EntityItem item = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, storageItems[i].copy());
            world.spawnEntityInWorld(item);
          }
        }
      }
    }
  }

  @Override
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
    return new TilePinCodeStorage();
  }
}
