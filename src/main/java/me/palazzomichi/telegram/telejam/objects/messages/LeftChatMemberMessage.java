package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public class LeftChatMemberMessage extends Message {

  static final String LEFT_CHAT_MEMBER_FIELD = "left_chat_member";

  /**
   * A member was removed from the group and
   * information about them (this member may be the bot itself).
   */
  @SerializedName(LEFT_CHAT_MEMBER_FIELD)
  private User leftChatMember;


  public LeftChatMemberMessage(long id,
                               User sender,
                               long date,
                               Chat chat,
                               Message replyToMessage,
                               Long editDate,
                               String authorSignature,
                               User leftChatMember) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.leftChatMember = Objects.requireNonNull(leftChatMember);
  }


  /**
   * Getter for property {@link #leftChatMember}.
   *
   * @return value for property {@link #leftChatMember}
   */
  public User getLeftChatMember() {
    return leftChatMember;
  }

}
