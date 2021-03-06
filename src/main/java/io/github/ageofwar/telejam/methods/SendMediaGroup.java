package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.media.InputMedia;
import io.github.ageofwar.telejam.messages.Message;
import io.github.ageofwar.telejam.connection.UploadFile;

import java.util.HashMap;
import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to send a group of photos or videos as an album.
 * On success, an array of the sent Messages is returned.
 *
 * @author Michi Palazzo
 */
public class SendMediaGroup implements TelegramMethod<Message[]> {
  
  public static final String NAME = "sendMediaGroup";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String MEDIA_FIELD = "media";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * Array describing photos and videos to be sent, must include 2–10 items.
   */
  private InputMedia[] media;
  
  /**
   * Sends the messages silently.
   * Users will receive a notification with no sound.
   */
  private Boolean disableNotification;
  
  /**
   * If the messages are a reply, ID of the original message.
   */
  private Long replyToMessageId;
  
  
  public SendMediaGroup chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendMediaGroup chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendMediaGroup chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }
  
  public SendMediaGroup disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendMediaGroup disableNotification() {
    disableNotification = true;
    return this;
  }
  
  public SendMediaGroup replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    return this;
  }
  
  public SendMediaGroup replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }
  
  public SendMediaGroup media(InputMedia... media) {
    this.media = media;
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
        MEDIA_FIELD, media,
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    Map<String, UploadFile> files = new HashMap<>();
    for (InputMedia media : media) {
      media.getFile().ifPresent(file -> files.put(file.getFileName(), file));
      media.getThumbnail().ifPresent(thumb -> files.put(thumb.getFileName(), thumb));
    }
    return files;
  }
  
  @Override
  public Class<? extends Message[]> getReturnType() {
    return Message[].class;
  }
  
}
