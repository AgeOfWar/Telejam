package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.TextMessage;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;
import me.palazzomichi.telegram.telejam.util.text.ParseMode;
import me.palazzomichi.telegram.telejam.util.text.Text;

/**
 * Use this method to send text messages.
 * The sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendMessage extends JsonTelegramMethod<TextMessage> {

  public static final String NAME = "sendMessage";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String TEXT_FIELD = "text";
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DISABLE_WEB_PAGE_PREVIEW_FIELD = "disable_web_page_preview";

  private static final ParseMode defaultParseMode = ParseMode.HTML;

  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  @SerializedName(DISABLE_NOTIFICATION_FIELD)
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  @SerializedName(REPLY_TO_MESSAGE_ID_FIELD)
  private Long replyToMessageId;

  /**
   * Additional interface options.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private ReplyMarkup replyMarkup;

  /**
   * Text of the message to be sent.
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


  public SendMessage chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendMessage chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendMessage chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public SendMessage disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendMessage disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public SendMessage replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    return this;
  }

  public SendMessage replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendMessage replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public SendMessage text(Text text) {
    this.text = text.toString(defaultParseMode);
    this.parseMode = defaultParseMode.toString();
    return this;
  }

  public SendMessage text(String text) {
    this.text = text;
    return this;
  }

  public SendMessage parseMode(ParseMode parseMode) {
    this.parseMode = parseMode.toString();
    return this;
  }

  public SendMessage parseMode(String parseMode) {
    this.parseMode = parseMode;
    return this;
  }

  public SendMessage disableWebPagePreview(Boolean disableWebPagePreview) {
    this.disableWebPagePreview = disableWebPagePreview;
    return this;
  }

  public SendMessage disableWebPagePreview() {
    this.disableWebPagePreview = true;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends TextMessage> getReturnType(JsonElement response) {
    return TextMessage.class;
  }

}
