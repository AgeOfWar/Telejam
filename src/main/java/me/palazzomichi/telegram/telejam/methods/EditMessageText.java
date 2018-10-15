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
 * Use this method to edit text and game messages sent by the bot or via the bot (for inline bots).
 * On success, if edited message is sent by the bot, the edited Message is returned,
 * otherwise True is returned.
 *
 * @author Michi Palazzo
 */
public class EditMessageText implements TelegramMethod<Serializable> {

  public static final String NAME = "editMessageText";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
  static final String TEXT_FIELD = "text";
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DISABLE_WEB_PAGE_PREVIEW_FIELD = "disable_web_page_preview";
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
   * A JSON-serialized object for an inline keyboard.
   */
  private InlineKeyboardMarkup replyMarkup;

  /**
   * New text of the message
   */
  private Text text;

  /**
   * Disables link previews for links in this message.
   */
  private Boolean disableWebPagePreview;
  
  
  public EditMessageText chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageText chat(long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageText chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageText message(Long messageId) {
    this.messageId = messageId;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageText message(Message message) {
    chatId = message.getChat().getId();
    messageId = message.getId();
    chatUsername = null;
    inlineMessageId = null;
    return this;
  }
  
  public EditMessageText inlineMessage(String inlineMessageId) {
    this.inlineMessageId = inlineMessageId;
    chatId = null;
    messageId = null;
    return this;
  }
  
  public EditMessageText callbackQuery(CallbackQuery callbackQuery) {
    inlineMessageId = callbackQuery.getInlineMessageId().orElse(null);
    chatId = callbackQuery.getMessage().map(Message::getChat).map(Chat::getId).orElse(null);
    messageId = callbackQuery.getMessage().map(Message::getId).orElse(null);
    chatUsername = null;
    return this;
  }

  public EditMessageText replyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public EditMessageText text(Text text) {
    this.text = text;
    return this;
  }

  public EditMessageText text(String text) {
    this.text = new Text(text);
    return this;
  }

  public EditMessageText disableWebPagePreview(Boolean disableWebPagePreview) {
    this.disableWebPagePreview = disableWebPagePreview;
    return this;
  }

  public EditMessageText disableWebPagePreview() {
    disableWebPagePreview = true;
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
        TEXT_FIELD, text.toHtmlString(),
        PARSE_MODE_FIELD, "HTML",
        DISABLE_WEB_PAGE_PREVIEW_FIELD, disableWebPagePreview,
        REPLY_MARKUP_FIELD, replyMarkup
    );
  }
  
  @Override
  public Class<? extends Serializable> getReturnType() {
    return inlineMessageId == null ? Message.class : Boolean.class;
  }

}
