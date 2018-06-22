package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.Bot;
import me.palazzomichi.telegram.telejam.objects.TextMessage;

/**
 * Abstract class that handles command received from a bot.
 */
public abstract class CommandHandler {
  
  /**
   * Bot that receives command.
   */
  protected final Bot bot;
  
  public CommandHandler(Bot bot) {
    this.bot = bot;
  }
  
  /**
   * Handles a command received from the bot.
   *
   * @param command the command name
   * @param args    the command args
   * @param message the command message
   */
  public abstract void onCommand(String command, String[] args, TextMessage message) throws Throwable;
  
}
