package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.ResponseParameters;
import me.palazzomichi.telegram.telejam.objects.updates.Update;
import me.palazzomichi.telegram.telejam.util.handlers.UpdateHandler;
import me.palazzomichi.telegram.telejam.util.stream.UpdateReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.LongUnaryOperator;

/**
 * Thread that receives and handles updates of a bot.
 *
 * @author Michi Palazzo
 */
public class BotThread extends Thread {

  /**
   * Reader of updates.
   */
  private final UpdateReader updateReader;

  /**
   * Handler of updates for the bot.
   */
  private final List<UpdateHandler> updateHandlers;

  /**
   * Handler of errors.
   */
  private final List<Consumer<? super IOException>> errorHandlers;

  /**
   * Executes operations of update handlers.
   */
  private Executor executor;


  /**
   * Constructs a bot thread.
   *
   * @param bot     the bot used by the reader
   * @param backOff back off to be used when long polling fails
   */
  public BotThread(Bot bot, LongUnaryOperator backOff) {
    super();
    updateReader = new UpdateReader(bot, backOff);
    updateHandlers = new ArrayList<>();
    errorHandlers = new ArrayList<>();
    executor = Runnable::run;
  }

  /**
   * Constructs a bot thread.
   *
   * @param bot     the bot used by the reader
   * @param backOff back off to be used when long polling fails
   */
  public BotThread(Bot bot, long backOff) {
    this(bot, a -> backOff);
  }

  /**
   * Constructs a bot thread with 500 milliseconds of back off.
   *
   * @param bot the bot used by the reader
   */
  public BotThread(Bot bot) {
    this(bot, 500L);
  }


  @Override
  public void run() {
    while (!Thread.interrupted()) {
      try {
        if (updateReader.getUpdates() == 0) {
          break;
        }
        updateReader.discard(); //Discards previous updates
      } catch (IOException e) {
        checkResponseParameters(e);
        handleError(e);
      }
    }

    while (!Thread.interrupted()) {
      try {
        Update update = updateReader.read();

        handleUpdate(update);
      } catch (IOException e) {
        checkResponseParameters(e);
        handleError(e);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  private void checkResponseParameters(IOException e) {
    if (!(e instanceof TelegramException)) {
      return;
    }

    Optional<ResponseParameters> responseParameters =
        ((TelegramException) e).getResponseParameters();

    if (!responseParameters.isPresent()) {
      return;
    }

    responseParameters.get().getRetryAfter().ifPresent(seconds -> {
      try {
        Thread.sleep(seconds * 1000);
      } catch (InterruptedException e1) {
        Thread.currentThread().interrupt();
      }
    });
  }

  private void handleUpdate(Update update) {
    updateHandlers.forEach(
        updateHandler -> executor.execute(
            () -> updateHandler.accept(update)
        )
    );
  }

  private void handleError(IOException e) {
    errorHandlers.forEach(errorHandler -> errorHandler.accept(e));
  }

  /**
   * Shortcut for {@link #getUpdateReader()}.{@linkplain UpdateReader#getConnection() getConnection()}.
   *
   * @return the connection of the update reader
   */
  public Bot getBot() {
    return (Bot) updateReader.getConnection();
  }

  /**
   * Setter for property {@link UpdateReader#backOff} of {@link #updateReader}.
   *
   * @param backOff value for property {@link UpdateReader#backOff} of {@link #updateReader}
   */
  public void setBackOff(LongUnaryOperator backOff) {
    updateReader.setBackOff(backOff);
  }

  /**
   * Setter for property {@link UpdateReader#backOff} of {@link #updateReader}.
   *
   * @param backOff value for property {@link UpdateReader#backOff} of {@link #updateReader}
   */
  public void setBackOff(long backOff) {
    updateReader.setBackOff(backOff);
  }

  /**
   * Getter for property {@link #updateReader}.
   *
   * @return value for property {@link #updateReader}
   */
  public UpdateReader getUpdateReader() {
    return updateReader;
  }

  /**
   * Getter for property {@link #updateHandlers}.
   * The list is backed by this BotThread, so changes to this
   * object are reflected in the list, and vice-versa.
   *
   * @return value for property {@link #updateHandlers}
   */
  public List<UpdateHandler> getUpdateHandlers() {
    return updateHandlers;
  }

  /**
   * Getter for property {@link #errorHandlers}.
   * The list is backed by this BotThread, so changes to this
   * object are reflected in the list, and vice-versa.
   *
   * @return value for property {@link #errorHandlers}
   */
  public List<Consumer<? super IOException>> getErrorHandlers() {
    return errorHandlers;
  }

  /**
   * Getter for property {@link #executor}.
   *
   * @return value for property {@link #executor}
   */
  public Executor getExecutor() {
    return executor;
  }

  /**
   * Setter for property {@link #executor}.
   *
   * @param executor value for property {@link #executor}
   */
  public void setExecutor(Executor executor) {
    this.executor = executor;
  }

}
