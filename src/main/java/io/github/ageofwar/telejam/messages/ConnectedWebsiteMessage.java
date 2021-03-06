package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.users.User;

import java.util.Objects;

/**
 * This object represents a connected website message.
 *
 * @author Michi Palazzo
 */
public class ConnectedWebsiteMessage extends Message {
  
  static final String CONNECTED_WEBSITE_FIELD = "connected_website";
  
  /**
   * The domain name of the website on which the user has logged in.
   */
  @SerializedName(CONNECTED_WEBSITE_FIELD)
  private final String connectedWebsite;
  
  public ConnectedWebsiteMessage(long id,
                                 User sender,
                                 long date,
                                 Chat chat,
                                 Message replyToMessage,
                                 Long editDate,
                                 String authorSignature,
                                 String connectedWebsite,
                                 InlineKeyboardMarkup replyMarkup) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature, replyMarkup);
    this.connectedWebsite = Objects.requireNonNull(connectedWebsite);
  }
  
  /**
   * Getter for property {@link #connectedWebsite}.
   *
   * @return value for property {@link #connectedWebsite}
   */
  public String getConnectedWebsite() {
    return connectedWebsite;
  }
  
}
