package me.palazzomichi.telegram.telejam.objects.payments;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.TelegramObject;

import java.util.Objects;
import java.util.Optional;

/**
 * This object contains basic information about a successful payment.
 *
 * @author Michi Palazzo
 */
public class SuccessfulPayment implements TelegramObject {

  static final String CURRENCY_FIELD = "currency";
  static final String TOTAL_AMOUNT_FIELD = "total_amount";
  static final String INVOICE_PAYLOAD_FIELD = "invoice_payload";
  static final String SHIPPING_OPTION_ID_FIELD = "shipping_option_id";
  static final String ORDER_INFO_FIELD = "order_info";
  static final String TELEGRAM_PAYMENT_CHARGE_ID_FIELD = "telegram_payment_charge_id";
  static final String PROVIDER_PAYMENT_CHARGE_ID_FIELD = "provider_payment_charge_id";

  /**
   * Three-letter ISO 4217 currency code.
   */
  @SerializedName(CURRENCY_FIELD)
  private String currency;

  /**
   * Total price in the smallest units of the currency.
   * <p> For example, for a price of <code>US$ 1.45</code> pass <code>amount = 145</code>.
   * See {@link Currency#getExponent()}, it shows the
   * number of digits past the decimal point for each
   * currency (2 for the majority of currencies).</p>
   */
  @SerializedName(TOTAL_AMOUNT_FIELD)
  private int totalAmount;

  /**
   * Bot specified invoice payload.
   */
  @SerializedName(INVOICE_PAYLOAD_FIELD)
  private String invoicePayloadField;

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

  /**
   * Telegram payment identifier.
   */
  @SerializedName(TELEGRAM_PAYMENT_CHARGE_ID_FIELD)
  private String telegramPaymentChargeId;

  /**
   * Provider payment identifier.
   */
  @SerializedName(PROVIDER_PAYMENT_CHARGE_ID_FIELD)
  private String providerPaymentChargeId;


  public SuccessfulPayment(String currency,
                           int totalAmount,
                           String invoicePayloadField,
                           String shippingOptionId,
                           OrderInfo orderInfo,
                           String telegramPaymentChargeId,
                           String providerPaymentChargeId) {
    this.currency = Objects.requireNonNull(currency);
    this.totalAmount = totalAmount;
    this.invoicePayloadField = Objects.requireNonNull(invoicePayloadField);
    this.shippingOptionId = shippingOptionId;
    this.orderInfo = orderInfo;
    this.telegramPaymentChargeId = Objects.requireNonNull(telegramPaymentChargeId);
    this.providerPaymentChargeId = Objects.requireNonNull(providerPaymentChargeId);
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
   * Getter for property {@link #invoicePayloadField}.
   *
   * @return value for property {@link #invoicePayloadField}
   */
  public String getInvoicePayloadField() {
    return invoicePayloadField;
  }

  /**
   * Getter for property {@link #shippingOptionId}.
   *
   * @return value for property {@link #shippingOptionId}
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

  /**
   * Getter for property {@link #telegramPaymentChargeId}.
   *
   * @return value for property {@link #telegramPaymentChargeId}
   */
  public String getTelegramPaymentChargeId() {
    return telegramPaymentChargeId;
  }

  /**
   * Getter for property {@link #providerPaymentChargeId}.
   *
   * @return value for property {@link #providerPaymentChargeId}
   */
  public String getProviderPaymentChargeId() {
    return providerPaymentChargeId;
  }

}
