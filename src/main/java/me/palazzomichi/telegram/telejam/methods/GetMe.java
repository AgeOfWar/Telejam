package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.User;

/**
 * Returns basic information about the bot in form of a User object.
 *
 * @author Michi Palazzo
 */
public class GetMe extends JsonTelegramMethod<User> {

  public static final String NAME = "getMe";

  public static final GetMe INSTANCE = new GetMe();

  private GetMe() {
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends User> getReturnType(JsonElement response) {
    return User.class;
  }

}
