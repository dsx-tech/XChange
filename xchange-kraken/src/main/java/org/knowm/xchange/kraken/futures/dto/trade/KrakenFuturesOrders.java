package org.knowm.xchange.kraken.futures.dto.trade;

import org.knowm.xchange.kraken.futures.dto.KrakenFuturesResult;

import java.util.List;

public class KrakenFuturesOrders extends KrakenFuturesResult {
    /**
     * A list containing structures with information on open orders, see below. The list is sorted descending by receivedTime
     */
    private List<KrakenFuturesOrder> openOrders;

    public List<KrakenFuturesOrder> getOpenOrders() {
        return openOrders;
    }

    public void setOpenOrders(List<KrakenFuturesOrder> openOrders) {
        this.openOrders = openOrders;
    }
}
