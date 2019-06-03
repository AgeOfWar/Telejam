package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.payments.SuccessfulPayment;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents a successful payment message.
 *
 * @author Michi Palazzo
 */
public class SuccessfulPaymentMessage extends Message {
  
  static final String SUCCESSFUL_PAYMENT_FIELD = "successful_payment";
  
  /**
   * Information about the payment.
   */
  @SerializedName(SUCCESSFUL_PAYMENT_FIELD)
  private final SuccessfulPayment successfulPayment;
  
  
  public SuccessfulPaymentMessage(long id,
                                  User sender,
                                  long date,
                                  Chat chat,
                                  Message replyToMessage,
                                  Long editDate,
                                  String authorSignature,
                                  SuccessfulPayment successfulPayment,
                                  InlineKeyboardMarkup replyMarkup) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature, replyMarkup);
    this.successfulPayment = Objects.requireNonNull(successfulPayment);
  }
  
  
  /**
   * Getter for property {@link #successfulPayment}.
   *
   * @return value for property {@link #successfulPayment}
   */
  public SuccessfulPayment getSuccessfulPayment() {
    return successfulPayment;
  }
  
}
