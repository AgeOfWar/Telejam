package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

import java.util.List;
import java.util.Objects;

/**
 * This object represents a text message.
 *
 * @author Michi Palazzo
 */
public class TextMessage extends Message {

  static final String TEXT_FIELD = "text";
  static final String ENTITIES_FIELD = "entities";

  /**
   * The actual UTF-8 text of the message, 0-4096 characters.
   */
  @SerializedName(TEXT_FIELD)
  private String text;

  /**
   * For text messages, special entities like usernames,
   * URLs, bot commands, etc. that appear in the text.
   */
  @SerializedName(ENTITIES_FIELD)
  private List<MessageEntity> entities;


  public TextMessage(long id,
                     User sender,
                     long date,
                     Chat chat,
                     Message replyToMessage,
                     Long editDate,
                     String authorSignature,
                     String text,
                     List<MessageEntity> entities) {
    super(id, sender, date, chat, replyToMessage, editDate, authorSignature);
    this.text = Objects.requireNonNull(text);
    this.entities = entities;
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
