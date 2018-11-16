package io.github.ageofwar.telejam.methods;

import io.github.ageofwar.telejam.games.GameHighScore;
import io.github.ageofwar.telejam.messages.Message;
import io.github.ageofwar.telejam.users.User;

import java.util.Map;

import static io.github.ageofwar.telejam.methods.Maps.mapOf;

/**
 * Use this method to get data for high score tables.
 * Will return the score of the specified user and several of
 * his neighbors in a game. On success, returns an Array of GameHighScore objects.
 *
 * <p>This method will currently return scores for the target user,
 * plus two of his closest neighbors on each side. Will also return
 * the top three users if the user and his neighbors are not among them.
 * Please note that this behavior is subject to change.</p>
 *
 * @author Michi Palazzo
 */
public class GetGameHighScores implements TelegramMethod<GameHighScore[]> {

  public static final String NAME = "getGameHighScores";

  static final String USER_ID_FIELD = "user_id";
  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";

  /**
   * Target user id.
   */
  private Long userId;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Unique identifier for the target chat.
   */
  private Long chatId;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Identifier of the sent message.
   */
  private Long messageId;

  /**
   * Required if {@link #chatId} and message_id are not specified.
   * Identifier of the inline message.
   */
  private String inlineMessageId;


  public GetGameHighScores user(Long userId) {
    this.userId = userId;
    return this;
  }

  public GetGameHighScores user(User user) {
    userId = user.getId();
    return this;
  }

  public GetGameHighScores message(Long messageId) {
    inlineMessageId = null;
    this.messageId = messageId;
    return this;
  }

  public GetGameHighScores message(Message message) {
    inlineMessageId = null;
    chatId = message.getChat().getId();
    messageId = message.getId();
    return this;
  }

  public GetGameHighScores inlineMessage(String inlineMessageId) {
    chatId = null;
    messageId = null;
    this.inlineMessageId = inlineMessageId;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        USER_ID_FIELD, userId,
        CHAT_ID_FIELD, chatId,
        MESSAGE_ID_FIELD, messageId,
        INLINE_MESSAGE_ID_FIELD, inlineMessageId
    );
  }
  
  @Override
  public Class<? extends GameHighScore[]> getReturnType() {
    return GameHighScore[].class;
  }

}
