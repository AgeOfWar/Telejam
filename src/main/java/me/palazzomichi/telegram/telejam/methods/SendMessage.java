package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.Message;
import me.palazzomichi.telegram.telejam.objects.ReplyMarkup;
import me.palazzomichi.telegram.telejam.objects.TextMessage;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to send text messages.
 * The sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendMessage implements TelegramMethod<TextMessage> {

  public static final String NAME = "sendMessage";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String TEXT_FIELD = "text";
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DISABLE_WEB_PAGE_PREVIEW_FIELD = "disable_web_page_preview";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  private Long replyToMessageId;

  /**
   * Additional interface options.
   */
  private ReplyMarkup replyMarkup;

  /**
   * Text of the message to be sent.
   */
  private Text text;

  /**
   * Disables link previews for links in this message.
   */
  private Boolean disableWebPagePreview;
  
  
  public SendMessage chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendMessage chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendMessage chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public SendMessage disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendMessage disableNotification() {
    disableNotification = true;
    return this;
  }

  public SendMessage replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    chatUsername = null;
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
    this.text = text;
    return this;
  }

  public SendMessage text(String text) {
    this.text = new Text(text);
    return this;
  }

  public SendMessage disableWebPagePreview(Boolean disableWebPagePreview) {
    this.disableWebPagePreview = disableWebPagePreview;
    return this;
  }

  public SendMessage disableWebPagePreview() {
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
        TEXT_FIELD, text.toHtmlString(),
        PARSE_MODE_FIELD, "HTML",
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup,
        DISABLE_WEB_PAGE_PREVIEW_FIELD, disableWebPagePreview
    );
  }
  
  @Override
  public Class<? extends TextMessage> getReturnType() {
    return TextMessage.class;
  }

}
