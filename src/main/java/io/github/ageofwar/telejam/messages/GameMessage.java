package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.games.Game;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents a game message.
 *
 * @author Michi Palazzo
 */
public class GameMessage extends Message {
  
  static final String GAME_FIELD = "game";
  
  /**
   * Information about the game.
   */
  @SerializedName(GAME_FIELD)
  private final Game game;
  
  
  public GameMessage(long id,
                     User sender,
                     long date,
                     Chat chat,
                     Message replyToMessage,
                     Long editDate,
                     String authorSignature,
                     Game game,
                     InlineKeyboardMarkup replyMarkup) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature, replyMarkup);
    this.game = Objects.requireNonNull(game);
  }
  
  
  /**
   * Getter for property {@link #game}.
   *
   * @return value for property {@link #game}
   */
  public Game getGame() {
    return game;
  }
  
}
