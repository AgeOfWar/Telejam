package io.github.ageofwar.telejam.loggers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.*;

/**
 * Utility class for {@link Logger}.
 */
public final class Loggers {
  
  /**
   * Returns the default logger.
   *
   * @return the default logger
   */
  public static Logger newLogger() {
    Logger logger = Logger.getAnonymousLogger();
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
   * Returns a logger for a bot that does nothing.
   *
   * @return an empty logger for the bot
   */
  public static Logger emptyLogger() {
    Logger logger = Logger.getAnonymousLogger();
    logger.setUseParentHandlers(false);
    return logger;
  }
  
  /**
   * Logs an error.
   *
   * @param logger the logger
   * @param t      the error
   */
  public static void logError(Logger logger, Throwable t) {
    logger.warning(t.getLocalizedMessage());
    StackTraceElement[] stackTrace = t.getStackTrace();
    if (stackTrace.length > 0) {
      logger.throwing(stackTrace[0].getClassName(), stackTrace[0].getMethodName(), t);
    } else {
      logger.throwing("Unknown", "Unknown", t);
    }
  }
  
  private Loggers() {
    throw new AssertionError();
  }
  
}
