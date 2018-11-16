package io.github.ageofwar.telejam.games;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents one row of the high scores table for a game.
 *
 * @author Michi Palazzo
 */
public class GameHighScore implements TelegramObject {
  
  static final String POSITION_FIELD = "position";
  static final String USER_FIELD = "user";
  static final String SCORE_FIELD = "score";
  
  /**
   * Position in high score table for the game.
   */
  @SerializedName(POSITION_FIELD)
  private final int position;
  
  /**
   * User.
   */
  @SerializedName(USER_FIELD)
  private final User user;
  
  /**
   * Score.
   */
  @SerializedName(SCORE_FIELD)
  private final int score;
  
  
  public GameHighScore(int position, User user, int score) {
    this.position = position;
    this.user = Objects.requireNonNull(user);
    this.score = score;
  }
  
  
  /**
   * Getter for property {@link #position}.
   *
   * @return value for property {@link #position}
   */
  public int getPosition() {
    return position;
  }
  
  /**
   * Getter for property {@link #user}.
   *
   * @return value for property {@link #user}
   */
  public User getUser() {
    return user;
  }
  
  /**
   * Getter for property {@link #score}.
   *
   * @return value for property {@link #score}
   */
  public int getScore() {
    return score;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof GameHighScore)) {
      return false;
    }
    
    GameHighScore gameHighScore = (GameHighScore) obj;
    return position == gameHighScore.getPosition() &&
        user.equals(gameHighScore.getUser()) &&
        score == gameHighScore.getScore();
  }
  
  @Override
  public int hashCode() {
    int result = 1;
    result = 37 * result + position;
    result = 37 * result + user.hashCode();
    result = 37 * result + score;
    return result;
  }
  
}
