package org.knowm.xchange.krakenFutures.dto.trade;

import java.util.Date;

public class KrakenFuturesRecentOrderEvent {

  /** The event timestamp */
  private Date timestamp;

  /** The event unique identifier * */
  private String uid;

  /** Order Events */
  private KrakenFuturesOrderEvent event;

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public KrakenFuturesOrderEvent getEvent() {
    return event;
  }

  public void setEvent(KrakenFuturesOrderEvent event) {
    this.event = event;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
}
