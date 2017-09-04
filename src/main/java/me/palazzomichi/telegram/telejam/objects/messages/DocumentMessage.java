package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Document;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a document message.
 *
 * @author Michi Palazzo
 */
public class DocumentMessage extends Message {

  static final String DOCUMENT_FIELD = "document";
  static final String CAPTION_FIELD = "caption";

  /**
   * Information about the file.
   */
  @SerializedName(DOCUMENT_FIELD)
  private Document document;

  /**
   * Caption for the file.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;


  public DocumentMessage(long id,
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
                         Document document,
                         String caption) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.document = Objects.requireNonNull(document);
    this.caption = caption;
  }


  /**
   * Getter for property {@link #document}.
   *
   * @return value for property {@link #document}
   */
  public Document getDocument() {
    return document;
  }

  /**
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<String> getCaption() {
    return Optional.ofNullable(caption);
  }

}
