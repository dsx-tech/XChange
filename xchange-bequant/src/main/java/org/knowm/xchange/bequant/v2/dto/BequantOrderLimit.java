package org.knowm.xchange.bequant.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class BequantOrderLimit {

  private final BigDecimal price;
  private final BigDecimal size;

  public BequantOrderLimit(
      @JsonProperty("price") BigDecimal price, @JsonProperty("size") BigDecimal size) {
    this.price = price;
    this.size = size;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getSize() {
    return size;
  }

  @Override
  public String toString() {
    return "BequantOrderLimit{" + "price=" + price + ", size=" + size + '}';
  }
}
