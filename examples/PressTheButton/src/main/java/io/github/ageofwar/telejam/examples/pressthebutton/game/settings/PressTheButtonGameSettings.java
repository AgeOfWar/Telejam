package io.github.ageofwar.telejam.examples.pressthebutton.game.settings;


public final class PressTheButtonGameSettings {
  
  private final Size size;
  
  public PressTheButtonGameSettings(Size size) {
    if (size.getWidth() <= 0 || size.getHeight() <= 0) {
      throw new IllegalArgumentException("width and height must be positive");
    }
    this.size = size;
  }
  
  public Size getSize() {
    return size;
  }
  
}
