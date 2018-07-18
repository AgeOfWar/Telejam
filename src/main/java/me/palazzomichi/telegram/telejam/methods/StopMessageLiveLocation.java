package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;

import java.io.Serializable;
import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to stop updating a live location message sent by the bot or
 * via the bot (for inline bots) before live period expires.
 * On success, if the message was sent by the bot,
 * the sent Message is returned, otherwise True is returned.
 *
 * @author Michi Palazzo
 */
public class StopMessageLiveLocation implements TelegramMethod<Serializable> {
  
  public static final String NAME = "stopMessageLiveLocation";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  
  /**
   * Required if {@link #inlineMessageId} and {@link #chatUsername} are not specified.
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Required if {@link #inlineMessageId} and {@link #chatId} are not specified.
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Identifier of the sent message.
   */
  private Long messageId;
  
  /**
   * Required if {@link #chatId} and {@link #messageId} are not specified.
   * Identifier of the inline message
   */
  private String inlineMessageId;
  
  /**
   * A new inline keyboard.
   */
  private InlineKeyboardMarkup replyMarkup;
  
  
  public StopMessageLiveLocation chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    this.inlineMessageId = null;
    return this;
  }
  
  public StopMessageLiveLocation chat(long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    this.inlineMessageId = null;
    return this;
  }
  
  public StopMessageLiveLocation chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    this.inlineMessageId = null;
    return this;
  }
  
  public StopMessageLiveLocation message(Long messageId) {
    this.messageId = messageId;
    this.inlineMessageId = null;
    return this;
  }
  
  public StopMessageLiveLocation message(Message message) {
    this.chatId = message.getChat().getId();
    this.messageId = message.getId();
    this.chatUsername = null;
    this.inlineMessageId = null;
    return this;
  }
  
  public StopMessageLiveLocation inlineMessage(String inlineMessageId) {
    this.inlineMessageId = inlineMessageId;
    this.chatId = null;
    this.chatUsername = null;
    this.messageId = null;
    return this;
  }
  
  public StopMessageLiveLocation callbackQuery(CallbackQuery callbackQuery) {
    this.inlineMessageId = callbackQuery.getInlineMessageId().orElse(null);
    this.chatId = callbackQuery.getMessage().map(Message::getChat).map(Chat::getId).orElse(null);
    this.messageId = callbackQuery.getMessage().map(Message::getId).orElse(null);
    this.chatUsername = null;
    return this;
  }
  
  public StopMessageLiveLocation replyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        MESSAGE_ID_FIELD, messageId,
        INLINE_MESSAGE_ID_FIELD, inlineMessageId,
        REPLY_MARKUP_FIELD, replyMarkup
    );
  }
  
  @Override
  public Class<? extends Serializable> getReturnType() {
    return inlineMessageId == null ? LocationMessage.class : Boolean.class;
  }
  
}
