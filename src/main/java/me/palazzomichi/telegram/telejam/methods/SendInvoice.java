package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.*;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to send invoices.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendInvoice implements TelegramMethod<InvoiceMessage> {

  public static final String NAME = "sendInvoice";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String TITLE_FIELD = "title";
  static final String DESCRIPTION_FIELD = "description";
  static final String PAYLOAD_FIELD = "payload";
  static final String PROVIDER_TOKEN_FIELD = "provider_token";
  static final String START_PARAMETER_FIELD = "start_parameter";
  static final String CURRENCY_FIELD = "currency";
  static final String PRICES_FIELD = "prices";
  static final String PROVIDER_DATA_FIELD = "provider_data";
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
  private Long chatId;

  /**
   * Product name, 1-32 characters.
   */
  private String title;

  /**
   * Product description, 1-255 characters.
   */
  private String description;

  /**
   * Bot-defined invoice payload, 1-128 bytes.
   * This will not be displayed to the user, use for your internal processes.
   */
  private String payload;

  /**
   * Payments provider token, obtained via Botfather.
   */
  private String providerToken;

  /**
   * Unique deep-linking parameter that can be used to
   * generate this invoice when used as a start parameter.
   */
  private String startParameter;

  /**
   * The currency.
   */
  private String currency;

  /**
   * Price breakdown, a list of components (e.g. product price,
   * tax, discount, delivery cost, delivery tax, bonus, etc.).
   */
  private LabeledPrice[] prices;
  
  /**
   * Data about the invoice, which will be shared with the payment provider.
   * A detailed description of required fields should be provided by the payment provider.
   */
  private String providerData;

  /**
   * URL of the product photo for the invoice.
   * Can be a photo of the goods or a marketing image for a service.
   * People like it better when they see what they are paying for.
   */
  private String photoUrl;

  /**
   * Photo size.
   */
  private Integer photoSize;

  /**
   * Photo width.
   */
  private Integer photoWidth;

  /**
   * Photo height.
   */
  private Integer photoHeight;

  /**
   * Pass <code>true</code>, if you require the user's full name to complete the order.
   */
  private Boolean needName;

  /**
   * Pass <code>true</code>, if you require the user's phone number to complete the order.
   */
  private Boolean needPhoneNumber;

  /**
   * Pass <code>true</code>, if you require the user's email to complete the order.
   */
  private Boolean needEmail;

  /**
   * Pass <code>true</code>, if you require the user's shipping address to complete the order.
   */
  private Boolean needShippingAddress;

  /**
   * Pass <code>true</code>, if the final price depends on the shipping method.
   */
  private Boolean isFlexible;

  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  private Long replyToMessageId;

  /**
   * A JSON-serialized object for an inline keyboard.
   * If empty, one 'Pay total price' button will be shown.
   * If not empty, the first button must be a Pay button.
   */
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
  
  public SendInvoice providerData(String providerData) {
    this.providerData = providerData;
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

  public SendInvoice replyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        CHAT_ID_FIELD, chatId,
        TITLE_FIELD, title,
        DESCRIPTION_FIELD, description,
        PAYLOAD_FIELD, payload,
        PROVIDER_TOKEN_FIELD, providerToken,
        START_PARAMETER_FIELD, startParameter,
        CURRENCY_FIELD, currency,
        PRICES_FIELD, prices,
        PROVIDER_DATA_FIELD, providerData,
        PHOTO_URL_FIELD, photoUrl,
        PHOTO_SIZE_FIELD, photoSize,
        PHOTO_WIDTH_FIELD, photoWidth,
        PHOTO_HEIGHT_FIELD, photoHeight,
        NEED_NAME_FIELD, needName,
        NEED_PHONE_NUMBER_FIELD, needPhoneNumber,
        NEED_EMAIL_FIELD, needEmail,
        NEED_SHIPPING_ADDRESS_FIELD, needShippingAddress,
        IS_FLEXIBLE_FIELD, isFlexible,
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup
    );
  }
  
  @Override
  public Class<? extends InvoiceMessage> getReturnType() {
    return InvoiceMessage.class;
  }

}
