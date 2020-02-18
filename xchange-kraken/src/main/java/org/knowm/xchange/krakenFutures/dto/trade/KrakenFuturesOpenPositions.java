package org.knowm.xchange.krakenFutures.dto.trade;

import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

import java.util.List;

public class KrakenFuturesOpenPositions extends KrakenFuturesResult {
    private List<KrakenFuturesPosition> openPositions;

    public List<KrakenFuturesPosition> getOpenPositions() {
        return openPositions;
    }
}
