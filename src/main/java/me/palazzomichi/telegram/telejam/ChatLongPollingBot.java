package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.updates.UpdateReader;

import java.io.IOException;
import java.util.function.LongUnaryOperator;
import java.util.logging.Logger;

import static me.palazzomichi.telegram.telejam.LongPollingBots.runLongPollingBot;
import static me.palazzomichi.telegram.telejam.loggers.Loggers.emptyLogger;
import static me.palazzomichi.telegram.telejam.loggers.Loggers.newLogger;

/**
 * Runnable that receives and handles updates of a bot received from
 * members of a chat, specified in the {@link #chatId} field.
 *
 * @author Michi Palazzo
 */
public abstract class ChatLongPollingBot extends ChatTelegramBot implements AutoCloseable {
  
  private final Logger logger;
  private final UpdateReader updateReader;
  
  /**
   * Constructs a long polling bot for a chat.
   *
   * @param bot     the bot used by the reader
   * @param chatId  the chat
   * @param backOff back off to be used when long polling fails
   * @param logger  optional logger
   */
  public ChatLongPollingBot(Bot bot, long chatId, LongUnaryOperator backOff, Logger logger) {
    super(bot, chatId);
    this.logger = logger != null ? logger : emptyLogger(bot);
    updateReader = new UpdateReader(bot, backOff);
  }
  
  /**
   * Constructs a long polling bot for a chat.
   *
   * @param bot    the bot used by the reader
   * @param chatId the chat
   * @param logger optional logger
   */
  public ChatLongPollingBot(Bot bot, long chatId, Logger logger) {
    this(bot, chatId, a -> 500, logger);
  }
  
  /**
   * Constructs a long polling bot for a chat.
   *
   * @param bot     the bot used by the reader
   * @param chatId  the chat
   * @param backOff back off to be used when long polling fails
   */
  public ChatLongPollingBot(Bot bot, long chatId, LongUnaryOperator backOff) {
    this(bot, chatId, backOff, newLogger(bot));
  }
  
  /**
   * Constructs a long polling bot for a chat with 500 milliseconds of back off.
   *
   * @param bot    the bot used by the reader
   * @param chatId the chat
   */
  public ChatLongPollingBot(Bot bot, long chatId) {
    this(bot, chatId, a -> 500L, newLogger(bot));
  }
  
  @Override
  public void run() {
    runLongPollingBot(updateReader, bot, this, logger);
  }
  
  @Override
  public void onError(Throwable t) {
  }
  
  @Override
  public void close() throws IOException {
    try {
      updateReader.close();
    } catch (IOException e) {
      throw new IOException("Unable to close LongPollingBot", e);
    }
  }
  
}
