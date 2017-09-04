package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;

/**
 * This object represents a new chat members message.
 *
 * @author Michi Palazzo
 */
public class NewChatMembersMessage extends Message {

  static final String NEW_CHAT_MEMBERS_FIELD = "new_chat_members";

  /**
   * New members that were added to the group or supergroup and
   * information about them (the bot itself may be one of these members).
   */
  @SerializedName(NEW_CHAT_MEMBERS_FIELD)
  private User[] newChatMembers;


  public NewChatMembersMessage(long id,
                               User sender,
                               long date,
                               Chat chat,
                               User forwardMessageSender,
                               Chat forwardMessageChat,
                               Long forwardMessageId,
                               Long forwardMessageDate,
                               Message replyToMessage,
                               Long editDate,
                               String authorSignature,
                               String forwardSignature,
                               User[] newChatMembers) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.newChatMembers = Objects.requireNonNull(newChatMembers);
  }


  /**
   * Getter for property {@link #newChatMembers}.
   *
   * @return value for property {@link #newChatMembers}
   */
  public User[] getNewChatMembers() {
    return newChatMembers;
  }

}
