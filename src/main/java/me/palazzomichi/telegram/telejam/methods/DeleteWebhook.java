package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;

/**
 * Use this method to remove webhook integration if you decide to
 * switch back to getUpdates.
 * Returns True on success.
 * Requires no parameters.
 *
 * @author Michi Palazzo
 */
public class DeleteWebhook extends JsonTelegramMethod<Boolean> {

  public static final String NAME = "deleteWebhook";

  public static final DeleteWebhook INSTANCE = new DeleteWebhook();

  private DeleteWebhook() {
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }

}
