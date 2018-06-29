package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.objects.Message;

/**
 * Interface that handles edited channel posts received from a bot.
 */
public interface ChannelPostEditHandler {
  
  /**
   * Handles an incoming channel post edit.
   *
   * @param message  new incoming edit
   * @param editDate the date of the edit
   * @throws Throwable if a throwable is thrown
   */
  void onChannelPostEdit(Message message, long editDate) throws Throwable;
  
}