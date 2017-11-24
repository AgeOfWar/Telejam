package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.TextMessage;

import java.util.Arrays;

/**
 * Represents an operation that accepts a command message and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
@FunctionalInterface
public interface CommandHandler extends MessageHandler {
  
  /**
   * Performs this operation on the given command.
   *
   * @param command the command name
   * @param args    the command args
   * @param message the message
   * @throws Throwable if a throwable is thrown
   */
  void accept(String command, String[] args, TextMessage message) throws Throwable;
  
  @Override
  default void accept(Message message) throws Throwable {
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      String[] command = getArgs(textMessage);
      if (command.length > 0) {
        accept(command[0], Arrays.copyOfRange(command, 1, command.length), textMessage);
      }
    }
  }
  
  /**
   * Returns the array of args (the first arg is the command name) contained in a
   * message, or an empty array if the message is not a command.
   *
   * @param message the message
   * @return an array that contains the command name and args, or
   *         an empty array if the message is not a command
   */
  default String[] getArgs(TextMessage message) {
    if (!message.isCommand()) {
      return new String[0];
    }
    
    String[] command = message.getText().toString().split("\\s+");
    command[0] = command[0].substring(1);
    return command;
  }
  
}
