package org.knowm.xchange.bequant.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BequantException extends RuntimeException {

  private BequantError bequantError;

  public BequantException(@JsonProperty("error") BequantError bequantError) {

    super(bequantError.getMessage());
    this.bequantError = bequantError;
  }

  public BequantError getBequantError() {
    return bequantError;
  }
}
