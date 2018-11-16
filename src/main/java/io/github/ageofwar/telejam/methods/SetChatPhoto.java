package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.connection.UploadFile;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to set a new profile photo for the chat.
 * Photos can't be changed for private chats.
 * The bot must be an administrator in the chat for this to
 * work and must have the appropriate admin rights.
 * Returns True on success.
 *
 * In regular groups (non-supergroups), this method will only work if
 * the ‘All Members Are Admins’ setting is off in the target group.
 *
 * @author Michi Palazzo
 */
public class SetChatPhoto implements TelegramMethod<Boolean> {

  public static final String NAME = "setChatPhoto";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String PHOTO_FIELD = "photo";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;

  /**
   * New chat photo.
   */
  private UploadFile newPhoto;
  
  
  public SetChatPhoto chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SetChatPhoto chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SetChatPhoto chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }
  
  public SetChatPhoto photo(UploadFile newPhoto) {
    this.newPhoto = newPhoto;
    return this;
  }
  
  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(CHAT_ID_FIELD, chatId != null ? chatId : chatUsername);
  }
  
  @Override
  public Map<String, UploadFile> getFiles() {
    return mapOf(PHOTO_FIELD, newPhoto);
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
