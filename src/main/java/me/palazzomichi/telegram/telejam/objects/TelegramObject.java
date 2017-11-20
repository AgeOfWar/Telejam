package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import me.palazzomichi.telegram.telejam.objects.chats.Chat;
import me.palazzomichi.telegram.telejam.objects.chats.ChatAdapter;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContent;
import me.palazzomichi.telegram.telejam.objects.inline.inputmessagecontents.InputMessageContentAdapter;
import me.palazzomichi.telegram.telejam.objects.inline.results.InlineQueryResult;
import me.palazzomichi.telegram.telejam.objects.inline.results.InlineQueryResultAdapter;
import me.palazzomichi.telegram.telejam.objects.messages.Forward;
import me.palazzomichi.telegram.telejam.objects.messages.ForwardMessageAdapter;
import me.palazzomichi.telegram.telejam.objects.messages.Message;
import me.palazzomichi.telegram.telejam.objects.messages.MessageAdapter;
import me.palazzomichi.telegram.telejam.objects.replymarkups.inlinekeyboardbuttons.InlineKeyboardButton;
import me.palazzomichi.telegram.telejam.objects.replymarkups.inlinekeyboardbuttons.InlineKeyboardButtonAdapter;
import me.palazzomichi.telegram.telejam.objects.replymarkups.keyboardbuttons.KeyboardButton;
import me.palazzomichi.telegram.telejam.objects.replymarkups.keyboardbuttons.KeyboardButtonAdapter;
import me.palazzomichi.telegram.telejam.objects.updates.Update;
import me.palazzomichi.telegram.telejam.objects.updates.UpdateAdapter;

import java.io.Serializable;
import java.lang.reflect.Modifier;

/**
 * Class representing a Telegram object.
 *
 * @author Michi Palazzo
 */
public interface TelegramObject extends Serializable {

  /**
   * Utility object for JSON serialization and deserialization.
   */
  Gson gson = new GsonBuilder()
      .excludeFieldsWithModifiers(Modifier.TRANSIENT)
      .addSerializationExclusionStrategy(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
          return fieldAttributes.getAnnotation(SerializedName.class) == null;
        }
  
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
          return false;
        }
      }).addDeserializationExclusionStrategy(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
          return fieldAttributes.hasModifier(Modifier.FINAL) ||
              fieldAttributes.hasModifier(Modifier.STATIC) ||
              fieldAttributes.getAnnotation(SerializedName.class) == null;
        }
      
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
          return false;
        }
      }).registerTypeAdapter(Update.class, UpdateAdapter.INSTANCE)
      .registerTypeAdapter(Chat.class, ChatAdapter.INSTANCE)
      .registerTypeAdapter(Message.class, MessageAdapter.INSTANCE)
      .registerTypeAdapter(Forward.class, ForwardMessageAdapter.INSTANCE)
      .registerTypeAdapter(KeyboardButton.class, KeyboardButtonAdapter.INSTANCE)
      .registerTypeAdapter(InlineKeyboardButton.class, InlineKeyboardButtonAdapter.INSTANCE)
      .registerTypeAdapter(InlineQueryResult.class, InlineQueryResultAdapter.INSTANCE)
      .registerTypeAdapter(InputMessageContent.class, InputMessageContentAdapter.INSTANCE)
      .create();

  /**
   * Returns the JSON representation of this object.
   *
   * @return the JSON representation of this object
   */
  default String toJson() {
    return gson.toJson(this, getClass());
  }

}
