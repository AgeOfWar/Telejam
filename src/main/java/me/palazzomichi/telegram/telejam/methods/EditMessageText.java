package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;
import me.palazzomichi.telegram.telejam.util.text.ParseMode;
import me.palazzomichi.telegram.telejam.util.text.Text;

import java.io.Serializable;

/**
 * Use this method to edit text and game messages sent by the bot or via the bot (for inline bots).
 * On success, if edited message is sent by the bot, the edited Message is returned,
 * otherwise True is returned.
 *
 * @author Michi Palazzo
 */
public class EditMessageText extends JsonTelegramMethod<Serializable> {

  public static final String NAME = "editMessageText";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
  static final String TEXT_FIELD = "text";
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DISABLE_WEB_PAGE_PREVIEW_FIELD = "disable_web_page_preview";
  static final String INLINE_KEYBOARD_FIELD = "reply_markup";

  private static final ParseMode defaultParseMode = ParseMode.HTML;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Unique identifier for the target chat or username
   * of the target channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

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

  /**
   * A JSON-serialized object for an inline keyboard.
   */
  @SerializedName(INLINE_KEYBOARD_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * New text of the message
   */
  @SerializedName(TEXT_FIELD)
  private String text;

  /**
   * <code>"Markdown"</code> or <code>"HTML"</code>, if you want Telegram apps to show bold,
   * italic, fixed-width text or inline URLs in your bot's message.
   */
  @SerializedName(PARSE_MODE_FIELD)
  private String parseMode;

  /**
   * Disables link previews for links in this message.
   */
  @SerializedName(DISABLE_WEB_PAGE_PREVIEW_FIELD)
  private Boolean disableWebPagePreview;


  public EditMessageText chat(String chatId) {
    this.chatId = chatId;
    this.inlineMessageId = null;
    return this;
  }

  public EditMessageText chat(long chatId) {
    this.chatId = Long.toString(chatId);
    this.inlineMessageId = null;
    return this;
  }

  public EditMessageText chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    this.inlineMessageId = null;
    return this;
  }

  public EditMessageText message(Long messageId) {
    this.messageId = messageId;
    this.inlineMessageId = null;
    return this;
  }

  public EditMessageText message(Message message) {
    this.chatId = Long.toString(message.getChat().getId());
    this.messageId = message.getId();
    this.inlineMessageId = null;
    return this;
  }

  public EditMessageText inlineMessage(String inlineMessageId) {
    this.inlineMessageId = inlineMessageId;
    this.chatId = null;
    this.messageId = null;
    return this;
  }

  public EditMessageText replyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public EditMessageText text(Text text) {
    this.text = text.toString(defaultParseMode);
    this.parseMode = defaultParseMode.toString();
    return this;
  }

  public EditMessageText text(String text) {
    this.text = text;
    return this;
  }

  public EditMessageText parseMode(ParseMode parseMode) {
    this.parseMode = parseMode.toString();
    return this;
  }

  public EditMessageText parseMode(String parseMode) {
    this.parseMode = parseMode;
    return this;
  }

  public EditMessageText disableWebPagePreview(Boolean disableWebPagePreview) {
    this.disableWebPagePreview = disableWebPagePreview;
    return this;
  }

  public EditMessageText disableWebPagePreview() {
    this.disableWebPagePreview = true;
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
    return response.isJsonPrimitive() ? Boolean.class : Message.class;
  }

}
