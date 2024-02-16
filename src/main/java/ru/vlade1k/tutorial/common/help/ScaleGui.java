package ru.vlade1k.tutorial.common.help;

import net.minecraft.client.Minecraft;

public class ScaleGui {

  public static final float DEFAULT_WIDTH = 1920f;
  public static final float HALF_WIDTH = DEFAULT_WIDTH / 2f;
  public static final float DEFAULT_HEIGHT = 1080f;
  public static final float HALF_HEIGHT = DEFAULT_HEIGHT / 2f;
  private static final Minecraft mc = Minecraft.getMinecraft();
  public static float FULL_HD = 16 / 9f;
  public static float SXGA = 4 / 3f;
  public static float screenCenterX, screenCenterY;
  public static int screenWidth, screenHeight;
  public static float scaleValue;

  public static void update() {
    refresh(FULL_HD);
  }

  public static void update(float minAspect) {
    refresh(minAspect);
  }

  private static void refresh(float minAspect) {
    screenWidth = mc.displayWidth;
    screenHeight = mc.displayHeight;
    screenCenterX = screenWidth / 2f;
    screenCenterY = screenHeight / 2f;
    float ratio = screenWidth / (float) screenHeight;
    scaleValue = ratio < minAspect ? screenHeight / (1.0f + (minAspect - ratio)) : screenHeight;
  }

  public static int get(float value) {
    return (int) (scaleValue / (DEFAULT_HEIGHT / value));
  }

  public static int get(float value, float size) {
    return (int) (scaleValue / (DEFAULT_HEIGHT / value) - get(size) / 2f);
  }

  public static float getf(float value) {
    return (scaleValue / (DEFAULT_HEIGHT / value));
  }

  public static float getf(float value, float size) {
    return (scaleValue / (DEFAULT_HEIGHT / value) - get(size) / 2f);
  }

  public static int getCenterX(float value, float size) {
    return (int) (screenCenterX + scaleValue / (DEFAULT_HEIGHT / (value - HALF_WIDTH)) - get(size) / 2f);
  }

  public static int getCenterX(float value) {
    return (int) (screenCenterX + scaleValue / (DEFAULT_HEIGHT / (value - HALF_WIDTH)));
  }

  public static int getCenterY(float value) {
    return (int) (screenCenterY + scaleValue / (DEFAULT_HEIGHT / (value - HALF_HEIGHT)));
  }

  public static int getCenterY(float value, float size) {
    return (int) (screenCenterY + scaleValue / (DEFAULT_HEIGHT / (value - HALF_HEIGHT)) - get(size) / 2f);
  }

  public static int getRight(float value) {
    return (int) (screenWidth + scaleValue / (DEFAULT_HEIGHT / (value - DEFAULT_WIDTH)));
  }

  public static int getRight(float value, float size) {
    return (int) (screenWidth + scaleValue / (DEFAULT_HEIGHT / (value - DEFAULT_WIDTH)) - get(size) / 2f);
  }

  public static int getBot(float value) {
    return (int) (screenHeight - scaleValue / (DEFAULT_HEIGHT / (DEFAULT_HEIGHT - value)));
  }

  public static int getBot(float y, float size) {
    return (int) (screenHeight - scaleValue / (DEFAULT_HEIGHT / (DEFAULT_HEIGHT - y)) - get(size) / 2f);
  }
}
