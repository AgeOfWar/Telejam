package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a result of an inline query that was
 * chosen by the user and sent to their chat partner.
 *
 * @author Michi Palazzo
 */
public class ChosenInlineResult implements TelegramObject {

  static final String RESULT_ID_FIELD = "result_id";
  static final String SENDER_FIELD = "from";
  static final String LOCATION_FIELD = "location";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
  static final String QUERY_FIELD = "query";

  /**
   * The unique identifier for the result that was chosen.
   */
  @SerializedName(RESULT_ID_FIELD)
  private String resultId;

  /**
   * The user that chose the result.
   */
  @SerializedName(SENDER_FIELD)
  private User sender;

  /**
   * Sender location, only for bots that require user location.
   */
  @SerializedName(LOCATION_FIELD)
  private Location location;

  /**
   * Identifier of the sent inline message.
   * Available only if there is an inline keyboard attached to the message.
   * Will be also received in callback queries and can be used to edit the message.
   */
  @SerializedName(INLINE_MESSAGE_ID_FIELD)
  private String inlineMessageId;

  /**
   * The query that was used to obtain the result.
   */
  @SerializedName(QUERY_FIELD)
  private String query;


  public ChosenInlineResult(String resultId, User sender, Location location, String inlineMessageId, String query) {
    this.resultId = Objects.requireNonNull(resultId);
    this.sender = Objects.requireNonNull(sender);
    this.location = location;
    this.inlineMessageId = inlineMessageId;
    this.query = Objects.requireNonNull(query);
  }


  /**
   * Getter for property {@link #resultId}.
   *
   * @return value for property {@link #resultId}
   */
  public String getResultId() {
    return resultId;
  }

  /**
   * Getter for property {@link #sender}.
   *
   * @return value for property {@link #sender}
   */
  public User getSender() {
    return sender;
  }

  /**
   * Getter for property {@link #location}.
   *
   * @return optional value for property {@link #location}
   */
  public Optional<Location> getLocation() {
    return Optional.ofNullable(location);
  }

  /**
   * Getter for property {@link #inlineMessageId}.
   *
   * @return optional value for property {@link #inlineMessageId}
   */
  public Optional<String> getInlineMessageId() {
    return Optional.ofNullable(inlineMessageId);
  }

  /**
   * Getter for property {@link #query}.
   *
   * @return value for property {@link #query}
   */
  public String getQuery() {
    return query;
  }

}
