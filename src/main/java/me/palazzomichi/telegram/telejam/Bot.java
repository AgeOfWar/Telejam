package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.User;

import java.io.IOException;

/**
 * This object represents a Telegram long polling bot.
 *
 * @author Michi Palazzo
 */
public class Bot extends TelegramConnection {

  /**
   * Unique identifier for this bot.
   */
  private long id;

  /**
   * Bot’s first name.
   */
  private String name;

  /**
   * Bot’s username.
   */
  private String username;


  /**
   * Creates a new Telegram Bot.
   *
   * @param token the token used for the connection to the Telegram API
   * @throws IOException if an I/O Exception occurs during the bot creation
   */
  public Bot(String token) throws IOException {
    super(token);
    User thisBot = getMe();
    id = thisBot.getId();
    name = thisBot.getFirstName();
    username = thisBot.getUsername().orElseThrow(AssertionError::new);
  }


  /**
   * Returns this bot in a User object.
   *
   * @return this bot
   */
  public User toUser() {
    return new User(id, name, null, username, null, true);
  }

  /**
   * Getter for property {@link #id}.
   *
   * @return value for property {@link #id}
   */
  public long getId() {
    return id;
  }

  /**
   * Getter for property {@link #name}.
   * This method returns the name of the bot taken at its creation,
   * so it might be different from its actual name.
   *
   * @return value for property {@link #name}
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for property {@link #username}.
   *
   * @return value for property {@link #username}
   */
  public String getUsername() {
    return username;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Bot)) {
      return false;
    }
    
    Bot bot = (Bot) obj;
    return bot.getId() == id;
  }
  
  @Override
  public int hashCode() {
    return Long.hashCode(id);
  }
  
}
