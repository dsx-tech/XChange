package org.knowm.xchange.bequant.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;

public class BequantOwnTrade {

  public final String symbol;
  private final Long id;
  private final String clientOrderId;
  private final Long orderId;
  private final BequantSide side;
  private final BigDecimal quantity;
  private final BigDecimal fee;
  private final BigDecimal price;
  private final Date timestamp;

  public BequantOwnTrade(
      @JsonProperty("id") Long id,
      @JsonProperty("clientOrderId") String clientOrderId,
      @JsonProperty("orderId") Long orderId,
      @JsonProperty("symbol") String symbol,
      @JsonProperty("side") BequantSide side,
      @JsonProperty("quantity") BigDecimal quantity,
      @JsonProperty("fee") BigDecimal fee,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("timestamp") Date timestamp) {
    this.id = id;
    this.clientOrderId = clientOrderId;
    this.orderId = orderId;
    this.symbol = symbol;
    this.side = side;
    this.quantity = quantity;
    this.fee = fee;
    this.price = price;
    this.timestamp = timestamp;
  }

  public Long getId() {
    return id;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public BequantSide getSide() {
    return side;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "BequantOwnTrade{"
        + "id="
        + id
        + ", clientOrderId='"
        + clientOrderId
        + '\''
        + ", orderId="
        + orderId
        + ", side="
        + side
        + ", quantity="
        + quantity
        + ", fee="
        + fee
        + ", price="
        + price
        + ", timestamp="
        + timestamp
        + '}';
  }
}
