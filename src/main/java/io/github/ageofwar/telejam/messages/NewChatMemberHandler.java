package io.github.ageofwar.telejam.messages;

import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;

/**
 * Interface that handles new chat members messages received from a bot.
 */
@FunctionalInterface
public interface NewChatMemberHandler {
  
  /**
   * Handles an incoming new chat member.
   *
   * @param chat    the chat
   * @param user    new chat member
   * @param message message relative to the update
   * @throws Throwable if a throwable is thrown
   */
  void onNewChatMember(Chat chat, User user, NewChatMembersMessage message) throws Throwable;
  
}
