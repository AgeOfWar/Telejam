package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to delete a group sticker set from a supergroup.
 * The bot must be an administrator in the chat for this to work and must
 * have the appropriate admin rights.
 *
 * @author Michi Palazzo
 */
public class DeleteChatStickerSet implements TelegramMethod<Boolean> {
  
  public static final String NAME = "deleteChatStickerSet";
  
  static final String CHAT_ID_FIELD = "chat_id";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  
  public DeleteChatStickerSet chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public DeleteChatStickerSet chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public DeleteChatStickerSet chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
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
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }
  
}
