package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.GameHighScore;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.messages.Message;

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
public class GetGameHighScores extends JsonTelegramMethod<GameHighScore[]> {

  public static final String NAME = "getGameHighScores";

  static final String USER_ID_FIELD = "user_id";
  static final String CHAT_ID_FIELD = "chat_id";
  static final String MESSAGE_ID_FIELD = "message_id";
  static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";

  /**
   * Target user id.
   */
  @SerializedName(USER_ID_FIELD)
  private Long userId;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Unique identifier for the target chat.
   */
  @SerializedName(CHAT_ID_FIELD)
  private Long chatId;

  /**
   * Required if {@link #inlineMessageId} is not specified.
   * Identifier of the sent message.
   */
  @SerializedName(MESSAGE_ID_FIELD)
  private Long messageId;

  /**
   * Required if {@link #chatId} and message_id are not specified.
   * Identifier of the inline message.
   */
  @SerializedName(INLINE_MESSAGE_ID_FIELD)
  private String inlineMessageId;


  public GetGameHighScores user(Long userId) {
    this.userId = userId;
    return this;
  }

  public GetGameHighScores user(User user) {
    this.userId = user.getId();
    return this;
  }

  public GetGameHighScores message(Long messageId) {
    inlineMessageId = null;
    this.messageId = messageId;
    return this;
  }

  public GetGameHighScores message(Message message) {
    inlineMessageId = null;
    this.chatId = message.getChat().getId();
    this.messageId = message.getId();
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
  public Class<? extends GameHighScore[]> getReturnType(JsonElement response) {
    return GameHighScore[].class;
  }

}
