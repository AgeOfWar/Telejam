package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON adapter for class {@link InputMedia}.
 *
 * @author Michi Palazzo
 */
public final class InputMediaAdapter implements JsonDeserializer<InputMedia>, JsonSerializer<InputMedia> {
  
  public static final InputMediaAdapter INSTANCE = new InputMediaAdapter();
  
  private InputMediaAdapter() {
  }
  
  @Override
  public InputMedia deserialize(JsonElement src, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject object = src.getAsJsonObject();
    switch (object.getAsJsonPrimitive(InputMedia.TYPE_FIELD).getAsString()) {
      case InputMediaPhoto.TYPE:
        return context.deserialize(src, InputMediaPhoto.class);
      case InputMediaVideo.TYPE:
        return context.deserialize(src, InputMediaVideo.class);
      case InputMediaAnimation.TYPE:
        return context.deserialize(src, InputMediaAnimation.class);
      case InputMediaAudio.TYPE:
        return context.deserialize(src, InputMediaAudio.class);
      case InputMediaDocument.TYPE:
        return context.deserialize(src, InputMediaDocument.class);
      default:
        throw new JsonParseException("Unknown input media: " + src);
    }
  }
  
  @Override
  public JsonElement serialize(InputMedia inputMedia, Type typeOfT, JsonSerializationContext context) {
    return context.serialize(inputMedia, inputMedia.getClass());
  }
  
}
