package ru.vlade1k.tutorial.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.gui.pin.PinCodeGui;
import ru.vlade1k.tutorial.common.handler.ModTab;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

import java.util.ArrayList;
import java.util.Random;

public class BlockPinCodeStorage extends BlockContainer {

  private final Random rand = new Random();
  public BlockPinCodeStorage() {
    super(Material.wood);
    setBlockName("storage");
    setCreativeTab(ModTab.INSTANCE);
  }

  @Override
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float lx, float ly, float lz) {
    if (world.isRemote) return true;

    TileEntity te = world.getTileEntity(x, y, z);
    if (te != null && te instanceof TilePinCodeStorage) {
      Minecraft.getMinecraft().displayGuiScreen(new PinCodeGui((TilePinCodeStorage) te, player, x, y, z));
      return true;
    }
    return false;
  }

  @Override
  public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
    if (world.isRemote) return;

    ArrayList drops = new ArrayList();

    TileEntity teRaw = world.getTileEntity(x, y, z);

    if (teRaw != null && teRaw instanceof TilePinCodeStorage) {
      TilePinCodeStorage te = (TilePinCodeStorage) teRaw;

      for (int i = 0; i < te.getSizeInventory(); i++) {
        ItemStack stack = te.getStackInSlot(i);

        if (stack != null) drops.add(stack.copy());
      }
    }

    for (int i = 0;i < drops.size();i++) {
      EntityItem item = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, (ItemStack) drops.get(i));
      item.setVelocity((rand.nextDouble() - 0.5) * 0.25, rand.nextDouble() * 0.5 * 0.25, (rand.nextDouble() - 0.5) * 0.25);
      world.spawnEntityInWorld(item);
    }
  }

  public TileEntity createNewTileEntity(World world, int par2) {
    return new TilePinCodeStorage();
  }
}
