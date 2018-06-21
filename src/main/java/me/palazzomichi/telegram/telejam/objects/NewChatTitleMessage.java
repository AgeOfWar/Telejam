package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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
                             String newChatTitle) {
    super(id, sender, date, chat, null, null, null);
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
