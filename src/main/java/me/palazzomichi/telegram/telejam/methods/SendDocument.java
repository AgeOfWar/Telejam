package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.DocumentMessage;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.replymarkups.ReplyMarkup;
import me.palazzomichi.telegram.telejam.util.text.Text;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
   * File to send.
   */
  private String file;

  /**
   * Document caption (may also be used when resending photos by file_id), 0-200 characters.
   */
  private String caption;

  /**
   * True if the file is not present in Telegram servers.
   */
  private boolean newFile;


  public SendDocument chat(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendDocument chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }

  public SendDocument chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
    return this;
  }

  public SendDocument disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendDocument disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public SendDocument replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = Long.toString(message.getChat().getId());
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

  public SendDocument file(String file) {
    newFile = false;
    this.file = file;
    return this;
  }

  public SendDocument file(File file) {
    newFile = true;
    this.file = file.getPath();
    return this;
  }

  public SendDocument photo(Path file) {
    newFile = true;
    this.file = file.toString();
    return this;
  }

  public SendDocument caption(String caption) {
    this.caption = caption;
    return this;
  }

  public SendDocument caption(Text caption) {
    this.caption = caption.toString();
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends DocumentMessage> getReturnType(JsonElement response) {
    return DocumentMessage.class;
  }

  @Override
  public HttpEntity getHttpEntity() {
    if (newFile) {
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();

      if (chatId != null)
        builder.addTextBody(CHAT_ID_FIELD, chatId);

      if (disableNotification != null)
        builder.addTextBody(DISABLE_NOTIFICATION_FIELD, disableNotification.toString());

      if (replyToMessageId != null)
        builder.addTextBody(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString());

      if (replyMarkup != null)
        builder.addTextBody(REPLY_MARKUP_FIELD, replyMarkup.toString());

      if (file != null)
        builder.addBinaryBody(FILE_FIELD, new File(file));

      if (caption != null)
        builder.addTextBody(CAPTION_FIELD, caption);

      return builder.build();
    } else {
      List<NameValuePair> params = new ArrayList<>();

      if (chatId != null)
        params.add(
            new BasicNameValuePair(CHAT_ID_FIELD, chatId)
        );

      if(disableNotification != null)
        params.add(
            new BasicNameValuePair(DISABLE_NOTIFICATION_FIELD, disableNotification.toString())
        );

      if(replyToMessageId != null)
        params.add(
            new BasicNameValuePair(REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId.toString())
        );

      if(replyMarkup != null)
        params.add(
            new BasicNameValuePair(REPLY_MARKUP_FIELD, replyMarkup.toString())
        );

      if (file != null)
        params.add(
            new BasicNameValuePair(FILE_FIELD, file)
        );

      if (caption != null)
        params.add(
            new BasicNameValuePair(CAPTION_FIELD, caption)
        );

      return EntityBuilder.create()
          .setParameters(params)
          .build();
    }
  }

}
