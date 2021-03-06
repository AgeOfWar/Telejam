package io.github.ageofwar.telejam.inline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.messages.Venue;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a venue. By default, the venue will be sent by the user.
 * Alternatively, you can use {@link #inputMessageContent} to send a message
 * with the specified content instead of the venue.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultVenue extends InlineQueryResult {
  
  static final String LATITUDE_FIELD = "latitude";
  static final String LONGITUDE_FIELD = "longitude";
  static final String TITLE_FIELD = "title";
  static final String ADDRESS_FIELD = "address";
  static final String FOURSQUARE_ID_FIELD = "foursquare_id";
  static final String FOURSQUARE_TYPE_FIELD = "foursquare_type";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String THUMB_URL_FIELD = "thumb_url";
  static final String THUMB_WIDTH_FIELD = "thumb_width";
  static final String THUMB_HEIGHT_FIELD = "thumb_height";
  
  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "venue";
  
  /**
   * Latitude of the venue location in degrees.
   */
  @SerializedName(LATITUDE_FIELD)
  private final float latitude;
  
  /**
   * Longitude of the venue location in degrees.
   */
  @SerializedName(LONGITUDE_FIELD)
  private final float longitude;
  
  /**
   * Title of the venue.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;
  
  /**
   * Address of the venue.
   */
  @SerializedName(ADDRESS_FIELD)
  private final String address;
  
  /**
   * Foursquare identifier of the venue if known.
   */
  @SerializedName(FOURSQUARE_ID_FIELD)
  private final String foursquareId;
  
  /**
   * Foursquare type of the venue.
   */
  @SerializedName(FOURSQUARE_TYPE_FIELD)
  private final String foursquareType;
  
  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;
  
  /**
   * Content of the message to be sent instead of the venue.
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
  
  
  public InlineQueryResultVenue(String id,
                                Venue venue,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent,
                                String thumbUrl,
                                Integer thumbWidth,
                                Integer thumbHeight) {
    super(id);
    latitude = venue.getLocation().getLatitude();
    longitude = venue.getLocation().getLongitude();
    title = venue.getTitle();
    address = venue.getAddress();
    foursquareId = venue.getFoursquareId().orElse(null);
    foursquareType = venue.getFoursquareType().orElse(null);
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
    this.thumbUrl = thumbUrl;
    this.thumbWidth = thumbWidth;
    this.thumbHeight = thumbHeight;
  }
  
  public InlineQueryResultVenue(String id, Venue venue) {
    this(id, venue, null, null, null, null, null);
  }
  
  
  /**
   * Getter for property {@link #latitude}.
   *
   * @return value for property {@link #latitude}
   */
  public float getLatitude() {
    return latitude;
  }
  
  /**
   * Getter for property {@link #longitude}.
   *
   * @return value for property {@link #longitude}
   */
  public float getLongitude() {
    return longitude;
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
   * Getter for property {@link #address}.
   *
   * @return value for property {@link #address}
   */
  public String getAddress() {
    return address;
  }
  
  /**
   * Getter for property {@link #foursquareId}.
   *
   * @return optional value for property {@link #foursquareId}
   */
  public Optional<String> getFoursquareId() {
    return Optional.ofNullable(foursquareId);
  }
  
  /**
   * Getter for property {@link #foursquareType}.
   *
   * @return optional value for property {@link #foursquareType}
   */
  public Optional<String> getFoursquareType() {
    return Optional.ofNullable(foursquareType);
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
