package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.User;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

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
