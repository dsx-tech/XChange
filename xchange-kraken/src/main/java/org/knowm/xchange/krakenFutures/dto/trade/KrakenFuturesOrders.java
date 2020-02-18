package org.knowm.xchange.krakenFutures.dto.trade;

import java.util.List;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenFuturesOrders extends KrakenFuturesResult {
  /**
   * A list containing structures with information on open orders, see below. The list is sorted
   * descending by receivedTime
   */
  private List<KrakenFuturesOrder> openOrders;

  public List<KrakenFuturesOrder> getOpenOrders() {
    return openOrders;
  }

  public void setOpenOrders(List<KrakenFuturesOrder> openOrders) {
    this.openOrders = openOrders;
  }
}
