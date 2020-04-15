package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.messages.DiceMessage;
import io.github.ageofwar.telejam.messages.GameMessage;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.messages.Message;
import io.github.ageofwar.telejam.replymarkups.ReplyMarkup;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to send a dice, which will have a random value from 1 to 6.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendDice implements TelegramMethod<DiceMessage> {
  
  public static final String NAME = "sendDice";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Sends the message silently.
   * Users will receive a notification with no sound.
   */
  private Boolean disableNotification;
  
  /**
   * If the message is a reply, ID of the original message.
   */
  private Long replyToMessageId;
  
  /**
   * An inline keyboard. If empty, one ‘Play game_title’ button will be shown.
   * If not empty, the first button must launch the game.
   */
  private ReplyMarkup replyMarkup;
  
  
  public SendDice chat(Long chatId) {
    this.chatId = chatId;
    return this;
  }
  
  public SendDice chat(Chat chat) {
    chatId = chat.getId();
    return this;
  }
  
  public SendDice disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendDice disableNotification() {
    disableNotification = true;
    return this;
  }
  
  public SendDice replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }
  
  public SendDice replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    return this;
  }
  
  public SendDice replyMarkup(InlineKeyboardMarkup replyMarkup) {
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
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup
    );
  }
  
  @Override
  public Class<? extends DiceMessage> getReturnType() {
    return DiceMessage.class;
  }
  
}

