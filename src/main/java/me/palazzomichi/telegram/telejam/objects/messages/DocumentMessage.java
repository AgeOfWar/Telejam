package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Document;
import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.util.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a document message.
 *
 * @author Michi Palazzo
 */
public class DocumentMessage extends Message implements Captioned, Forwardable {

  static final String DOCUMENT_FIELD = "document";
  static final String CAPTION_FIELD = "caption";
  static final String CAPTION_ENTITIES = "caption_entities";

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
  
  /**
   * Special entities like usernames, URLs, bot
   * commands, etc. that appear in the caption.
   */
  @SerializedName(CAPTION_ENTITIES)
  private MessageEntity[] captionEntities;
  

  public DocumentMessage(long id,
                         User sender,
                         long date,
                         Chat chat,
                         Message replyToMessage,
                         Long editDate,
                         String authorSignature,
                         Document document,
                         String caption,
                         MessageEntity[] captionEntities) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.document = Objects.requireNonNull(document);
    this.caption = caption;
    this.captionEntities = captionEntities;
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
    if (caption == null) {
      return Optional.empty();
    }
    
    return Optional.of(
        new Text(caption, captionEntities == null ? new MessageEntity[0] : captionEntities)
    );
  }

}
