package io.github.ageofwar.telejam.payments;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.github.ageofwar.telejam.json.Json.fromJson;
import static io.github.ageofwar.telejam.json.Json.genericTypeOf;

/**
 * Information about a currency.
 *
 * @author Michi Palazzo
 */
public class Currency implements TelegramObject {
  
  static final String CODE_FIELD = "code";
  static final String TITLE_FIELD = "title";
  static final String SYMBOL_FIELD = "symbol";
  static final String NATIVE_FIELD = "native";
  static final String THOUSANDS_SEPARATOR_FIELD = "thousands_sep";
  static final String DECIMAL_SEPARATOR_FIELD = "decimal_sep";
  static final String SYMBOL_LEFT_FIELD = "symbol_left";
  static final String SPACE_BETWEEN_FIELD = "space_between";
  static final String EXPONENT_FIELD = "exp";
  static final String MIN_AMOUNT_FIELD = "min_amount";
  static final String MAX_AMOUNT_FIELD = "max_amount";
  private static final String URL = "https://core.telegram.org/bots/payments/currencies.json";
  /**
   * Map of currencies supported by Telegram with their code.
   */
  private static transient Map<String, Currency> currencies;
  
  /**
   * Currency code.
   */
  @SerializedName(CODE_FIELD)
  private final String code;
  
  /**
   * Currency title.
   */
  @SerializedName(TITLE_FIELD)
  private final String title;
  
  /**
   * Currency symbol.
   */
  @SerializedName(SYMBOL_FIELD)
  private final String symbol;
  
  /**
   * Currency native symbol.
   */
  @SerializedName(NATIVE_FIELD)
  private final String nativeSymbol;
  
  /**
   * Currency thousands separator.
   */
  @SerializedName(THOUSANDS_SEPARATOR_FIELD)
  private final String thousandsSeparator;
  
  /**
   * Currency decimal separator.
   */
  @SerializedName(DECIMAL_SEPARATOR_FIELD)
  private final String decimalSeparator;
  
  /**
   * Whether or not the symbol is on the left of the number.
   */
  @SerializedName(SYMBOL_LEFT_FIELD)
  private final boolean symbolLeft;
  
  /**
   * Whether or not there is a space between the symbol and the number.
   */
  @SerializedName(SPACE_BETWEEN_FIELD)
  private final boolean spaceBetween;
  
  /**
   * Number of max decimal digits.
   */
  @SerializedName(EXPONENT_FIELD)
  private final int exponent;
  
  /**
   * Minimum price in Telegram payments.
   */
  @SerializedName(MIN_AMOUNT_FIELD)
  private final int minAmount;
  
  /**
   * Maximum price in Telegram payments.
   */
  @SerializedName(MAX_AMOUNT_FIELD)
  private final int maxAmount;
  
  
  /**
   * Creates a Currency.
   *
   * @param code               three-letter ISO 4217 currency code
   * @param title              the title of the currency
   * @param symbol             the symbol of the currency
   * @param nativeSymbol       the native symbol of the currency
   * @param thousandsSeparator the thousands separator of the currency
   * @param decimalSeparator   the decimal separator of the currency
   * @param symbolLeft         <code>true</code> if the symbol is on the left,
   *                           <code>false</code> otherwise
   * @param spaceBetween       <code>true</code> if there is a space between the
   *                           value and the symbol, <code>false</code> otherwise
   * @param exponent           maximum number of decimal digits
   * @param minAmount          minimum amount of price
   * @param maxAmount          maximum amount of price
   */
  public Currency(String code,
                  String title,
                  String symbol,
                  String nativeSymbol,
                  String thousandsSeparator,
                  String decimalSeparator,
                  boolean symbolLeft,
                  boolean spaceBetween,
                  int exponent,
                  int minAmount,
                  int maxAmount) {
    this.code = Objects.requireNonNull(code);
    this.title = Objects.requireNonNull(title);
    this.symbol = Objects.requireNonNull(symbol);
    this.nativeSymbol = Objects.requireNonNull(nativeSymbol);
    this.thousandsSeparator = Objects.requireNonNull(thousandsSeparator);
    this.decimalSeparator = Objects.requireNonNull(decimalSeparator);
    this.symbolLeft = symbolLeft;
    this.spaceBetween = spaceBetween;
    this.exponent = exponent;
    this.minAmount = minAmount;
    this.maxAmount = maxAmount;
    if (currencies == null) {
      currencies = new HashMap<>();
    }
    currencies.put(code, this);
  }
  
  /**
   * Loads currencies from the Telegram API.
   *
   * @throws IOException if an IOException occurs
   */
  public static void init() throws IOException {
    URL url = new URL(URL);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    InputStream inputStream = connection.getInputStream();
    
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      Map<String, Currency> currencies =
          fromJson(reader, genericTypeOf(Map.class, String.class, Currency.class));
      
      if (Currency.currencies == null) {
        Currency.currencies = currencies;
      } else {
        Currency.currencies.putAll(currencies);
      }
    }
  }
  
  /**
   * Returns the currency with the specified code.
   *
   * @param code the code
   * @return the currency with the specified code
   */
  public static Currency fromCode(String code) {
    if (currencies == null) {
      throw new IllegalStateException("Currencies not initialized! Call init() or use constructor");
    }
    return currencies.get(code);
  }
  
  /**
   * Converts a value to a price with this currency.
   *
   * @param value           the value
   * @param useNativeSymbol <code>true</code> if you want to use
   *                        the native symbol of the currency
   * @return the formatted price
   */
  public String format(int value, boolean useNativeSymbol) {
    StringBuilder numberBuilder = new StringBuilder();
    numberBuilder.append(value)
        .reverse();
    int len = numberBuilder.length();
    
    for (int i = 0; i < exponent + 1 - len; i++) {
      numberBuilder.append('0');
    }
    
    for (int i = exponent + 3, k = 0;
         i < numberBuilder.length();
         k += thousandsSeparator.length(), i += 3 + k) {
      numberBuilder.insert(i, thousandsSeparator);
    }
    numberBuilder.insert(exponent, decimalSeparator)
        .reverse();
    
    StringBuilder resultBuilder = new StringBuilder();
    if (symbolLeft) {
      resultBuilder.append(useNativeSymbol ? nativeSymbol : symbol);
      if (spaceBetween) {
        resultBuilder.append(" ");
      }
      resultBuilder.append(numberBuilder);
    } else {
      resultBuilder.append(numberBuilder);
      if (spaceBetween) {
        resultBuilder.append(" ");
      }
      resultBuilder.append(useNativeSymbol ? nativeSymbol : symbol);
    }
    
    return resultBuilder.toString();
  }
  
  /**
   * Getter for property {@link #code}.
   *
   * @return value for property {@link #code}
   */
  public String getCode() {
    return code;
  }
  
  /**
   * Getter for property {@link #title}.
   *
   * @return value for property {@link #title}
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * Getter for property {@link #symbol}.
   *
   * @return value for property {@link #symbol}
   */
  public String getSymbol() {
    return symbol;
  }
  
  /**
   * Getter for property {@link #nativeSymbol}.
   *
   * @return value for property {@link #nativeSymbol}
   */
  public String getNativeSymbol() {
    return nativeSymbol;
  }
  
  /**
   * Getter for property {@link #thousandsSeparator}.
   *
   * @return value for property {@link #thousandsSeparator}
   */
  public String getThousandsSeparator() {
    return thousandsSeparator;
  }
  
  /**
   * Getter for property {@link #decimalSeparator}.
   *
   * @return value for property {@link #decimalSeparator}
   */
  public String getDecimalSeparator() {
    return decimalSeparator;
  }
  
  /**
   * Getter for property {@link #symbolLeft}.
   *
   * @return value for property {@link #symbolLeft}
   */
  public boolean isSymbolLeft() {
    return symbolLeft;
  }
  
  /**
   * Getter for property {@link #spaceBetween}.
   *
   * @return value for property {@link #spaceBetween}
   */
  public boolean isSpaceBetween() {
    return spaceBetween;
  }
  
  /**
   * Getter for property {@link #exponent}.
   *
   * @return value for property {@link #exponent}
   */
  public int getExponent() {
    return exponent;
  }
  
  /**
   * Getter for property {@link #minAmount}.
   *
   * @return value for property {@link #minAmount}
   */
  public int getMinAmount() {
    return minAmount;
  }
  
  /**
   * Getter for property {@link #maxAmount}.
   *
   * @return value for property {@link #maxAmount}
   */
  public int getMaxAmount() {
    return maxAmount;
  }
  
}
