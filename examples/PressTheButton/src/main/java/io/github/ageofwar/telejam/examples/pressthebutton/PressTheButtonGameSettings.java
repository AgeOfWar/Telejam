package io.github.ageofwar.telejam.examples.pressthebutton;

import io.github.ageofwar.telejam.examples.pressthebutton.math.Point;

import java.util.Random;

public final class PressTheButtonGameSettings {
  
  private final int width;
  private final int height;
  
  public PressTheButtonGameSettings(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height must be positive");
    }
    this.width = width;
    this.height = height;
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
}
