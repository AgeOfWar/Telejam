package me.palazzomichi.telegram.telejam.objects.updates;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.messages.Message;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class ChannelPostUpdate extends Update {

  static final String CHANNEL_POST_FIELD = "channel_post";

  /**
   * New incoming channel post of any kind — text, photo, sticker, etc.
   */
  @SerializedName(CHANNEL_POST_FIELD)
  private Message channelPost;


  public ChannelPostUpdate(long id, Message channelPost) {
    super(id);
    this.channelPost = Objects.requireNonNull(channelPost);
  }


  /**
   * Getter for property {@link #channelPost}.
   *
   * @return value for property {@link #channelPost}
   */
  public Message getChannelPost() {
    return channelPost;
  }

}
