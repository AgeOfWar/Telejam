package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.StickerMessage;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Use this method to send .webp stickers.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendSticker implements TelegramMethod<StickerMessage> {
  
  public static final String NAME = "sendSticker";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String STICKER_FIELD = "sticker";
  
  /**
   * Unique identifier for the target chat or username of
   * the target channel (in the format <code>@channelusername</code>)
   */
  private String chatId; // String or long
  
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
   * Sticker to send.
   */
  private String sticker;
  
  /**
   * Sticker not present in Telegram servers.
   */
  private InputStream newSticker;
  
  /**
   * Name of {@link #newSticker}.
   */
  private String fileName;
  
  
  public SendSticker chat(String chatId) {
    this.chatId = chatId;
    return this;
  }
  
  public SendSticker chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }
  
  public SendSticker chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }
  
  public SendSticker disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendSticker disableNotification() {
    this.disableNotification = true;
    return this;
  }
  
  public SendSticker replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = Long.toString(message.getChat().getId());
    return this;
  }
  
  public SendSticker replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }
  
  public SendSticker replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }
  
  public SendSticker sticker(String sticker) {
    newSticker = null;
    this.sticker = sticker;
    return this;
  }
  
  public SendSticker sticker(InputStream newSticker, String name) {
    sticker = null;
    this.newSticker = newSticker;
    this.fileName = name;
    return this;
  }
  
  public SendSticker sticker(File newSticker) throws FileNotFoundException {
    sticker = null;
    this.newSticker = new FileInputStream(newSticker);
    this.fileName = newSticker.getName();
    return this;
  }
  
  public SendSticker sticker(File newSticker, String name) throws FileNotFoundException {
    sticker = null;
    this.newSticker = new FileInputStream(newSticker);
    this.fileName = name;
    return this;
  }
  
  public SendSticker sticker(Path newSticker, String name) throws IOException {
    sticker = null;
    this.newSticker = Files.newInputStream(newSticker);
    this.fileName = name;
    return this;
  }
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Class<? extends StickerMessage> getReturnType(JsonElement response) {
    return StickerMessage.class;
  }
  
  @Override
  public HttpEntity getHttpEntity() {
    if (newSticker != null) {
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      
      if (chatId != null)
        builder.addTextBody(CHAT_ID_FIELD, chatId);
      
      if (disableNotification != null)
        builder.addTextBody(DISABLE_NOTIFICATION_FIELD, disableNotification.toString());
      
      if (replyToMessageId != null)
        builder.addTextBody(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString());
      
      if (replyMarkup != null)
        builder.addTextBody(REPLY_MARKUP_FIELD, replyMarkup.toString());
      
      builder.addBinaryBody(STICKER_FIELD, newSticker, ContentType.APPLICATION_OCTET_STREAM, fileName);
      
      return builder.build();
    } else {
      List<NameValuePair> params = new ArrayList<>();
      
      if (chatId != null)
        params.add(
            new BasicNameValuePair(CHAT_ID_FIELD, chatId)
        );
      
      if (disableNotification != null)
        params.add(
            new BasicNameValuePair(DISABLE_NOTIFICATION_FIELD, disableNotification.toString())
        );
      
      if (replyToMessageId != null)
        params.add(
            new BasicNameValuePair(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString())
        );
      
      if (replyMarkup != null)
        params.add(
            new BasicNameValuePair(REPLY_MARKUP_FIELD, replyMarkup.toString())
        );
      
      if (sticker != null)
        params.add(
            new BasicNameValuePair(STICKER_FIELD, sticker)
        );
      
      return EntityBuilder.create()
          .setParameters(params)
          .build();
    }
  }
  
}
