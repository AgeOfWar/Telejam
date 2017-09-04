package me.palazzomichi.telegram.telejam.objects.payments;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

import java.util.Objects;

/**
 * This object represents a shipping address.
 *
 * @author Michi Palazzo
 */
public class ShippingAddress implements TelegramObject {

  static final String COUNTRY_CODE_FIELD = "country_code";
  static final String STATE_FIELD = "state";
  static final String CITY_FIELD = "city";
  static final String STREET_LINE1_FIELD = "street_line1";
  static final String STREET_LINE2_FIELD = "street_line2";
  static final String POST_CODE_FIELD = "post_code";

  /**
   * ISO 3166-1 alpha-2 country code.
   */
  @SerializedName(COUNTRY_CODE_FIELD)
  private String countryCode;

  /**
   * State, if applicable.
   */
  @SerializedName(STATE_FIELD)
  private String stateField;

  /**
   * City.
   */
  @SerializedName(CITY_FIELD)
  private String city;

  /**
   * First line for the address.
   */
  @SerializedName(STREET_LINE1_FIELD)
  private String streetLine1;

  /**
   * Second line for the address.
   */
  @SerializedName(STREET_LINE2_FIELD)
  private String streetLine2;

  /**
   * Address post code.
   */
  @SerializedName(POST_CODE_FIELD)
  private String postCode;


  public ShippingAddress(String countryCode,
                         String stateField,
                         String city,
                         String streetLine1,
                         String streetLine2,
                         String postCode) {
    this.countryCode = Objects.requireNonNull(countryCode);
    this.stateField = Objects.requireNonNull(stateField);
    this.city = Objects.requireNonNull(city);
    this.streetLine1 = Objects.requireNonNull(streetLine1);
    this.streetLine2 = Objects.requireNonNull(streetLine2);
    this.postCode = Objects.requireNonNull(postCode);
  }


  /**
   * Getter for property {@link #countryCode}.
   *
   * @return value for property {@link #countryCode}
   */
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * Getter for property {@link #stateField}.
   *
   * @return value for property {@link #stateField}
   */
  public String getStateField() {
    return stateField;
  }

  /**
   * Getter for property {@link #city}.
   *
   * @return value for property {@link #city}
   */
  public String getCity() {
    return city;
  }

  /**
   * Getter for property {@link #streetLine1}.
   *
   * @return value for property {@link #streetLine1}
   */
  public String getStreetLine1() {
    return streetLine1;
  }

  /**
   * Getter for property {@link #streetLine2}.
   *
   * @return value for property {@link #streetLine2}
   */
  public String getStreetLine2() {
    return streetLine2;
  }

  /**
   * Getter for property {@link #postCode}.
   *
   * @return value for property {@link #postCode}
   */
  public String getPostCode() {
    return postCode;
  }

}
