package io.github.ageofwar.telejam.inline;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON adapter for class {@link InlineKeyboardButton}.
 *
 * @author Michi Palazzo
 */
public final class InlineKeyboardButtonAdapter implements JsonSerializer<InlineKeyboardButton>, JsonDeserializer<InlineKeyboardButton> {
  
  public static final InlineKeyboardButtonAdapter INSTANCE = new InlineKeyboardButtonAdapter();
  
  private InlineKeyboardButtonAdapter() {
  }
  
  @Override
  public InlineKeyboardButton deserialize(JsonElement src, Type type, JsonDeserializationContext context) throws JsonParseException {
    JsonObject object = src.getAsJsonObject();
    if (object.has(UrlInlineKeyboardButton.URL_FIELD)) {
      return context.deserialize(src, UrlInlineKeyboardButton.class);
    } else if (object.has(CallbackDataInlineKeyboardButton.CALLBACK_DATA_FIELD)) {
      return context.deserialize(src, CallbackDataInlineKeyboardButton.class);
    } else if (object.has(SwitchInlineQueryInlineKeyboardButton.SWITCH_INLINE_QUERY_FIELD)) {
      return context.deserialize(src, SwitchInlineQueryInlineKeyboardButton.class);
    } else if (object.has(SwitchInlineQueryCurrentChatInlineKeyboardButton.SWITCH_INLINE_QUERY_CURRENT_CHAT_FIELD)) {
      return context.deserialize(src, SwitchInlineQueryCurrentChatInlineKeyboardButton.class);
    } else if (object.has(CallbackGameInlineKeyboardButton.CALLBACK_GAME_FIELD)) {
      return context.deserialize(src, CallbackGameInlineKeyboardButton.class);
    } else if (object.has(PayInlineKeyboardButton.PAY_FIELD)) {
      return context.deserialize(src, PayInlineKeyboardButton.class);
    } else if (object.has(LoginUrlInlineKeyboardButton.LOGIN_URL_FIELD)) {
      return context.deserialize(src, LoginUrlInlineKeyboardButton.class);
    }
    return new InlineKeyboardButton(
        context.deserialize(object.get(InlineKeyboardButton.TEXT_FIELD), String.class)
    ) {
    };
  }
  
  @Override
  public JsonElement serialize(InlineKeyboardButton button, Type type, JsonSerializationContext context) {
    return context.serialize(button, button.getClass());
  }
  
}
