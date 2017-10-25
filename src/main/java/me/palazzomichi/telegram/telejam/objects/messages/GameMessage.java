package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.Game;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

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
  private Game game;


  public GameMessage(long id,
                     User sender,
                     long date,
                     Chat chat,
                     Message replyToMessage,
                     Long editDate,
                     String authorSignature,
                     Game game) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
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
