package org.knowm.xchange.bequant.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BequantError {

  private final String code;
  private final String message;
  private final String description;

  public BequantError(
      @JsonProperty("code") String code,
      @JsonProperty("message") String message,
      @JsonProperty("description") String description) {
    this.code = code;
    this.message = message;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "BequantError{"
        + "code='"
        + code
        + '\''
        + ", message='"
        + message
        + '\''
        + ", description='"
        + description
        + '\''
        + '}';
  }
}
