package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.WebhookInfo;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

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
