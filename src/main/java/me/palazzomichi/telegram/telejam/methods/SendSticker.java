package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

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
   * Sticker to send.
   */
  private String sticker;
  
  /**
   * Sticker not present in Telegram servers.
   */
  private UploadFile newSticker;
  
  
  public SendSticker chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendSticker chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendSticker chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }
  
  public SendSticker disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendSticker disableNotification() {
    disableNotification = true;
    return this;
  }
  
  public SendSticker replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    chatUsername = null;
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
  
  public SendSticker sticker(UploadFile newSticker) {
    sticker = null;
    this.newSticker = newSticker;
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
        STICKER_FIELD, sticker
    );
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(STICKER_FIELD, newSticker);
  }
  
  @Override
  public Class<? extends StickerMessage> getReturnType() {
    return StickerMessage.class;
  }
  
}
