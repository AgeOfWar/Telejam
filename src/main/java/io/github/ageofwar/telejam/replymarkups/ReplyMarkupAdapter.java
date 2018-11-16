package io.github.ageofwar.telejam.replymarkups;

import com.google.gson.*;

import java.lang.reflect.Type;


/**
 * JSON Adapter for class {@link ReplyMarkup}.
 *
 * @author Michi Palazzo
 */
public final class ReplyMarkupAdapter implements JsonSerializer<ReplyMarkup>, JsonDeserializer<ReplyMarkup> {
  
  public static final ReplyMarkupAdapter INSTANCE = new ReplyMarkupAdapter();
  
  private ReplyMarkupAdapter() {
  }
  
  @Override
  public ReplyMarkup deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject obj = json.getAsJsonObject();
    if (obj.has(ReplyKeyboardMarkup.KEYBOARD_FIELD)) {
      return context.deserialize(json, ReplyKeyboardMarkup.class);
    } else if (obj.has(InlineKeyboardMarkup.INLINE_KEYBOARD_FIELD)) {
      return context.deserialize(json, InlineKeyboardMarkup.class);
    } else if (obj.has(ForceReply.FORCE_REPLY_FIELD)) {
      return context.deserialize(json, ForceReply.class);
    } else if (obj.has(ReplyKeyboardRemove.REMOVE_KEYBOARD_FIELD)) {
      return context.deserialize(json, ReplyKeyboardRemove.class);
    }
    throw new JsonParseException("Unknown reply markup: " + json);
  }
  
  @Override
  public JsonElement serialize(ReplyMarkup replyMarkup, Type typeOfSrc, JsonSerializationContext context) {
    return context.serialize(replyMarkup, replyMarkup.getClass());
  }
  
}
