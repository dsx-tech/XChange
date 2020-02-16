package org.knowm.xchange.kraken.futures.dto.trade;

import org.knowm.xchange.kraken.futures.dto.KrakenFuturesResult;

public class KrakenFuturesOrders extends KrakenFuturesResult {
    /**
     * A list containing structures with information on open orders, see below. The list is sorted descending by receivedTime
     */
    private KrakenFuturesOrder openOrders;

    public KrakenFuturesOrder getOpenOrders() {
        return openOrders;
    }

    public void setOpenOrders(KrakenFuturesOrder openOrders) {
        this.openOrders = openOrders;
    }
}
