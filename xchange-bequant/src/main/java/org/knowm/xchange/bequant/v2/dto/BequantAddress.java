package org.knowm.xchange.bequant.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BequantAddress {

  private final String address;
  private final String paymentId;

  public BequantAddress(
      @JsonProperty("address") String address, @JsonProperty("paymentId") String paymentId) {
    this.address = address;
    this.paymentId = paymentId;
  }

  public String getAddress() {
    return address;
  }

  public String getPaymentId() {
    return paymentId;
  }

  @Override
  public String toString() {
    return "BequantAddress{"
        + "address='"
        + address
        + '\''
        + "paymentId='"
        + paymentId
        + '\''
        + '}';
  }
}
