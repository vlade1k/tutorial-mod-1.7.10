package ru.vlade1k.tutorial.common.render;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import ru.vlade1k.tutorial.TutorialMod;
import ru.vlade1k.tutorial.common.block.BlockPinCodeChest;
import ru.vlade1k.tutorial.common.tile.TileEntityPinCodeChest;

import java.util.Calendar;

public class RendererPinCodeChest extends TileEntitySpecialRenderer {
  private static final ResourceLocation field_147505_d = new ResourceLocation(
      TutorialMod.MOD_ID + ":textures/blocks/customChestDouble.png");
  private static final ResourceLocation field_147504_g = new ResourceLocation(
      TutorialMod.MOD_ID + ":textures/blocks/customChest.png");
  private ModelChest field_147510_h = new ModelChest();
  private ModelChest field_147511_i = new ModelLargeChest();
  private static final String __OBFID = "CL_00000965";


  public void renderTileEntityAt(TileEntityPinCodeChest tileEntityPinCodeChest, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
    int i;

    if (!tileEntityPinCodeChest.hasWorldObj()) {
      i = 0;
    }
    else {
      Block block = tileEntityPinCodeChest.getBlockType();
      i = tileEntityPinCodeChest.getBlockMetadata();

      if (block instanceof BlockPinCodeChest && i == 0) {
        try {
          ((BlockPinCodeChest)block).func_149954_e(tileEntityPinCodeChest.getWorldObj(), tileEntityPinCodeChest.xCoord, tileEntityPinCodeChest.yCoord, tileEntityPinCodeChest.zCoord);
        }
        catch (ClassCastException e) {
          FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", tileEntityPinCodeChest.xCoord, tileEntityPinCodeChest.yCoord, tileEntityPinCodeChest.zCoord);
        }
        i = tileEntityPinCodeChest.getBlockMetadata();
      }

      tileEntityPinCodeChest.checkForAdjacentChests();
    }

    if (tileEntityPinCodeChest.adjacentChestZNeg == null && tileEntityPinCodeChest.adjacentChestXNeg == null) {
      ModelChest modelchest;

      if (tileEntityPinCodeChest.adjacentChestXPos == null && tileEntityPinCodeChest.adjacentChestZPos == null) {
        modelchest = this.field_147510_h;

        this.bindTexture(field_147504_g);

      }
      else {
        modelchest = this.field_147511_i;

        this.bindTexture(field_147505_d);
      }

      GL11.glPushMatrix();
      GL11.glEnable(GL12.GL_RESCALE_NORMAL);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef((float)p_147500_2_, (float)p_147500_4_ + 1.0F, (float)p_147500_6_ + 1.0F);
      GL11.glScalef(1.0F, -1.0F, -1.0F);
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
      short short1 = 0;

      if (i == 2) {
        short1 = 180;
      }

      if (i == 3) {
        short1 = 0;
      }

      if (i == 4) {
        short1 = 90;
      }

      if (i == 5) {
        short1 = -90;
      }

      if (i == 2 && tileEntityPinCodeChest.adjacentChestXPos != null) {
        GL11.glTranslatef(1.0F, 0.0F, 0.0F);
      }

      if (i == 5 && tileEntityPinCodeChest.adjacentChestZPos != null) {
        GL11.glTranslatef(0.0F, 0.0F, -1.0F);
      }

      GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      float f1 = tileEntityPinCodeChest.prevLidAngle + (tileEntityPinCodeChest.lidAngle - tileEntityPinCodeChest.prevLidAngle) * p_147500_8_;
      float f2;

      if (tileEntityPinCodeChest.adjacentChestZNeg != null) {
        f2 = tileEntityPinCodeChest.adjacentChestZNeg.prevLidAngle + (tileEntityPinCodeChest.adjacentChestZNeg.lidAngle - tileEntityPinCodeChest.adjacentChestZNeg.prevLidAngle) * p_147500_8_;

        if (f2 > f1) {
          f1 = f2;
        }
      }

      if (tileEntityPinCodeChest.adjacentChestXNeg != null) {
        f2 = tileEntityPinCodeChest.adjacentChestXNeg.prevLidAngle + (tileEntityPinCodeChest.adjacentChestXNeg.lidAngle - tileEntityPinCodeChest.adjacentChestXNeg.prevLidAngle) * p_147500_8_;

        if (f2 > f1) {
          f1 = f2;
        }
      }

      f1 = 1.0F - f1;
      f1 = 1.0F - f1 * f1 * f1;
      modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
      modelchest.renderAll();
      GL11.glDisable(GL12.GL_RESCALE_NORMAL);
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
  }

  public TileEntity createNewTileEntity(World world, int p_149915_2_) {
    return new TileEntityPinCodeChest();
  }

  public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
    this.renderTileEntityAt((TileEntityPinCodeChest) p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
  }
}
