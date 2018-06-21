package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;

/**
 * This object contains information about an incoming pre-checkout query.
 *
 * @author Michi Palazzo
 */
public class PreCheckoutQuery implements TelegramObject {

  static final String ID_FIELD = "id";
  static final String SENDER_FIELD = "from";
  static final String CURRENCY_FIELD = "currency";
  static final String TOTAL_AMOUNT_FIELD = "total_amount";
  static final String INVOICE_PAYLOAD_FIELD = "invoice_payload";
  static final String SHIPPING_OPTION_ID_FIELD = "shipping_option_id";
  static final String ORDER_INFO_FIELD = "order_info";

  /**
   * Unique query identifier.
   */
  @SerializedName(ID_FIELD)
  private String id;

  /**
   * User who sent the query.
   */
  @SerializedName(SENDER_FIELD)
  private User sender;

  /**
   * Three-letter ISO 4217 currency code.
   */
  @SerializedName(CURRENCY_FIELD)
  private String currency;

  /**
   * Total price in the smallest units of the currency.
   */
  @SerializedName(TOTAL_AMOUNT_FIELD)
  private int totalAmount;

  /**
   * Bot specified invoice payload.
   */
  @SerializedName(INVOICE_PAYLOAD_FIELD)
  private String invoicePayload;

  /**
   * Identifier of the shipping option chosen by the user.
   */
  @SerializedName(SHIPPING_OPTION_ID_FIELD)
  private String shippingOptionId;

  /**
   * Order info provided by the user.
   */
  @SerializedName(ORDER_INFO_FIELD)
  private OrderInfo orderInfo;


  public PreCheckoutQuery(String id,
                          User sender,
                          Currency currency,
                          int totalAmount,
                          String invoicePayload,
                          String shippingOptionId,
                          OrderInfo orderInfo) {
    this.id = Objects.requireNonNull(id);
    this.sender = Objects.requireNonNull(sender);
    this.currency = currency.getCode();
    this.totalAmount = totalAmount;
    this.invoicePayload = Objects.requireNonNull(invoicePayload);
    this.shippingOptionId = shippingOptionId;
    this.orderInfo = orderInfo;
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
   * Getter for property {@link #sender}.
   *
   * @return value for property {@link #sender}
   */
  public User getSender() {
    return sender;
  }

  /**
   * Getter for property {@link #currency}.
   *
   * @return value for property {@link #currency}
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

  /**
   * Getter for property {@link #invoicePayload}.
   *
   * @return value for property {@link #invoicePayload}
   */
  public String getInvoicePayload() {
    return invoicePayload;
  }

  /**
   * Getter for property {@link #shippingOptionId}.
   *
   * @return optional value for property {@link #shippingOptionId}
   */
  public Optional<String> getShippingOptionId() {
    return Optional.ofNullable(shippingOptionId);
  }

  /**
   * Getter for property {@link #orderInfo}.
   *
   * @return optional value for property {@link #orderInfo}
   */
  public Optional<OrderInfo> getOrderInfo() {
    return Optional.ofNullable(orderInfo);
  }

}
