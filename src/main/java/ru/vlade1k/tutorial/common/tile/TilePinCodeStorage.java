package ru.vlade1k.tutorial.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TilePinCodeStorage extends TileEntity implements IInventory {

  private ItemStack[] storageItems = new ItemStack[15];
  private int storageSize;
  private String inventoryName = "container.storage";


  public int getStorageSize() {
    return storageSize;
  }

  public void setStorageSize(int size) { this.storageSize = size; }

  public ItemStack getStorageItem(int index) {
    if (index < getStorageSize()) {
      return storageItems[index];
    }
    return null;
  }

  public void putItemInStorage(ItemStack item) {
    if (getStorageSize() < storageItems.length) {
      storageItems[storageSize++] = item;
    }
    markDirty();
  }

  public ItemStack[] getStorageItems() {
    return storageItems;
  }

  @Override
  public int getSizeInventory() {
    return getStorageSize();
  }

  @Override
  public ItemStack getStackInSlot(int slot) {
    return getStorageItem(slot);
  }

  public ItemStack decrStackSize(int slot, int amount) {
    if (slot < storageItems.length && storageItems[slot] != null) {
      ItemStack itemStack;
      if (storageItems[slot].stackSize == amount) {
        itemStack = storageItems[slot];
        storageItems[slot] = null;
        markDirty();
        return itemStack;
      } else {
        itemStack = storageItems[slot].splitStack(amount);
        markDirty();
        return itemStack;
      }
    }
    return null;
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int slot) {
    if (slot < storageItems.length && storageItems[slot] != null) {
      ItemStack item = storageItems[slot];
      storageItems[slot] = null;
      return item;
    }
    return null;
  }

  @Override
  public void setInventorySlotContents(int slot, ItemStack stack) {
    storageItems[slot] = stack;
    if (stack != null && stack.stackSize > getInventoryStackLimit())
    {
      stack.stackSize = getInventoryStackLimit();
    }

    markDirty();
  }

  @Override
  public String getInventoryName() {
    return inventoryName;
  }

  @Override
  public boolean hasCustomInventoryName() {
    return getInventoryName() != null && !getInventoryName().isEmpty();
  }

  @Override
  public int getInventoryStackLimit() {
    return 64;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player) {
    return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64.0D;
  }

  @Override
  public void openInventory() {

  }

  @Override
  public void closeInventory() {

  }

  @Override
  public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
    return true;
  }

  @Override
  public void writeToNBT(NBTTagCompound nbt) {
    super.writeToNBT(nbt);

    nbt.setInteger("StorageSize", getStorageSize());

    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.storageItems.length; ++i) {
      if (this.storageItems[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setByte("Slot", (byte)i);
        this.storageItems[i].writeToNBT(nbttagcompound1);
        nbttaglist.appendTag(nbttagcompound1);
      }
    }
    nbt.setTag("Items", nbttaglist);

  }

  public void readFromNBT(NBTTagCompound nbt) {
    super.readFromNBT(nbt);

    setStorageSize(nbt.getInteger("StorageSize"));

    NBTTagList nbttaglist = nbt.getTagList("Items", 10);
    this.storageItems = new ItemStack[this.getStorageSize()];

    for (int i = 0; i < nbttaglist.tagCount(); ++i) {
      NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
      int j = nbttagcompound1.getByte("Slot") & 255;

      if (j >= 0 && j < this.storageItems.length) {
        this.storageItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
      }
    }

  }


}
