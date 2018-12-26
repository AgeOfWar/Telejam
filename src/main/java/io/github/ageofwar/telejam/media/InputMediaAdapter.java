package io.github.ageofwar.telejam.media;

import com.google.gson.*;
import io.github.ageofwar.telejam.text.Text;

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
        return new InputMedia(
            (String) context.deserialize(object.get(InputMedia.MEDIA_FIELD), String.class),
            null,
            Text.parseHtml(context.deserialize(object.get(InputMedia.CAPTION_FIELD), String.class))
        ) {
        };
    }
  }
  
  @Override
  public JsonElement serialize(InputMedia inputMedia, Type typeOfT, JsonSerializationContext context) {
    return context.serialize(inputMedia, inputMedia.getClass());
  }
  
}
