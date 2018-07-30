package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a message.
 */
public class AnimationMessage extends Message {
  
  static final String ANIMATION_FIELD = "animation";
  static final String CAPTION_FIELD = "caption";
  static final String CAPTION_ENTITIES = "caption_entities";
  
  /**
   * Animation of the message.
   */
  @SerializedName(ANIMATION_FIELD)
  private Animation animation;
  
  /**
   * Caption for the video, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;
  
  /**
   * Special entities like usernames, URLs, bot
   * commands, etc. that appear in the caption.
   */
  @SerializedName(CAPTION_ENTITIES)
  private List<MessageEntity> captionEntities;
  
  
  public AnimationMessage(long id,
                          User sender,
                          long date,
                          Chat chat,
                          Message replyToMessage,
                          Long editDate,
                          String authorSignature,
                          Animation animation,
                          Text caption) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.animation = Objects.requireNonNull(animation);
    this.caption = caption != null ? caption.toString() : null;
    this.captionEntities = caption != null ? caption.getEntities() : null;
  }
  
  
  /**
   * Getter for property {@link #animation}.
   *
   * @return value for property {@link #animation}
   */
  public Animation getAnimation() {
    return animation;
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
