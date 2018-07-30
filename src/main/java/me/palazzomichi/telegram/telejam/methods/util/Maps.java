package me.palazzomichi.telegram.telejam.methods.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Maps {
  
  public static <K, V> Map<K, V> mapOf() {
    return Collections.emptyMap();
  }
  
  public static <K, V> Map<K, V> mapOf(K key, V value) {
    if (value != null) {
      return Collections.singletonMap(key, value);
    } else {
      return Collections.emptyMap();
    }
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6,
                                       K key7, V value7) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    putNotNull(map, key7, value7);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6,
                                       K key7, V value7,
                                       K key8, V value8) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    putNotNull(map, key7, value7);
    putNotNull(map, key8, value8);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6,
                                       K key7, V value7,
                                       K key8, V value8,
                                       K key9, V value9) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    putNotNull(map, key7, value7);
    putNotNull(map, key8, value8);
    putNotNull(map, key9, value9);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6,
                                       K key7, V value7,
                                       K key8, V value8,
                                       K key9, V value9,
                                       K key10, V value10) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    putNotNull(map, key7, value7);
    putNotNull(map, key8, value8);
    putNotNull(map, key9, value9);
    putNotNull(map, key10, value10);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6,
                                       K key7, V value7,
                                       K key8, V value8,
                                       K key9, V value9,
                                       K key10, V value10,
                                       K key11, V value11) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    putNotNull(map, key7, value7);
    putNotNull(map, key8, value8);
    putNotNull(map, key9, value9);
    putNotNull(map, key10, value10);
    putNotNull(map, key11, value11);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6,
                                       K key7, V value7,
                                       K key8, V value8,
                                       K key9, V value9,
                                       K key10, V value10,
                                       K key11, V value11,
                                       K key12, V value12) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    putNotNull(map, key7, value7);
    putNotNull(map, key8, value8);
    putNotNull(map, key9, value9);
    putNotNull(map, key10, value10);
    putNotNull(map, key11, value11);
    putNotNull(map, key12, value12);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6,
                                       K key7, V value7,
                                       K key8, V value8,
                                       K key9, V value9,
                                       K key10, V value10,
                                       K key11, V value11,
                                       K key12, V value12,
                                       K key13, V value13,
                                       K key14, V value14,
                                       K key15, V value15,
                                       K key16, V value16,
                                       K key17, V value17) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    putNotNull(map, key7, value7);
    putNotNull(map, key8, value8);
    putNotNull(map, key9, value9);
    putNotNull(map, key10, value10);
    putNotNull(map, key11, value11);
    putNotNull(map, key12, value12);
    putNotNull(map, key13, value13);
    putNotNull(map, key14, value14);
    putNotNull(map, key15, value15);
    putNotNull(map, key16, value16);
    putNotNull(map, key17, value17);
    return map;
  }
  
  public static <K, V> Map<K, V> mapOf(K key1, V value1,
                                       K key2, V value2,
                                       K key3, V value3,
                                       K key4, V value4,
                                       K key5, V value5,
                                       K key6, V value6,
                                       K key7, V value7,
                                       K key8, V value8,
                                       K key9, V value9,
                                       K key10, V value10,
                                       K key11, V value11,
                                       K key12, V value12,
                                       K key13, V value13,
                                       K key14, V value14,
                                       K key15, V value15,
                                       K key16, V value16,
                                       K key17, V value17,
                                       K key18, V value18,
                                       K key19, V value19,
                                       K key20, V value20,
                                       K key21, V value21) {
    Map<K, V> map = new HashMap<>();
    putNotNull(map, key1, value1);
    putNotNull(map, key2, value2);
    putNotNull(map, key3, value3);
    putNotNull(map, key4, value4);
    putNotNull(map, key5, value5);
    putNotNull(map, key6, value6);
    putNotNull(map, key7, value7);
    putNotNull(map, key8, value8);
    putNotNull(map, key9, value9);
    putNotNull(map, key10, value10);
    putNotNull(map, key11, value11);
    putNotNull(map, key12, value12);
    putNotNull(map, key13, value13);
    putNotNull(map, key14, value14);
    putNotNull(map, key15, value15);
    putNotNull(map, key16, value16);
    putNotNull(map, key17, value17);
    putNotNull(map, key18, value18);
    putNotNull(map, key19, value19);
    putNotNull(map, key20, value20);
    putNotNull(map, key21, value21);
    return map;
  }
  
  public static <K, V> void putNotNull(Map<K, V> map, K key, V value) {
    if (value != null) {
      map.put(key, value);
    }
  }
  
  private Maps() {
    throw new AssertionError();
  }
  
}
