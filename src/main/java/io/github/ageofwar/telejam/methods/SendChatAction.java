package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method when you need to tell the user that something is happening on the bot's side.
 * The status is set for 5 seconds or less (when a message arrives from your bot,
 * Telegram clients clear its typing status).
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SendChatAction implements TelegramMethod<Boolean> {

  public static final String NAME = "sendChatAction";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String ACTION_FIELD = "action";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * Type of action to broadcast.
   * Choose one, depending on what the user is about to receive:
   * <code>typing</code> for text messages,
   * <code>upload_photo</code> for photos,
   * <code>record_video</code> or <code>upload_video</code> for videos,
   * <code>record_audio</code> or <code>upload_audio</code> for audio files,
   * <code>upload_document</code> for general files,
   * <code>find_location</code> for location data,
   * <code>record_video_note</code> or <code>upload_video_note</code> for video notes.
   */
  private String action;
  
  
  public SendChatAction chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendChatAction chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendChatAction chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }

  public SendChatAction action(String action) {
    this.action = action;
    return this;
  }
  
  public SendChatAction typing() {
    action = "typing";
    return this;
  }
  
  public SendChatAction uploadPhoto() {
    action = "upload_photo";
    return this;
  }
  
  public SendChatAction recordVideo() {
    action = "record_video";
    return this;
  }
  
  public SendChatAction uploadVideo() {
    action = "upload_video";
    return this;
  }
  
  public SendChatAction recordAudio() {
    action = "record_audio";
    return this;
  }
  
  public SendChatAction uploadAudio() {
    action = "upload_audio";
    return this;
  }
  
  public SendChatAction uploadDocument() {
    action = "upload_document";
    return this;
  }
  
  public SendChatAction findLocation() {
    action = "find_location";
    return this;
  }
  
  public SendChatAction recordVideoNote() {
    action = "record_video_note";
    return this;
  }
  
  public SendChatAction uploadVideoNote() {
    action = "record_video_note";
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
        ACTION_FIELD, action
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
