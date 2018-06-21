package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

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
                          Message replyToMessage,
                          String authorSignature,
                          VideoNote videoNote) {
    super(id, sender, date, chat, replyToMessage, null, authorSignature);
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
