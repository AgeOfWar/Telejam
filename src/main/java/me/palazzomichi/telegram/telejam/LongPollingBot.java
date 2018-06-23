package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.Update;
import me.palazzomichi.telegram.telejam.util.UpdateReader;

import java.util.function.LongUnaryOperator;

/**
 * Runnable that receives and handles updates of a bot.
 *
 * @author Michi Palazzo
 */
public abstract class LongPollingBot extends TelegramBot implements Runnable {
  
  private final UpdateReader updateReader;
  private boolean running = false;
  
  /**
   * Constructs a long polling bot.
   *
   * @param bot          the bot used by the reader
   * @param backOff      back off to be used when long polling fails
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
  
  /**
   * Stops the long polling bot.
   * Call again {@link #run()} to resume the long polling bot.
   */
  public void stop() {
    running = false;
  }
  
  @Override
  public void run() {
    running = true;
    while (!Thread.interrupted() && running) {
      try {
        Update update = updateReader.read();
        onUpdate(update);
      } catch (InterruptedException e) {
        stop();
      } catch (Throwable e) {
        onError(e);
      }
    }
    running = false;
  }
  
}
