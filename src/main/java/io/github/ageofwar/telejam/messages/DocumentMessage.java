package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.media.Document;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;
import io.github.ageofwar.telejam.text.Text;

import java.util.List;
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
  static final String CAPTION_ENTITIES = "caption_entities";
  
  /**
   * Information about the file.
   */
  @SerializedName(DOCUMENT_FIELD)
  private final Document document;
  
  /**
   * Caption for the file.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;
  
  /**
   * Special entities like usernames, URLs, bot
   * commands, etc. that appear in the caption.
   */
  @SerializedName(CAPTION_ENTITIES)
  private final List<MessageEntity> captionEntities;
  
  
  public DocumentMessage(long id,
                         User sender,
                         long date,
                         Chat chat,
                         Message replyToMessage,
                         Long editDate,
                         String authorSignature,
                         Document document,
                         Text caption) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.document = Objects.requireNonNull(document);
    this.caption = caption != null ? caption.toString() : null;
    captionEntities = caption != null ? caption.getEntities() : null;
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
   * Returns the text of the caption.
   *
   * @return text of the optional caption
   */
  public Optional<Text> getCaption() {
    return Optional.ofNullable(caption).map(caption -> new Text(caption, captionEntities));
  }
  
}
