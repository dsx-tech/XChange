package org.knowm.xchange.kraken.futures.dto.trade;

import org.knowm.xchange.kraken.futures.dto.KrakenFuturesResult;

import java.util.List;

public class KrakenFuturesOpenPositions extends KrakenFuturesResult {
    private List<KrakenFuturesPosition> openPositions;

    public List<KrakenFuturesPosition> getOpenPositions() {
        return openPositions;
    }
}
