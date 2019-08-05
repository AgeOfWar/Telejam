package io.github.ageofwar.telejam.messages;

import com.google.gson.*;
import io.github.ageofwar.telejam.chats.Chat;
import io.github.ageofwar.telejam.users.User;

import java.lang.reflect.Type;

/**
 * JSON Adapter for class {@link ForwardMessageAdapter}.
 *
 * @author Michi Palazzo
 */
public final class ForwardMessageAdapter implements JsonSerializer<Forward<?>>, JsonDeserializer<Forward<?>> {
  
  public static final ForwardMessageAdapter INSTANCE = new ForwardMessageAdapter();
  
  private ForwardMessageAdapter() {
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Forward<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject object = json.getAsJsonObject();
  
    long id = object.get(Message.ID_FIELD).getAsLong();
    User sender = object.has(Message.SENDER_FIELD) ? context.deserialize(object.getAsJsonObject(Message.SENDER_FIELD), User.class) : null;
    long date = object.get(Message.DATE_FIELD).getAsLong();
    Chat chat = context.deserialize(object.getAsJsonObject(Message.CHAT_FIELD), Chat.class);
    
    object.add(Message.SENDER_FIELD, object.get(Forward.FORWARD_MESSAGE_SENDER_FIELD));
    object.add(Message.CHAT_FIELD, object.get(Forward.FORWARD_MESSAGE_CHAT_FIELD));
    object.add(Message.ID_FIELD, object.get(Forward.FORWARD_MESSAGE_ID_FIELD));
    object.add(Message.DATE_FIELD, object.get(Forward.FORWARD_MESSAGE_DATE_FIELD));
    object.add(Message.AUTHOR_SIGNATURE_FIELD, object.get(Forward.FORWARD_SIGNATURE_FIELD));
    
    object.remove(Forward.FORWARD_MESSAGE_SENDER_FIELD);
    object.remove(Forward.FORWARD_MESSAGE_CHAT_FIELD);
    object.remove(Forward.FORWARD_MESSAGE_ID_FIELD);
    object.remove(Forward.FORWARD_MESSAGE_DATE_FIELD);
    object.remove(Forward.FORWARD_SIGNATURE_FIELD);
    object.remove(Forward.FORWARD_MESSAGE_SENDER_NAME_FIELD);
    
    return new Forward(
        id, sender, date, chat, context.deserialize(object, Message.class)
    );
  }
  
  @Override
  public JsonElement serialize(Forward<?> message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject object = context.serialize(message.getForwardedMessage()).getAsJsonObject();
    object.add(Forward.FORWARD_MESSAGE_SENDER_FIELD, object.get(Message.SENDER_FIELD));
    object.add(Forward.FORWARD_MESSAGE_CHAT_FIELD, object.get(Message.CHAT_FIELD));
    object.add(Forward.FORWARD_MESSAGE_ID_FIELD, object.get(Message.ID_FIELD));
    object.add(Forward.FORWARD_MESSAGE_DATE_FIELD, object.get(Message.DATE_FIELD));
    object.add(Forward.FORWARD_SIGNATURE_FIELD, object.get(Message.AUTHOR_SIGNATURE_FIELD));
    
    object.addProperty(Message.ID_FIELD, message.getId());
    object.add(Message.SENDER_FIELD, context.serialize(message.getSender()));
    object.addProperty(Message.DATE_FIELD, message.getDate());
    object.add(Message.CHAT_FIELD, context.serialize(message.getDate()));
    if (message.getAuthorSignature().isPresent())
      object.add(Message.AUTHOR_SIGNATURE_FIELD, context.serialize(message.getAuthorSignature().get()));
    else object.remove(Message.AUTHOR_SIGNATURE_FIELD);
    
    return object;
  }
  
}
