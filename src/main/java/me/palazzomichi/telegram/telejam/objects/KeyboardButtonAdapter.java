package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON adapter for class {@link KeyboardButton}.
 *
 * @author Michi Palazzo
 */
public final class KeyboardButtonAdapter implements JsonSerializer<KeyboardButton>, JsonDeserializer<KeyboardButton> {

  public static final KeyboardButtonAdapter INSTANCE = new KeyboardButtonAdapter();

  private KeyboardButtonAdapter() {
  }

  @Override
  public KeyboardButton deserialize(JsonElement src, Type type, JsonDeserializationContext context) throws JsonParseException {
    JsonObject object = src.getAsJsonObject();
    if (object.has(RequestContactKeyboardButton.REQUEST_CONTACT_FIELD)) {
      if (object.get(RequestContactKeyboardButton.REQUEST_CONTACT_FIELD).getAsBoolean()) {
        return context.deserialize(src, RequestContactKeyboardButton.class);
      }
    } else if (object.has(RequestLocationKeyboardButton.REQUEST_LOCATION_FIELD)) {
      if (object.get(RequestLocationKeyboardButton.REQUEST_LOCATION_FIELD).getAsBoolean()) {
        return context.deserialize(src, RequestLocationKeyboardButton.class);
      }
    }
    return new KeyboardButton(object.get(KeyboardButton.TEXT_FIELD).getAsString());
  }

  @Override
  public JsonElement serialize(KeyboardButton button, Type type, JsonSerializationContext context) {
    if (button.getClass() == KeyboardButton.class) {
      JsonObject src = new JsonObject();
      src.addProperty(KeyboardButton.TEXT_FIELD, button.getText());
      return src;
    }
    return context.serialize(button, button.getClass());
  }

}
