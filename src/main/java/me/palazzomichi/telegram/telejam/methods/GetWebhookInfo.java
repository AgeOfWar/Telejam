package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.WebhookInfo;

/**
 * Use this method to get current webhook status.
 * Requires no parameters. On success, returns a WebhookInfo object.
 * If the bot is using getUpdates, will return an object with the url field empty.
 *
 * @author Michi Palazzo
 */
public class GetWebhookInfo extends JsonTelegramMethod<WebhookInfo> {

  public static final String NAME = "getWebhookInfo";

  public static final GetWebhookInfo INSTANCE = new GetWebhookInfo();

  private GetWebhookInfo() {
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends WebhookInfo> getReturnType(JsonElement response) {
    return WebhookInfo.class;
  }

}
