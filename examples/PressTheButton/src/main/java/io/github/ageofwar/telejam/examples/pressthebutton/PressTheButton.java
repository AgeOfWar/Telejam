package io.github.ageofwar.telejam.examples.pressthebutton;

import io.github.ageofwar.telejam.examples.pressthebutton.math.Point;
import io.github.ageofwar.telejam.users.User;

import java.util.*;

public final class PressTheButton {
  
  private final boolean[][] buttons;
  private final Point rightButtonPosition;
  private final Set<Long> players;
  private User winner;
  
  private PressTheButton(boolean[][] buttons,
                         Point rightButtonPosition,
                         Set<Long> players) {
    this.buttons = buttons;
    this.rightButtonPosition = rightButtonPosition;
    this.players = players;
  }
  
  public static PressTheButton fromSettings(PressTheButtonGameSettings settings) {
    return new PressTheButton(
        new boolean[settings.getWidth()][settings.getHeight()],
        new Point(
            new Random().nextInt(settings.getWidth()),
            new Random().nextInt(settings.getHeight())
        ),
        new HashSet<>()
    );
  }
  
  public void press(User user, Point point) {
    if (isTerminated()) throw new IllegalStateException("Game terminated");
    if (!isPressed(point)) {
      players.add(user.getId());
      buttons[point.getX()][point.getY()] = true;
      if (point.equals(rightButtonPosition)) {
        winner = user;
      }
    }
  }
  
  public boolean isPressed(Point point) {
    return buttons[point.getX()][point.getY()];
  }
  
  public boolean[][] getButtonsClickState() {
    return buttons;
  }
  
  public boolean isTerminated() {
    return buttons[rightButtonPosition.getX()][rightButtonPosition.getY()];
  }
  
  public User getWinner() {
    return winner;
  }
  
  public Point getRightButtonPosition() {
    return rightButtonPosition;
  }
  
  public int getWidth() {
    return buttons.length;
  }
  
  public int getHeight() {
    return buttons[0].length;
  }
  
}
