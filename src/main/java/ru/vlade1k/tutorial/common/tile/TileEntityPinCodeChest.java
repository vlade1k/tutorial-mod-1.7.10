package ru.vlade1k.tutorial.common.tile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import ru.vlade1k.tutorial.common.block.BlockPinCodeChest;

public class TileEntityPinCodeChest extends TileEntityChest {

  private String customName;

  private int cachedChestType;
  private ItemStack[] chestContents = new ItemStack[36];

  public TileEntityPinCodeChest adjacentChestZNeg;
  /** Contains the chest tile located adjacent to this one (if any) */
  public TileEntityPinCodeChest adjacentChestXPos;
  /** Contains the chest tile located adjacent to this one (if any) */
  public TileEntityPinCodeChest adjacentChestXNeg;
  /** Contains the chest tile located adjacent to this one (if any) */
  public TileEntityPinCodeChest adjacentChestZPos;

  @Override
  public String getInventoryName() {
    return this.hasCustomInventoryName() ? this.customName : "Custom pin-code Chest";
  }

  @Override
  public boolean hasCustomInventoryName() {
    return this.customName != null && !this.customName.isEmpty();
  }

  /** Set customName*/
  @Override
  public void func_145976_a(String p_145976_1_) {
    this.customName = p_145976_1_;
  }

  @Override
  public void readFromNBT(NBTTagCompound p_145839_1_) {
    super.readFromNBT(p_145839_1_);
    NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
    this.chestContents = new ItemStack[this.getSizeInventory()];

    if (p_145839_1_.hasKey("Custom pin-code Chest", 8))
    {
      this.customName = p_145839_1_.getString("Custom pin-code Chest");
    }

    for (int i = 0; i < nbttaglist.tagCount(); ++i)
    {
      NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
      int j = nbttagcompound1.getByte("Slot") & 255;

      if (j >= 0 && j < this.chestContents.length) {
        this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
      }
    }
  }

  @Override
  public void writeToNBT(NBTTagCompound p_145841_1_) {
    super.writeToNBT(p_145841_1_);
    NBTTagList nbttaglist = new NBTTagList();

    for (int i = 0; i < this.chestContents.length; ++i)
    {
      if (this.chestContents[i] != null)
      {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setByte("Slot", (byte)i);
        this.chestContents[i].writeToNBT(nbttagcompound1);
        nbttaglist.appendTag(nbttagcompound1);
      }
    }

    p_145841_1_.setTag("Items", nbttaglist);

    if (this.hasCustomInventoryName())
    {
      p_145841_1_.setString("Custom Chest", this.customName);
    }
  }

  private void func_145978_a(TileEntityPinCodeChest p_145978_1_, int p_145978_2_)
  {
    if (p_145978_1_.isInvalid())
    {
      this.adjacentChestChecked = false;
    }
    else if (this.adjacentChestChecked)
    {
      switch (p_145978_2_)
      {
        case 0:
          if (this.adjacentChestZPos != p_145978_1_)
          {
            this.adjacentChestChecked = false;
          }

          break;
        case 1:
          if (this.adjacentChestXNeg != p_145978_1_)
          {
            this.adjacentChestChecked = false;
          }

          break;
        case 2:
          if (this.adjacentChestZNeg != p_145978_1_)
          {
            this.adjacentChestChecked = false;
          }

          break;
        case 3:
          if (this.adjacentChestXPos != p_145978_1_)
          {
            this.adjacentChestChecked = false;
          }
      }
    }
  }

  public int func_145980_j() {
    if (this.cachedChestType == -1) {
      if (this.worldObj == null || !(this.getBlockType() instanceof BlockPinCodeChest)) {
        return 0;
      }

      this.cachedChestType = ((BlockPinCodeChest)this.getBlockType()).field_149956_a;
    }

    return this.cachedChestType;
  }
  public ItemStack getStackInSlot(int itemStackIndex) {
    return this.chestContents[itemStackIndex];
  }

  public ItemStack decrStackSize(int itemStackIndex, int countToRemove) {
    if (this.chestContents[itemStackIndex] != null)
    {
      ItemStack itemStack;

      if (this.chestContents[itemStackIndex].stackSize <= countToRemove)
      {
        itemStack = this.chestContents[itemStackIndex];
        this.chestContents[itemStackIndex] = null;
        this.markDirty();
        return itemStack;
      }
      else
      {
        itemStack = this.chestContents[itemStackIndex].splitStack(countToRemove);

        if (this.chestContents[itemStackIndex].stackSize == 0)
        {
          this.chestContents[itemStackIndex] = null;
        }

        this.markDirty();
        return itemStack;
      }
    }
    else
    {
      return null;
    }
  }

  private boolean func_145977_a(int x, int y, int z)
  {
    if (this.worldObj == null)
    {
      return false;
    }
    else
    {
      Block block = this.worldObj.getBlock(x, y, z);
      return block instanceof BlockChest && ((BlockChest)block).field_149956_a == this.func_145980_j();
    }
  }

  public void checkForAdjacentChests()
  {
    if (!this.adjacentChestChecked)
    {
      this.adjacentChestChecked = true;
      this.adjacentChestZNeg = null;
      this.adjacentChestXPos = null;
      this.adjacentChestXNeg = null;
      this.adjacentChestZPos = null;

      if (this.func_145977_a(this.xCoord - 1, this.yCoord, this.zCoord))
      {
        this.adjacentChestXNeg = (TileEntityPinCodeChest)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
      }

      if (this.func_145977_a(this.xCoord + 1, this.yCoord, this.zCoord))
      {
        this.adjacentChestXPos = (TileEntityPinCodeChest)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
      }

      if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord - 1))
      {
        this.adjacentChestZNeg = (TileEntityPinCodeChest)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
      }

      if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord + 1))
      {
        this.adjacentChestZPos = (TileEntityPinCodeChest)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
      }

      if (this.adjacentChestZNeg != null)
      {
        this.adjacentChestZNeg.func_145978_a(this, 0);
      }

      if (this.adjacentChestZPos != null)
      {
        this.adjacentChestZPos.func_145978_a(this, 2);
      }

      if (this.adjacentChestXPos != null)
      {
        this.adjacentChestXPos.func_145978_a(this, 1);
      }

      if (this.adjacentChestXNeg != null)
      {
        this.adjacentChestXNeg.func_145978_a(this, 3);
      }
    }
  }

  public ItemStack getStackInSlotOnClosing(int itemStackIndex) {
    if (this.chestContents[itemStackIndex] != null)
    {
      ItemStack itemstack = this.chestContents[itemStackIndex];
      this.chestContents[itemStackIndex] = null;
      return itemstack;
    }
    else
    {
      return null;
    }
  }

  public void setInventorySlotContents(int itemStackIndex, ItemStack itemStack) {
    this.chestContents[itemStackIndex] = itemStack;

    if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
    {
      itemStack.stackSize = this.getInventoryStackLimit();
    }

    this.markDirty();
  }



}
