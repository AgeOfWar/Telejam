package io.github.ageofwar.telejam.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.connection.UploadFile;
import io.github.ageofwar.telejam.text.Text;

public class InputMediaAudio extends InputMedia {
  
  static final String DURATION_FIELD = "duration";
  static final String PERFORMER_FIELD = "performer";
  static final String TITLE_FIELD = "title";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "audio";
  
  /**
   * Duration of the audio in seconds.
   */
  @SerializedName(DURATION_FIELD)
  private Integer duration;
  
  /**
   * Performer of the audio.
   */
  @SerializedName(PERFORMER_FIELD)
  private String performer;
  
  /**
   * Title of the audio.
   */
  @SerializedName(TITLE_FIELD)
  private String title;
  
  
  /**
   * Constructs an input media audio.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   * @param duration  duration of the audio
   * @param performer performer of the audio
   * @param title     title of the audio
   */
  public InputMediaAudio(String media,
                         UploadFile thumbnail,
                         Text caption,
                         Integer duration,
                         String performer,
                         String title) {
    super(media, thumbnail, caption);
    this.duration = duration;
    this.performer = performer;
    this.title = title;
  }
  
  /**
   * Constructs an input media audio.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   * @param duration  duration of the audio
   * @param performer performer of the audio
   * @param title     title of the audio
   */
  public InputMediaAudio(UploadFile media,
                         UploadFile thumbnail,
                         Text caption,
                         Integer duration,
                         String performer,
                         String title) {
    super(media, thumbnail, caption);
    this.duration = duration;
    this.performer = performer;
    this.title = title;
  }
  
  /**
   * Constructs an input media audio.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   */
  public InputMediaAudio(String media, UploadFile thumbnail, Text caption) {
    super(media, thumbnail, caption);
  }
  
  /**
   * Constructs an input media audio.
   *
   * @param media     file to send
   * @param thumbnail thumbnail of the file sent
   * @param caption   caption of the file to be sent, 0-200 characters
   */
  public InputMediaAudio(UploadFile media, UploadFile thumbnail, Text caption) {
    super(media, thumbnail, caption);
  }
  
  
  /**
   * Getter for property {@link #duration}.
   *
   * @return optional value for property {@link #duration}
   */
  public Integer getDuration() {
    return duration;
  }
  
  /**
   * Getter for property {@link #performer}.
   *
   * @return optional value for property {@link #performer}
   */
  public String getPerformer() {
    return performer;
  }
  
  /**
   * Getter for property {@link #title}.
   *
   * @return optional value for property {@link #title}
   */
  public String getTitle() {
    return title;
  }
  
}
