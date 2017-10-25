package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.payments.SuccessfulPayment;

import java.util.Objects;

/**
 * This object represents a successful payment message.
 *
 * @author Michi Palazzo
 */
public class SuccessfulPaymentMessage extends Message implements Forwardable {

  static final String SUCCESSFUL_PAYMENT_FIELD = "successful_payment";

  /**
   * Information about the payment.
   */
  @SerializedName(SUCCESSFUL_PAYMENT_FIELD)
  private SuccessfulPayment successfulPayment;


  public SuccessfulPaymentMessage(long id,
                                  User sender,
                                  long date,
                                  Chat chat,
                                  Message replyToMessage,
                                  Long editDate,
                                  String authorSignature,
                                  SuccessfulPayment successfulPayment) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
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
