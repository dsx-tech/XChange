package org.knowm.xchange.kraken.futures.dto.marketdata;

import org.knowm.xchange.kraken.futures.dto.KrakenFuturesResult;

import java.util.List;

public class KrakenFuturesInstruments extends KrakenFuturesResult {
    private List<KrakenFuturesInstrument> instruments;

    public List<KrakenFuturesInstrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<KrakenFuturesInstrument> instruments) {
        this.instruments = instruments;
    }
}
