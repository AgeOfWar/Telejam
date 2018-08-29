package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;

import java.io.Serializable;
import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to set the score of the specified user in a game.
 * On success, if the message was sent by the bot, returns the edited Message,
 * otherwise returns True. Returns an error, if the new score is not greater
 * than the user's current score in the chat and force is False.
 *
 * @author Michi Palazzo
 */
public class SetGameScore implements TelegramMethod<Serializable> {

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
  private Long userId;

  /**
   * New score, must be non-negative.
   */
  private Integer score;

  /**
   * Pass True, if the high score is allowed to decrease.
   * This can be useful when fixing mistakes or banning cheaters.
   */
  private Boolean force;

  /**
   * Pass True, if the game message should not be automatically
   * edited to include the current scoreboard.
   */
  private Boolean disableEdit;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Unique identifier for the target chat.
   */
  private Long chatId;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Identifier of the sent message.
   */
  private Long messageId;

  /**
   * Required if {@link #chatId} and {@link #messageId} are not specified.
   * Identifier of the inline message.
   */
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

  public SetGameScore message(Long messageId) {
    inlineMessageId = null;
    this.messageId = messageId;
    return this;
  }

  public SetGameScore message(Message message) {
    inlineMessageId = null;
    this.chatId = message.getChat().getId();
    this.messageId = message.getId();
    return this;
  }

  public SetGameScore inlineMessage(String inlineMessageId) {
    chatId = null;
    messageId = null;
    this.inlineMessageId = inlineMessageId;
    return this;
  }
  
  public SetGameScore callbackQuery(CallbackQuery callbackQuery) {
    this.inlineMessageId = callbackQuery.getInlineMessageId().orElse(null);
    this.chatId = callbackQuery.getMessage().map(Message::getChat).map(Chat::getId).orElse(null);
    this.messageId = callbackQuery.getMessage().map(Message::getId).orElse(null);
    return this;
  }
  
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        USER_ID_FIELD, userId,
        SCORE_FIELD, score,
        FORCE_FIELD, force,
        DISABLE_EDIT_MESSAGE_FIELD, disableEdit,
        CHAT_ID_FIELD, chatId,
        MESSAGE_ID_FIELD, messageId,
        INLINE_MESSAGE_ID_FIELD, inlineMessageId
    );
  }
  
  @Override
  public Class<? extends Serializable> getReturnType() {
    return inlineMessageId == null ? GameMessage.class : Boolean.class;
  }

}
