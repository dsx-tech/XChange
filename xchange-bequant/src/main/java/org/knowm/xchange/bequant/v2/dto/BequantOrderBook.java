package org.knowm.xchange.bequant.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BequantOrderBook {

  private final BequantOrderLimit[] asks;
  private final BequantOrderLimit[] bids;

  public BequantOrderBook(
      @JsonProperty("ask") BequantOrderLimit[] asks,
      @JsonProperty("bid") BequantOrderLimit[] bids) {

    this.asks = asks;
    this.bids = bids;
  }

  public BequantOrderLimit[] getAsks() {
    return asks;
  }

  public BequantOrderLimit[] getBids() {
    return bids;
  }

  @Override
  public String toString() {

    StringBuilder asksBuilder = new StringBuilder();
    StringBuilder bidsBuilder = new StringBuilder();

    for (BequantOrderLimit ask : getAsks()) {
      asksBuilder.append(ask + ";");
    }

    for (BequantOrderLimit bid : getBids()) {
      bidsBuilder.append(bid + ";");
    }

    return "BequantOrderBook{" + "asks=" + asksBuilder + ", bids=" + bidsBuilder + '}';
  }
}
