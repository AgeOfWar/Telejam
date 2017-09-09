package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
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
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String INPUT_MESSAGE_CONTENT_FIELD = "input_message_content";
  static final String THUMB_URL_FIELD = "thumb_url";
  static final String THUMB_WIDTH_FIELD = "thumb_width";
  static final String THUMB_HEIGHT_FIELD = "thumb_height";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "venue";

  /**
   * Latitude of the venue location in degrees.
   */
  @SerializedName(LATITUDE_FIELD)
  private float latitude;

  /**
   * Longitude of the venue location in degrees.
   */
  @SerializedName(LONGITUDE_FIELD)
  private float longitude;

  /**
   * Title of the venue.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Address of the venue.
   */
  @SerializedName(ADDRESS_FIELD)
  private String address;

  /**
   * Foursquare identifier of the venue if known.
   */
  @SerializedName(FOURSQUARE_ID_FIELD)
  private String foursquareId;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;

  /**
   * Content of the message to be sent instead of the venue.
   */
  @SerializedName(INPUT_MESSAGE_CONTENT_FIELD)
  private InputMessageContent inputMessageContent;

  /**
   * Url of the thumbnail for the result.
   */
  @SerializedName(THUMB_URL_FIELD)
  private String thumbUrl;

  /**
   * Thumbnail width.
   */
  @SerializedName(THUMB_WIDTH_FIELD)
  private Integer thumbWidth;

  /**
   * Thumbnail height.
   */
  @SerializedName(THUMB_HEIGHT_FIELD)
  private Integer thumbHeight;


  public InlineQueryResultVenue(String id,
                                float latitude,
                                float longitude,
                                String title,
                                String address,
                                String foursquareId,
                                InlineKeyboardMarkup replyMarkup,
                                InputMessageContent inputMessageContent,
                                String thumbUrl,
                                Integer thumbWidth,
                                Integer thumbHeight) {
    super(id);
    this.latitude = latitude;
    this.longitude = longitude;
    this.title = Objects.requireNonNull(title);
    this.address = Objects.requireNonNull(address);
    this.foursquareId = foursquareId;
    this.replyMarkup = replyMarkup;
    this.inputMessageContent = inputMessageContent;
    this.thumbUrl = thumbUrl;
    this.thumbWidth = thumbWidth;
    this.thumbHeight = thumbHeight;
  }

  public InlineQueryResultVenue(String id, float latitude, float longitude, String title, String address) {
    this(id, latitude, longitude, title, address, null, null, null, null, null, null);
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
   * Setter for property {@link #latitude}.
   *
   * @param latitude value for property {@link #latitude}
   */
  public void setLatitude(float latitude) {
    this.latitude = latitude;
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
   * Setter for property {@link #longitude}.
   *
   * @param longitude value for property {@link #longitude}
   */
  public void setLongitude(float longitude) {
    this.longitude = longitude;
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
   * Setter for property {@link #title}.
   *
   * @param title value for property {@link #title}
   */
  public void setTitle(String title) {
    this.title = Objects.requireNonNull(title);
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
   * Setter for property {@link #address}.
   *
   * @param address value for property {@link #address}
   */
  public void setAddress(String address) {
    this.address = Objects.requireNonNull(address);
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
   * Setter for property {@link #foursquareId}.
   *
   * @param foursquareId value for property {@link #foursquareId}
   */
  public void setFoursquareId(String foursquareId) {
    this.foursquareId = foursquareId;
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
   * Setter for property {@link #replyMarkup}.
   *
   * @param replyMarkup value for property {@link #replyMarkup}
   */
  public void setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
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
   * Setter for property {@link #inputMessageContent}.
   *
   * @param inputMessageContent value for property {@link #inputMessageContent}
   */
  public void setInputMessageContent(InputMessageContent inputMessageContent) {
    this.inputMessageContent = inputMessageContent;
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
   * Setter for property {@link #thumbUrl}.
   *
   * @param thumbUrl value for property {@link #thumbUrl}
   */
  public void setThumbUrl(String thumbUrl) {
    this.thumbUrl = thumbUrl;
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
   * Setter for property {@link #thumbWidth}.
   *
   * @param thumbWidth value for property {@link #thumbWidth}
   */
  public void setThumbWidth(Integer thumbWidth) {
    this.thumbWidth = thumbWidth;
  }

  /**
   * Getter for property {@link #thumbHeight}.
   *
   * @return optional value for property {@link #thumbHeight}
   */
  public OptionalInt getThumbHeight() {
    return thumbHeight == null ? OptionalInt.empty() : OptionalInt.of(thumbHeight);
  }

  /**
   * Setter for property {@link #thumbHeight}.
   *
   * @param thumbHeight value for property {@link #thumbHeight}
   */
  public void setThumbHeight(Integer thumbHeight) {
    this.thumbHeight = thumbHeight;
  }

}
