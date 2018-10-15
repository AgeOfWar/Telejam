package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * @author Michi Palazzo
 */
public class SendPhoto implements TelegramMethod<PhotoMessage> {

  public static final String NAME = "sendPhoto";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String PHOTO_FIELD = "photo";
  static final String CAPTION_FIELD = "caption";
  static final String PARSE_MODE_FIELD = "parse_mode";
  
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
   * Photo to send.
   */
  private String photo;

  /**
   * Photo caption (may also be used when resending photos by file_id), 0-200 characters.
   */
  private Text caption;

  /**
   * Photo not present in Telegram servers.
   */
  private UploadFile newPhoto;
  
  
  public SendPhoto chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendPhoto chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendPhoto chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public SendPhoto disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendPhoto disableNotification() {
    disableNotification = true;
    return this;
  }

  public SendPhoto replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    chatUsername = null;
    return this;
  }

  public SendPhoto replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendPhoto replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public SendPhoto photo(String photo) {
    newPhoto = null;
    this.photo = photo;
    return this;
  }
  
  public SendPhoto photo(UploadFile newPhoto) {
    photo = null;
    this.newPhoto = newPhoto;
    return this;
  }
  
  public SendPhoto caption(Text caption) {
    this.caption = caption;
    return this;
  }

  public SendPhoto caption(String caption) {
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
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup,
        CAPTION_FIELD, caption != null ? caption.toHtmlString() : null,
        PARSE_MODE_FIELD, "HTML",
        PHOTO_FIELD, photo
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(PHOTO_FIELD, newPhoto);
  }
  
  @Override
  public Class<? extends PhotoMessage> getReturnType() {
    return PhotoMessage.class;
  }

}
