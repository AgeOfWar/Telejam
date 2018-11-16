package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.users.User;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Returns basic information about the bot in form of a User object.
 *
 * @author Michi Palazzo
 */
public final class GetMe implements TelegramMethod<User> {

  public static final String NAME = "getMe";

  public static final GetMe INSTANCE = new GetMe();

  private GetMe() {
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
  public Class<? extends User> getReturnType() {
    return User.class;
  }
  
}
