package io.github.ageofwar.telejam.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Locale;

final class LocaleTypeAdapter extends TypeAdapter<Locale> {
  
  public static final TypeAdapter<Locale> INSTANCE = new LocaleTypeAdapter().nullSafe();
  
  private LocaleTypeAdapter() {
  }
  
  @Override
  public void write(JsonWriter out, Locale value) throws IOException {
    if (value == Locale.ROOT) {
      out.nullValue();
      return;
    }
    out.value(value.toLanguageTag());
  }
  
  @Override
  public Locale read(JsonReader in) throws IOException {
    return Locale.forLanguageTag(in.nextString());
  }
  
}
