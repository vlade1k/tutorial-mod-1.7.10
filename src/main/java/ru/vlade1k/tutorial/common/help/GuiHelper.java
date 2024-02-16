package ru.vlade1k.tutorial.common.help;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class GuiHelper {

  public static void drawTextureCustomSize(double posX, double posY, double startPixX, double startPixY,
      double pieceSizeX, double pieceSizeY, float sizeTextureX, float sizeTextureY) {
    GL11.glPushMatrix();
    GL11.glDisable(GL11.GL_ALPHA_TEST);
    GL11.glEnable(GL11.GL_BLEND);
    OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
    float f4 = 1.0F / sizeTextureX;
    float f5 = 1.0F / sizeTextureY;
    Tessellator tessellator = Tessellator.instance;
    tessellator.startDrawingQuads();
    tessellator.addVertexWithUV(posX, posY + pieceSizeY, 0.0D, startPixX * f4, (startPixY + (float) pieceSizeY) * f5);
    tessellator.addVertexWithUV(posX + pieceSizeX, posY + pieceSizeY, 0.0D, (startPixX + (float) pieceSizeX) * f4,
        (startPixY + (float) pieceSizeY) * f5);
    tessellator.addVertexWithUV(posX + pieceSizeX, posY, 0.0D, (startPixX + (float) pieceSizeX) * f4, startPixY * f5);
    tessellator.addVertexWithUV(posX, posY, 0.0D, startPixX * f4, startPixY * f5);
    tessellator.draw();
    GL11.glDisable(GL11.GL_BLEND);
    GL11.glEnable(GL11.GL_ALPHA_TEST);
    GL11.glPopMatrix();
  }

}
