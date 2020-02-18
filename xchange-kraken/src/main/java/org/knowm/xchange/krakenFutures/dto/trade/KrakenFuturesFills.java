package org.knowm.xchange.krakenFutures.dto.trade;

import java.util.List;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenFuturesFills extends KrakenFuturesResult {

  /**
   * A list containing structures with information on filled orders, see below. The list is sorted
   * descending by fillTime.
   */
  private List<KrakenFuturesFill> fills;

  public List<KrakenFuturesFill> getFills() {
    return fills;
  }

  public void setFills(List<KrakenFuturesFill> fills) {
    this.fills = fills;
  }
}
