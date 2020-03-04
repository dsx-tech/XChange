package org.knowm.xchange.krakenFutures.dto;

public class KrakenFuturesDetailedError {
  private Long code;
  private String message;

  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "{" + "code=" + code + ", message='" + message + '\'' + '}';
  }
}
