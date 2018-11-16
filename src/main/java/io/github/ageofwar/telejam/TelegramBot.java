package io.github.ageofwar.telejam;

import io.github.ageofwar.telejam.events.EventRegistry;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

/**
 * Abstract class that handles Telegram updates.
 */
public abstract class TelegramBot implements Runnable, UpdateHandler {
  
  /**
   * Bot that receives updates.
   */
  protected final Bot bot;
  
  /**
   * Utility field that handles events.
   */
  protected final EventRegistry events;
  
  public TelegramBot(Bot bot) {
    this.bot = bot;
    events = new EventRegistry(bot);
  }
  
  @Override
  public void onUpdate(Update update) throws Throwable {
    events.onUpdate(update);
  }
  
}
