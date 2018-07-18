package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.StickerSet;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to set a new group sticker set for a supergroup.
 * The bot must be an administrator in the chat for this to work and must have
 * the appropriate admin rights.
 *
 * @author Michi Palazzo
 */
public class SetChatStickerSet implements TelegramMethod<Boolean> {
  
  public static final String NAME = "setChatStickerSet";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String STICKER_SET_NAME_FIELD = "sticker_set_name";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * Name of the sticker set to be set as the group sticker set.
   */
  private String stickerSetName;
  
  
  public SetChatStickerSet chat(String chatUsername) {
    this.chatUsername = chatUsername;
    this.chatId = null;
    return this;
  }
  
  public SetChatStickerSet chat(Long chatId) {
    this.chatId = chatId;
    this.chatUsername = null;
    return this;
  }
  
  public SetChatStickerSet chat(Chat chat) {
    this.chatId = chat.getId();
    this.chatUsername = null;
    return this;
  }
  
  public SetChatStickerSet stickerSet(String stickerSetName) {
    this.stickerSetName = stickerSetName;
    return this;
  }
  
  public SetChatStickerSet stickerSet(StickerSet stickerSet) {
    this.stickerSetName = stickerSet.getName();
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
        STICKER_SET_NAME_FIELD, stickerSetName
    );
  }
  
  @Override
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
  }
  
}
