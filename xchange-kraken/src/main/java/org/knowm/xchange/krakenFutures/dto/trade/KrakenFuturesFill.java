package org.knowm.xchange.krakenFutures.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesFillType;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesSide;

public class KrakenFuturesFill {

  /**
   * The unique identifier of the fill. Note that several fill_id can pertain to one order_id (but
   * not vice versa)
   */
  @JsonProperty("fill_id")
  private String fillId;

  /** The unique identifier of the order */
  @JsonProperty("order_id")
  private String orderId;

  /**
   * The unique client order identifier. This field is returned only if the order has a client order
   * id
   */
  private String cliOrdId;

  /** The date and time the order was filled */
  private Date fillTime;

  /** The symbol of the futures the fill occurred in */
  private String symbol;

  /** The direction of the order, either buy for a buy order or sell for a sell order */
  private KrakenFuturesSide side;

  /** The size of the fill */
  private Long size;

  /** The price of the fill */
  private BigDecimal price;

  /**
   * The classification of the fill: "maker" if user has a limit order that gets filled, "taker" if
   * the user makes an execution that crosses the spread, "liquidation" if execution is result of a
   * liquidation, "assignee" if execution is a result of a counterparty receiving an Assignment in
   * PAS, "assignor" if execution is a result of user assigning their position due to failed
   * liquidation.
   */
  private KrakenFuturesFillType fillType;

  public String getFillId() {
    return fillId;
  }

  public void setFillId(String fillId) {
    this.fillId = fillId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getCliOrdId() {
    return cliOrdId;
  }

  public void setCliOrdId(String cliOrdId) {
    this.cliOrdId = cliOrdId;
  }

  public Date getFillTime() {
    return fillTime;
  }

  public void setFillTime(Date fillTime) {
    this.fillTime = fillTime;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public KrakenFuturesSide getSide() {
    return side;
  }

  public void setSide(KrakenFuturesSide side) {
    this.side = side;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public KrakenFuturesFillType getFillType() {
    return fillType;
  }

  public void setFillType(KrakenFuturesFillType fillType) {
    this.fillType = fillType;
  }
}
