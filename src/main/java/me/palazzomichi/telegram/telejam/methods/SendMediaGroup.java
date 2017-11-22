package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.inputmedia.InputMedia;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;

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
   * Unique identifier for the target chat or username of
   * the target channel (in the format @channelusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId;
  
  /**
   * Array describing photos and videos to be sent, must include 2–10 items.
   */
  @SerializedName(MEDIA_FIELD)
  private InputMedia[] media;
  
  /**
   * Sends the messages silently.
   * Users will receive a notification with no sound.
   */
  @SerializedName(DISABLE_NOTIFICATION_FIELD)
  private Boolean disableNotification;
  
  /**
   * If the messages are a reply, ID of the original message.
   */
  @SerializedName(REPLY_TO_MESSAGE_ID_FIELD)
  private Long replyToMessageId;
  
  
  public SendMediaGroup chat(String chatId) {
    this.chatId = chatId;
    return this;
  }
  
  public SendMediaGroup chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }
  
  public SendMediaGroup chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }
  
  public SendMediaGroup disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendMediaGroup disableNotification() {
    this.disableNotification = true;
    return this;
  }
  
  public SendMediaGroup replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = Long.toString(message.getChat().getId());
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
  public Class<? extends Message[]> getReturnType(JsonElement response) {
    return Message[].class;
  }
  
  @Override
  public HttpEntity getHttpEntity() {
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    
    if (chatId != null)
      builder.addTextBody(CHAT_ID_FIELD, chatId);
    
    if (media != null) {
      builder.addTextBody(MEDIA_FIELD, TelegramObject.gson.toJson(media));
      for (InputMedia inputMedia : media) {
        inputMedia.getFileAttachName().ifPresent(name -> {
          String file = inputMedia.getFile().orElseThrow(NullPointerException::new);
          builder.addBinaryBody(name, new File(file));
        });
      }
    }
    
    if (disableNotification != null)
      builder.addTextBody(DISABLE_NOTIFICATION_FIELD, disableNotification.toString());
    
    if (replyToMessageId != null)
      builder.addTextBody(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString());
    
    return builder.build();
  }
  
}
