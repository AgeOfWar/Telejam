package me.palazzomichi.telegram.telejam.util.text;

/**
 * Enum of parsing modalities.
 *
 * @author Michi Palazzo
 */
public enum ParseMode {

  /**
   * Markdown style:<br><br>
   * <code>
   * *bold text*<br>
   * _italic text_<br>
   * [text](http://www.example.com)<br>
   * `inline fixed-width code`<br>
   * ```text<br>
   * pre-formatted fixed-width code block<br>
   * ```<br>
   * </code>
   */
  MARKDOWN("Markdown", MarkdownTextParser.INSTANCE, MarkdownTextWriter.INSTANCE),

  /**
   * HTML style:<br><br>
   * <code>
   * &lt;b&gt;bold&lt;/b&gt;, &lt;strong&gt;bold&lt;/strong&gt;<br>
   * &lt;i&gt;italic&lt;/i&gt;, &lt;em&gt;italic&lt;/em&gt;<br>
   * &lt;a href="http://www.example.com"&gt;inline URL&lt;/a&gt;<br>
   * &lt;code&gt;inline fixed-width code&lt;/code&gt;<br>
   * &lt;pre&gt;pre-formatted fixed-width code block&lt;/pre&gt;
   * </code>
   */
  HTML("HTML", HtmlTextParser.INSTANCE, HtmlTextWriter.INSTANCE);

  private final TextParser reader;
  private final TextWriter writer;

  private final String toString;

  public static ParseMode forName(String name) {
    for (ParseMode value : values()) {
      if (value.toString().equals(name)) {
        return value;
      }
    }
    return null;
  }

  ParseMode(String toString, TextParser reader, TextWriter writer) {
    this.toString = toString;
    this.reader = reader;
    this.writer = writer;
  }

  /**
   * Getter for property {@link #reader}.
   *
   * @return value for property {@link #reader}
   */
  public TextParser getParser() {
    return reader;
  }

  /**
   * Getter for property {@link #writer}.
   *
   * @return value for property {@link #writer}
   */
  public TextWriter getWriter() {
    return writer;
  }

  @Override
  public String toString() {
    return toString;
  }

}
