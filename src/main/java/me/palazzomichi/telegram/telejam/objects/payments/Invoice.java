package me.palazzomichi.telegram.telejam.objects.payments;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

import java.util.Objects;

/**
 * This object contains basic information about an invoice.
 *
 * @author Michi Palazzo
 */
public class Invoice implements TelegramObject {

  static final String TITLE_FIELD = "title";
  static final String DESCRIPTION_FIELD = "description";
  static final String START_PARAMETER_FIELD = "start_parameter";
  static final String CURRENCY_FIELD = "currency";
  static final String TOTAL_AMOUNT_FIELD = "total_amount";

  /**
   * Product name.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Product description.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;

  /**
   * Unique bot deep-linking parameter that can be used to generate this invoice.
   */
  @SerializedName(START_PARAMETER_FIELD)
  private String startParameter;

  /**
   * Three-letter ISO 4217 currency code.
   */
  @SerializedName(CURRENCY_FIELD)
  private String currency;

  /**
   * Total price in the smallest units of the currency.
   * <p> For example, for a price of <code>US$ 1.45</code> pass <code>amount = 145</code>.
   * See {@link Currency#getExponent()},
   * it shows the number of digits past the decimal point for each
   * currency (2 for the majority of currencies).</p>
   */
  @SerializedName(TOTAL_AMOUNT_FIELD)
  private int totalAmount;


  /**
   * Constructs an Invoice.
   *
   * @param title          product name
   * @param description    product description
   * @param startParameter unique bot deep-linking parameter
   *                       that can be used to generate this invoice
   * @param currency       the currency
   * @param totalAmount    total price in the smallest units of the currency
   *                       <p> For example, for a price of <code>US$ 1.45</code> pass
   *                       <code>amount = 145</code>. See {@link Currency#getExponent()},
   *                       it shows the number of digits past the decimal point for each
   *                       currency (2 for the majority of currencies).</p>
   */
  public Invoice(String title, String description, String startParameter, Currency currency, int totalAmount) {
    this.title = Objects.requireNonNull(title);
    this.description = Objects.requireNonNull(description);
    this.startParameter = Objects.requireNonNull(startParameter);
    this.currency = currency.getCode();
    this.totalAmount = totalAmount;
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
   * Getter for property {@link #description}.
   *
   * @return value for property {@link #description}
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for property {@link #startParameter}.
   *
   * @return value for property {@link #startParameter}
   */
  public String getStartParameter() {
    return startParameter;
  }

  /**
   * Returns the currency of the invoice.
   *
   * @return the currency of the invoice
   */
  public Currency getCurrency() {
    return Currency.fromCode(currency);
  }

  /**
   * Getter for property {@link #totalAmount}.
   *
   * @return value for property {@link #totalAmount}
   */
  public int getTotalAmount() {
    return totalAmount;
  }

}
