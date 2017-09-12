package me.palazzomichi.telegram.telejam.methods;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.updates.Update;

/**
 * Use this method to receive incoming updates using long polling.
 * An Array of Update objects is returned.
 *
 * @author Michi Palazzo
 */
public class GetUpdates extends JsonTelegramMethod<Update[]> {

  public static final String NAME = "getUpdates";

  static final String OFFSET_FIELD = "offset";
  static final String LIMIT_FIELD = "limit";
  static final String TIMEOUT_FIELD = "timeout";
  static final String ALLOWED_UPDATES_FIELD = "allowed_updates";

  /**
   * Identifier of the first update to be returned.
   * Must be greater by one than the highest among the identifiers of previously received updates.
   * By default, updates starting with the earliest unconfirmed update are returned. An update is
   * considered confirmed as soon as getUpdates is called with an offset higher than its update_id.
   * The negative offset can be specified to retrieve updates starting from -offset update from
   * the end of the updates queue.
   * All previous updates will forgotten.
   */
  @SerializedName(OFFSET_FIELD)
  private Long offset;

  /**
   * Limits the number of updates to be retrieved.
   * Values between 1—100 are accepted. Defaults to 100.
   */
  @SerializedName(LIMIT_FIELD)
  private Integer limit;

  /**
   * Timeout in seconds for long polling.
   * Defaults to 0, i.e. usual short polling.
   * Should be positive, short polling should be used for testing purposes only.
   */
  @SerializedName(TIMEOUT_FIELD)
  private Integer timeout;

  /**
   * List the types of updates you want your bot to receive.
   * For example, specify ["message", "edited_channel_post", "callback_query"] to
   * only receive updates of these types.
   * See <a href='https://core.telegram.org/bots/api#update'>Update</a> for a
   * complete list of available update types.
   * Specify an empty list to receive all updates regardless of type (default).
   * If not specified, the previous setting will be used.
   *
   * Please note that this parameter doesn't affect updates created before the call to
   * the getUpdates, so unwanted updates may be received for a short period of time.
   */
  @SerializedName(ALLOWED_UPDATES_FIELD)
  private String[] allowedUpdates;


  public GetUpdates offset(Long offset) {
    this.offset = offset;
    return this;
  }

  public GetUpdates limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  public GetUpdates timeout(Integer timeout) {
    this.timeout = timeout;
    return this;
  }

  public GetUpdates allowedUpdates(String... allowedUpdates) {
    this.allowedUpdates = allowedUpdates;
    return this;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Class<? extends Update[]> getReturnType(JsonElement response) {
    return Update[].class;
  }

}
