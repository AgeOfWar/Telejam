package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.replymarkups.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Game.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultGame extends InlineQueryResult {

  static final String GAME_SHORT_NAME_FIELD = "game_short_name";
  static final String REPLY_MARKUP_FIELD = "reply_markup";

  @SerializedName(TYPE_FIELD)
  static final String TYPE = "game";

  /**
   * Short name of the game.
   */
  @SerializedName(GAME_SHORT_NAME_FIELD)
  private String gameShortName;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private InlineKeyboardMarkup replyMarkup;


  public InlineQueryResultGame(String id, String gameShortName, InlineKeyboardMarkup replyMarkup) {
    super(id);
    this.gameShortName = Objects.requireNonNull(gameShortName);
    this.replyMarkup = replyMarkup;
  }

  public InlineQueryResultGame(String id, String gameShortName) {
    this(id, gameShortName, null);
  }


  /**
   * Getter for property {@link #gameShortName}.
   *
   * @return value for property {@link #gameShortName}
   */
  public String getGameShortName() {
    return gameShortName;
  }

  /**
   * Setter for property {@link #gameShortName}.
   *
   * @param gameShortName value for property {@link #gameShortName}
   */
  public void setGameShortName(String gameShortName) {
    this.gameShortName = Objects.requireNonNull(gameShortName);
  }

  /**
   * Getter for property {@link #replyMarkup}.
   *
   * @return optional value for property {@link #replyMarkup}
   */
  public Optional<InlineKeyboardMarkup> getReplyMarkup() {
    return Optional.ofNullable(replyMarkup);
  }

  /**
   * Setter for property {@link #replyMarkup}.
   *
   * @param replyMarkup value for property {@link #replyMarkup}
   */
  public void setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
    this.replyMarkup = replyMarkup;
  }

}
