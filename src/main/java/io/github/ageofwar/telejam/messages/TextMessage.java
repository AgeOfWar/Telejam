package io.github.ageofwar.telejam.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;
import io.github.ageofwar.telejam.users.User;
import io.github.ageofwar.telejam.text.Text;

import java.util.List;

/**
 * This object represents a text message.
 *
 * @author Michi Palazzo
 */
public class TextMessage extends Message {
  
  static final String TEXT_FIELD = "text";
  static final String ENTITIES_FIELD = "entities";
  
  /**
   * The actual text of the message, 0-4096 characters.
   */
  @SerializedName(TEXT_FIELD)
  private final String text;
  
  /**
   * For text messages, special entities like usernames,
   * URLs, bot commands, etc. that appear in the text.
   */
  @SerializedName(ENTITIES_FIELD)
  private final MessageEntity[] entities;
  
  
  public TextMessage(long id,
                     User sender,
                     long date,
                     Chat chat,
                     Message replyToMessage,
                     Long editDate,
                     String authorSignature,
                     Text text,
                     InlineKeyboardMarkup replyMarkup) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature, replyMarkup);
    this.text = text.toString();
    entities = text.getEntities().toArray(new MessageEntity[0]);
  }
  
  
  /**
   * Returns whether or not this message is a command.
   *
   * @return whether or not this message is a command
   */
  public boolean isCommand() {
    if (entities == null || entities.length < 1)
      return false;
    MessageEntity entity = entities[0];
    return entity.getType() == MessageEntity.Type.BOT_COMMAND && entity.getOffset() == 0;
  }
  
  /**
   * Returns the text of the message.
   *
   * @return the text of the message
   */
  public Text getText() {
    return new Text(text, entities);
  }
  
}
