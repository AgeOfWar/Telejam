package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public class VideoMessage extends Message {

  static final String VIDEO_FIELD = "video";
  static final String CAPTION_FIELD = "caption";
  static final String CAPTION_ENTITIES = "caption_entities";

  /**
   * Information about the video.
   */
  @SerializedName(VIDEO_FIELD)
  private final Video video;

  /**
   * Caption for the video, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;
  
  /**
   * Special entities like usernames, URLs, bot
   * commands, etc. that appear in the caption.
   */
  @SerializedName(CAPTION_ENTITIES)
  private final List<MessageEntity> captionEntities;


  public VideoMessage(long id,
                      User sender,
                      long date,
                      Chat chat,
                      Message replyToMessage,
                      Long editDate,
                      String authorSignature,
                      Video video,
                      Text caption) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.video = Objects.requireNonNull(video);
    this.caption = caption != null ? caption.toString() : null;
    captionEntities = caption != null ? caption.getEntities() : null;
  }


  /**
   * Getter for property {@link #video}.
   *
   * @return value for property {@link #video}
   */
  public Video getVideo() {
    return video;
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
