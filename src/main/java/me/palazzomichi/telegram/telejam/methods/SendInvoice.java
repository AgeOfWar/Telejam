package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.InvoiceMessage;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.payments.Currency;
import me.palazzomichi.telegram.telejam.objects.payments.LabeledPrice;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

/**
 * Use this method to send invoices.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendInvoice extends JsonTelegramMethod<InvoiceMessage> {

  public static final String NAME = "sendInvoice";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String TITLE_FIELD = "title";
  static final String DESCRIPTION_FIELD = "description";
  static final String PAYLOAD_FIELD = "payload";
  static final String PROVIDER_TOKEN_FIELD = "provider_token";
  static final String START_PARAMETER_FIELD = "start_parameter";
  static final String CURRENCY_FIELD = "currency";
  static final String PRICES_FIELD = "prices";
  static final String PHOTO_URL_FIELD = "photo_url";
  static final String PHOTO_SIZE_FIELD = "photo_size";
  static final String PHOTO_WIDTH_FIELD = "photo_width";
  static final String PHOTO_HEIGHT_FIELD = "photo_height";
  static final String NEED_NAME_FIELD = "need_name";
  static final String NEED_PHONE_NUMBER_FIELD = "need_phone_number";
  static final String NEED_EMAIL_FIELD = "need_email";
  static final String NEED_SHIPPING_ADDRESS_FIELD = "need_shipping_address";
  static final String IS_FLEXIBLE_FIELD = "is_flexible";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";

  /**
   * Unique identifier for the target private chat.
   */
  @SerializedName(CHAT_ID_FIELD)
  private Long chatId;

  /**
   * Product name, 1-32 characters.
   */
  @SerializedName(TITLE_FIELD)
  private String title;

  /**
   * Product description, 1-255 characters.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;

  /**
   * Bot-defined invoice payload, 1-128 bytes.
   * This will not be displayed to the user, use for your internal processes.
   */
  @SerializedName(PAYLOAD_FIELD)
  private String payload;

  /**
   * Payments provider token, obtained via Botfather.
   */
  @SerializedName(PROVIDER_TOKEN_FIELD)
  private String providerToken;

  /**
   * Unique deep-linking parameter that can be used to
   * generate this invoice when used as a start parameter.
   */
  @SerializedName(START_PARAMETER_FIELD)
  private String startParameter;

  /**
   * The currency.
   */
  @SerializedName(CURRENCY_FIELD)
  private String currency;

  /**
   * Price breakdown, a list of components (e.g. product price,
   * tax, discount, delivery cost, delivery tax, bonus, etc.).
   */
  @SerializedName(PRICES_FIELD)
  private LabeledPrice[] prices;

  /**
   * URL of the product photo for the invoice.
   * Can be a photo of the goods or a marketing image for a service.
   * People like it better when they see what they are paying for.
   */
  @SerializedName(PHOTO_URL_FIELD)
  private String photoUrl;

  /**
   * Photo size.
   */
  @SerializedName(PHOTO_SIZE_FIELD)
  private Integer photoSize;

  /**
   * Photo width.
   */
  @SerializedName(PHOTO_WIDTH_FIELD)
  private Integer photoWidth;

  /**
   * Photo height.
   */
  @SerializedName(PHOTO_HEIGHT_FIELD)
  private Integer photoHeight;

  /**
   * Pass <code>true</code>, if you require the user's full name to complete the order.
   */
  @SerializedName(NEED_NAME_FIELD)
  private Boolean needName;

  /**
   * Pass <code>true</code>, if you require the user's phone number to complete the order.
   */
  @SerializedName(NEED_PHONE_NUMBER_FIELD)
  private Boolean needPhoneNumber;

  /**
   * Pass <code>true</code>, if you require the user's email to complete the order.
   */
  @SerializedName(NEED_EMAIL_FIELD)
  private Boolean needEmail;

  /**
   * Pass <code>true</code>, if you require the user's shipping address to complete the order.
   */
  @SerializedName(NEED_SHIPPING_ADDRESS_FIELD)
  private Boolean needShippingAddress;

  /**
   * Pass <code>true</code>, if the final price depends on the shipping method.
   */
  @SerializedName(IS_FLEXIBLE_FIELD)
  private Boolean isFlexible;

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  @SerializedName(DISABLE_NOTIFICATION_FIELD)
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  @SerializedName(REPLY_TO_MESSAGE_ID_FIELD)
  private Long replyToMessageId;

  /**
   * A JSON-serialized object for an inline keyboard.
   * If empty, one 'Pay total price' button will be shown.
   * If not empty, the first button must be a Pay button.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;


  public SendInvoice chat(Long chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendInvoice chat(Chat chat) {
    this.chatId = chat.getId();
    return this;
  }

  public SendInvoice title(String title) {
    this.title = title;
    return this;
  }

  public SendInvoice description(String description) {
    this.description = description;
    return this;
  }

  public SendInvoice payload(String payload) {
    this.payload = payload;
    return this;
  }

  public SendInvoice providerToken(String providerToken) {
    this.providerToken = providerToken;
    return this;
  }

  public SendInvoice startParameter(String startParameter) {
    this.startParameter = startParameter;
    return this;
  }

  public SendInvoice currency(String currency) {
    this.currency = currency;
    return this;
  }

  public SendInvoice currency(Currency currency) {
    this.currency = currency.getCode();
    return this;
  }

  public SendInvoice prices(LabeledPrice... prices) {
    this.prices = prices;
    return this;
  }

  public SendInvoice photoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
    return this;
  }

  public SendInvoice photoSize(Integer photoSize) {
    this.photoSize = photoSize;
    return this;
  }

  public SendInvoice photoWidth(Integer photoWidth) {
    this.photoWidth = photoWidth;
    return this;
  }

  public SendInvoice photoHeight(Integer photoHeight) {
    this.photoHeight = photoHeight;
    return this;
  }

  public SendInvoice needName(Boolean needName) {
    this.needName = needName;
    return this;
  }

  public SendInvoice needName() {
    this.needName = true;
    return this;
  }

  public SendInvoice needPhoneNumber(Boolean needPhoneNumber) {
    this.needPhoneNumber = needPhoneNumber;
    return this;
  }

  public SendInvoice needPhoneNumber() {
    this.needPhoneNumber = true;
    return this;
  }

  public SendInvoice needEmail(Boolean needEmail) {
    this.needEmail = needEmail;
    return this;
  }

  public SendInvoice needEmail() {
    this.needEmail = true;
    return this;
  }

  public SendInvoice needShippingAddress(Boolean needShippingAddress) {
    this.needShippingAddress = needShippingAddress;
    return this;
  }

  public SendInvoice needShippingAddress() {
    this.needShippingAddress = true;
    return this;
  }

  public SendInvoice isFlexible(Boolean isFlexible) {
    this.isFlexible = isFlexible;
    return this;
  }

  public SendInvoice isFlexible() {
    this.isFlexible = true;
    return this;
  }

  public SendInvoice disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendInvoice disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public SendInvoice replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendInvoice replyToMessage(Message message) {
    this.replyToMessageId = message.getId();
    this.chatId = message.getChat().getId();
    return this;
  }

  public SendInvoice setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends InvoiceMessage> getReturnType(JsonElement response) {
    return InvoiceMessage.class;
  }

}
