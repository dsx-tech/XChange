package org.knowm.xchange.krakenFutures.dto.trade;

import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesOrderStatus;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesOrderType;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesSide;

public class KrakenFuturesOrder {

  /** The date and time the order was placed */
  private Date timestamp;

  /** The date and time the order was last updated */
  private Date lastUpdateTimestamp;

  /**
   * The status of the order, either of: untouched: the entire size of the order is unfilled
   * partiallyFilled: the size of the order is partially but not entirely filled
   */
  private KrakenFuturesOrderStatus status;

  /** The unique identifier of the order */
  private String orderId;

  /**
   * The unique client order identifier. This field is returned only if the order has a client order
   * id
   */
  private String cliOrdId;

  /** The order type, either lmt for a limit order or stp for a stop order */
  private KrakenFuturesOrderType type;

  /** The symbol of the futures the order refers to. */
  private String symbol;

  /** The direction of the order, either buy for a buy order or sell for a sell order */
  private KrakenFuturesSide side;

  /** The unfilled size associated with the order */
  private Long unfilledSize;

  /** The filled size associated with the order */
  private Long filled;

  /** The limit price associated with the order */
  private BigDecimal limitPrice;

  /**
   * If orderType is stp: The stop price associated with the order If orderType is lmt: Not returned
   * because N/A
   */
  private BigDecimal stopPrice;

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public Date getLastUpdateTimestamp() {
    return lastUpdateTimestamp;
  }

  public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
  }

  public KrakenFuturesOrderStatus getStatus() {
    return status;
  }

  public void setStatus(KrakenFuturesOrderStatus status) {
    this.status = status;
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

  public KrakenFuturesOrderType getType() {
    return type;
  }

  public void setType(KrakenFuturesOrderType type) {
    this.type = type;
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

  public Long getUnfilledSize() {
    return unfilledSize;
  }

  public void setUnfilledSize(Long unfilledSize) {
    this.unfilledSize = unfilledSize;
  }

  public Long getFilled() {
    return filled;
  }

  public void setFilled(Long filled) {
    this.filled = filled;
  }

  public BigDecimal getLimitPrice() {
    return limitPrice;
  }

  public void setLimitPrice(BigDecimal limitPrice) {
    this.limitPrice = limitPrice;
  }

  public BigDecimal getStopPrice() {
    return stopPrice;
  }

  public void setStopPrice(BigDecimal stopPrice) {
    this.stopPrice = stopPrice;
  }
}
