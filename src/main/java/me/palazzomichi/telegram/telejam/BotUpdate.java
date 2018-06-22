package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.objects.Update;
import me.palazzomichi.telegram.telejam.util.UpdateReader;

import java.io.IOException;

/**
 * Runnable that receives and handles updates of a
 * bot <b>once</b> at every call of {@link #run()}.
 *
 * @author Michi Palazzo
 */
public abstract class BotUpdate extends TelegramBot implements Runnable {
  
  private final UpdateReader updateReader;
  
  /**
   * Constructs a BotUpdate.
   *
   * @param bot the update receiver
   */
  public BotUpdate(Bot bot) {
    super(bot);
    updateReader = new UpdateReader(bot);
  }
  
  @Override
  public void run() {
    try {
      updateReader.getUpdates();
    } catch (IOException e) {
      onError(e);
    }
    for (Update update : updateReader.readAll()) {
      try {
        onUpdate(update);
      } catch (Throwable e) {
        onError(e);
      }
    }
  }
  
}
