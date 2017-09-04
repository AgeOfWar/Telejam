package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.messages.GameMessage;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

/**
 * Use this method to send a game.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendGame extends JsonTelegramMethod<GameMessage> {

  public static final String NAME = "sendGame";

  static final String CHAT_ID_FIELD = "chat_id";
  static final String GAME_SHORT_NAME_FIELD = "game_short_name";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";

  /**
   * Unique identifier for the target chat.
   */
  @SerializedName(CHAT_ID_FIELD)
  private Long chatId;

  /**
   * Short name of the game, serves as the unique identifier for the game.
   * Set up your games via Botfather.
   */
  @SerializedName(GAME_SHORT_NAME_FIELD)
  private String gameShortName;

  /**
   * Sends the message silently.
   * Users will receive a notification with no sound.
   */
  @SerializedName(DISABLE_NOTIFICATION_FIELD)
  private Boolean disableNotification;

  /**
   * If the message is a reply, ID of the original message.
   */
  @SerializedName(REPLY_TO_MESSAGE_ID_FIELD)
  private Long replyToMessageId;

  /**
   * An inline keyboard. If empty, one ‘Play game_title’ button will be shown.
   * If not empty, the first button must launch the game.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;


  public SendGame chat(Long chatId) {
    this.chatId = chatId;
    return this;
  }

  public SendGame chat(Chat chat) {
    this.chatId = chat.getId();
    return this;
  }

  public SendGame gameShortName(String gameShortName) {
    this.gameShortName = gameShortName;
    return this;
  }

  public SendGame disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }

  public SendGame disableNotification() {
    this.disableNotification = true;
    return this;
  }

  public SendGame setReplyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }

  public SendGame setReplyToMessage(Message replyToMessage) {
    this.replyToMessageId = replyToMessage.getId();
    return this;
  }

  public SendGame replyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends GameMessage> getReturnType(JsonElement response) {
    return GameMessage.class;
  }

}
