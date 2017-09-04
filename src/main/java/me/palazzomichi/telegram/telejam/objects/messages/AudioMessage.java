package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Audio;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

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


  public AudioMessage(long id,
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
                      Audio audio,
                      String caption) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.audio = Objects.requireNonNull(audio);
    this.caption = caption;
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
  public Optional<String> getCaption() {
    return Optional.ofNullable(caption);
  }

}
