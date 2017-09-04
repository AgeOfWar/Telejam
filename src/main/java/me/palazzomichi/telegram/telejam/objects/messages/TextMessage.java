package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.util.text.Text;
import me.palazzomichi.telegram.telejam.util.text.TextEntity;

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
  private MessageEntity[] entities;


  public TextMessage(long id,
                     User sender,
                     long date,
                     Chat chat,
                     User forwardMessageSender,
                     Chat forwardMessageChat,
                     Long forwardMessageId,
                     Long forwardMessageDate,
                     Message replyToMessage,
                     Long editDate,
                     String authorSignature,
                     String forwardSignature,
                     String text,
                     MessageEntity[] entities) {
    super(id, sender, date, chat, forwardMessageSender, forwardMessageChat, forwardMessageId,
        forwardMessageDate, replyToMessage, editDate, authorSignature, forwardSignature);
    this.text = Objects.requireNonNull(text);
    this.entities = entities;
  }


  /**
   * Returns whether or not this message is a command.
   *
   * @return whether or not this message is a command
   */
  public boolean isCommand() {
   if(entities == null)
     return false;

   for (MessageEntity entity : entities) {
     if (entity.getType() == TextEntity.BOT_COMMAND && entity.getOffset() == 0) {
       return true;
     }
   }
   return false;
  }

  /**
   * Returns the text of the message.
   *
   * @return the text of the message
   */
  public Text getText() {
    return new Text(text, entities == null ? new MessageEntity[0] : entities);
  }

}
