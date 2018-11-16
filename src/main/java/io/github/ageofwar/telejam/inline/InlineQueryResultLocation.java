package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.locations.Location;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a location on a map. By default, the location will be sent by the user.
 * Alternatively, you can use {@link #inputMessageContent} to send a message with the
 * specified content instead of the location.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultLocation extends InlineQueryResult {
  
  static final String LATITUDE_FIELD = "latitude";
  static final String LONGITUDE_FIELD = "longitude";
  static final String TITLE_FIELD = "title";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String THUMB_URL_FIELD = "thumb_url";
  static final String THUMB_WIDTH_FIELD = "thumb_width";
  static final String THUMB_HEIGHT_FIELD = "thumb_height";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "location";
  
  /**
   * Location latitude in degrees.
   */
  @SerializedName(LATITUDE_FIELD)
  private final float latitude;
  
  /**
   * Location longitude in degrees.
   */
  @SerializedName(LONGITUDE_FIELD)
  private final float longitude;
  
  /**
   * Location title.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;
  
  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;
  
  /**
   * Content of the message to be sent instead of the location.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private final InputMessageContent inputMessageContent;
  
  /**
   * Url of the thumbnail for the result.
   */
  @SerializedName(THUMB_URL_FIELD)
  private final String thumbUrl;
  
  /**
   * Thumbnail width.
   */
  @SerializedName(THUMB_WIDTH_FIELD)
  private final Integer thumbWidth;
  
  /**
   * Thumbnail height.
   */
  @SerializedName(THUMB_HEIGHT_FIELD)
  private final Integer thumbHeight;
  
  
  public InlineQueryResultLocation(String id,
                                   Location location,
                                   String title,
                                   InlineKeyboardMarkup replyMarkup,
                                   InputMessageContent inputMessageContent,
                                   String thumbUrl,
                                   Integer thumbWidth,
                                   Integer thumbHeight) {
    super(id);
    latitude = location.getLatitude();
    longitude = location.getLongitude();
    this.title = Objects.requireNonNull(title);
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
    this.thumbUrl = thumbUrl;
    this.thumbWidth = thumbWidth;
    this.thumbHeight = thumbHeight;
  }
  
  public InlineQueryResultLocation(String id, Location location, String title) {
    this(id, location, title, null, null, null, null, null);
  }
  
  /**
   * Returns the location of the inline query result location.
   *
   * @return the location of the inline query result location
   */
  public Location getLocation() {
    return new Location(latitude, longitude);
  }
  
  /**
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * Getter for property {@link #replyMarkup}.
   *
   * @return optional value for property {@link #replyMarkup}
   */
  public Optional<InlineKeyboardMarkup> getReplyMarkup() {
    return Optional.ofNullable(replyMarkup);
  }
  
  /**
   * Getter for property {@link #inputMessageContent}.
   *
   * @return optional value for property {@link #inputMessageContent}
   */
  public Optional<InputMessageContent> getInputMessageContent() {
    return Optional.ofNullable(inputMessageContent);
  }
  
  /**
   * Getter for property {@link #thumbUrl}.
   *
   * @return optional value for property {@link #thumbUrl}
   */
  public Optional<String> getThumbUrl() {
    return Optional.ofNullable(thumbUrl);
  }
  
  /**
   * Getter for property {@link #thumbWidth}.
   *
   * @return optional value for property {@link #thumbWidth}
   */
  public OptionalInt getThumbWidth() {
    return thumbWidth == null ? OptionalInt.empty() : OptionalInt.of(thumbWidth);
  }
  
  /**
   * Getter for property {@link #thumbHeight}.
   *
   * @return optional value for property {@link #thumbHeight}
   */
  public OptionalInt getThumbHeight() {
    return thumbHeight == null ? OptionalInt.empty() : OptionalInt.of(thumbHeight);
  }
  
}
