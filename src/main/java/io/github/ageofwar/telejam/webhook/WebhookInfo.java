package io.github.ageofwar.telejam.webhook;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * Contains information about the current status of a webhook.
 *
 * @author Michi Palazzo
 */
public class WebhookInfo implements TelegramObject {
  
  static final String URL_FIELD = "url";
  static final String HAS_CUSTOM_CERTIFICATE_FIELD = "has_custom_certificate";
  static final String PENDING_UPDATE_COUNT_FIELD = "pending_update_count";
  static final String LAST_ERROR_DATE_FIELD = "last_error_date";
  static final String LAST_ERROR_MESSAGE_FIELD = "last_error_message";
  static final String MAX_CONNECTIONS_FIELD = "max_connections";
  static final String ALLOWED_UPDATES_FIELD = "allowed_updates";
  
  /**
   * Webhook URL, may be empty if webhook is not set up.
   */
  @SerializedName(URL_FIELD)
  private final String url;
  
  /**
   * True, if a custom certificate was provided for webhook certificate checks.
   */
  @SerializedName(HAS_CUSTOM_CERTIFICATE_FIELD)
  private final boolean hasCustomCertificate;
  
  /**
   * Number of updates awaiting delivery.
   */
  @SerializedName(PENDING_UPDATE_COUNT_FIELD)
  private final int pendingUpdateCount;
  
  /**
   * Unix time for the most recent error that happened when trying to deliver an update via webhook.
   */
  @SerializedName(LAST_ERROR_DATE_FIELD)
  private final Long lastErrorDate;
  
  /**
   * Error message in human-readable format for the most recent
   * error that happened when trying to deliver an update via webhook
   */
  @SerializedName(LAST_ERROR_MESSAGE_FIELD)
  private final String lastErrorMessage;
  
  /**
   * Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery.
   */
  @SerializedName(MAX_CONNECTIONS_FIELD)
  private final Integer maxConnections;
  
  /**
   * A list of update types the bot is subscribed to. Defaults to all update types.
   */
  @SerializedName(ALLOWED_UPDATES_FIELD)
  private final String[] allowedUpdates;
  
  
  public WebhookInfo(String url,
                     boolean hasCustomCertificate,
                     int pendingUpdateCount,
                     Long lastErrorDate,
                     String lastErrorMessage,
                     Integer maxConnections,
                     String[] allowedUpdates) {
    this.url = Objects.requireNonNull(url);
    this.hasCustomCertificate = hasCustomCertificate;
    this.pendingUpdateCount = pendingUpdateCount;
    this.lastErrorDate = lastErrorDate;
    this.lastErrorMessage = lastErrorMessage;
    this.maxConnections = maxConnections;
    this.allowedUpdates = allowedUpdates;
  }
  
  
  /**
   * Getter for property {@link #url}.
   *
   * @return value for property {@link #url}
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * Getter for property {@link #hasCustomCertificate}.
   *
   * @return value for property {@link #hasCustomCertificate}
   */
  public boolean isHasCustomCertificate() {
    return hasCustomCertificate;
  }
  
  /**
   * Getter for property {@link #pendingUpdateCount}.
   *
   * @return value for property {@link #pendingUpdateCount}
   */
  public int getPendingUpdateCount() {
    return pendingUpdateCount;
  }
  
  /**
   * Getter for property {@link #lastErrorDate}.
   *
   * @return optional value for property {@link #lastErrorDate}
   */
  public OptionalLong getLastErrorDate() {
    return lastErrorDate == null ? OptionalLong.empty() : OptionalLong.of(lastErrorDate);
  }
  
  /**
   * Getter for property {@link #lastErrorMessage}.
   *
   * @return optional value for property {@link #lastErrorMessage}
   */
  public Optional<String> getLastErrorMessage() {
    return Optional.ofNullable(lastErrorMessage);
  }
  
  /**
   * Getter for property {@link #maxConnections}.
   *
   * @return optional value for property {@link #maxConnections}
   */
  public OptionalInt getMaxConnections() {
    return maxConnections == null ? OptionalInt.empty() : OptionalInt.of(maxConnections);
  }
  
  /**
   * Getter for property {@link #allowedUpdates}.
   *
   * @return optional value for property {@link #allowedUpdates}
   */
  public String[] getAllowedUpdates() {
    return allowedUpdates == null ? new String[0] : allowedUpdates;
  }
  
}
