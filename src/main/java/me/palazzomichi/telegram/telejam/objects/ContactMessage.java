package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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
                        Message replyToMessage,
                        String authorSignature,
                        Contact contact) {
    super(id, sender, date, chat, replyToMessage, null, authorSignature);
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
