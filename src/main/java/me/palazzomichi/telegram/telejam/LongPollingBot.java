package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.updates.UpdateReader;

import java.io.IOException;
import java.util.function.LongUnaryOperator;
import java.util.logging.Logger;

import static me.palazzomichi.telegram.telejam.LongPollingBots.runLongPollingBot;
import static me.palazzomichi.telegram.telejam.loggers.Loggers.emptyLogger;
import static me.palazzomichi.telegram.telejam.loggers.Loggers.newLogger;

/**
 * Runnable that receives and handles updates of a bot.
 *
 * @author Michi Palazzo
 */
public abstract class LongPollingBot extends TelegramBot implements AutoCloseable {
  
  private final Logger logger;
  private final UpdateReader updateReader;
  
  /**
   * Constructs a long polling bot.
   *
   * @param bot     the bot used by the reader
   * @param backOff back off to be used when long polling fails
   * @param logger  optional logger
   */
  public LongPollingBot(Bot bot, LongUnaryOperator backOff, Logger logger) {
    super(bot);
    this.logger = logger != null ? logger : emptyLogger(bot);
    updateReader = new UpdateReader(bot, backOff);
  }
  
  /**
   * Constructs a long polling bot.
   *
   * @param bot    the bot used by the reader
   * @param logger optional logger
   */
  public LongPollingBot(Bot bot, Logger logger) {
    this(bot, a -> 500, logger);
  }
  
  /**
   * Constructs a long polling bot.
   *
   * @param bot     the bot used by the reader
   * @param backOff back off to be used when long polling fails
   */
  public LongPollingBot(Bot bot, LongUnaryOperator backOff) {
    this(bot, backOff, newLogger(bot));
  }
  
  /**
   * Constructs a long polling bot with 500 milliseconds of back off.
   *
   * @param bot the bot used by the reader
   */
  public LongPollingBot(Bot bot) {
    this(bot, a -> 500L, newLogger(bot));
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
