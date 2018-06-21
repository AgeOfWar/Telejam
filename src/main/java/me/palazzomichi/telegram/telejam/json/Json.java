package me.palazzomichi.telegram.telejam.json;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import me.palazzomichi.telegram.telejam.objects.*;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Locale;

/**
 * Utility class for JSON serialization and deserialization.
 */
public final class Json {
  
  private static final Gson gson = new GsonBuilder()
      .excludeFieldsWithModifiers(Modifier.TRANSIENT)
      .addSerializationExclusionStrategy(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
          return fieldAttributes.getAnnotation(SerializedName.class) == null &&
              fieldAttributes.getAnnotation(JsonAdapter.class) == null;
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
              (fieldAttributes.getAnnotation(SerializedName.class) == null &&
                  fieldAttributes.getAnnotation(JsonAdapter.class) == null);
        }
      
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
          return false;
        }
      }).registerTypeAdapter(Locale.class, LocaleTypeAdapter.INSTANCE)
      .registerTypeAdapter(Update.class, UpdateAdapter.INSTANCE)
      .registerTypeAdapter(Chat.class, ChatAdapter.INSTANCE)
      .registerTypeAdapter(Message.class, MessageAdapter.INSTANCE)
      .registerTypeAdapter(Forward.class, ForwardMessageAdapter.INSTANCE)
      .registerTypeAdapter(KeyboardButton.class, KeyboardButtonAdapter.INSTANCE)
      .registerTypeAdapter(InlineKeyboardButton.class, InlineKeyboardButtonAdapter.INSTANCE)
      .registerTypeAdapter(InlineQueryResult.class, InlineQueryResultAdapter.INSTANCE)
      .registerTypeAdapter(InputMessageContent.class, InputMessageContentAdapter.INSTANCE)
      .registerTypeAdapter(InputMedia.class, InputMediaAdapter.INSTANCE)
      .create();
  
  /**
   * This method serializes the specified object into its equivalent Json representation.
   * This method should be used when the specified object is not a generic type. This method uses
   * {@link Class#getClass()} to get the type for the specified object, but the
   * {@code getClass()} loses the generic type information because of the Type Erasure feature
   * of Java. Note that this method works fine if the any of the object fields are of generic type,
   * just the object itself should not be of a generic type. If the object is of generic type, use
   * {@link #toJson(Object, Type)} instead. If you want to write out the object to a
   * {@link java.io.Writer}, use {@link #toJson(Object, Appendable)} instead.
   *
   * @param src the object for which Json representation is to be created
   * @return Json representation of {@code src}.
   */
  public static String toJson(Object src) {
    Class<?> clazz = src.getClass();
    return gson.toJson(src, clazz.isAnonymousClass() ? clazz.getSuperclass() : clazz);
  }
  
  /**
   * This method serializes the specified object, including those of generic types, into its
   * equivalent Json representation. This method must be used if the specified object is a generic
   * type. For non-generic objects, use {@link #toJson(Object)} instead. If you want to write out
   * the object to a {@link Appendable}, use {@link #toJson(Object, Type, Appendable)} instead.
   *
   * @param src the object for which JSON representation is to be created
   * @param typeOfSrc The specific genericized type of src. You can obtain
   * this type by using the {@link #genericTypeOf(Class, Type...)} method. For example,
   * to get the type for {@code Collection<Foo>}, you should use:
   * <pre>
   * Type typeOfSrc = genericTypeOf(Collection.class, Foo.class);
   * </pre>
   * @return Json representation of {@code src}
   */
  public static String toJson(Object src, Type typeOfSrc) {
    return gson.toJson(src, typeOfSrc);
  }
  
  /**
   * This method serializes the specified object into its equivalent Json representation.
   * This method should be used when the specified object is not a generic type. This method uses
   * {@link Class#getClass()} to get the type for the specified object, but the
   * {@code getClass()} loses the generic type information because of the Type Erasure feature
   * of Java. Note that this method works fine if the any of the object fields are of generic type,
   * just the object itself should not be of a generic type. If the object is of generic type, use
   * {@link #toJson(Object, Type, Appendable)} instead.
   *
   * @param src the object for which Json representation is to be created
   * @param writer Writer to which the Json representation needs to be written
   * @throws IOException if there was a problem writing to the writer
   */
  public static void toJson(Object src, Appendable writer) throws IOException {
    try {
      gson.toJson(src, writer);
    } catch (JsonIOException e) {
      throw new IOException(e);
    }
  }
  
  /**
   * This method serializes the specified object, including those of generic types, into its
   * equivalent Json representation. This method must be used if the specified object is a generic
   * type. For non-generic objects, use {@link #toJson(Object, Appendable)} instead.
   *
   * @param src the object for which JSON representation is to be created
   * @param typeOfSrc The specific genericized type of src. You can obtain
   * this type by using the {@link #genericTypeOf(Class, Type...)} method. For example,
   * to get the type for {@code Collection<Foo>}, you should use:
   * <pre>
   * Type typeOfSrc = genericTypeOf(Collection.class, Foo.class);
   * </pre>
   * @param writer Writer to which the Json representation of src needs to be written.
   * @throws IOException if there was a problem writing to the writer
   */
  public static void toJson(Object src, Type typeOfSrc, Appendable writer) throws IOException {
    try {
      gson.toJson(src, typeOfSrc, writer);
    } catch (JsonIOException e) {
      throw new IOException(e);
    }
  }
  
  /**
   * This method deserializes the specified Json into an object of the specified class. It is not
   * suitable to use if the specified class is a generic type since it will not have the generic
   * type information because of the Type Erasure feature of Java. Therefore, this method should not
   * be used if the desired type is a generic type. Note that this method works fine if the any of
   * the fields of the specified object are generics, just the object itself should not be a
   * generic type. For the cases when the object is of generic type, invoke
   * {@link #fromJson(String, Type)}. If you have the Json in a {@link Reader} instead of
   * a String, use {@link #fromJson(Reader, Class)} instead.
   *
   * @param <T> the type of the desired object
   * @param json the string from which the object is to be deserialized
   * @param classOfT the class of T
   * @return an object of type T from the string.
   * @throws JsonSyntaxException if json is not a valid representation for an object of type
   * classOfT
   */
  public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
    try {
      return gson.getAdapter(classOfT).fromJson(json);
    } catch (IOException e) {
      throw new AssertionError(e);
    } catch (com.google.gson.JsonSyntaxException e) {
      throw new JsonSyntaxException(e);
    }
  }
  
  /**
   * This method deserializes the specified Json into an object of the specified type. This method
   * is useful if the specified object is a generic type. For non-generic objects, use
   * {@link #fromJson(String, Class)} instead. If you have the Json in a {@link Reader} instead of
   * a String, use {@link #fromJson(Reader, Type)} instead.
   *
   * @param <T> the type of the desired object
   * @param json the string from which the object is to be deserialized
   * @param typeOfT The specific genericized type of src. You can obtain this type by using the
   * {@link #genericTypeOf(Class, Type...)} method. For example, to get the type for
   * {@code Collection<Foo>}, you should use:
   * <pre>
   * Type typeOfT = genericTypeOf(Collection.class, Foo.class);
   * </pre>
   * @return an object of type T from the string.
   * @throws JsonSyntaxException if json is not a valid representation for an object of type
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
    try {
      return ((TypeAdapter<T>) gson.getAdapter(TypeToken.get(typeOfT))).fromJson(json);
    } catch (IOException e) {
      throw new AssertionError(e);
    } catch (com.google.gson.JsonSyntaxException e) {
      throw new JsonSyntaxException(e);
    }
  }
  
  /**
   * This method deserializes the Json read from the specified reader into an object of the
   * specified class. It is not suitable to use if the specified class is a generic type since it
   * will not have the generic type information because of the Type Erasure feature of Java.
   * Therefore, this method should not be used if the desired type is a generic type. Note that
   * this method works fine if the any of the fields of the specified object are generics, just the
   * object itself should not be a generic type. For the cases when the object is of generic type,
   * invoke {@link #fromJson(Reader, Type)}. If you have the Json in a String form instead of a
   * {@link Reader}, use {@link #fromJson(String, Class)} instead.
   *
   * @param <T> the type of the desired object
   * @param json the reader producing the Json from which the object is to be deserialized.
   * @param classOfT the class of T
   * @return an object of type T from the string.
   * @throws IOException if there was a problem reading from the Reader
   * @throws JsonSyntaxException if json is not a valid representation for an object of type
   */
  public static <T> T fromJson(Reader json, Class<T> classOfT) throws IOException, JsonSyntaxException {
    try {
      return gson.getAdapter(classOfT).fromJson(json);
    } catch (JsonIOException e) {
      throw new IOException(e);
    } catch (com.google.gson.JsonSyntaxException e) {
      throw new JsonSyntaxException(e);
    }
  }
  
  /**
   * This method deserializes the Json read from the specified reader into an object of the
   * specified type. This method is useful if the specified object is a generic type. For
   * non-generic objects, use {@link #fromJson(Reader, Class)} instead. If you have the Json in a
   * String form instead of a {@link Reader}, use {@link #fromJson(String, Type)} instead.
   *
   * @param <T> the type of the desired object
   * @param json the reader producing Json from which the object is to be deserialized
   * @param typeOfT The specific genericized type of src. You can obtain this type by using the
   * {@link #genericTypeOf(Class, Type...)} method. For example, to get the type for
   * {@code Collection<Foo>}, you should use:
   * <pre>
   * Type typeOfT = genericTypeOf(Collection.class, Foo.class);
   * </pre>
   * @return an object of type T from the json.
   * @throws IOException if there was a problem reading from the Reader
   * @throws JsonSyntaxException if json is not a valid representation for an object of type
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromJson(Reader json, Type typeOfT) throws IOException, JsonSyntaxException {
    try {
      return ((TypeAdapter<T>) gson.getAdapter(TypeToken.get(typeOfT))).fromJson(json);
    } catch (JsonIOException e) {
      throw new IOException(e);
    } catch (com.google.gson.JsonSyntaxException e) {
      throw new JsonSyntaxException(e);
    }
  }
  
  /**
   * Returns a new parameterized type, applying {@code typeArguments} to {@code rawType}.
   *
   * @return a parameterized type.
   */
  public static Type genericTypeOf(Class<?> rawType, Type... typeArguments) {
    return $Gson$Types.newParameterizedTypeWithOwner(null, rawType, typeArguments);
  }
  
  private Json() {
    throw new AssertionError();
  }
  
}
