package org.knowm.xchange.krakenFutures.dto.marketdata;

import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

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
