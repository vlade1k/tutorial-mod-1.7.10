package ru.vlade1k.tutorial.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.handler.ModTab;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

public class BlockPinCodeStorage extends BlockContainer {

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

        storageTile.handeInputStack(activator, activator.getHeldItem());

      }
    }
    return true;
  }

//  @Override
//  public void breakBlock(World world, int x, int y, int z, Block block, int wtf) {
//    super.breakBlock(world, x, y, z, block, wtf);
//
//    if (!world.isRemote) {
//      TileEntity tile = world.getTileEntity(x, y, z);
//      if (tile instanceof TilePinCodeStorage) {
//        TilePinCodeStorage storageTile = (TilePinCodeStorage) tile;
//
//      }
//    }
//  }

  @Override
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
    return new TilePinCodeStorage();
  }
}
