package org.knowm.xchange.kraken.futures.dto.marketdata;

import org.knowm.xchange.kraken.futures.dto.KrakenFuturesResult;

/**
 * @author pchertalev
 */
public class KrakenFuturesOrderBookResult extends KrakenFuturesResult {

    private KrakenFuturesOrderBook orderBook;

    public KrakenFuturesOrderBook getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(KrakenFuturesOrderBook orderBook) {
        this.orderBook = orderBook;
    }
}
