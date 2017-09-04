package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.messages.GameMessage;
import me.palazzomichi.telegram.telejam.objects.messages.Message;

import java.io.Serializable;

/**
 * Use this method to set the score of the specified user in a game.
 * On success, if the message was sent by the bot, returns the edited Message,
 * otherwise returns True. Returns an error, if the new score is not greater
 * than the user's current score in the chat and force is False.
 *
 * @author Michi Palazzo
 */
public class SetGameScore extends JsonTelegramMethod<Serializable> {

  public static final String NAME = "setGameScore";

  static final String USER_ID_FIELD = "user_id";
  static final String SCORE_FIELD = "score";
  static final String FORCE_FIELD = "force";
  static final String DISABLE_EDIT_MESSAGE_FIELD = "disable_edit_message";
  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";

  /**
   * User identifier.
   */
  @SerializedName(USER_ID_FIELD)
  private Long userId;

  /**
   * New score, must be non-negative.
   */
  @SerializedName(SCORE_FIELD)
  private Integer score;

  /**
   * Pass True, if the high score is allowed to decrease.
   * This can be useful when fixing mistakes or banning cheaters.
   */
  @SerializedName(FORCE_FIELD)
  private Boolean force;

  /**
   * Pass True, if the game message should not be automatically
   * edited to include the current scoreboard.
   */
  @SerializedName(DISABLE_EDIT_MESSAGE_FIELD)
  private Boolean disableEdit;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Unique identifier for the target chat.
   */
  @SerializedName(CHAT_ID_FIELD)
  private Long chatId;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Identifier of the sent message.
   */
  @SerializedName(MESSAGE_ID_FIELD)
  private Long messageId;

  /**
   * Required if {@link #chatId} and {@link #messageId} are not specified.
   * Identifier of the inline message.
   */
  @SerializedName(INLINE_MESSAGE_ID_FIELD)
  private String inlineMessageId;


  public SetGameScore user(Long userId) {
    this.userId = userId;
    return this;
  }

  public SetGameScore user(User user) {
    this.userId = user.getId();
    return this;
  }

  public SetGameScore score(Integer score) {
    this.score = score;
    return this;
  }

  public SetGameScore force(Boolean force) {
    this.force = force;
    return this;
  }

  public SetGameScore force() {
    this.force = true;
    return this;
  }

  public SetGameScore disableEdit(Boolean disableEdit) {
    this.disableEdit = disableEdit;
    return this;
  }

  public SetGameScore disableEdit() {
    this.disableEdit = true;
    return this;
  }

  public SetGameScore message(Long chatId, Long messageId) {
    inlineMessageId = null;
    this.chatId = chatId;
    this.messageId = messageId;
    return this;
  }

  public SetGameScore message(Message message) {
    inlineMessageId = null;
    this.chatId = message.getChat().getId();
    this.messageId = message.getId();
    return this;
  }

  public SetGameScore message(String inlineMessageId) {
    chatId = null;
    messageId = null;
    this.inlineMessageId = inlineMessageId;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Serializable> getReturnType(JsonElement response) {
    if (response == null) {
      return Serializable.class;
    }
    return response.isJsonPrimitive() ? Boolean.class : GameMessage.class;
  }

}
