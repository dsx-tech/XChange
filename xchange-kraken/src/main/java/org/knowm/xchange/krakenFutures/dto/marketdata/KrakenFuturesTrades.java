package org.knowm.xchange.krakenFutures.dto.marketdata;

import java.util.List;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenFuturesTrades extends KrakenFuturesResult {

  private List<KrakenFuturesTrade> history;

  public List<KrakenFuturesTrade> getHistory() {
    return history;
  }

  public void setHistory(List<KrakenFuturesTrade> history) {
    this.history = history;
  }
}
