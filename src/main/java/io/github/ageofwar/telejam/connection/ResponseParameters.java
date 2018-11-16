package io.github.ageofwar.telejam.connection;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * Contains information about why a request was unsuccessful.
 *
 * @author Michi Palazzo
 */
public class ResponseParameters implements TelegramObject {
  
  static final String MIGRATE_TO_CHAT_ID_FIELD = "migrate_to_chat_id";
  static final String RETRY_AFTER_FIELD = "retry_after";
  
  /**
   * The group has been migrated to a supergroup with the specified identifier.
   */
  @SerializedName(MIGRATE_TO_CHAT_ID_FIELD)
  private Long migrateToChatId;
  
  /**
   * In case of exceeding flood control, the number of seconds
   * left to wait before the request can be repeated.
   */
  @SerializedName(RETRY_AFTER_FIELD)
  private Integer retryAfter;
  
  
  public ResponseParameters(long migrateToChatId) {
    this.migrateToChatId = migrateToChatId;
  }
  
  public ResponseParameters(int retryAfter) {
    this.retryAfter = retryAfter;
  }
  
  
  /**
   * Getter for property {@link #migrateToChatId}.
   *
   * @return optional value for property {@link #migrateToChatId}
   */
  public OptionalLong getMigrateToChatId() {
    return migrateToChatId == null ? OptionalLong.empty() : OptionalLong.of(migrateToChatId);
  }
  
  /**
   * Getter for property {@link #retryAfter}.
   *
   * @return optional value for property {@link #retryAfter}
   */
  public OptionalInt getRetryAfter() {
    return retryAfter == null ? OptionalInt.empty() : OptionalInt.of(retryAfter);
  }
  
}
