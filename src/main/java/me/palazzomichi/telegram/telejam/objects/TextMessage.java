package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

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
  private final List<MessageEntity> entities;


  public TextMessage(long id,
                     User sender,
                     long date,
                     Chat chat,
                     Message replyToMessage,
                     Long editDate,
                     String authorSignature,
                     Text text) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.text = text.toString();
    entities = text.getEntities();
  }


  /**
   * Returns whether or not this message is a command.
   *
   * @return whether or not this message is a command
   */
  public boolean isCommand() {
   if(entities == null || entities.size() < 1)
     return false;
   MessageEntity entity = entities.get(0);
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
