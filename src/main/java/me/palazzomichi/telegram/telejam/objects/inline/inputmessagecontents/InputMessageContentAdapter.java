package me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON adapter for class {@link InputMessageContent}.
 *
 * @author Michi Palazzo
 */
public class InputMessageContentAdapter implements JsonDeserializer<InputMessageContent>, JsonSerializer<InputMessageContent> {

  public static final InputMessageContentAdapter INSTANCE = new InputMessageContentAdapter();

  private InputMessageContentAdapter() {
  }

  @Override
  public InputMessageContent deserialize(JsonElement src, Type type, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject object = src.getAsJsonObject();
    if (object.has(InputTextMessageContent.MESSAGE_TEXT_FIELD)) {
      return context.deserialize(src, InputTextMessageContent.class);
    } else if (object.has(InputLocationMessageContent.LATITUDE_FIELD)) {
      if (object.has(InputVenueMessageContent.TITLE_FIELD))
        return context.deserialize(src, InputVenueMessageContent.class);
      else return context.deserialize(src, InputLocationMessageContent.class);
    } else if (object.has(InputContactMessageContent.PHONE_NUMBER_FIELD)) {
      return context.deserialize(src, InputContactMessageContent.class);
    }
    return new InputMessageContent() {}; // unknown input message content
  }

  @Override
  public JsonElement serialize(InputMessageContent inputMessageContent, Type type, JsonSerializationContext context) {
    return context.serialize(inputMessageContent, inputMessageContent.getClass());
  }

}
