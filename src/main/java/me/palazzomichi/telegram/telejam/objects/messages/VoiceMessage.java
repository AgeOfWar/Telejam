package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.Voice;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

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


  public VoiceMessage(long id,
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
                      Voice voice,
                      String caption) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.voice = Objects.requireNonNull(voice);
    this.caption = caption;
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
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<String> getCaption() {
    return Optional.ofNullable(caption);
  }

}
