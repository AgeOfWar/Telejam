package me.palazzomichi.telegram.telejam.util;

import me.palazzomichi.telegram.telejam.objects.Chat;
import me.palazzomichi.telegram.telejam.objects.NewChatMembersMessage;
import me.palazzomichi.telegram.telejam.objects.User;

/**
 * Interface that handles new chat members messages received from a bot.
 */
@FunctionalInterface
public interface NewChatMemberHandler {
  
  /**
   * Handles an incoming new chat member.
   *
   * @param chat the chat
   * @param user new chat member
   * @param message message relative to the update
   * @throws Throwable if a throwable is thrown
   */
  void onNewChatMember(Chat chat, User user, NewChatMembersMessage message) throws Throwable;
  
}
