package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.Chat;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to export an invite link to a supergroup or a channel.
 * The bot must be an administrator in the chat for this to work and must
 * have the appropriate admin rights.
 * Returns exported invite link as String on success.
 *
 * @author Michi Palazzo
 */
public class ExportChatInviteLink implements TelegramMethod<String> {

  public static final String NAME = "exportChatInviteLink";

  static final String CHAT_ID_FIELD = "chat_id";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  
  public ExportChatInviteLink chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public ExportChatInviteLink chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public ExportChatInviteLink chat(Chat chat) {
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
  public Class<String> getReturnType() {
    return String.class;
  }

}
