package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.StickerSet;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * Use this method to set a new group sticker set for a supergroup.
 * The bot must be an administrator in the chat for this to work and must have
 * the appropriate admin rights. Use the field can_set_sticker_set optionally
 * returned in getChat requests to check if the bot can use this method.
 * Returns True on success.
 *
 * @author Michi Palazzo
 */
public class SetChatStickerSet extends JsonTelegramMethod<Boolean> {
  
  public static final String NAME = "setChatStickerSet";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String STICKER_SET_NAME_FIELD = "sticker_set_name";
  
  /**
   * Unique identifier for the target chat or username of
   * the target supergroup (in the format @supergroupusername).
   */
  @SerializedName(CHAT_ID_FIELD)
  private String chatId; // String or long
  
  /**
   * Name of the sticker set to be set as the group sticker set.
   */
  @SerializedName(STICKER_SET_NAME_FIELD)
  private String stickerSetName;
  
  
  public SetChatStickerSet chat(String chatId) {
    this.chatId = chatId;
    return this;
  }
  
  public SetChatStickerSet chat(long chatId) {
    this.chatId = Long.toString(chatId);
    return this;
  }
  
  public SetChatStickerSet chat(Chat chat) {
    this.chatId = Long.toString(chat.getId());
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
  public Class<? extends Boolean> getReturnType(JsonElement response) {
    return Boolean.class;
  }
  
}
