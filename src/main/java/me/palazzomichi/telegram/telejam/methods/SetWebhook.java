package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.nio.file.Path;

/**
 * Use this method to specify a url and receive incoming updates via an outgoing webhook.
 * Whenever there is an update for the bot, we will send an HTTPS POST request to the specified url,
 * containing a JSON-serialized Update. In case of an unsuccessful request,
 * we will give up after a reasonable amount of attempts.
 * Returns true.
 * <br /><br />
 * If you'd like to make sure that the Webhook request comes from Telegram,
 * we recommend using a secret path in the URL,
 * e.g. <code>https://www.example.com/&lt;token&gt;</code>.
 * Since nobody else knows your bot‘s token, you can be pretty sure it’s us.
 *
 * @author Michi Palazzo
 */
public class SetWebhook implements TelegramMethod<Boolean> {

  public static final String NAME = "setWebhook";

  static final String URL_FIELD = "url";
  static final String CERTIFICATE_FIELD = "certificate";
  static final String MAX_CONNECTIONS_FIELD = "max_connections";
  static final String ALLOWED_UPDATES_FIELD = "allowed_updates";

  /**
   * HTTPS url to send updates to.
   * Use an empty string to remove webhook integration.
   */
  private String url;

  /**
   * Upload your public key certificate so that the root
   * certificate in use can be checked.
   * See Telegram self-signed guide for details.
   */
  private String certificate;

  /**
   * Maximum allowed number of simultaneous HTTPS connections to the webhook
   * for update delivery, 1-100. Defaults to 40. Use lower values to limit
   * the load on your bot‘s server, and higher values to increase your bot’s throughput.
   */
  private Integer maxConnections;

  /**
   * List the types of updates you want your bot to receive.
   * For example, specify [“message”, “edited_channel_post”, “callback_query”] to
   * only receive updates of these types. See Update for a complete list of
   * available update types. Specify an empty list to receive all updates
   * regardless of type (default).
   * If not specified, the previous setting will be used.
   * <br /> <br />
   * Please note that this parameter doesn't affect updates created before the
   * call to the setWebhook, so unwanted updates may be received for a short period of time.
   */
  private String[] allowedUpdates;


  public SetWebhook url(String url) {
    this.url = url;
    return this;
  }

  public SetWebhook certificate(String certificate) {
    this.certificate = certificate;
    return this;
  }

  public SetWebhook certificate(File certificate) {
    this.certificate = certificate.getPath();
    return this;
  }

  public SetWebhook certificate(Path certificate) {
    this.certificate = certificate.toString();
    return this;
  }

  public SetWebhook maxConnections(Integer maxConnections) {
    this.maxConnections = maxConnections;
    return this;
  }

  public SetWebhook setAllowedUpdates(String... allowedUpdates) {
    this.allowedUpdates = allowedUpdates;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }

  @Override
  public HttpEntity getHttpEntity() {
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();

    if (url != null)
      builder.addTextBody(URL_FIELD, url);

    if (certificate != null)
      builder.addBinaryBody(CERTIFICATE_FIELD, new File(certificate));

    if (maxConnections != null)
      builder.addTextBody(MAX_CONNECTIONS_FIELD, maxConnections.toString());

    if (allowedUpdates != null) {
      if (allowedUpdates.length == 0) {
        builder.addTextBody(ALLOWED_UPDATES_FIELD, "[]");
      } else {
        for (String allowedUpdate : allowedUpdates) {
          builder.addTextBody(ALLOWED_UPDATES_FIELD + "[]", allowedUpdate);
        }
      }
    }

    return builder.build();
  }

}
