package org.knowm.xchange.bequant.v2.service;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BequantTimeInForce {
  GTC,
  FOK,
  IOC;

  @JsonCreator
  public static BequantTimeInForce getTimeInForce(String s) {
    try {
      return BequantTimeInForce.valueOf(s);
    } catch (Exception e) {
      throw new RuntimeException("Unknown ordtime in force " + s + ".");
    }
  }
}
