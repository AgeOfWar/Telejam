package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.commands.BotCommand;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to send a dice, which will have a random value from 1 to 6.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class GetMyCommands implements TelegramMethod<BotCommand[]> {
  
  public static final String NAME = "getMyCommands";
  
  public static final GetMyCommands INSTANCE = new GetMyCommands();
  
  private GetMyCommands() {
  }
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf();
  }
  
  @Override
  public Class<? extends BotCommand[]> getReturnType() {
    return BotCommand[].class;
  }
  
}

