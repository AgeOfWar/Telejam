package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON Adapter for class {@link Update}.
 *
 * @author Michi Palazzo
 */
public final class UpdateAdapter implements JsonDeserializer<Update>, JsonSerializer<Update> {

  public static final UpdateAdapter INSTANCE = new UpdateAdapter();

  private UpdateAdapter() {
  }

  @Override
  public Update deserialize(JsonElement src, Type type, JsonDeserializationContext context) throws JsonParseException {
    JsonObject object = src.getAsJsonObject();
    if (object.has(MessageUpdate.MESSAGE_FIELD)) {
      return context.deserialize(src, MessageUpdate.class);
    } else if (object.has(EditedMessageUpdate.EDITED_MESSAGE_FIELD)) {
      return context.deserialize(src, EditedMessageUpdate.class);
    } else if (object.has(ChannelPostUpdate.CHANNEL_POST_FIELD)) {
      return context.deserialize(src, ChannelPostUpdate.class);
    } else if (object.has(EditedChannelPostUpdate.EDITED_CHANNEL_POST_FIELD)) {
      return context.deserialize(src, EditedChannelPostUpdate.class);
    } else if (object.has(InlineQueryUpdate.INLINE_QUERY_FIELD)) {
      return context.deserialize(src, InlineQueryUpdate.class);
    } else if (object.has(ChosenInlineResultUpdate.CHOSEN_INLINE_RESULT_FIELD)) {
      return context.deserialize(src, ChosenInlineResultUpdate.class);
    } else if (object.has(CallbackQueryUpdate.CALLBACK_QUERY_FIELD)) {
      return context.deserialize(src, CallbackQueryUpdate.class);
    } else if (object.has(ShippingQueryUpdate.SHIPPING_QUERY_FIELD)) {
      return context.deserialize(src, ShippingQueryUpdate.class);
    } else if (object.has(PreCheckoutQueryUpdate.PRE_CHECKOUT_QUERY_FIELD)) {
      return context.deserialize(src, PreCheckoutQueryUpdate.class);
    }
    throw new JsonParseException("Unknown update: " + src);
  }

  @Override
  public JsonElement serialize(Update update, Type type, JsonSerializationContext context) {
    return context.serialize(update, update.getClass());
  }

}
