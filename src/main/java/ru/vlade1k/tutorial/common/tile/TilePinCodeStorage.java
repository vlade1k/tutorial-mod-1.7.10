package ru.vlade1k.tutorial.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TilePinCodeStorage extends TileEntity {

  private ItemStack[] storageItems = new ItemStack[36];

  private int storageSize;


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

  public void handeInputStack(EntityPlayer player, ItemStack stack) {
    if (player.getHeldItem() == null) {
      for (int i = 0; i < this.getStorageSize(); i++) {
        player.addChatMessage(new ChatComponentText(this.getStorageItem(i).getUnlocalizedName()));
      }
    } else {
      this.putItemInStorage(player.getHeldItem().copy());
      stack.stackSize = 0;
    }

    markDirty();
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
