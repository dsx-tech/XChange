package org.knowm.xchange.krakenFutures.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesSide;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesTradeType;

/** @author pchertalev */
public class KrakenFuturesTrade {

  /**
   * For Futures: A continuous index starting at 1 for the first fill in a Futures contract maturity
   * For indices: Not returned because N/A
   */
  @JsonProperty("trade_id")
  private Long tradeId;

  /**
   * For Futures: The date and time of a trade. Data is not aggregated For indices: The date and
   * time of an index computation. For real-time indices, data is aggregated to the last computation
   * of each full hour. For reference rates, data is not aggregated
   */
  private Date time;

  /** For Futures: The price of a fill For indices: The calculated value */
  private BigDecimal price;

  /** For Futures: The size of a fill For indices: Not returned because N/A */
  private Long size;

  /**
   * The classification of the taker side in the matched trade: "buy" if the taker is a buyer,
   * "sell" if the taker is a seller.
   */
  private KrakenFuturesSide side;

  /**
   * The classification of the matched trade in an orderbook: "fill" if it is a normal buyer and
   * seller, "liquidation" if it is a result of a user being liquidated from their position,
   * "termination" if it is a result of a user being terminated.
   */
  private KrakenFuturesTradeType type;

  public Long getTradeId() {
    return tradeId;
  }

  public void setTradeId(Long tradeId) {
    this.tradeId = tradeId;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public KrakenFuturesSide getSide() {
    return side;
  }

  public void setSide(KrakenFuturesSide side) {
    this.side = side;
  }

  public KrakenFuturesTradeType getType() {
    return type;
  }

  public void setType(KrakenFuturesTradeType type) {
    this.type = type;
  }
}
