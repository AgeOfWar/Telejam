package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.webhook.WebhookInfo;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to get current webhook status.
 * Requires no parameters. On success, returns a WebhookInfo object.
 * If the bot is using getUpdates, will return an object with the url field empty.
 *
 * @author Michi Palazzo
 */
public final class GetWebhookInfo implements TelegramMethod<WebhookInfo> {

  public static final String NAME = "getWebhookInfo";

  public static final GetWebhookInfo INSTANCE = new GetWebhookInfo();

  private GetWebhookInfo() {
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
  public Class<? extends WebhookInfo> getReturnType() {
    return WebhookInfo.class;
  }

}
