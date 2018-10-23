package me.palazzomichi.telegram.telejam.handlers;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.NewChatMembersMessage;

/**
 * Interface that handles joins of the bot in a chat.
 */
@FunctionalInterface
public interface ChatJoinHandler {
  
  /**
   * Handles a join in a chat.
   *
   * @param chat the chat
   * @param message message relative to the update
   * @throws Throwable if a throwable is thrown
   */
  void onJoin(Chat chat, NewChatMembersMessage message) throws Throwable;
  
}
