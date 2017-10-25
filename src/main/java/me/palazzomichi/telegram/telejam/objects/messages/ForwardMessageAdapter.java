package me.palazzomichi.telegram.telejam.objects.messages;

import com.google.gson.*;
import me.palazzomichi.telegram.telejam.objects.User;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;

import java.lang.reflect.Type;

/**
 * JSON Adapter for class {@link ForwardMessageAdapter}.
 *
 * @author Michi Palazzo
 */
public class ForwardMessageAdapter implements JsonSerializer<ForwardMessage<?>>, JsonDeserializer<ForwardMessage<?>> {
  
  public static final ForwardMessageAdapter INSTANCE = new ForwardMessageAdapter();
  
  private ForwardMessageAdapter() {
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public ForwardMessage<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject object = json.getAsJsonObject();
    
    long id = object.getAsJsonPrimitive(Message.ID_FIELD).getAsLong();
    User sender = context.deserialize(object.getAsJsonObject(Message.SENDER_FIELD), User.class);
    long date = object.getAsJsonPrimitive(Message.DATE_FIELD).getAsLong();
    Chat chat = context.deserialize(object.getAsJsonObject(Message.CHAT_FIELD), Chat.class);
    String authorSignature = object.has(Message.AUTHOR_SIGNATURE_FIELD) ?
        object.getAsJsonPrimitive(Message.AUTHOR_SIGNATURE_FIELD).getAsString() :
        null;
    
    object.add(Message.SENDER_FIELD, object.get(ForwardMessage.FORWARD_MESSAGE_SENDER_FIELD));
    object.add(Message.CHAT_FIELD, object.get(ForwardMessage.FORWARD_MESSAGE_CHAT_FIELD));
    object.add(Message.ID_FIELD, object.get(ForwardMessage.FORWARD_MESSAGE_ID_FIELD));
    object.add(Message.DATE_FIELD, object.get(ForwardMessage.FORWARD_MESSAGE_DATE_FIELD));
    object.add(Message.AUTHOR_SIGNATURE_FIELD, object.get(ForwardMessage.FORWARD_SIGNATURE_FIELD));
    
    object.remove(ForwardMessage.FORWARD_MESSAGE_SENDER_FIELD);
    object.remove(ForwardMessage.FORWARD_MESSAGE_CHAT_FIELD);
    object.remove(ForwardMessage.FORWARD_MESSAGE_ID_FIELD);
    object.remove(ForwardMessage.FORWARD_MESSAGE_DATE_FIELD);
    object.remove(ForwardMessage.FORWARD_SIGNATURE_FIELD);
    
    return new ForwardMessage(
        id, sender, date, chat, authorSignature, context.deserialize(object, Message.class)
    );
  }
  
  @Override
  public JsonElement serialize(ForwardMessage<?> message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject object = context.serialize(message.getForwardedMessage()).getAsJsonObject();
    object.add(ForwardMessage.FORWARD_MESSAGE_SENDER_FIELD, object.get(Message.SENDER_FIELD));
    object.add(ForwardMessage.FORWARD_MESSAGE_CHAT_FIELD, object.get(Message.CHAT_FIELD));
    object.add(ForwardMessage.FORWARD_MESSAGE_ID_FIELD, object.get(Message.ID_FIELD));
    object.add(ForwardMessage.FORWARD_MESSAGE_DATE_FIELD, object.get(Message.DATE_FIELD));
    object.add(ForwardMessage.FORWARD_SIGNATURE_FIELD, object.get(Message.AUTHOR_SIGNATURE_FIELD));
    
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
