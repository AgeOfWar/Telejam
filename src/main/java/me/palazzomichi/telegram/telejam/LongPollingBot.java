package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.util.UpdateReader;

import java.io.IOException;
import java.util.function.LongUnaryOperator;

/**
 * Runnable that receives and handles updates of a bot.
 *
 * @author Michi Palazzo
 */
public abstract class LongPollingBot extends TelegramBot implements Runnable, AutoCloseable {
  
  private final UpdateReader updateReader;
  
  /**
   * Constructs a long polling bot.
   *
   * @param bot     the bot used by the reader
   * @param backOff back off to be used when long polling fails
   */
  public LongPollingBot(Bot bot, LongUnaryOperator backOff) {
    super(bot);
    updateReader = new UpdateReader(bot, backOff);
  }
  
  /**
   * Constructs a bot thread with 500 milliseconds of back off.
   *
   * @param bot the bot used by the reader
   */
  public LongPollingBot(Bot bot) {
    this(bot, a -> 500L);
  }
  
  @Override
  public void run() {
    while (!Thread.interrupted()) {
      try {
        onUpdate(updateReader.read());
      } catch (InterruptedException e) {
        break;
      } catch (Throwable e) {
        onError(e);
      }
    }
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
