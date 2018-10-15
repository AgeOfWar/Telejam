package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.Update;
import me.palazzomichi.telegram.telejam.util.UpdateReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.LongUnaryOperator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static me.palazzomichi.telegram.telejam.json.Json.toJson;

/**
 * Runnable that receives and handles updates of a bot.
 *
 * @author Michi Palazzo
 */
public abstract class LongPollingBot extends TelegramBot implements Runnable, AutoCloseable {
  
  private final Logger logger;
  private final UpdateReader updateReader;
  
  private static Logger newLogger(Bot bot) {
    Logger logger = Logger.getLogger(bot.getUsername());
    logger.setUseParentHandlers(false);
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(new Formatter() {
      @Override
      public String format(LogRecord record) {
        String time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        String level = record.getLevel().getLocalizedName();
        String text = formatMessage(record);
        String message = "[" + time + "][" + level + "] " + text + System.lineSeparator();
        Throwable t = record.getThrown();
        if (t != null) {
          StringWriter stringWriter = new StringWriter();
          PrintWriter writer = new PrintWriter(stringWriter);
          writer.println();
          t.printStackTrace(writer);
          message += stringWriter.toString();
        }
        return message;
      }
    });
    logger.addHandler(handler);
    return logger;
  }
  
  /**
   * Constructs a long polling bot.
   *
   * @param bot     the bot used by the reader
   * @param backOff back off to be used when long polling fails
   * @param logger  optional logger
   */
  public LongPollingBot(Bot bot, LongUnaryOperator backOff, Logger logger) {
    super(bot);
    this.logger = Objects.requireNonNull(logger);
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
   * Constructs a bot thread with 500 milliseconds of back off.
   *
   * @param bot the bot used by the reader
   */
  public LongPollingBot(Bot bot) {
    this(bot, a -> 500L, newLogger(bot));
  }
  
  @Override
  public void run() {
    logger.info(() -> "Starting " + bot.getUsername() + "...");
    try {
      logger.info(() -> "Discarding previous updates...");
      updateReader.discardAll();
    } catch (IOException e) {
      logError(e);
      onError(e);
    }
    logger.info(() -> "Waiting for updates...");
    while (!Thread.interrupted()) {
      try {
        Update update = updateReader.read();
        logger.finer(() -> "New update: " + toJson(update));
        onUpdate(update);
      } catch (InterruptedException e) {
        logger.info(() -> "Stopping " + bot.getUsername() + "...");
        Thread.currentThread().interrupt();
      } catch (Throwable e) {
        logError(e);
        onError(e);
      }
    }
  }
  
  private void logError(Throwable t) {
    logger.warning(t.getLocalizedMessage());
    StackTraceElement[] stackTrace = t.getStackTrace();
    if (stackTrace.length > 0) {
      logger.throwing(stackTrace[0].getClassName(), stackTrace[0].getMethodName(), t);
    } else {
      logger.throwing("Unknown", "Unknown", t);
    }
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
