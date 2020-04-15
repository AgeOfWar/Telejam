package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

/**
 * This object represents a dice with random value from 1 to 6.
 */
public class Dice implements TelegramObject {
  
  static final String VALUE_FIELD = "value";
  
  @SerializedName(VALUE_FIELD)
  private final int value;
  
  public Dice(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return value;
  }
  
}
