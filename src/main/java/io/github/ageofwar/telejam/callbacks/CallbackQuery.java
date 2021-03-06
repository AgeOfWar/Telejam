package io.github.ageofwar.telejam.callbacks;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.messages.Message;
import io.github.ageofwar.telejam.users.User;
import io.github.ageofwar.telejam.games.Game;
import io.github.ageofwar.telejam.TelegramObject;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents an incoming callback query from a callback button in an inline keyboard.
 * If the button that originated the query was attached to a message sent by the bot,
 * the field message will be present. If the button was attached to a message sent via the
 * bot (in inline mode), the field inline_message_id will be present. Exactly one of the
 * fields data or game_short_name will be present.
 *
 * @author Michi Palazzo
 */
public class CallbackQuery implements TelegramObject {
  
  static final String ID_FIELD = "id";
  static final String SENDER_FIELD = "from";
  static final String MESSAGE_FIELD = "message";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
  static final String CHAT_INSTANCE_FIELD = "chat_instance";
  static final String DATA_FIELD = "data";
  static final String GAME_SHORT_NAME_FIELD = "game_short_name";
  
  /**
   * Unique identifier for this query.
   */
  @SerializedName(ID_FIELD)
  private final String id;
  
  /**
   * Sender.
   */
  @SerializedName(SENDER_FIELD)
  private final User sender;
  /**
   * Global identifier, uniquely corresponding to the chat to
   * which the message with the callback button was sent.
   * Useful for high scores in games.
   */
  @SerializedName(CHAT_INSTANCE_FIELD)
  private final String chatInstance;
  /**
   * Data associated with the callback button.
   * Be aware that a bad client can send arbitrary data in this field.
   */
  @SerializedName(DATA_FIELD)
  private final String data;
  /**
   * Short name of a Game to be returned, serves as the unique identifier for the game.
   *
   * @see Game
   */
  @SerializedName(GAME_SHORT_NAME_FIELD)
  private final String gameShortName;
  /**
   * Message with the callback button that originated the query.
   * <p>Note that message content and message date will not be
   * available if the message is too old.</p>
   */
  @SerializedName(MESSAGE_FIELD)
  private Message message;
  /**
   * Identifier of the message sent via the bot in inline mode, that originated the query.
   */
  @SerializedName(INLINE_MESSAGE_ID_FIELD)
  private String inlineMessageId;
  
  
  public CallbackQuery(String id, User sender, Message message, String chatInstance, String data, String gameShortName) {
    this.id = Objects.requireNonNull(id);
    this.sender = Objects.requireNonNull(sender);
    this.message = Objects.requireNonNull(message);
    this.chatInstance = Objects.requireNonNull(chatInstance);
    this.data = data;
    this.gameShortName = gameShortName;
  }
  
  public CallbackQuery(String id, User sender, String inlineMessageId, String chatInstance, String data, String gameShortName) {
    this.id = Objects.requireNonNull(id);
    this.sender = Objects.requireNonNull(sender);
    this.inlineMessageId = Objects.requireNonNull(inlineMessageId);
    this.chatInstance = Objects.requireNonNull(chatInstance);
    this.data = data;
    this.gameShortName = gameShortName;
  }
  
  
  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public String getId() {
    return id;
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
   * Getter for property {@link #message}.
   *
   * @return optional value for property {@link #message}
   */
  public Optional<Message> getMessage() {
    return Optional.ofNullable(message);
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
   * Getter for property {@link #chatInstance}.
   *
   * @return value for property {@link #chatInstance}
   */
  public String getChatInstance() {
    return chatInstance;
  }
  
  /**
   * Getter for property {@link #data}.
   *
   * @return optional value for property {@link #data}
   */
  public Optional<String> getData() {
    return Optional.ofNullable(data);
  }
  
  /**
   * Getter for property {@link #gameShortName}.
   *
   * @return optional value for property {@link #gameShortName}
   */
  public Optional<String> getGameShortName() {
    return Optional.ofNullable(gameShortName);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof CallbackQuery)) {
      return false;
    }
    
    CallbackQuery callbackQuery = (CallbackQuery) obj;
    return id.equals(callbackQuery.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  
}
