package io.github.ageofwar.telejam.keyboards;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents type of a poll, which is allowed to be
 * created and sent when the corresponding button is pressed.
 */
public class KeyboardButtonPollType {
  
  static final String TYPE_FIELD = "type";
  
  /**
   * If quiz is passed, the user will be allowed to create only polls in the quiz mode.
   * If regular is passed, only regular polls will be allowed.
   * Otherwise, the user will be allowed to create a poll of any type.
   */
  @SerializedName(TYPE_FIELD)
  private String type;
  
  public KeyboardButtonPollType(String type) {
    this.type = type;
  }
  
  /**
   * Getter for property {@link #type}.
   *
   * @return value for property {@link #type}
   */
  public String getType() {
    return type;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof KeyboardButtonPollType)) return false;
    return type.equals(((KeyboardButtonPollType) obj).type);
  }
  
  @Override
  public int hashCode() {
    return type.hashCode();
  }
  
}
