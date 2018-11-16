package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.media.Audio;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;
import io.github.ageofwar.telejam.text.Text;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public class AudioMessage extends Message {
  
  static final String AUDIO_FIELD = "audio";
  static final String CAPTION_FIELD = "caption";
  static final String CAPTION_ENTITIES = "caption_entities";
  
  /**
   * Information about the audio.
   */
  @SerializedName(AUDIO_FIELD)
  private final Audio audio;
  
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
  
  
  public AudioMessage(long id,
                      User sender,
                      long date,
                      Chat chat,
                      Message replyToMessage,
                      Long editDate,
                      String authorSignature,
                      Audio audio,
                      Text caption) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.audio = Objects.requireNonNull(audio);
    this.caption = caption != null ? caption.toString() : null;
    captionEntities = caption != null ? caption.getEntities() : null;
  }
  
  
  /**
   * Getter for property {@link #audio}.
   *
   * @return value for property {@link #audio}
   */
  public Audio getAudio() {
    return audio;
  }
  
  /**
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<Text> getCaption() {
    return Optional.ofNullable(caption).map(caption -> new Text(caption, captionEntities));
  }
  
}
