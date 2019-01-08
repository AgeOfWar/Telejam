package io.github.ageofwar.telejam.examples.pressthebutton.game.settings;

import static java.util.Objects.hash;

public class Size {
  
  private final int width;
  private final int height;
  
  public Size(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Size)) return false;
    Size other = (Size) obj;
    return width == other.width && height == other.height;
  }
  
  @Override
  public int hashCode() {
    return hash(width, height);
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
}
