package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Audio;
import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.util.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a message.
 *
 * @author Michi Palazzo
 */
public class AudioMessage extends Message implements Captioned, Forwardable {

  static final String AUDIO_FIELD = "audio";
  static final String CAPTION_FIELD = "caption";
  static final String CAPTION_ENTITIES = "caption_entities";

  /**
   * Information about the audio.
   */
  @SerializedName(AUDIO_FIELD)
  private Audio audio;

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


  public AudioMessage(long id,
                      User sender,
                      long date,
                      Chat chat,
                      Message replyToMessage,
                      Long editDate,
                      String authorSignature,
                      Audio audio,
                      String caption,
                      MessageEntity[] captionEntities) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.audio = Objects.requireNonNull(audio);
    this.caption = caption;
    this.captionEntities = captionEntities;
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
