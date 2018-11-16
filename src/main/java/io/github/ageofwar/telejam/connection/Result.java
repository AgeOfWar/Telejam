package io.github.ageofwar.telejam.connection;

import com.google.gson.annotations.SerializedName;
import io.github.ageofwar.telejam.TelegramException;
import io.github.ageofwar.telejam.TelegramObject;

import java.io.Serializable;
import java.util.Optional;

/**
 * Result of the invocation of a Telegram API method.
 *
 * @author Michi Palazzo
 */
public class Result<T extends Serializable> implements TelegramObject {
  
  static final String SUCCESS_FIELD = "ok";
  static final String RESULT_FIELD = "result";
  static final String ERROR_CODE_FIELD = "error_code";
  static final String DESCRIPTION_FIELD = "description";
  static final String RESPONSE_PARAMETERS_FIELD = "parameters";
  
  /**
   * Whether or not the method invocation was successful.
   */
  @SerializedName(SUCCESS_FIELD)
  private final boolean success;
  
  /**
   * The result of the method invocation.
   */
  @SerializedName(RESULT_FIELD)
  private T result;
  
  /**
   * If an error occurred, the error code.
   */
  @SerializedName(ERROR_CODE_FIELD)
  private int errorCode;
  
  /**
   * If an error occurred, the error description.
   */
  @SerializedName(DESCRIPTION_FIELD)
  private String description;
  
  /**
   * Object that help to automatically handle the error.
   */
  @SerializedName(RESPONSE_PARAMETERS_FIELD)
  private ResponseParameters responseParameters;
  
  
  /**
   * Constructs a Result.
   *
   * @param result the result
   */
  public Result(T result) {
    success = true;
    this.result = result;
  }
  
  /**
   * Constructs a Result.
   *
   * @param exception the result
   */
  public Result(TelegramException exception) {
    success = false;
    errorCode = exception.getErrorCode();
    description = exception.getMessage();
    responseParameters = exception.getResponseParameters().orElse(null);
  }
  
  
  /**
   * Returns this object as an Exception.
   *
   * @return this object as an Exception
   * @throws IllegalStateException if the method invocation is successful
   *                               and no error occurred
   */
  public TelegramException toException() throws IllegalStateException {
    if (success) {
      throw new IllegalStateException("No error occurred");
    }
    return new TelegramException(errorCode, description, responseParameters);
  }
  
  /**
   * Getter for property {@link #success}.
   *
   * @return value for property {@link #success}
   */
  public boolean ok() {
    return success;
  }
  
  /**
   * Getter for property {@link #result}.
   *
   * @return value for property {@link #result}
   * @throws IllegalStateException if the method invocation resulted
   *                               in an error
   */
  public T get() throws IllegalStateException {
    if (!success) {
      throw new IllegalStateException("An error occurs", toException());
    }
    return result;
  }
  
  /**
   * Getter for property {@link #errorCode}.
   *
   * @return value for property {@link #errorCode}
   */
  public int getErrorCode() {
    if (success) {
      throw new IllegalStateException("No error occurred");
    }
    return errorCode;
  }
  
  /**
   * Getter for property {@link #description}.
   *
   * @return optional value for property {@link #description}
   */
  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }
  
  /**
   * Getter for property {@link #responseParameters}.
   *
   * @return optional value for property {@link #responseParameters}
   */
  public Optional<ResponseParameters> getResponseParameters() {
    if (success) {
      throw new IllegalStateException("No error occurred");
    }
    return Optional.ofNullable(responseParameters);
  }
  
}
