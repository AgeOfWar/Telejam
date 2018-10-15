package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

  @Expose
  @SerializedName(TYPE_FIELD)
  static final String TYPE = "game";

  /**
   * Short name of the game.
   */
  @SerializedName(GAME_SHORT_NAME_FIELD)
  private final String gameShortName;

  /**
   * Inline keyboard attached to the message.
   */
  @SerializedName(REPLY_MARKUP_FIELD)
  private final InlineKeyboardMarkup replyMarkup;


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
   * Getter for property {@link #replyMarkup}.
   *
   * @return optional value for property {@link #replyMarkup}
   */
  public Optional<InlineKeyboardMarkup> getReplyMarkup() {
    return Optional.ofNullable(replyMarkup);
  }

}
