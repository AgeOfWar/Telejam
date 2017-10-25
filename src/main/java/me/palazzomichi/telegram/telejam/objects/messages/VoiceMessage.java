package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.Voice;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.util.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * This object represents a voice message.
 *
 * @author Michi Palazzo
 */
public class VoiceMessage extends Message implements Captioned, Forwardable {

  static final String VOICE_FIELD = "voice";
  static final String CAPTION_FIELD = "caption";
  static final String CAPTION_ENTITIES = "caption_entities";

  /**
   * Information about the voice.
   */
  @SerializedName(VOICE_FIELD)
  private Voice voice;

  /**
   * Caption for the voice, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;
  
  /**
   * Special entities like usernames, URLs, bot
   * commands, etc. that appear in the caption.
   */
  @SerializedName(CAPTION_ENTITIES)
  private MessageEntity[] captionEntities;


  public VoiceMessage(long id,
                      User sender,
                      long date,
                      Chat chat,
                      Message replyToMessage,
                      Long editDate,
                      String authorSignature,
                      Voice voice,
                      String caption,
                      MessageEntity[] captionEntities) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.voice = Objects.requireNonNull(voice);
    this.caption = caption;
    this.captionEntities = captionEntities;
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
    if (caption == null) {
      return Optional.empty();
    }
    
    return Optional.of(
        new Text(caption, captionEntities == null ? new MessageEntity[0] : captionEntities)
    );
  }

}
