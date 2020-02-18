package org.knowm.xchange.krakenFutures.dto.trade;

import java.util.List;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenFuturesOpenPositions extends KrakenFuturesResult {
  private List<KrakenFuturesPosition> openPositions;

  public List<KrakenFuturesPosition> getOpenPositions() {
    return openPositions;
  }
}
