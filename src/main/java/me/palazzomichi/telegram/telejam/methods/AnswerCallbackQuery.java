package me.palazzomichi.telegram.telejam.methods;

import me.palazzomichi.telegram.telejam.objects.CallbackQuery;

import java.util.Map;

import static me.palazzomichi.telegram.telejam.methods.util.Maps.mapOf;

/**
 * Use this method to send answers to callback queries sent from inline keyboards.
 * The answer will be displayed to the user as a notification at the top of the
 * chat screen or as an alert.
 * On success, True is returned.
 *
 * @author Michi Palazzo
 */
public class AnswerCallbackQuery implements TelegramMethod<Boolean> {

  public static final String NAME = "answerCallbackQuery";

  static final String CALLBACK_QUERY_ID_FIELD = "callback_query_id";
  static final String TEXT_FIELD = "text";
  static final String SHOW_ALERT_FIELD = "show_alert";
  static final String URL_FIELD = "url";
  static final String CACHE_TIME_FIELD = "cache_time";

  /**
   * Unique identifier for the query to be answered.
   */
  private String callbackQueryId;

  /**
   * Text of the notification.
   * If not specified, nothing will be shown to the user, 0-200 characters.
   */
  private String text;

  /**
   * If true, an alert will be shown by the client instead of
   * a notification at the top of the chat screen.
   * Defaults to false.
   */
  private Boolean showAlert;

  /**
   * URL that will be opened by the user's client.
   * If you have created a Game and accepted the conditions via @Botfather,
   * specify the URL that opens your game – note that this will only work
   * if the query comes from a callback_game button.
   *
   * Otherwise, you may use links like <code>t.me/your_bot?start=XXXX</code>
   * that open your bot with a parameter.
   */
  private String url;

  /**
   * The maximum amount of time in seconds that the result of the
   * callback query may be cached client-side.
   * Telegram apps will support caching starting in version 3.14.
   * Defaults to 0.
   */
  private Integer cacheTime;


  public AnswerCallbackQuery callbackQuery(String callbackQueryId) {
    this.callbackQueryId = callbackQueryId;
    return this;
  }

  public AnswerCallbackQuery callbackQuery(CallbackQuery callbackQuery) {
    callbackQueryId = callbackQuery.getId();
    return this;
  }

  public AnswerCallbackQuery text(String text) {
    this.text = text;
    return this;
  }

  public AnswerCallbackQuery showAlert(Boolean showAlert) {
    this.showAlert = showAlert;
    return this;
  }

  public AnswerCallbackQuery showAlert() {
    showAlert = true;
    return this;
  }

  public AnswerCallbackQuery url(String url) {
    this.url = url;
    return this;
  }

  public AnswerCallbackQuery cacheTime(Integer cacheTime) {
    this.cacheTime = cacheTime;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }
  
  @Override
  public Map<String, Object> getParameters() {
    return mapOf(
        CALLBACK_QUERY_ID_FIELD, callbackQueryId,
        TEXT_FIELD, text,
        SHOW_ALERT_FIELD, showAlert,
        URL_FIELD, url,
        CACHE_TIME_FIELD, cacheTime
    );
  }
  
  @Override
  public Class<Boolean> getReturnType() {
    return Boolean.class;
  }

}
