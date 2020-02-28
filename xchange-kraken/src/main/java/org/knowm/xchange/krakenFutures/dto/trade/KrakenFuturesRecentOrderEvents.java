package org.knowm.xchange.krakenFutures.dto.trade;

import java.util.List;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenFuturesRecentOrderEvents extends KrakenFuturesResult {

  private List<KrakenFuturesRecentOrderEvent> event;

  public List<KrakenFuturesRecentOrderEvent> getEvent() {
    return event;
  }

  public void setEvent(List<KrakenFuturesRecentOrderEvent> event) {
    this.event = event;
  }
}
