package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.commands.BotCommand;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to change the list of the bot's commands.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SetMyCommands implements TelegramMethod<Boolean> {
  
  public static final String NAME = "setMyCommands";
  
  static final String COMMANDS_FIELD = "commands";
  
  /**
   * A list of the bot's commands.
   * At most 100 commands can be specified.
   */
  private BotCommand[] commands;
  
  public SetMyCommands commands(BotCommand... commands) {
    this.commands = commands;
    return this;
  }
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(COMMANDS_FIELD, commands);
  }
  
  @Override
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
  }
  
}

