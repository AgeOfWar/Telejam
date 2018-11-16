package io.github.ageofwar.telejam.messages;

import io.github.ageofwar.telejam.chats.Chat;

/**
 * Interface that handles joins of the bot in a chat.
 */
@FunctionalInterface
public interface ChatJoinHandler {
  
  /**
   * Handles a join in a chat.
   *
   * @param chat    the chat
   * @param message message relative to the update
   * @throws Throwable if a throwable is thrown
   */
  void onJoin(Chat chat, NewChatMembersMessage message) throws Throwable;
  
}
