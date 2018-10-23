package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.Update;
import me.palazzomichi.telegram.telejam.updates.UpdateReader;

import java.io.IOException;
import java.util.logging.Logger;

import static me.palazzomichi.telegram.telejam.json.Json.toJson;
import static me.palazzomichi.telegram.telejam.loggers.Loggers.logError;

final class LongPollingBots {
  
  public static void runLongPollingBot(
      UpdateReader updateReader,
      Bot bot,
      TelegramBot telegramBot,
      Logger logger) {
    logger.info(() -> "Starting " + bot.getUsername() + "...");
    try {
      logger.info(() -> "Discarding previous updates...");
      updateReader.discardAll();
    } catch (IOException e) {
      logError(logger, e);
      telegramBot.onError(e);
    }
    logger.info(() -> "Waiting for updates...");
    while (!Thread.interrupted()) {
      try {
        Update update = updateReader.read();
        logger.finer(() -> "New update: " + toJson(update));
        telegramBot.onUpdate(update);
      } catch (InterruptedException e) {
        logger.info(() -> "Stopping " + bot.getUsername() + "...");
        Thread.currentThread().interrupt();
      } catch (Throwable e) {
        logError(logger, e);
        telegramBot.onError(e);
      }
    }
  }
  
  private LongPollingBots() {
    throw new AssertionError();
  }
  
}
