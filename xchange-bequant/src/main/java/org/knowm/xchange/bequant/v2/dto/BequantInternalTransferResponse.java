package org.knowm.xchange.bequant.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BequantInternalTransferResponse {

  public final String id;
  public final BequantError error;

  public BequantInternalTransferResponse(
      @JsonProperty("id") String id, @JsonProperty("error") BequantError error) {
    this.id = id;
    this.error = error;
  }

  @Override
  public String toString() {
    return "InternalTransferResponse{" + "id='" + id + '\'' + ", error=" + error + '}';
  }
}
