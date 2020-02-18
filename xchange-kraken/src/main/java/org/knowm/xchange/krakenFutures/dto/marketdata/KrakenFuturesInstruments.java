package org.knowm.xchange.krakenFutures.dto.marketdata;

import java.util.List;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenFuturesInstruments extends KrakenFuturesResult {
  private List<KrakenFuturesInstrument> instruments;

  public List<KrakenFuturesInstrument> getInstruments() {
    return instruments;
  }

  public void setInstruments(List<KrakenFuturesInstrument> instruments) {
    this.instruments = instruments;
  }
}
