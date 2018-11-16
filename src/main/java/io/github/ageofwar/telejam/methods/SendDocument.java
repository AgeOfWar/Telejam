package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.replymarkups.ReplyMarkup;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.messages.*;
import io.github.ageofwar.telejam.chats.Chat;

import io.github.ageofwar.telejam.text.Text;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to send general files.
 * On success, the sent Message is returned.
 * Bots can currently send files of any type of up to
 * 50 MB in size, this limit may be changed in the future.
 *
 * @author Michi Palazzo
 */
public class SendDocument implements TelegramMethod<DocumentMessage> {

  public static final String NAME = "sendDocument";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String FILE_FIELD = "document";
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
   * File to send.
   */
  private String file;

  /**
   * Document caption (may also be used when resending photos by file_id), 0-200 characters.
   */
  private Text caption;

  /**
   * File not present in Telegram servers.
   */
  private UploadFile newFile;
  
  
  public SendDocument chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendDocument chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendDocument chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public SendDocument disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendDocument disableNotification() {
    disableNotification = true;
    return this;
  }

  public SendDocument replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    chatUsername = null;
    return this;
  }

  public SendDocument replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendDocument replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  public SendDocument document(String file) {
    newFile = null;
    this.file = file;
    return this;
  }
  
  public SendDocument document(UploadFile newFile) {
    file = null;
    this.newFile = newFile;
    return this;
  }
  
  public SendDocument caption(String caption) {
    this.caption = new Text(caption);
    return this;
  }
  
  public SendDocument caption(Text caption) {
    this.caption = caption;
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
        FILE_FIELD, file,
        CAPTION_FIELD, caption != null ? caption.toHtmlString() : null,
        PARSE_MODE_FIELD, "HTML"
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(FILE_FIELD, newFile);
  }
  
  @Override
  public Class<? extends DocumentMessage> getReturnType() {
    return DocumentMessage.class;
  }
  
}
