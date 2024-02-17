package ru.vlade1k.tutorial.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.gui.pin.PinCodeGui;
import ru.vlade1k.tutorial.common.handler.ModTab;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

import java.util.ArrayList;
import java.util.Random;

public class BlockPinCodeStorage extends BlockContainer {

  private IIcon blockIcon;
  private IIcon frontBlockIcon;

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

  @SideOnly(Side.CLIENT)
  @Override
  public void registerBlockIcons(IIconRegister register) {
    blockIcon = register.registerIcon(TutorialMod.MOD_ID + ":block-pincode");
    frontBlockIcon = register.registerIcon(TutorialMod.MOD_ID + ":block-pincode-face");
  }

  @SideOnly(Side.CLIENT)
  @Override
  public IIcon getIcon(int side, int meta) {
    ForgeDirection direction = ForgeDirection.getOrientation(side);
    ForgeDirection blockDirection;

    if (meta != 0) {
      blockDirection = ForgeDirection.getOrientation(meta);
    } else {
      blockDirection = ForgeDirection.WEST;
    }

    if (direction == blockDirection) return frontBlockIcon;
    return blockIcon;
  }

  @Override
  public void onBlockPlacedBy(World world, int x, int y, int z,
      EntityLivingBase entity, ItemStack itemStack) {
    super.onBlockPlacedBy(world, x, y, z, entity, itemStack);
    int dir = (MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3);
    int[] r = {2, 5, 3, 4};
    world.setBlockMetadataWithNotify(x, y, z, r[dir], 3);
  }

  public TileEntity createNewTileEntity(World world, int par2) {
    return new TilePinCodeStorage();
  }
}
