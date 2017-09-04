package me.palazzomichi.telegram.telejam.objects.inline.results;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON adapter for class {@link InlineQueryResult}.
 *
 * @author Michi Palazzo
 */
public class InlineQueryResultAdapter implements JsonDeserializer<InlineQueryResult>, JsonSerializer<InlineQueryResult> {

  public static final InlineQueryResultAdapter INSTANCE = new InlineQueryResultAdapter();

  private InlineQueryResultAdapter() {
  }

  @Override
  public InlineQueryResult deserialize(JsonElement src, Type type, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject object = src.getAsJsonObject();
    switch (object.getAsJsonPrimitive(InlineQueryResult.TYPE_FIELD).getAsString()) {
      case InlineQueryResultArticle.TYPE:
        return context.deserialize(src, InlineQueryResultArticle.class);
      case InlineQueryResultPhoto.TYPE:
        return context.deserialize(src, InlineQueryResultPhoto.class);
    }
    throw new JsonParseException("Unknown object: " + src);
  }

  @Override
  public JsonElement serialize(InlineQueryResult inlineQueryResult, Type type, JsonSerializationContext context) {
    return context.serialize(inlineQueryResult, inlineQueryResult.getClass());
  }

}
