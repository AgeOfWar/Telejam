package me.palazzomichi.telegram.telejam;

import me.palazzomichi.telegram.telejam.methods.GetChatMember;
import me.palazzomichi.telegram.telejam.objects.*;
import me.palazzomichi.telegram.telejam.util.Command;

import java.io.IOException;

/**
 * Abstract class that handles Telegram updates received
 * from members of a chat, specified in the {@link #chatId} field.
 */
public abstract class ChatTelegramBot extends TelegramBot {
  
  /**
   * Identifier of the chat to handle.
   */
  protected final long chatId;
  
  public ChatTelegramBot(Bot bot, long chatId) {
    super(bot);
    this.chatId = chatId;
  }
  
  @Override
  public final void onMessage(Message message) throws Throwable {
    ChatMember member = getChatMember(message.getSender());
    if (member != null) {
      onMemberMessage(message, member);
    }
  }
  
  /**
   * Handles an incoming message from a member of {@link #chatId}.
   *
   * @param message new incoming message
   * @param member  sender of the message
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberMessage(Message message, ChatMember member) throws Throwable {
  }
  
  @Override
  public final void onMessageEdit(Message message, long editDate) throws Throwable {
    ChatMember member = getChatMember(message.getSender());
    if (member != null) {
      onMemberMessageEdit(message, editDate, member);
    }
  }
  
  /**
   * Handles an incoming message edit from a member of {@link #chatId}.
   *
   * @param message  new incoming edit
   * @param editDate the date of the edit
   * @param member   sender of the message edit
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberMessageEdit(Message message, long editDate, ChatMember member)
      throws Throwable {
  }
  
  @Override
  public final void onChannelPost(Message message) throws Throwable {
    ChatMember member = getChatMember(message.getSender());
    if (member != null) {
      onMemberChannelPost(message, member);
    }
  }
  
  /**
   * Handles an incoming channel post from a member of {@link #chatId}.
   *
   * @param message new incoming message
   * @param member  sender of the channel post
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberChannelPost(Message message, ChatMember member) throws Throwable {
  }
  
  @Override
  public final void onChannelPostEdit(Message message, long editDate) throws Throwable {
    ChatMember member = getChatMember(message.getSender());
    if (member != null) {
      onMemberChannelPostEdit(message, editDate, member);
    }
  }
  
  /**
   * Handles an incoming channel post edit from a member of {@link #chatId}.
   *
   * @param message  new incoming edit
   * @param editDate the date of the edit
   * @param member   sender of the channel post edit
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberChannelPostEdit(Message message, long editDate, ChatMember member)
      throws Throwable {
  }
  
  @Override
  public final void onInlineQuery(InlineQuery inlineQuery) throws Throwable {
    ChatMember member = getChatMember(inlineQuery.getSender());
    if (member != null) {
      onMemberInlineQuery(inlineQuery, member);
    }
  }
  
  /**
   * Handles an incoming inline query from a member of {@link #chatId}.
   *
   * @param inlineQuery new incoming message
   * @param member      sender of the inline query
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberInlineQuery(InlineQuery inlineQuery, ChatMember member) throws Throwable {
  }
  
  @Override
  public final void onChosenInlineResult(ChosenInlineResult chosenInlineResult) throws Throwable {
    ChatMember member = getChatMember(chosenInlineResult.getSender());
    if (member != null) {
      onMemberChosenInlineResult(chosenInlineResult, member);
    }
  }
  
  /**
   * Handles an incoming chosen inline result from a member of {@link #chatId}.
   *
   * @param chosenInlineResult new incoming chosen inline result
   * @param member             sender of the chosen inline result
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberChosenInlineResult(ChosenInlineResult chosenInlineResult, ChatMember member)
      throws Throwable {
  }
  
  @Override
  public final void onCallbackQuery(CallbackQuery callbackQuery) throws Throwable {
    ChatMember member = getChatMember(callbackQuery.getSender());
    if (member != null) {
      onMemberCallbackQuery(callbackQuery, member);
    }
  }
  
  /**
   * Handles an incoming callback query from a member of {@link #chatId}.
   *
   * @param callbackQuery new incoming callback query
   * @param member        sender of the callback query
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberCallbackQuery(CallbackQuery callbackQuery, ChatMember member)
      throws Throwable {
  }
  
  @Override
  public final void onShippingQuery(ShippingQuery shippingQuery) throws Throwable {
    ChatMember member = getChatMember(shippingQuery.getSender());
    if (member != null) {
      onMemberShippingQuery(shippingQuery, member);
    }
  }
  
  /**
   * Handles an incoming shipping query from a member of {@link #chatId}.
   *
   * @param shippingQuery new incoming shipping query
   * @param member        sender of the shipping query
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberShippingQuery(ShippingQuery shippingQuery, ChatMember member)
      throws Throwable {
  }
  
  @Override
  public final void onPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) throws Throwable {
    ChatMember member = getChatMember(preCheckoutQuery.getSender());
    if (member != null) {
      onMemberPreCheckoutQuery(preCheckoutQuery, member);
    }
  }
  
  /**
   * Handles an incoming pre-checkout query from a member of {@link #chatId}.
   *
   * @param preCheckoutQuery new incoming pre-checkout query
   * @param member           sender of the pre-checkout query
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery, ChatMember member)
      throws Throwable {
  }
  
  @Override
  public final void onTextMessage(TextMessage message) throws Throwable {
    ChatMember member = getChatMember(message.getSender());
    if (member != null) {
      onMemberTextMessage(message, member);
    }
  }
  
  /**
   * Handles an incoming text message from a member of {@link #chatId}.
   *
   * @param message new incoming message
   * @param member  sender of the message
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberTextMessage(TextMessage message, ChatMember member) throws Throwable {
  }
  
  @Override
  public final void onCommand(Command command, TextMessage message) throws Throwable {
    ChatMember member = getChatMember(message.getSender());
    if (member != null) {
      onMemberCommand(command, message, member);
    }
  }
  
  /**
   * Handles a command received from a member of {@link #chatId}.
   *
   * @param command the command
   * @param message the command message
   * @param member  the sender of the command
   * @throws Throwable if a throwable is thrown
   */
  public void onMemberCommand(Command command, TextMessage message, ChatMember member)
      throws Throwable {
  }
  
  @Override
  public final void onNewChatMember(Chat chat, User user, NewChatMembersMessage message)
      throws Throwable {
    if (chat.getId() == chatId) {
      onChatNewChatMember(chat, user, message);
    }
  }
  
  /**
   * Handles an incoming new chat member of {@link #chatId}.
   *
   * @param chat the chat
   * @param user new chat member
   * @param message message relative to the update
   * @throws Throwable if a throwable is thrown
   */
  public void onChatNewChatMember(Chat chat, User user, NewChatMembersMessage message)
    throws Throwable {
  }
  
  @Override
  public final void onJoin(Chat chat, NewChatMembersMessage message) throws Throwable {
    if (chat.getId() == chatId) {
      onChatJoin(chat, message);
    }
  }
  
  /**
   * Handles a join in {@link #chatId}.
   *
   * @param chat the chat
   * @param message message relative to the update
   * @throws Throwable if a throwable is thrown
   */
  public void onChatJoin(Chat chat, NewChatMembersMessage message) throws Throwable {
  }
  
  private ChatMember getChatMember(User user) throws IOException {
    GetChatMember getChatMember = new GetChatMember()
        .chat(chatId)
        .user(user);
    try {
      return bot.execute(getChatMember);
    } catch (TelegramException e) {
      return null;
    }
  }
  
}
