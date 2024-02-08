package ru.vlade1k.tutorial.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

public class GuiStorage extends GuiContainer {
  private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(
      TutorialMod.MOD_ID + ":textures/gui/container/guiStorage15.png");

  private InventoryPlayer inventoryPlayer;
  private TilePinCodeStorage entityStorage;

  public GuiStorage(TilePinCodeStorage entity, EntityPlayer player) {
    super(new ContainerStorage(entity, player));
    this.inventoryPlayer = player.inventory;
    this.entityStorage = entity;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
  {
    Minecraft.getMinecraft().renderEngine.bindTexture(GUI_TEXTURE);

    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;

    drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int par1, int par2)
  {
    fontRendererObj.drawString(I18n.format(entityStorage.getInventoryName()), (xSize / 2) - (fontRendererObj.getStringWidth(I18n.format(entityStorage.getInventoryName())) / 2), 6, 4210752, false);
    fontRendererObj.drawString(I18n.format(inventoryPlayer.getInventoryName()), 8, ySize - 96 + 2, 4210752);
  }

}
