package io.github.ageofwar.telejam.examples.pressthebutton.game;

import static java.util.Objects.hash;

public final class Point {
  
  private final int x;
  private final int y;
  
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Point)) return false;
    Point other = (Point) obj;
    return x == other.x && y == other.y;
  }
  
  @Override
  public int hashCode() {
    return hash(x, y);
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
}
