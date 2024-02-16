package ru.vlade1k.tutorial.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

public class ContainerStorage extends Container {
  private TilePinCodeStorage tileStorage;

  private int slotID = 0;

  public ContainerStorage(TilePinCodeStorage te, EntityPlayer player) {
    this.tileStorage = te;

    //Хранение
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 5; j++) {
        addSlotToContainer(new Slot(te, slotID++, 44 + j * 18, 17 + i * 18));
      }
    }

    //Инвентарь
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++) {
        addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }
    //Хотбар
    for (int i = 0; i < 9; i++) {
      addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
    }
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer player, int slotRaw) {
    ItemStack stack = null;
    Slot slot = (Slot)inventorySlots.get(slotRaw);

    if (slot != null && slot.getHasStack()) {
      ItemStack stackInSlot = slot.getStack();
      stack = stackInSlot.copy();

      if (slotRaw < 3 * 9) {
        if (!mergeItemStack(stackInSlot, 3 * 9, inventorySlots.size(), true)) {
          return null;
        }
      }
      else if (!mergeItemStack(stackInSlot, 0, 3 * 9, false)) {
        return null;
      }

      if (stackInSlot.stackSize == 0) {
        slot.putStack(null);
      }
      else {
        slot.onSlotChanged();
      }
    }
    return stack;
  }

  @Override
  public boolean canInteractWith(EntityPlayer player)
  {
    return tileStorage.isUseableByPlayer(player);
  }
}
