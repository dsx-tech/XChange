package org.knowm.xchange.krakenFutures.dto.marketdata;

import java.math.BigDecimal;
import java.util.Date;

/** @author pchertalev */
public class KrakenFuturesTicker {

  /** The symbol of the Futures or index e.g. rr_xrpxbt */
  private String symbol;

  /** True if the market is suspended, false otherwise */
  private boolean suspended;

  /**
   * For Futures: The price of the last fill For indices: The last calculated value. For spot price
   * indices, this is a U.S. dollar value. For the volatility index, this is a percentage value
   */
  private BigDecimal last;

  /** The date and time at which last was observed */
  private Date lastTime;

  /**
   * For Futures: The price of the fill observed 24 hours ago For spot price indices: The value
   * calculated 24 hours ago For volatility indices: Not returned because N/A
   */
  private BigDecimal open24h;

  /**
   * For Futures: The highest price of all fills observed in the last 24 hours For spot price
   * indices: The highest value calculated in the last 24 hours For volatility indices: Not returned
   * because N/A
   */
  private BigDecimal high24h;

  /**
   * For Futures: The lowest price of all fills observed in the last 24 hours For spot price
   * indices: The lowest value calculated in the last 24 hours For volatility indices: Not returned
   * because N/A
   */
  private BigDecimal low24h;

  /**
   * For Futures: The sum of the sizes of all fills observed in the last 24 hours For indices: Not
   * returned because N/A
   */
  private Long vol24h;

  /** For Futures: The price of the current best bid For indices: Not returned because N/A */
  private BigDecimal bid;

  /** For Futures: The price of the current best ask For indices: Not returned because N/A */
  private BigDecimal ask;

  /** For Futures: The size of the current best bid For indices: Not returned because N/A */
  private Long bidSize;

  /** For Futures: The size of the current best ask For indices: Not returned because N/A */
  private Long askSize;

  /**
   * For Futures: The price to which Crypto Facilities currently marks the Futures for margining
   * purposes For indices: Not returned because N/A
   */
  private BigDecimal markPrice;

  /**
   * For Futures: The current open interest of the instrument For indices: Not returned because N/A
   */
  private BigDecimal openInterest;

  /**
   * For Futures: Currently can be 'week', 'month' or 'quarter'. Other tags may be added without
   * notice. For indices: Not returned because N/A
   */
  private String tag;

  /** For Futures: The currency pair of the instrument For indices: Not returned because N/A */
  private String pair;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public boolean isSuspended() {
    return suspended;
  }

  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }

  public BigDecimal getLast() {
    return last;
  }

  public void setLast(BigDecimal last) {
    this.last = last;
  }

  public Date getLastTime() {
    return lastTime;
  }

  public void setLastTime(Date lastTime) {
    this.lastTime = lastTime;
  }

  public BigDecimal getOpen24h() {
    return open24h;
  }

  public void setOpen24h(BigDecimal open24h) {
    this.open24h = open24h;
  }

  public BigDecimal getHigh24h() {
    return high24h;
  }

  public void setHigh24h(BigDecimal high24h) {
    this.high24h = high24h;
  }

  public BigDecimal getLow24h() {
    return low24h;
  }

  public void setLow24h(BigDecimal low24h) {
    this.low24h = low24h;
  }

  public Long getVol24h() {
    return vol24h;
  }

  public void setVol24h(Long vol24h) {
    this.vol24h = vol24h;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public void setBid(BigDecimal bid) {
    this.bid = bid;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public void setAsk(BigDecimal ask) {
    this.ask = ask;
  }

  public Long getBidSize() {
    return bidSize;
  }

  public void setBidSize(Long bidSize) {
    this.bidSize = bidSize;
  }

  public Long getAskSize() {
    return askSize;
  }

  public void setAskSize(Long askSize) {
    this.askSize = askSize;
  }

  public BigDecimal getMarkPrice() {
    return markPrice;
  }

  public void setMarkPrice(BigDecimal markPrice) {
    this.markPrice = markPrice;
  }

  public BigDecimal getOpenInterest() {
    return openInterest;
  }

  public void setOpenInterest(BigDecimal openInterest) {
    this.openInterest = openInterest;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getPair() {
    return pair;
  }

  public void setPair(String pair) {
    this.pair = pair;
  }
}
