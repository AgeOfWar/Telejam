package me.palazzomichi.telegram.telejam.objects.chats;

import com.google.gson.*;
import me.palazzomichi.telegram.telejam.objects.ChatPhoto;

import java.lang.reflect.Type;

/**
 * JSON Adapter for class {@link Chat}.
 *
 * @author Michi Palazzo
 */
public class ChatAdapter implements JsonDeserializer<Chat>, JsonSerializer<Chat> {

  public static final ChatAdapter INSTANCE = new ChatAdapter();

  private ChatAdapter() {
  }

  @Override
  public Chat deserialize(JsonElement src, Type type, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject object = src.getAsJsonObject();
    String chatType = object.getAsJsonPrimitive(Chat.TYPE_FIELD).getAsString();
    switch (chatType) {
      case PrivateChat.TYPE:
        return context.deserialize(src, PrivateChat.class);
      case Group.TYPE:
        return context.deserialize(src, Group.class);
      case SuperGroup.TYPE:
        return context.deserialize(src, SuperGroup.class);
      case Channel.TYPE:
        return context.deserialize(src, Channel.class);
      default:
        long id = object.getAsJsonPrimitive(Chat.ID_FIELD).getAsLong();
        ChatPhoto chatPhoto = object.has(Chat.PHOTO_FIELD) ?
            context.deserialize(object.get(Chat.PHOTO_FIELD), ChatPhoto.class) :
            null;
        return new Chat(id, chatPhoto) {
          @Override
          public String getTitle() {
            return null;
          }
        }; // unknown chat
    }
  }

  @Override
  public JsonElement serialize(Chat chat, Type type, JsonSerializationContext context) {
    return context.serialize(chat, chat.getClass());
  }

}
