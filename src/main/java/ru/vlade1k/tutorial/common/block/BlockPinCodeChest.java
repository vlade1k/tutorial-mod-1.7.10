package ru.vlade1k.tutorial.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.handler.ModTab;
import ru.vlade1k.tutorial.common.tile.TileEntityPinCodeChest;
import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import java.util.Iterator;
import java.util.Random;


public class BlockPinCodeChest extends BlockContainer {

  private final Random field_149955_b = new Random();
  public final int field_149956_a;
  public BlockPinCodeChest(int field149956A) {
    super(Material.wood);
    field_149956_a = field149956A;
    setBlockName("pincodechest");
    setBlockTextureName(TutorialMod.MOD_ID + ":storage");
    setCreativeTab(ModTab.INSTANCE);
  }


  @Override
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) { return new TileEntityPinCodeChest(); }

  public void onBlockPlacedBy(World world, int xCoord, int yCoord, int zCoord, EntityLivingBase entityPlayer, ItemStack itemStack) {
    Block block = world.getBlock(xCoord, yCoord, zCoord - 1);
    Block block1 = world.getBlock(xCoord, yCoord, zCoord + 1);
    Block block2 = world.getBlock(xCoord - 1, yCoord, zCoord);
    Block block3 = world.getBlock(xCoord + 1, yCoord, zCoord);
    byte b0 = 0;
    int l = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

    if (l == 0) {
      b0 = 2;
    }

    if (l == 1) {
      b0 = 5;
    }

    if (l == 2) {
      b0 = 3;
    }

    if (l == 3) {
      b0 = 4;
    }

    if (block != this && block1 != this && block2 != this && block3 != this) {
      world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, b0, 3);
    }
    else {
      if ((block == this || block1 == this) && (b0 == 4 || b0 == 5)) {
        if (block == this) {
          world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord - 1, b0, 3);
        }
        else {
          world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord + 1, b0, 3);
        }

        world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, b0, 3);
      }

      if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3)) {
        if (block2 == this) {
          world.setBlockMetadataWithNotify(xCoord - 1, yCoord, zCoord, b0, 3);
        }
        else {
          world.setBlockMetadataWithNotify(xCoord + 1, yCoord, zCoord, b0, 3);
        }

        world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, b0, 3);
      }
    }

    if (itemStack.hasDisplayName()) {
      ((TileEntityPinCodeChest)world.getTileEntity(xCoord, yCoord, zCoord)).func_145976_a(itemStack.getDisplayName());
    }
  }

  public void onNeighborBlockChange(World world, int xCoord, int yCoord, int zCoord, Block block) {
    super.onNeighborBlockChange(world, xCoord, yCoord, zCoord, block);
    TileEntityPinCodeChest tileEntityPinCodeChest = (TileEntityPinCodeChest)world.getTileEntity(xCoord, yCoord, zCoord);

    if (tileEntityPinCodeChest != null) {
      tileEntityPinCodeChest.updateContainingBlockInfo();
    }
  }

  public void breakBlock(World world, int xCoord, int yCoord, int zCoord, Block block, int p_149749_6_)
  {
    TileEntityPinCodeChest tileEntityPinCodeChest = (TileEntityPinCodeChest)world.getTileEntity(xCoord, yCoord, zCoord);

    if (tileEntityPinCodeChest != null)
    {
      for (int i1 = 0; i1 < tileEntityPinCodeChest.getSizeInventory(); ++i1)
      {
        ItemStack itemstack = tileEntityPinCodeChest.getStackInSlot(i1);

        if (itemstack != null)
        {
          float f = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
          float f1 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
          EntityItem entityitem;

          for (float f2 = this.field_149955_b.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
          {
            int j1 = this.field_149955_b.nextInt(21) + 10;

            if (j1 > itemstack.stackSize)
            {
              j1 = itemstack.stackSize;
            }

            itemstack.stackSize -= j1;
            entityitem = new EntityItem(world, (double)((float)xCoord + f), (double)((float)yCoord + f1), (double)((float)zCoord + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
            float f3 = 0.05F;
            entityitem.motionX = (double)((float)this.field_149955_b.nextGaussian() * f3);
            entityitem.motionY = (double)((float)this.field_149955_b.nextGaussian() * f3 + 0.2F);
            entityitem.motionZ = (double)((float)this.field_149955_b.nextGaussian() * f3);

            if (itemstack.hasTagCompound())
            {
              entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
            }
          }
        }
      }

      world.func_147453_f(xCoord, yCoord, zCoord, block);
    }

    super.breakBlock(world, xCoord, yCoord, zCoord, block, p_149749_6_);
  }

  public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
  {
    if (world.isRemote)
    {
      return true;
    }
    else
    {
      IInventory iinventory = this.func_149951_m(world, xCoord, yCoord, zCoord);

      if (iinventory != null)
      {
        player.displayGUIChest(iinventory);
      }

      return true;
    }
  }
  private static boolean func_149953_o(World world, int xCoord, int yCoord, int zCoord) {
    Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)xCoord,
        (double)(yCoord + 1), (double)zCoord, (double)(xCoord + 1),
        (double)(yCoord + 2), (double)(zCoord + 1))).iterator();

    EntityOcelot entityocelot;

    do {
      if (!iterator.hasNext()) {
        return false;
      }

      Entity entity = (Entity)iterator.next();
      entityocelot = (EntityOcelot)entity;
    }
    while (!entityocelot.isSitting());

    return true;
  }

  public IInventory func_149951_m(World world, int xCoord, int yCoord, int zCoord)
  {
    Object object = (TileEntityPinCodeChest)world.getTileEntity(xCoord, yCoord, zCoord);

    if (object == null)
    {
      return null;
    }
    else if (world.isSideSolid(xCoord, yCoord + 1, zCoord, DOWN))
    {
      return null;
    }
    else if (func_149953_o(world, xCoord, yCoord, zCoord))
    {
      return null;
    }
    else if (world.getBlock(xCoord - 1, yCoord, zCoord) == this && (world.isSideSolid(xCoord - 1, yCoord + 1, zCoord, DOWN) || func_149953_o(world, xCoord - 1, yCoord, zCoord)))
    {
      return null;
    }
    else if (world.getBlock(xCoord + 1, yCoord, zCoord) == this && (world.isSideSolid(xCoord + 1, yCoord + 1, zCoord, DOWN) || func_149953_o(world, xCoord + 1, yCoord, zCoord)))
    {
      return null;
    }
    else if (world.getBlock(xCoord, yCoord, zCoord - 1) == this && (world.isSideSolid(xCoord, yCoord + 1, zCoord - 1, DOWN) || func_149953_o(world, xCoord, yCoord, zCoord - 1)))
    {
      return null;
    }
    else if (world.getBlock(xCoord, yCoord, zCoord + 1) == this && (world.isSideSolid(xCoord, yCoord + 1, zCoord + 1, DOWN) || func_149953_o(world, xCoord, yCoord, zCoord + 1)))
    {
      return null;
    }
    else
    {
      if (world.getBlock(xCoord - 1, yCoord, zCoord) == this)
      {
        object = new InventoryLargeChest("Double pin-code Chest", (TileEntityPinCodeChest)world.getTileEntity(xCoord - 1, yCoord, zCoord), (IInventory)object);
      }

      if (world.getBlock(xCoord + 1, yCoord, zCoord) == this)
      {
        object = new InventoryLargeChest("Double pin-code Chest", (IInventory)object, (TileEntityPinCodeChest)world.getTileEntity(xCoord + 1, yCoord, zCoord));
      }

      if (world.getBlock(xCoord, yCoord, zCoord - 1) == this)
      {
        object = new InventoryLargeChest("Double pin-code Chest", (TileEntityPinCodeChest)world.getTileEntity(xCoord, yCoord, zCoord - 1), (IInventory)object);
      }

      if (world.getBlock(xCoord, yCoord, zCoord + 1) == this)
      {
        object = new InventoryLargeChest("Double pin-code Chest", (IInventory)object, (TileEntityPinCodeChest)world.getTileEntity(xCoord, yCoord, zCoord + 1));
      }

      return (IInventory)object;
    }
  }
  public boolean renderAsNormalBlock()
  {
    return false;
  }

  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister p_149651_1_) {
    this.blockIcon = p_149651_1_.registerIcon(TutorialMod.MOD_ID + ":storage");
  }

  public void func_149954_e(World world, int xCoord, int yCoord, int zCoord) {
    if (!world.isRemote) {
      Block block = world.getBlock(xCoord, yCoord, zCoord - 1);
      Block block1 = world.getBlock(xCoord, yCoord, zCoord + 1);
      Block block2 = world.getBlock(xCoord - 1, yCoord, zCoord);
      Block block3 = world.getBlock(xCoord + 1, yCoord, zCoord);
      boolean flag = true;
      int l;
      Block block4;
      int i1;
      Block block5;
      boolean flag1;
      byte b0;
      int j1;

      if (block != this && block1 != this) {
        if (block2 != this && block3 != this) {
          b0 = 3;

          if (block.func_149730_j() && !block1.func_149730_j()) {
            b0 = 3;
          }

          if (block1.func_149730_j() && !block.func_149730_j()) {
            b0 = 2;
          }

          if (block2.func_149730_j() && !block3.func_149730_j()) {
            b0 = 5;
          }

          if (block3.func_149730_j() && !block2.func_149730_j()) {
            b0 = 4;
          }
        }
        else {
          l = block2 == this ? xCoord - 1 : xCoord + 1;
          block4 = world.getBlock(l, yCoord, zCoord - 1);
          i1 = block2 == this ? xCoord - 1 : xCoord + 1;
          block5 = world.getBlock(i1, yCoord, zCoord + 1);
          b0 = 3;
          flag1 = true;

          if (block2 == this) {
            j1 = world.getBlockMetadata(xCoord - 1, yCoord, zCoord);
          }
          else {
            j1 = world.getBlockMetadata(xCoord + 1, yCoord, zCoord);
          }

          if (j1 == 2) {
            b0 = 2;
          }

          if ((block.func_149730_j() || block4.func_149730_j()) && !block1.func_149730_j() && !block5.func_149730_j()) {
            b0 = 3;
          }

          if ((block1.func_149730_j() || block5.func_149730_j()) && !block.func_149730_j() && !block4.func_149730_j()) {
            b0 = 2;
          }
        }
      }
      else {
        l = block == this ? zCoord - 1 : zCoord + 1;
        block4 = world.getBlock(xCoord - 1, yCoord, l);
        i1 = block == this ? zCoord - 1 : zCoord + 1;
        block5 = world.getBlock(xCoord + 1, yCoord, i1);
        b0 = 5;
        flag1 = true;

        if (block == this) {
          j1 = world.getBlockMetadata(xCoord, yCoord, zCoord - 1);
        }
        else {
          j1 = world.getBlockMetadata(xCoord, yCoord, zCoord + 1);
        }

        if (j1 == 4) {
          b0 = 4;
        }

        if ((block2.func_149730_j() || block4.func_149730_j()) && !block3.func_149730_j() && !block5.func_149730_j()) {
          b0 = 5;
        }

        if ((block3.func_149730_j() || block5.func_149730_j()) && !block2.func_149730_j() && !block4.func_149730_j()) {
          b0 = 4;
        }
      }

      world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, b0, 3);
    }
  }
}
