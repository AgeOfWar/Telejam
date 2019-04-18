package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.messages.Message;
import io.github.ageofwar.telejam.messages.Poll;
import io.github.ageofwar.telejam.replymarkups.ReplyMarkup;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to stop a poll which was sent by the bot.
 * On success, the stopped Poll with the final results is returned.
 *
 * @author Michi Palazzo
 */
public class StopPoll implements TelegramMethod<Poll> {
  
  public static final String NAME = "stopPoll";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * Identifier of the original message with the poll
   */
  private Long messageId;
  
  /**
   * Additional interface options.
   */
  private ReplyMarkup replyMarkup;
  
  public StopPoll chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public StopPoll chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public StopPoll chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }
  
  public StopPoll message(Long messageId) {
    this.messageId = messageId;
    return this;
  }
  
  public StopPoll message(Message message) {
    chatId = message.getChat().getId();
    messageId = message.getId();
    chatUsername = null;
    return this;
  }
  
  public StopPoll replyMarkup(ReplyMarkup replyMarkup) {
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
        CHAT_ID_FIELD, chatId != null ? chatId : chatUsername,
        MESSAGE_ID_FIELD, messageId,
        REPLY_MARKUP_FIELD, replyMarkup
    );
  }
  
  @Override
  public Class<? extends Poll> getReturnType() {
    return Poll.class;
  }
}
