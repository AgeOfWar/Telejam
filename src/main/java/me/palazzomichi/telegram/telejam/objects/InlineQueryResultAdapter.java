package me.palazzomichi.telegram.telejam.objects;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON adapter for class {@link InlineQueryResult}.
 *
 * @author Michi Palazzo
 */
public final class InlineQueryResultAdapter implements JsonDeserializer<InlineQueryResult>, JsonSerializer<InlineQueryResult> {

  public static final InlineQueryResultAdapter INSTANCE = new InlineQueryResultAdapter();

  private InlineQueryResultAdapter() {
  }

  @Override
  public InlineQueryResult deserialize(JsonElement src, Type type, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject object = src.getAsJsonObject();
    switch (object.getAsJsonPrimitive(InlineQueryResult.TYPE_FIELD).getAsString()) {
      case InlineQueryResultArticle.TYPE:
        return context.deserialize(src, InlineQueryResultArticle.class);
      case InlineQueryResultPhoto.TYPE:
        return object.has(InlineQueryResultCachedPhoto.PHOTO_FILE_ID_FIELD) ?
            context.deserialize(src, InlineQueryResultCachedPhoto.class) :
            context.deserialize(src, InlineQueryResultPhoto.class);
      case InlineQueryResultGif.TYPE:
        return object.has(InlineQueryResultCachedGif.GIF_FILE_ID_FIELD) ?
            context.deserialize(src, InlineQueryResultCachedGif.class) :
            context.deserialize(src, InlineQueryResultGif.class);
      case InlineQueryResultMpeg4Gif.TYPE:
        return object.has(InlineQueryResultCachedMpeg4Gif.MPEG4_FILE_ID_FIELD) ?
            context.deserialize(src, InlineQueryResultCachedMpeg4Gif.class) :
            context.deserialize(src, InlineQueryResultMpeg4Gif.class);
      case InlineQueryResultVideo.TYPE:
        return object.has(InlineQueryResultCachedVideo.VIDEO_FILE_ID_FIELD) ?
            context.deserialize(src, InlineQueryResultCachedVideo.class) :
            context.deserialize(src, InlineQueryResultVideo.class);
      case InlineQueryResultAudio.TYPE:
        return object.has(InlineQueryResultCachedAudio.AUDIO_FILE_ID_FIELD) ?
            context.deserialize(src, InlineQueryResultAudio.class) :
            context.deserialize(src, InlineQueryResultAudio.class);
      case InlineQueryResultVoice.TYPE:
        return object.has(InlineQueryResultCachedVoice.VOICE_FILE_ID_FIELD) ?
            context.deserialize(src, InlineQueryResultCachedVoice.class) :
            context.deserialize(src, InlineQueryResultVoice.class);
      case InlineQueryResultDocument.TYPE:
        return object.has(InlineQueryResultCachedDocument.DOCUMENT_FILE_ID_FIELD) ?
            context.deserialize(src, InlineQueryResultCachedDocument.class) :
            context.deserialize(src, InlineQueryResultDocument.class);
      case InlineQueryResultLocation.TYPE:
        return context.deserialize(src, InlineQueryResultLocation.class);
      case InlineQueryResultVenue.TYPE:
        return context.deserialize(src, InlineQueryResultVenue.class);
      case InlineQueryResultContact.TYPE:
        return context.deserialize(src, InlineQueryResultContact.class);
      case InlineQueryResultGame.TYPE:
        return context.deserialize(src, InlineQueryResultGame.class);
      case InlineQueryResultCachedSticker.TYPE:
        return context.deserialize(src, InlineQueryResultCachedSticker.class);
      default:
        throw new JsonParseException("Unknown inline query result: " + src);
    }
  }

  @Override
  public JsonElement serialize(InlineQueryResult inlineQueryResult, Type type, JsonSerializationContext context) {
    return context.serialize(inlineQueryResult, inlineQueryResult.getClass());
  }

}
