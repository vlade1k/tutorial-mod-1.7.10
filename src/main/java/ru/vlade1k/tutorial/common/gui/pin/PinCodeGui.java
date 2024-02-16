package ru.vlade1k.tutorial.common.gui.pin;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.gui.pin.button.PinCodeButton;
import ru.vlade1k.tutorial.common.help.GuiHelper;
import ru.vlade1k.tutorial.common.tile.TilePinCodeStorage;

public class PinCodeGui extends GuiScreen {
  private static final ResourceLocation
      PINCODE_GUI_FRAME = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/gui_background.png"),
      BUTTON = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/pin-code-button.png");

  private TilePinCodeStorage tileEntity;
  private EntityPlayer player;
  private int x, y, z;

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

    this.buttonList.add(new PinCodeButton(0, 0, (int) xPosition + 5, (int) yPosition + 40, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeButton(1, 0, (int) xPosition + 20, (int) yPosition + 40, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeButton(2, 0, (int) xPosition + 35, (int) yPosition + 40, "",
        BUTTON, 10, 10));

    this.buttonList.add(new PinCodeButton(3, 0, (int) xPosition + 5, (int) yPosition + 55, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeButton(4, 0, (int) xPosition + 20, (int) yPosition + 55, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeButton(5, 0, (int) xPosition + 35, (int) yPosition + 55, "",
        BUTTON, 10, 10));

    this.buttonList.add(new PinCodeButton(6, 0, (int) xPosition + 5, (int) yPosition + 70, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeButton(7, 0, (int) xPosition + 20, (int) yPosition + 70, "",
        BUTTON, 10, 10));
    this.buttonList.add(new PinCodeButton(8, 0, (int) xPosition + 35, (int) yPosition + 70, "",
        BUTTON, 10, 10));

    this.buttonList.add(new PinCodeButton(9, 0, (int) xPosition + 20, (int) yPosition + 85, "",
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
    GL11.glDisable(GL11.GL_BLEND);
    GL11.glPopMatrix();
    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  protected void actionPerformed(GuiButton button) {
    if (button.id == 0) {
      if (button instanceof PinCodeButton) {
        PinCodeButton pinButton = (PinCodeButton) button;
        tileEntity.getPinCodeLock().getEnteredNum(pinButton.getValue());
        System.out.println(tileEntity.getPinCodeLock().getEnteredPassword());
        System.out.println(tileEntity.getPinCodeLock().isCorrectPassword());
        if (tileEntity.getPinCodeLock().isCorrectPassword()) {
          tileEntity.getPinCodeLock().cleanEnteredPassword();
          this.mc.displayGuiScreen(null);
          player.openGui(TutorialMod.INSTANCE, 0, player.worldObj, x, y, z);
        }
      }
    }
  }


}
