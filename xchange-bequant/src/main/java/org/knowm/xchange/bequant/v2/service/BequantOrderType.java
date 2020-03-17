package org.knowm.xchange.bequant.v2.service;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BequantOrderType {
  limit,
  market,
  stopLimit,
  stopMarket;

  @JsonCreator
  public static BequantOrderType getOrderType(String s) {
    try {
      return BequantOrderType.valueOf(s);
    } catch (Exception e) {
      throw new RuntimeException("Unknown order type " + s + ".");
    }
  }
}
