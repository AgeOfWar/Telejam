package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.messages.Message;
import io.github.ageofwar.telejam.messages.PollMessage;
import io.github.ageofwar.telejam.replymarkups.ReplyMarkup;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to send a native poll.
 * A native poll can't be sent to a private chat.
 * On success, the sent Message is returned.
 *
 * @author Michi Palazzo
 */
public class SendPoll implements TelegramMethod<PollMessage> {
  
  public static final String NAME = "sendPoll";
  
  static final String CHAT_ID_FIELD = "chat_id";
  static final String QUESTION_FIELD = "question";
  static final String OPTIONS_FIELD = "options";
  static final String DISABLE_NOTIFICATION_FIELD = "disable_notification";
  static final String REPLY_TO_MESSAGE_ID_FIELD = "reply_to_message_id";
  static final String REPLY_MARKUP_FIELD = "reply_markup";
  static final String IS_ANONYMOUS_FIELD = "is_anonymous";
  static final String TYPE_FIELD = "type";
  static final String ALLOWS_MULTIPLE_ANSWERS_FIELD = "allows_multiple_answers";
  static final String CORRECT_OPTION_ID_FIELD = "correct_option_id";
  static final String IS_CLOSED_FIELD = "is_closed";
  
  /**
   * Unique identifier for the target chat.
   */
  private Long chatId;
  
  /**
   * Username of the target channel (in the format @channelusername).
   */
  private String chatUsername;
  
  /**
   * Poll question, 1-255 characters.
   */
  private String question;
  
  /**
   * List of answer options, 2-10 strings 1-100 characters each.
   */
  private String[] options;
  
  /**
   * Sends the message silently. Users will receive a notification with no sound.
   */
  private Boolean disableNotification;
  
  /**
   * If the message is a reply, ID of the original message.
   */
  private Long replyToMessageId;
  
  /**
   * Additional interface options.
   */
  private ReplyMarkup replyMarkup;
  
  /**
   * Whether or not the poll needs to be immediately closed.
   */
  private Boolean isAnonymous;
  
  /**
   * Poll type, "quiz" or "regular", defaults to "regular".
   */
  private String type;
  
  /**
   * Whether or not the poll allows multiple answers, ignored for polls in quiz mode, defaults to False.
   */
  private Boolean allowsMultipleAnswers;
  
  /**
   * 0-based identifier of the correct answer option, required for polls in quiz mode.
   */
  private Integer correctOptionId;
  
  /**
   * Whether or not the poll needs to be immediately closed.
   */
  private Boolean isClosed;
  
  public SendPoll chat(String chatUsername) {
    this.chatUsername = chatUsername;
    chatId = null;
    return this;
  }
  
  public SendPoll chat(Long chatId) {
    this.chatId = chatId;
    chatUsername = null;
    return this;
  }
  
  public SendPoll chat(Chat chat) {
    chatId = chat.getId();
    chatUsername = null;
    return this;
  }
  
  public SendPoll disableNotification(Boolean disableNotification) {
    this.disableNotification = disableNotification;
    return this;
  }
  
  public SendPoll disableNotification() {
    disableNotification = true;
    return this;
  }
  
  public SendPoll anonymous(Boolean isAnonymous) {
    this.isAnonymous = isAnonymous;
    return this;
  }
  
  public SendPoll anonymous() {
    isAnonymous = true;
    return this;
  }
  
  public SendPoll quiz(int correctOptionId) {
    type = "quiz";
    this.correctOptionId = correctOptionId;
    return this;
  }
  
  public SendPoll regular() {
    type = "regular";
    correctOptionId = null;
    return this;
  }
  
  public SendPoll allowsMultipleAnswers(Boolean allowsMultipleAnswers) {
    this.allowsMultipleAnswers = allowsMultipleAnswers;
    return this;
  }
  
  public SendPoll allowsMultipleAnswers() {
    allowsMultipleAnswers = true;
    return this;
  }
  
  public SendPoll isClosed(Boolean isClosed) {
    this.isClosed = isClosed;
    return this;
  }
  
  public SendPoll isClosed() {
    isClosed = true;
    return this;
  }
  
  public SendPoll replyToMessage(Message message) {
    replyToMessageId = message.getId();
    chatId = message.getChat().getId();
    chatUsername = null;
    return this;
  }
  
  public SendPoll replyToMessage(Long replyToMessageId) {
    this.replyToMessageId = replyToMessageId;
    return this;
  }
  
  public SendPoll replyMarkup(ReplyMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
    return this;
  }
  
  public SendPoll question(String question) {
    this.question = question;
    return this;
  }
  
  public SendPoll options(String... options) {
    this.options = options;
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
        DISABLE_NOTIFICATION_FIELD, disableNotification,
        REPLY_TO_MESSAGE_ID_FIELD, replyToMessageId,
        REPLY_MARKUP_FIELD, replyMarkup,
        QUESTION_FIELD, question,
        OPTIONS_FIELD, options,
        IS_ANONYMOUS_FIELD, isAnonymous,
        TYPE_FIELD, type,
        ALLOWS_MULTIPLE_ANSWERS_FIELD, allowsMultipleAnswers,
        CORRECT_OPTION_ID_FIELD, correctOptionId,
        IS_CLOSED_FIELD, isClosed
    );
  }
  
  @Override
  public Class<? extends PollMessage> getReturnType() {
    return PollMessage.class;
  }
  
}
