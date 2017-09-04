package me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents;

import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.util.text.ParseMode;
import me.palazzomichi.telegram.telejam.util.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the content of a text message to be sent as the result of an inline query.
 *
 * @author Michi Palazzo
 */
public class InputTextMessageContent implements InputMessageContent {

  static final String MESSAGE_TEXT_FIELD = "message_text";
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DISABLE_WEB_PAGE_PREVIEW_FIELD = "disable_web_page_preview";

  /**
   * Text of the message to be sent, 1-4096 characters.
   */
  @SerializedName(MESSAGE_TEXT_FIELD)
  private String messageText;

  /**
   * Send Markdown or HTML, if you want Telegram apps to show bold, italic,
   * fixed-width text or inline URLs in your bot's message.
   */
  @SerializedName(PARSE_MODE_FIELD)
  private String parseMode;

  /**
   * Disables link previews for links in the sent message.
   */
  @SerializedName(DISABLE_WEB_PAGE_PREVIEW_FIELD)
  private Boolean disableWebPagePreview;


  public InputTextMessageContent(String messageText, ParseMode parseMode, Boolean disableWebPagePreview) {
    this.messageText = Objects.requireNonNull(messageText);
    this.parseMode = parseMode == null ? null : parseMode.toString();
    this.disableWebPagePreview = disableWebPagePreview;
  }

  public InputTextMessageContent(Text messageText, Boolean disableWebPagePreview) {
    this(messageText.toString(ParseMode.HTML), ParseMode.HTML, disableWebPagePreview);
  }

  public InputTextMessageContent(Text text) {
    this(text, null);
  }


  /**
   * Getter for property {@link #messageText}.
   *
   * @return value for property {@link #messageText}
   */
  public String getMessageText() {
    return messageText;
  }

  /**
   * Setter for property {@link #messageText}.
   *
   * @param messageText value for property {@link #messageText}
   */
  public void setMessageText(String messageText) {
    this.messageText = Objects.requireNonNull(messageText);
  }

  /**
   * Getter for property {@link #parseMode}.
   *
   * @return value for property {@link #parseMode}
   */
  public Optional<ParseMode> getParseMode() {
    return parseMode == null ? Optional.empty() : Optional.ofNullable(ParseMode.forName(parseMode));
  }

  /**
   * Setter for property {@link #parseMode}.
   *
   * @param parseMode value for property {@link #parseMode}
   */
  public void setParseMode(String parseMode) {
    this.parseMode = parseMode;
  }

  /**
   * Sets the text of the InputTextMessageContent.
   *
   * @param text the text
   */
  public void setText(Text text) {
    this.messageText = text.toString(ParseMode.HTML);
    this.parseMode = ParseMode.HTML.toString();
  }

  /**
   * Getter for property {@link #disableWebPagePreview}.
   *
   * @return value for property {@link #disableWebPagePreview}
   */
  public Boolean getDisableWebPagePreview() {
    return disableWebPagePreview;
  }

  /**
   * Setter for property {@link #disableWebPagePreview}.
   *
   * @param disableWebPagePreview value for property {@link #disableWebPagePreview}
   */
  public void setDisableWebPagePreview(Boolean disableWebPagePreview) {
    this.disableWebPagePreview = disableWebPagePreview;
  }

}
