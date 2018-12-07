package io.github.ageofwar.telejam.replymarkups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Upon receiving a message with this object, Telegram clients will display a
 * reply interface to the user (act as if the user has selected the bot‘s
 * message and tapped ’Reply'). This can be extremely useful if you want to
 * create user-friendly step-by-step interfaces without having to sacrifice privacy mode.
 *
 * @author Michi Palazzo
 */
public final class ForceReply implements ReplyMarkup {
  
  public static final ForceReply NONSELECTIVE = new ForceReply(false);
  public static final ForceReply SELECTIVE = new ForceReply(true);
  
  static final String FORCE_REPLY_FIELD = "force_reply";
  static final String SELECTIVE_FIELD = "selective";
  
  @Expose
  @SerializedName(FORCE_REPLY_FIELD)
  private static final boolean FORCE_REPLY = true;
  
  /**
   * If true forces reply from specific users only.
   * Targets:
   * 1) users that are @mentioned in the text of the Message object;
   * 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
   */
  @SerializedName(SELECTIVE_FIELD)
  private final boolean selective;
  
  /**
   * Constructs a ForceReply.
   *
   * @param selective set to <code>true</code> if you want to
   *                  force reply from specific users only
   */
  private ForceReply(boolean selective) {
    this.selective = selective;
  }
  
}
