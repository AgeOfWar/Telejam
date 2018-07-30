package me.palazzomichi.telegram.telejam.text;

import me.palazzomichi.telegram.telejam.objects.MessageEntity;
import me.palazzomichi.telegram.telejam.objects.User;

import java.util.ArrayList;

/**
 * Factory class for {@link Text}.
 *
 * @author Michi Palazzo
 * @see Text
 */
public class TextBuilder {
  
  static final String TEXT_MENTION_LINK = "tg://user?id=";
  
  /**
   * Builder of the string of the Text.
   */
  private StringBuilder builder;
  
  /**
   * Array of entities.
   */
  private ArrayList<MessageEntity> entities;
  
  
  /**
   * Constructs a TextBuilder.
   */
  public TextBuilder() {
    entities = new ArrayList<>();
    builder = new StringBuilder();
  }
  
  
  /**
   * Appends a text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder append(String text) {
    builder.append(text);
    return this;
  }
  
  /**
   * Appends a text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder append(Text text) {
    for (MessageEntity entity : text.getEntities()) {
      append(
          entity.move(entity.getOffset() + length(), entity.getLength()),
          entity.getType() != MessageEntity.Type.BOT_COMMAND &&
              entity.getType() != MessageEntity.Type.URL &&
              entity.getType() != MessageEntity.Type.HASHTAG &&
              entity.getType() != MessageEntity.Type.EMAIL
      );
    }
    builder.append(text.toString());
    return this;
  }
  
  /**
   * Appends a text builder to this TextBuilder.
   *
   * @param builder the builder to append
   * @return this instance
   */
  public TextBuilder append(TextBuilder builder) {
    for (MessageEntity entity : builder.entities) {
      append(
          entity.move(entity.getOffset() + length(), entity.getLength()),
          entity.getType() != MessageEntity.Type.BOT_COMMAND &&
              entity.getType() != MessageEntity.Type.URL &&
              entity.getType() != MessageEntity.Type.HASHTAG &&
              entity.getType() != MessageEntity.Type.EMAIL
      );
    }
    builder.append(builder.builder.toString());
    return this;
  }
  
  /**
   * Appends a bold text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder appendBold(String text) {
    return append(text, MessageEntity.Type.BOLD, true);
  }
  
  /**
   * Appends a italic text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder appendItalic(String text) {
    return append(text, MessageEntity.Type.ITALIC, true);
  }
  
  /**
   * Appends a code text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder appendCode(String text) {
    return append(text, MessageEntity.Type.CODE, true);
  }
  
  /**
   * Appends a code block text to this TextBuilder.
   *
   * @param text the text to append
   * @return this instance
   */
  public TextBuilder appendCodeBlock(String text) {
    return append(text, MessageEntity.Type.CODE_BLOCK, true);
  }
  
  /**
   * Appends a link to this TextBuilder.
   *
   * @param link the link to append
   * @return this instance
   */
  public TextBuilder appendLink(Link link) {
    return append(link.getText(), link.getUrl(), true);
  }
  
  /**
   * Appends a link to this TextBuilder.
   *
   * @param text the text of the link
   * @param url the url of the link
   * @return this instance
   */
  public TextBuilder appendLink(String text, String url) {
    return append(text, url, true);
  }
  
  /**
   * Appends a mention to this TextBuilder.
   *
   * @param text the username to mention, without <code>@</code>
   * @return this instance
   */
  public TextBuilder appendMention(String text) {
    return append("@" + text, MessageEntity.Type.MENTION, false);
  }
  
  /**
   * Appends a mention to this TextBuilder, or a text mention
   * if the username is not available.
   *
   * @param mention mentioned user
   * @return this instance
   */
  public TextBuilder appendMention(User mention) {
    return mention.getUsername()
        .map(this::appendMention)
        .orElseGet(() -> appendTextMention(mention));
  }
  
  /**
   * Appends a text mention to this TextBuilder.
   *
   * @param mention the mentioned to append
   * @return this instance
   */
  public TextBuilder appendTextMention(Mention mention) {
    return append(mention.getText(), mention.getUser(), true);
  }
  
  /**
   * Appends a text mention to this TextBuilder.
   *
   * @param text    the text of the mention
   * @param mention the mention
   * @return this instance
   */
  public TextBuilder appendTextMention(String text, User mention) {
    return append(text, mention, true);
  }
  
  /**
   * Appends a text mention to this TextBuilder.
   *
   * @param mention the mentioned user
   * @return this instance
   */
  public TextBuilder appendTextMention(User mention) {
    return append(mention.getName(), mention, true);
  }
  
  /**
   * Appends an hashtag to this TextBuilder.
   *
   * @param hashtag the hashtag to append, without <code>#</code>
   * @return this instance
   */
  public TextBuilder appendHashtag(String hashtag) {
    return append("#" + hashtag, MessageEntity.Type.HASHTAG, false);
  }
  
  /**
   * Appends a bot command to this TextBuilder.
   *
   * @param botCommand the bot command to append, without <code>/</code>
   * @return this instance
   */
  public TextBuilder appendBotCommand(String botCommand) {
    return append("/" + botCommand, MessageEntity.Type.BOT_COMMAND, false);
  }
  
  /**
   * Appends a bot command to this TextBuilder.
   *
   * @param url the url to append
   * @return this instance
   */
  public TextBuilder appendUrl(String url) {
    return append(url, MessageEntity.Type.URL, false);
  }
  
  /**
   * Appends an email to this TextBuilder.
   *
   * @param email the email to append
   * @return this instance
   */
  public TextBuilder appendEmail(String email) {
    return append(email, MessageEntity.Type.BOT_COMMAND, false);
  }
  
  /**
   * Appends a phone number to this TextBuilder.
   *
   * @param phoneNumber the phone number to append
   * @return this instance
   */
  public TextBuilder appendPhoneNumber(String phoneNumber) {
    return append(phoneNumber, MessageEntity.Type.PHONE_NUMBER, false);
  }
  
  /**
   * Appends a cashtag to this TextBuilder.
   *
   * @param cashtag the cashtag to append
   * @return this instance
   */
  public TextBuilder appendCashtag(String cashtag) {
    return append("$" + cashtag.toUpperCase(), MessageEntity.Type.CASHTAG, false);
  }
  
  /**
   * Appends a mention to this TextBuilder.
   *
   * @param text    the text of the mention
   * @param mention the id of the mentioned user
   * @return this instance
   * @deprecated use this method only if you can't
   * obtain the user relative to its id
   */
  @Deprecated
  public TextBuilder appendMention(String text, long mention) {
    return appendLink(text, TEXT_MENTION_LINK + mention);
  }
  
  private TextBuilder append(String text, MessageEntity.Type type, boolean concat) {
    append(new MessageEntity(type, builder.length(), text.length()), concat);
    builder.append(text);
    return this;
  }
  
  private TextBuilder append(String text, String url, boolean concat) {
    append(new MessageEntity(url, builder.length(), text.length()), concat);
    builder.append(text);
    return this;
  }
  
  private TextBuilder append(String text, User user, boolean concat) {
    append(new MessageEntity(user, builder.length(), text.length()), concat);
    builder.append(text);
    return this;
  }
  
  private void append(MessageEntity entity, boolean concat) {
    if (entity.getLength() > 0) {
      if (!concat || entities.isEmpty()) {
        entities.add(entity);
      } else {
        MessageEntity lastEntity = entities.get(entities.size() - 1);
        if (lastEntity.getType() == entity.getType() &&
            entity.getUrl().equals(lastEntity.getUrl()) &&
            entity.getUser().equals(lastEntity.getUser()) &&
            lastEntity.getOffset() + lastEntity.getLength() == entity.getOffset()) {
          entities.set(
              entities.size() - 1,
              lastEntity.move(lastEntity.getOffset(), lastEntity.getLength() + entity.getLength())
          );
        } else {
          entities.add(entity);
        }
      }
    }
  }
  
  /**
   * Returns the length of the text.
   *
   * @return the length of the text
   */
  public int length() {
    return builder.length();
  }
  
  /**
   * Sets the length of the text.
   *
   * @param length the new length
   * @throws StringIndexOutOfBoundsException if the {@code newLength} argument is negative.
   */
  public void setLength(int length) {
    if (length < 0)
      throw new StringIndexOutOfBoundsException(length);
    
    builder.setLength(length);
    
    for (int i = entities.size() - 1; i >= 0; i--) {
      MessageEntity entity = entities.get(i);
      if (entity.getOffset() > length) {
        entities.remove(entity);
      } else if (entity.getOffset() + entity.getLength() > length) {
        entities.remove(entity);
        int newLength = length - entity.getOffset();
        entities.add(entity.move(entity.getOffset(), newLength));
      } else {
        break;
      }
    }
  }
  
  /**
   * Builds the Text.
   *
   * @return the created text
   */
  public Text build() {
    return new Text(builder.toString(), entities);
  }
  
}
