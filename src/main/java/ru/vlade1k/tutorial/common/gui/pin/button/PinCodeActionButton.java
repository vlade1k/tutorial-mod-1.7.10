package ru.vlade1k.tutorial.common.gui.pin.button;

import cpw.mods.fml.client.config.GuiButtonExt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

public class PinCodeActionButton extends GuiButtonExt {
  private final ResourceLocation texture;
  private int textureWidth, textureHeight;
  private float U, V;
  private FontRenderer font;


  public PinCodeActionButton(int id, int xPos, int yPos, String displayString,
      ResourceLocation texture, int width, int height) {
    super(id, xPos, yPos, displayString);
    this.texture = texture;
    this.textureWidth = width;
    this.textureHeight = height;
    this.width = width;
    this.height = height;
    this.font = Minecraft.getMinecraft().fontRenderer;
    this.U = this.V = 0;

  }

  @Override
  public void drawButton(Minecraft mc, int mouseX, int mouseY) {
    if (!this.visible) return;

    this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
        && mouseY < this.yPosition + this.height;
    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    GL11.glPushMatrix();
    GL11.glDisable(GL11.GL_ALPHA_TEST);
    GL11.glEnable(GL11.GL_BLEND);
    OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
    mc.getTextureManager().bindTexture(texture);
    func_146110_a(this.xPosition, this.yPosition, U, V, width, height, textureWidth, textureHeight);
    GL11.glPushMatrix();
    GL11.glTranslatef(this.xPosition + (14), this.yPosition + (this.height - (3f)) / 2, 1f);
    GL11.glScalef((0.6f), (0.6f), (0.6f));
    this.drawCenteredString(font, displayString,-15, 0, field_146123_n ? Color.WHITE.getRGB() : Color.GRAY.getRGB());
    GL11.glPopMatrix();
    GL11.glDisable(GL11.GL_BLEND);
    GL11.glEnable(GL11.GL_ALPHA_TEST);
    GL11.glPopMatrix();
  }

}
