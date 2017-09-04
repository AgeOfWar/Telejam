package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.VideoNote;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.util.Objects;

/**
 * This object represents a video note message.
 *
 * @author Michi Palazzo
 */
public class VideoNoteMessage extends Message {

  static final String VIDEO_NOTE_FIELD = "video_note";

  /**
   * Information about the video message.
   */
  @SerializedName(VIDEO_NOTE_FIELD)
  private VideoNote videoNote;


  public VideoNoteMessage(long id,
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
                          VideoNote videoNote) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.videoNote = Objects.requireNonNull(videoNote);
  }


  /**
   * Getter for property {@link #videoNote}.
   *
   * @return value for property {@link #videoNote}
   */
  public VideoNote getVideoNote() {
    return videoNote;
  }

}
