package io.github.ageofwar.telejam.updates;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.messages.Message;

import java.util.Objects;

/**
 * This object represents an incoming update.
 *
 * @author Michi Palazzo
 */
public class EditedChannelPostUpdate extends Update {
  
  static final String EDITED_CHANNEL_POST_FIELD = "edited_channel_post";
  
  /**
   * New version of a channel post that is known to the bot and was edited.
   */
  @SerializedName(EDITED_CHANNEL_POST_FIELD)
  private final Message editedChannelPost;
  
  
  public EditedChannelPostUpdate(long id, Message editedChannelPost) {
    super(id);
    this.editedChannelPost = Objects.requireNonNull(editedChannelPost);
  }
  
  
  /**
   * Getter for property {@link #editedChannelPost}.
   *
   * @return value for property {@link #editedChannelPost}
   */
  public Message getEditedChannelPost() {
    return editedChannelPost;
  }
  
}
