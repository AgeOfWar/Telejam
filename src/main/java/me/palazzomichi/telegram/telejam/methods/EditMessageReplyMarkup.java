package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.CallbackQuery;
import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.InlineKeyboardMarkup;
import me.palazzomichi.telegram.telejam.objects.Message;

import java.io.Serializable;
import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to edit only the reply markup of
 * messages sent by the bot or via the bot (for inline bots).
 * On success, if edited message is sent by the bot, the edited
 * Message is returned, otherwise True is returned.
 *
 * @author Michi Palazzo
 */
public class EditMessageReplyMarkup implements TelegramMethod<Serializable> {

  public static final String NAME = "editMessageReplyMarkup";

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
   * Identifier of the inline message.
   */
  private String inlineMessageId;

  /**
   * An inline keyboard.
   */
  private InlineKeyboardMarkup replyMarkup;
  
  
  public EditMessageReplyMarkup chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageReplyMarkup chat(long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageReplyMarkup chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }

  public EditMessageReplyMarkup message(Long messageId) {
    this.messageId = messageId;
    inlineMessageId = null;
    return this;
  }

  public EditMessageReplyMarkup message(Message message) {
    chatId = message.getChat().getId();
    messageId = message.getId();
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }

  public EditMessageReplyMarkup inlineMessage(String inlineMessageId) {
    this.inlineMessageId = inlineMessageId;
    chatId = null;
    chatUsername = null;
    messageId = null;
    return this;
  }
  
  public EditMessageReplyMarkup callbackQuery(CallbackQuery callbackQuery) {
    inlineMessageId = callbackQuery.getInlineMessageId().orElse(null);
    chatId = callbackQuery.getMessage().map(Message::getChat).map(Chat::getId).orElse(null);
    messageId = callbackQuery.getMessage().map(Message::getId).orElse(null);
    chatUsername = null;
    return this;
  }

  public EditMessageReplyMarkup replyMarkup(InlineKeyboardMarkup replyMarkup) {
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
    return inlineMessageId == null ? Message.class : Boolean.class;
  }

}
