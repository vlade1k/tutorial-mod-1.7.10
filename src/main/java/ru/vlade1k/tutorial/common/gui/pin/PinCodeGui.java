package ru.vlade1k.tutorial.common.gui.pin;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.gui.pin.button.PinCodeActionButton;
import ru.vlade1k.tutorial.common.gui.pin.button.PinCodeNumButton;
import ru.vlade1k.tutorial.common.help.GuiHelper;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

import java.awt.Color;

public class PinCodeGui extends GuiScreen {
  private static final ResourceLocation
      PINCODE_GUI_FRAME = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/gui_background.png"),
      BUTTON = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/pin-code-button.png"),
      NUMS_PANEL = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/nums-panel.png");

  private TilePinCodeStorage tileEntity;
  private EntityPlayer player;
  private int x, y, z;
  private boolean isHiddenVisible = false;
  private boolean isDefaultMode = false;


  public PinCodeGui(TilePinCodeStorage tileEntity, EntityPlayer player, int x, int y, int z) {
    this.tileEntity = tileEntity;
    this.player = player;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public void initGui() {
    super.initGui();

    float xPosition = (this.width - 60) / 2;
    float yPosition = (this.height - 134) / 2;

    this.buttonList.add(new PinCodeNumButton(0, 0, (int) xPosition + 5, (int) yPosition + 40, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeNumButton(1, 0, (int) xPosition + 20, (int) yPosition + 40, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeNumButton(2, 0, (int) xPosition + 35, (int) yPosition + 40, "",
        BUTTON, 10, 10));

    this.buttonList.add(new PinCodeNumButton(3, 0, (int) xPosition + 5, (int) yPosition + 55, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeNumButton(4, 0, (int) xPosition + 20, (int) yPosition + 55, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeNumButton(5, 0, (int) xPosition + 35, (int) yPosition + 55, "",
        BUTTON, 10, 10));

    this.buttonList.add(new PinCodeNumButton(6, 0, (int) xPosition + 5, (int) yPosition + 70, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeNumButton(7, 0, (int) xPosition + 20, (int) yPosition + 70, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeNumButton(8, 0, (int) xPosition + 35, (int) yPosition + 70, "",
        BUTTON, 10, 10));

    this.buttonList.add(new PinCodeActionButton(-2, (int) xPosition + 5, (int) yPosition + 85, "C",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeNumButton(9, 0, (int) xPosition + 20, (int) yPosition + 85, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeActionButton(-1, (int) xPosition + 35, (int) yPosition + 85, "<",
        BUTTON, 10, 10));

    this.buttonList.add(new PinCodeActionButton(-3, (int) xPosition + 55, (int) yPosition + 40, "*",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeActionButton(-4, (int) xPosition + 55, (int) yPosition + 55, "S",
        BUTTON, 10, 10));
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    drawDefaultBackground();

    float xPosition = (this.width - 108) / 2;
    float yPosition = (this.height - 134) / 2;

    GL11.glPushMatrix();
    GL11.glColor4f(1, 1, 1, 1);
    GL11.glEnable(GL11.GL_BLEND);

    this.mc.getTextureManager().bindTexture(PINCODE_GUI_FRAME);
    GuiHelper.drawTextureCustomSize(xPosition, yPosition,
        0, 0, 100, 100, 100, 100);

    this.mc.getTextureManager().bindTexture(NUMS_PANEL);
    GuiHelper.drawTextureCustomSize(xPosition + 10, yPosition + 17,
        0, 0, 80, 13, 80, 13);

    String enteredPassword = tileEntity.getPinCodeLock().getEnteredPassword();
    for (int i = 0; i < enteredPassword.length(); i++) {
      drawString(fontRendererObj, isHiddenVisible ? "*" : Character.toString(enteredPassword.charAt(i)),
          (int) xPosition + 15 + i * 16, (int) yPosition + 20, Color.GREEN.getRGB());
    }

    GL11.glDisable(GL11.GL_BLEND);
    GL11.glPopMatrix();
    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  protected void actionPerformed(GuiButton button) {
    if (button.id == 0) {
      if (button instanceof PinCodeNumButton) {
        PinCodeNumButton pinButton = (PinCodeNumButton) button;
        tileEntity.getPinCodeLock().getEnteredNum(pinButton.getValue());
        isDefaultMode = tileEntity.getPinCodeLock().getPasswordStatus();
        if (isDefaultMode && tileEntity.getPinCodeLock().isCorrectPassword()) {
          tileEntity.getPinCodeLock().cleanEnteredPassword();
          this.mc.displayGuiScreen(null);
          player.openGui(TutorialMod.INSTANCE, 0, player.worldObj, x, y, z);
        }
      }
    } else if (button.id == -1) {
      if (button instanceof PinCodeActionButton) {
        tileEntity.getPinCodeLock().decrEnteredPassword();
      }
    } else if (button.id == -2) {
      if (button instanceof PinCodeActionButton) {
        tileEntity.getPinCodeLock().cleanEnteredPassword();
      }
    } else if (button.id == -3) {
      if (button instanceof PinCodeActionButton) {
        isHiddenVisible = !isHiddenVisible;
      }
    } else if (button.id == -4) {
      if (button instanceof PinCodeActionButton) {
        if (!tileEntity.getPinCodeLock().getPasswordStatus() && tileEntity.getPinCodeLock().getEnteredPassword().length() == 5) {
          tileEntity.getPinCodeLock().setPasswordStatus(true);
          tileEntity.getPinCodeLock().setPassword(tileEntity.getPinCodeLock().getEnteredPassword());
          tileEntity.getPinCodeLock().cleanEnteredPassword();
        }
      }
    }
  }


}
