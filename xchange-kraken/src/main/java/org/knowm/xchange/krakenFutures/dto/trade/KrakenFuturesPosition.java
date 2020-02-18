package org.knowm.xchange.krakenFutures.dto.trade;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesPositionSide;
import org.knowm.xchange.krakenFutures.utils.KrakenFuturesPositionSideDeserializer;

public class KrakenFuturesPosition {

  /** The date and time the order was filled */
  private Date fillTime;

  /** The symbol of the futures the fill occurred in */
  private String symbol;

  /** The direction of the order, either buy for a buy order or sell for a sell order */
  @JsonDeserialize(using = KrakenFuturesPositionSideDeserializer.class)
  private KrakenFuturesPositionSide side;

  /** The size of the fill */
  private Long size;

  /** The price of the fill */
  private BigDecimal price;

  private BigDecimal unrealizedFunding;

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

  public KrakenFuturesPositionSide getSide() {
    return side;
  }

  public void setSide(KrakenFuturesPositionSide side) {
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

  public BigDecimal getUnrealizedFunding() {
    return unrealizedFunding;
  }

  public void setUnrealizedFunding(BigDecimal unrealizedFunding) {
    this.unrealizedFunding = unrealizedFunding;
  }
}
