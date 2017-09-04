package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.TextMessage;

/**
 * Represents an operation that accepts a command message and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface CommandHandler extends MessageHandler {

  /**
   * Performs this operation on the given command.
   *
   * @param command the command name
   * @param args    the command args
   * @param message the message
   */
  void accept(String command, String[] args, TextMessage message);

  @Override
  default void accept(Message message) {
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      if (textMessage.isCommand()) {
        String[] command = textMessage.getText().toString().split("\\s+", 2);
        String commandName = command[0].substring(1);
        String[] commandArgs = command.length == 1 ? new String[0] : command[1].split("\\s+");
        accept(commandName, commandArgs, textMessage);
      }
    }
  }

}
