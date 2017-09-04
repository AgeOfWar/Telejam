package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Contact;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;

/**
 * This object represents a contact message.
 *
 * @author Michi Palazzo
 */
public class ContactMessage extends Message {

  static final String CONTACT_FIELD = "contact";

  /**
   * Information about the contact.
   */
  @SerializedName(CONTACT_FIELD)
  private Contact contact;

  public ContactMessage(long id,
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
                        Contact contact) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.contact = Objects.requireNonNull(contact);
  }

  /**
   * Getter for property {@link #contact}.
   *
   * @return value for property {@link #contact}
   */
  public Contact getContact() {
    return contact;
  }

}
