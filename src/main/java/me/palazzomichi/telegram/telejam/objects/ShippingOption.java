package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This object represents one shipping option.
 *
 * @author Michi Palazzo
 */
public class ShippingOption implements TelegramObject {

  static final String ID_FIELD = "id";
  static final String TITLE_FIELD = "title";
  static final String PRICES_FIELD = "prices";

  /**
   * Shipping option identifier.
   */
  @SerializedName(ID_FIELD)
  private String id;

  /**
   * Option title.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * List of price portions.
   */
  @SerializedName(PRICES_FIELD)
  private LabeledPrice[] prices;


  /**
   * Constructs a ShippingOption.
   *
   * @param id     the shipping option identifier
   * @param title  the option title
   * @param prices the prices
   */
  public ShippingOption(String id, String title, LabeledPrice... prices) {
    this.id = Objects.requireNonNull(id);
    this.title = Objects.requireNonNull(title);
    this.prices = Objects.requireNonNull(prices);
  }


  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public String getId() {
    return id;
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
   * Getter for property {@link #prices}.
   *
   * @return value for property {@link #prices}
   */
  public LabeledPrice[] getPrices() {
    return prices;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof ShippingQuery)) return false;
    return id.equals(((ShippingQuery) obj).getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  
}
