package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;

/**
 * This object represents a new chat member message.
 *
 * @author Michi Palazzo
 */
public class NewChatMemberMessage extends Message {

  static final String NEW_CHAT_MEMBER_FIELD = "new_chat_member";

  /**
   * New member that was added to the group or supergroup and
   * information about them (the bot itself may be one of these members).
   */
  @SerializedName(NEW_CHAT_MEMBER_FIELD)
  private User newChatMember;


  public NewChatMemberMessage(long id,
                              User sender,
                              long date,
                              Chat chat,
                              Message replyToMessage,
                              Long editDate,
                              String authorSignature,
                              User newChatMember) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.newChatMember = Objects.requireNonNull(newChatMember);
  }


  /**
   * Getter for property {@link #newChatMember}.
   *
   * @return value for property {@link #newChatMember}
   */
  public User getNewChatMembers() {
    return newChatMember;
  }

}
