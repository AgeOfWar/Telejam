package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a voice message.
 *
 * @author Michi Palazzo
 */
public class VoiceMessage extends Message {

  static final String VOICE_FIELD = "voice";
  static final String CAPTION_FIELD = "caption";
  static final String CAPTION_ENTITIES = "caption_entities";

  /**
   * Information about the voice.
   */
  @SerializedName(VOICE_FIELD)
  private final Voice voice;

  /**
   * Caption for the voice, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private final String caption;
  
  /**
   * Special entities like usernames, URLs, bot
   * commands, etc. that appear in the caption.
   */
  @SerializedName(CAPTION_ENTITIES)
  private final List<MessageEntity> captionEntities;


  public VoiceMessage(long id,
                      User sender,
                      long date,
                      Chat chat,
                      Message replyToMessage,
                      Long editDate,
                      String authorSignature,
                      Voice voice,
                      Text caption) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.voice = Objects.requireNonNull(voice);
    this.caption = caption != null ? caption.toString() : null;
    captionEntities = caption != null ? caption.getEntities() : null;
  }


  /**
   * Getter for property {@link #voice}.
   *
   * @return value for property {@link #voice}
   */
  public Voice getVoice() {
    return voice;
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
