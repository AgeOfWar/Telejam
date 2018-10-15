package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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
  private final User leftChatMember;


  public LeftChatMemberMessage(long id,
                               User sender,
                               long date,
                               Chat chat,
                               User leftChatMember) {
    super(id, sender, date, chat, null, null, null);
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
