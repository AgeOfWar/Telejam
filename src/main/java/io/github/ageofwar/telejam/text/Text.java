package io.github.ageofwar.telejam.text;

import io.github.ageofwar.telejam.messages.Message;
import io.github.ageofwar.telejam.messages.MessageEntity;
import io.github.ageofwar.telejam.users.User;

import java.util.*;
import java.util.function.Function;

/**
 * Class representing a text with entities.
 *
 * @author Michi Palazzo
 */
public final class Text implements CharSequence {
  
  public static final Text EMPTY = new Text("");
  
  private final String text;
  private final MessageEntity[] entities;
  
  /**
   * Constructs a text.
   *
   * @param text     the text
   * @param entities text entities
   */
  public Text(String text, MessageEntity... entities) {
    this.text = text;
    Comparator<MessageEntity> comparator = (e1, e2) -> {
      int offset = e1.getOffset() - e2.getOffset();
      if (offset == 0) {
        return e2.getLength() - e1.getLength();
      } else {
        return offset;
      }
    };
    this.entities = entities != null ? Arrays.copyOf(entities, entities.length) : new MessageEntity[0];
    Arrays.sort(this.entities, comparator);
  }
  
  /**
   * Constructs a text.
   *
   * @param text     the text
   * @param entities text entities
   */
  public Text(String text, List<MessageEntity> entities) {
    this(text, entities.toArray(new MessageEntity[0]));
  }
  
  /**
   * Constructs a text.
   *
   * @param text the text
   */
  public Text(String text) {
    this(text, new MessageEntity[0]);
  }
  
  /**
   * Converts an HTML String into a Text.
   * The following tags are currently supported:
   * <pre>
   * &lt;b&gt;bold&lt;/b&gt;, &lt;strong&gt;bold&lt;/strong&gt;
   * &lt;i&gt;italic&lt;/i&gt;, &lt;em&gt;italic&lt;/em&gt;
   * &lt;a href="http://www.example.com/"&gt;inline URL&lt;/a&gt;
   * &lt;a href="tg://user?id=123456789"&gt;inline mention of a user&lt;/a&gt;
   * &lt;code&gt;inline fixed-width code&lt;/code&gt;
   * &lt;pre&gt;pre-formatted fixed-width code block&lt;/pre&gt;
   * &lt;pre&gt;&lt;code class="language-python"&gt;pre-formatted fixed-width code block written in the Python programming language&lt;/code&gt;&lt;/pre&gt;
   * </pre>
   * Only the tags mentioned above are currently supported.
   * All &lt;, &gt; and &amp; symbols that are not a part of a tag or an HTML
   * entity must be replaced with the corresponding HTML entities
   * (&lt; with &amp;lt;, &gt; with &amp;gt; and &amp; with &amp;amp;)
   * All numerical HTML entities are supported.
   * The API currently supports only the following named HTML entities: &lt;, &gt;, &amp; and &quot;.
   * Use nested pre and code tags, to define programming language for pre entity.
   * Programming language can't be specified for standalone code tags.
   *
   * @param text the string to convert
   * @return the parsed text
   * @throws TextParseException if an error occurs when parsing the string
   */
  public static Text parseHtml(String text) throws TextParseException {
    if (text == null) {
      return null;
    }
    return Html.INSTANCE.parseText(text);
  }
  
  /**
   * Converts an MarkdownV2 String into a Text.
   * Use the following syntax in your message:
   * <pre>
   * *bold \*text*
   * _italic \*text_
   * __underline__
   * ~strikethrough~
   * *bold _italic bold ~italic bold strikethrough~ __underline italic bold___ bold*
   * [inline URL](http://www.example.com/)
   * [inline mention of a user](tg://user?id=123456789)
   * `inline fixed-width code`
   * ```
   * pre-formatted fixed-width code block
   * ```
   * ```python
   * pre-formatted fixed-width code block written in the Python programming language
   * ```
   * </pre>
   * Any character between 1 and 126 inclusively can be escaped anywhere with a preceding '\' character,
   * in which case it is treated as an ordinary character and not a part of the markup.
   * Inside pre and code entities, all '`‘ and ’\‘ characters must be escaped with a preceding ’\' character.
   * Inside (...) part of inline link definition, all ')‘ and ’\‘ must be escaped with a preceding ’\' character.
   * In all other places characters '_‘, ’*‘, ’[‘, ’]‘, ’(‘, ’)‘, ’~‘, ’`‘, ’&gt;‘, ’#‘, ’+‘, ’-‘, ’=‘, ’|‘, ’{‘, ’}‘,
   * ’.‘, ’!‘ must be escaped with the preceding character ’\'.
   * In case of ambiguity between italic and underline entities ‘__’ is always greadily treated from left to right as
   * beginning or end of underline entity, so instead of ___italic underline___ use ___italic underline_\r__,
   * where \r is a character with code 13, which will be ignored.
   *
   * @param text the string to convert
   * @return the parsed text
   * @throws TextParseException if an error occurs when parsing the string
   */
  public static Text parseMarkdown(String text) throws TextParseException {
    if (text == null) {
      return null;
    }
    return Markdown.INSTANCE.parseText(text);
  }
  
  /**
   * Creates a bold text.
   *
   * @param text the bold text
   * @return the created text
   */
  public static Text bold(String text) {
    return new Text(text, new MessageEntity(MessageEntity.Type.BOLD, 0, text.length()));
  }
  
  /**
   * Creates an italic text.
   *
   * @param text the italic text
   * @return the created text
   */
  public static Text italic(String text) {
    return new Text(text, new MessageEntity(MessageEntity.Type.ITALIC, 0, text.length()));
  }
  
  /**
   * Creates a code.
   *
   * @param text the code
   * @return the created text
   */
  public static Text code(String text) {
    return new Text(text, new MessageEntity(MessageEntity.Type.CODE, 0, text.length()));
  }
  
  /**
   * Creates a code block.
   *
   * @param text the code block
   * @return the created text
   */
  public static Text codeBlock(String text) {
    return new Text(text, new MessageEntity(MessageEntity.Type.CODE_BLOCK, 0, text.length()));
  }
  
  /**
   * Creates a link.
   *
   * @param link the link
   * @return the created text
   */
  public static Text link(Link link) {
    return new Text(link.getText(), new MessageEntity(link.getUrl(), 0, link.getText().length()));
  }
  
  /**
   * Creates a link.
   *
   * @param text the text of the link
   * @param url  the url of the link
   * @return the created text
   */
  public static Text link(String text, String url) {
    return new Text(text, new MessageEntity(url, 0, text.length()));
  }
  
  /**
   * Creates a link.
   *
   * @param text    the text of the link
   * @param message the message to link
   * @return the created text
   */
  public static Text link(String text, Message message) {
    return new Text(text, new MessageEntity(message.toUrl(), 0, text.length()));
  }
  
  /**
   * Creates an url.
   *
   * @param url the url
   * @return the created text
   */
  public static Text url(String url) {
    return new Text(url, new MessageEntity(MessageEntity.Type.URL, 0, url.length()));
  }
  
  /**
   * Creates an url.
   *
   * @param message the message to link
   * @return the created text
   */
  public static Text url(Message message) {
    return url(message.toUrl());
  }
  
  /**
   * Creates an email.
   *
   * @param email the email
   * @return the created text
   */
  public static Text email(String email) {
    return new Text(email, new MessageEntity(MessageEntity.Type.EMAIL, 0, email.length()));
  }
  
  /**
   * Creates an hashtag.
   *
   * @param hashtag the hashtag
   * @return the created text
   */
  public static Text hashtag(String hashtag) {
    return new Text("#" + hashtag, new MessageEntity(MessageEntity.Type.HASHTAG, 0, hashtag.length() + 1));
  }
  
  /**
   * Creates a mention.
   *
   * @param mention the mentioned user
   * @return the created text
   */
  public static Text mention(User mention) {
    if (mention.getUsername().isPresent()) {
      String username = mention.getUsername().get();
      return mention(username);
    } else {
      return textMention(mention);
    }
  }
  
  /**
   * Creates a mention.
   *
   * @param mention the mentioned username
   * @return the created text
   */
  public static Text mention(String mention) {
    return new Text("@" + mention, new MessageEntity(MessageEntity.Type.MENTION, 0, mention.length() + 1));
  }
  
  /**
   * Creates a text mention.
   *
   * @param mention the mention
   * @return the created text
   */
  public static Text textMention(Mention mention) {
    return textMention(mention.getText(), mention.getUser());
  }
  
  /**
   * Creates a text mention.
   *
   * @param text    the text of the mention
   * @param mention the mentioned user
   * @return the created text
   */
  public static Text textMention(String text, User mention) {
    return new Text(text, new MessageEntity(mention, 0, text.length()));
  }
  
  /**
   * Creates a text mention.
   *
   * @param text    the text of the mention
   * @param mention the mentioned user id
   * @return the created text
   */
  public static Text textMention(String text, long mention) {
    return new Text(text, new MessageEntity("tg://user?id=" + mention, 0, text.length()));
  }
  
  /**
   * Creates a text mention.
   *
   * @param mention the mentioned user
   * @return the created text
   */
  public static Text textMention(User mention) {
    return textMention(mention.getName(), mention);
  }
  
  /**
   * Creates a bot command.
   *
   * @param botCommand the bot command
   * @return the created text
   */
  public static Text botCommand(String botCommand) {
    return new Text("/" + botCommand, new MessageEntity(MessageEntity.Type.BOT_COMMAND, 0, botCommand.length() + 1));
  }
  
  /**
   * Creates a phone number.
   *
   * @param phoneNumber the phone number
   * @return the created text
   */
  public static Text phoneNumber(String phoneNumber) {
    return new Text(phoneNumber, new MessageEntity(MessageEntity.Type.PHONE_NUMBER, 0, phoneNumber.length()));
  }
  
  /**
   * Creates a cashtag.
   *
   * @param cashtag the cashtag
   * @return the created text
   */
  public static Text cashtag(String cashtag) {
    return new Text("$" + cashtag.toUpperCase(), new MessageEntity(MessageEntity.Type.CASHTAG, 0, cashtag.length() + 1));
  }
  
  /**
   * Creates an underlined text.
   *
   * @param text the underlined text
   * @return the created text
   */
  public static Text underline(String text) {
    return new Text(text, new MessageEntity(MessageEntity.Type.UNDERLINE, 0, text.length()));
  }
  
  /**
   * Creates an strikethrough text.
   *
   * @param text the strikethrough text
   * @return the created text
   */
  public static Text strikethrough(String text) {
    return new Text(text, new MessageEntity(MessageEntity.Type.STRIKETHROUGH, 0, text.length()));
  }
  
  
  /**
   * Returns {@code true} if, and only if, {@link #text} length is {@code 0}.
   *
   * @return {@code true} if {@link #text} length is {@code 0}, otherwise {@code false}
   */
  public boolean isEmpty() {
    return text.isEmpty();
  }
  
  /**
   * Concatenates the specified text to the end of this text.
   *
   * @param other the text that is concatenated to the end of this text.
   * @return a text that represents the concatenation of this object to the other.
   */
  public Text concat(Text other) {
    if (other.isEmpty()) return this;
    if (isEmpty()) return other;
    MessageEntity[] entities = new MessageEntity[this.entities.length + other.entities.length];
    System.arraycopy(this.entities, 0, entities, 0, this.entities.length);
    for (int i = 0; i < other.entities.length; i++) {
      MessageEntity entity = other.entities[i];
      entities[this.entities.length + i] = entity.move(length() + entity.getOffset(), entity.getLength());
    }
    return new Text(text + other.text, entities);
  }
  
  /**
   * Returns a list containing all the bold strings in this text.
   *
   * @return a list containing all the bold strings in this text
   */
  public List<String> getBoldText() {
    return getEntities(MessageEntity.Type.BOLD);
  }
  
  /**
   * Returns a list containing all the italic strings in this text.
   *
   * @return a list containing all the italic strings in this text
   */
  public List<String> getItalicText() {
    return getEntities(MessageEntity.Type.ITALIC);
  }
  
  /**
   * Returns a list containing all the codes in this text.
   *
   * @return a list containing all the codes in this text
   */
  public List<String> getCodeText() {
    return getEntities(MessageEntity.Type.CODE);
  }
  
  /**
   * Returns a list containing all the code blocks in this text.
   *
   * @return a list containing all the code blocks in this text
   */
  public List<String> getCodeBlocks() {
    return getEntities(MessageEntity.Type.CODE_BLOCK);
  }
  
  /**
   * Returns a list containing all the links in this text with their relative urls.
   *
   * @return a list containing all the links in this text with their relative urls
   */
  public List<Link> getLinks() {
    return getEntities(MessageEntity.Type.LINK, entity -> new Link(
        text.substring(entity.getOffset(), entity.getOffset() + entity.getLength()),
        entity.getUrl().orElseThrow(AssertionError::new)
    ));
  }
  
  /**
   * Returns a list containing all the urls in this text.
   *
   * @return a list containing all the urls in this text
   */
  public List<String> getUrls() {
    return getEntities(MessageEntity.Type.URL);
  }
  
  /**
   * Returns a list containing all the emails in this text.
   *
   * @return a list containing all the emails in this text
   */
  public List<String> getEmails() {
    return getEntities(MessageEntity.Type.EMAIL);
  }
  
  /**
   * Returns a list containing all the hashtags in this text.
   *
   * @return a list containing all the hashtags in this text
   */
  public List<String> getHashtags() {
    return getEntities(
        MessageEntity.Type.HASHTAG,
        entity -> text.substring(entity.getOffset() + 1, entity.getOffset() + entity.getLength())
    );
  }
  
  /**
   * Returns a list containing all the mentions in this text.
   *
   * @return a list containing all the mentions in this text
   */
  public List<String> getMentions() {
    return getEntities(
        MessageEntity.Type.MENTION,
        entity -> text.substring(entity.getOffset() + 1, entity.getOffset() + entity.getLength())
    );
  }
  
  /**
   * Returns a list containing all the text mentions in this text with their relative users.
   *
   * @return a list containing all the text mentions in this text with their relative users
   */
  public List<Mention> getTextMentions() {
    return getEntities(MessageEntity.Type.TEXT_MENTION, entity -> new Mention(
        text.substring(entity.getOffset(), entity.getOffset() + entity.getLength()),
        entity.getUser().orElseThrow(AssertionError::new)
    ));
  }
  
  /**
   * Returns a list containing all the bot commands in this text.
   *
   * @return a list containing all the bot commands in this text
   */
  public List<String> getBotCommands() {
    return getEntities(
        MessageEntity.Type.BOT_COMMAND,
        entity -> text.substring(entity.getOffset() + 1, entity.getOffset() + entity.getLength())
    );
  }
  
  /**
   * Returns a list containing all the phone numbers in this text.
   *
   * @return a list containing all the phone numbers in this text
   */
  public List<String> getPhoneNumbers() {
    return getEntities(MessageEntity.Type.PHONE_NUMBER);
  }
  
  /**
   * Returns a list containing all the cashtags in this text.
   *
   * @return a list containing all the cashtags in this text
   */
  public List<String> getCashtags() {
    return getEntities(
        MessageEntity.Type.CASHTAG,
        entity -> text.substring(entity.getOffset() + 1, entity.getOffset() + entity.getLength())
    );
  }
  
  /**
   * Returns a list containing all the underlined strings in this text.
   *
   * @return a list containing all the underlined strings in this text
   */
  public List<String> getUnderlineText() {
    return getEntities(MessageEntity.Type.UNDERLINE);
  }
  
  /**
   * Returns a list containing all the strikethrough strings in this text.
   *
   * @return a list containing all the strikethrough strings in this text
   */
  public List<String> getStrikethroughText() {
    return getEntities(MessageEntity.Type.STRIKETHROUGH);
  }
  
  private <T> List<T> getEntities(MessageEntity.Type type, Function<MessageEntity, T> mapper) {
    List<T> result = new ArrayList<>();
    for (MessageEntity entity : entities) {
      if (entity.getType() == type) {
        result.add(mapper.apply(entity));
      }
    }
    return result;
  }
  
  private List<String> getEntities(MessageEntity.Type type) {
    return getEntities(
        type,
        entity -> text.substring(entity.getOffset(), entity.getOffset() + entity.getLength())
    );
  }
  
  /**
   * Return this text in HTML format.
   *
   * @return this text in HTML format
   */
  public String toHtmlString() {
    return Html.INSTANCE.toString(this);
  }
  
  /**
   * Return this text in MarkdownV2 format.
   *
   * @return this text in MarkdownV2 format
   */
  public String toMarkdownString() {
    return Markdown.INSTANCE.toString(this);
  }
  
  public Text trim() {
    int len = length();
    int start = 0;
    int end = len - 1;
    for (; start < len; start++) {
      if (charAt(start) != ' ') break;
    }
    if (start < len) {
      for (; end >= 0; end--) {
        if (charAt(end) != ' ') break;
      }
    }
    return subSequence(start, end + 1);
  }
  
  @Override
  public int length() {
    return text.length();
  }
  
  @Override
  public char charAt(int index) {
    return text.charAt(index);
  }
  
  @Override
  public Text subSequence(int start, int end) {
    if (start == 0 && end == text.length())
      return this;
    if (start == end)
      return EMPTY;
    String text = this.text.substring(start, end);
    
    List<MessageEntity> entities = new ArrayList<>();
    for (MessageEntity entity : this.entities) {
      int offset = entity.getOffset();
      int length = entity.getLength();
      if (offset >= end) {
        break;
      } else if (offset + length > start) {
        int oldEnd = offset + length;
        int newOffset = offset > start ? offset - start : 0;
        int newEnd = oldEnd < end ? oldEnd - start : end - start;
        entities.add(entity.move(newOffset, newEnd - newOffset));
      }
    }
    return new Text(text, entities.toArray(new MessageEntity[0]));
  }
  
  public Text subSequence(int start) {
    return subSequence(start, length());
  }
  
  @Override
  public String toString() {
    return text;
  }
  
  /**
   * Returns the text entities.
   *
   * @return the text entities
   */
  public List<MessageEntity> getEntities() {
    return Arrays.asList(entities);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Text)) {
      return false;
    }
    Text text = (Text) obj;
    return this.text.equals(text.text) && Arrays.equals(entities, text.entities);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(text, entities);
  }
  
}
