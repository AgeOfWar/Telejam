package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.text.Text;

/**
 * Represents the content of a text message to be sent as the result of an inline query.
 *
 * @author Michi Palazzo
 */
public class InputTextMessageContent implements InputMessageContent {

  static final String MESSAGE_TEXT_FIELD = "message_text";
  static final String PARSE_MODE_FIELD = "parse_mode";
  static final String DISABLE_WEB_PAGE_PREVIEW_FIELD = "disable_web_page_preview";
  
  @Expose
  @SerializedName(PARSE_MODE_FIELD)
  private static final String PARSE_MODE = "HTML";
  
  /**
   * Text of the message to be sent, 1-4096 characters.
   */
  @SerializedName(MESSAGE_TEXT_FIELD)
  private final String messageText;

  /**
   * Disables link previews for links in the sent message.
   */
  @SerializedName(DISABLE_WEB_PAGE_PREVIEW_FIELD)
  private final Boolean disableWebPagePreview;


  public InputTextMessageContent(Text text, Boolean disableWebPagePreview) {
    messageText = text.toHtmlString();
    this.disableWebPagePreview = disableWebPagePreview;
  }

  public InputTextMessageContent(Text text) {
    this(text, null);
  }


  /**
   * Getter for property {@link #messageText}.
   *
   * @return value for property {@link #messageText}
   */
  public Text getMessageText() {
    return Text.parseHtml(messageText);
  }

  /**
   * Getter for property {@link #disableWebPagePreview}.
   *
   * @return value for property {@link #disableWebPagePreview}
   */
  public Boolean getDisableWebPagePreview() {
    return disableWebPagePreview;
  }

}
