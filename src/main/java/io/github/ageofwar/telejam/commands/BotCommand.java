package io.github.ageofwar.telejam.commands;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;

/**
 * This object represents a bot command.
 */
public class BotCommand implements TelegramObject {
  
  static final String COMMAND_FIELD = "command";
  static final String DESCRIPTION_FIELD = "description";
  
  @SerializedName(COMMAND_FIELD)
  private final String command;
  
  @SerializedName(DESCRIPTION_FIELD)
  private final String description;
  
  public BotCommand(String command, String description) {
    this.command = Objects.requireNonNull(command);
    this.description = Objects.requireNonNull(description);
  }
  
  public String getCommand() {
    return command;
  }
  
  public String getDescription() {
    return description;
  }
  
}
