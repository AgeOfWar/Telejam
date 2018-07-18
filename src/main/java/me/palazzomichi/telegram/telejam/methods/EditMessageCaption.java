package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.CallbackQuery;
import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.InlineKeyboardMarkup;
import me.palazzomichi.telegram.telejam.objects.Message;
import me.palazzomichi.telegram.telejam.text.Text;

import java.io.Serializable;
import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to edit captions of messages sent by the bot or via the bot (for inline bots).
 * On success, if edited message is sent by the bot, the edited Message is returned,
 * otherwise True is returned.
 *
 * @author Michi Palazzo
 */
public class EditMessageCaption implements TelegramMethod<Serializable> {

  public static final String NAME = "editMessageCaption";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
  static final String CAPTION_FIELD = "caption";
  static final String PARSE_MODE_FIELD = "parse_mode";
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

  /**
   * New caption of the message.
   */
  private Text caption;
  
  
  public EditMessageCaption chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    this.inlineMessageId = null;
    return this;
  }
  
  public EditMessageCaption chat(long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    this.inlineMessageId = null;
    return this;
  }
  
  public EditMessageCaption chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    this.inlineMessageId = null;
    return this;
  }

  public EditMessageCaption message(Long messageId) {
    this.messageId = messageId;
    this.inlineMessageId = null;
    return this;
  }

  public EditMessageCaption message(Message message) {
    this.chatId = message.getChat().getId();
    this.messageId = message.getId();
    this.chatUsername = null;
    this.inlineMessageId = null;
    return this;
  }

  public EditMessageCaption inlineMessage(String inlineMessageId) {
    this.inlineMessageId = inlineMessageId;
    this.chatId = null;
    this.chatUsername = null;
    this.messageId = null;
    return this;
  }
  
  public EditMessageCaption callbackQuery(CallbackQuery callbackQuery) {
    this.inlineMessageId = callbackQuery.getInlineMessageId().orElse(null);
    this.chatId = callbackQuery.getMessage().map(Message::getChat).map(Chat::getId).orElse(null);
    this.messageId = callbackQuery.getMessage().map(Message::getId).orElse(null);
    this.chatUsername = null;
    return this;
  }
  
  public EditMessageCaption replyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public EditMessageCaption caption(Text caption) {
    this.caption = caption;
    return this;
  }

  public EditMessageCaption caption(String caption) {
    this.caption = new Text(caption);
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
        CAPTION_FIELD, caption.toHtmlString(),
        PARSE_MODE_FIELD, "HTML",
        REPLY_MARKUP_FIELD, replyMarkup
    );
  }
  
  @Override
  public Class<? extends Serializable> getReturnType() {
    return inlineMessageId == null ? Message.class : Boolean.class;
  }

}
