package org.knowm.xchange.bequant.v2.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BequantSide {
  BUY("buy"),
  SELL("sell");

  private final String value;

  BequantSide(String value) {

    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  @JsonValue
  public String toString() {

    return value;
  }
}
