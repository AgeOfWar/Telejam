package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public class NewChatTitleMessage extends Message {

  static final String NEW_CHAT_TITLE_FIELD = "new_chat_title";

  /**
   * The message chat title was changed to this value.
   */
  @SerializedName(NEW_CHAT_TITLE_FIELD)
  private String newChatTitle;


  public NewChatTitleMessage(long id,
                             User sender,
                             long date,
                             Chat chat,
                             Message replyToMessage,
                             Long editDate,
                             String authorSignature,
                             String newChatTitle) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.newChatTitle = newChatTitle;
  }


  /**
   * Getter for property {@link #newChatTitle}.
   *
   * @return value for property {@link #newChatTitle}
   */
  public String getNewChatTitle() {
    return newChatTitle;
  }

}
