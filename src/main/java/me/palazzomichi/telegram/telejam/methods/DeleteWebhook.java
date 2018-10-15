package me.palazzomichi.telegram.telejam.methods;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to remove webhook integration if you decide to
 * switch back to getUpdates.
 * Returns True on success.
 * Requires no parameters.
 *
 * @author Michi Palazzo
 */
public final class DeleteWebhook implements TelegramMethod<Boolean> {

  public static final String NAME = "deleteWebhook";

  public static final DeleteWebhook INSTANCE = new DeleteWebhook();

  private DeleteWebhook() {
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
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
