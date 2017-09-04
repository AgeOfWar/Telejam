package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.Video;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

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

  /**
   * Information about the video.
   */
  @SerializedName(VIDEO_FIELD)
  private Video video;

  /**
   * Caption for the video, 0-200 characters.
   */
  @SerializedName(CAPTION_FIELD)
  private String caption;


  public VideoMessage(long id,
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
                      Video video,
                      String caption) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.video = Objects.requireNonNull(video);
    this.caption = caption;
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
   * Getter for property {@link #caption}.
   *
   * @return optional value for property {@link #caption}
   */
  public Optional<String> getCaption() {
    return Optional.ofNullable(caption);
  }

}
