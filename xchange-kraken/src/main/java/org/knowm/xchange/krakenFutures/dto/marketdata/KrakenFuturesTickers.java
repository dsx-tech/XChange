package org.knowm.xchange.krakenFutures.dto.marketdata;

import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

import java.util.List;

/**
 * @author pchertalev
 */
public class KrakenFuturesTickers extends KrakenFuturesResult {

    /**
     * A list containing a structures for each available instrument, see below. The list is in no particular order
     */
    private List<KrakenFuturesTicker> tickers;

    public List<KrakenFuturesTicker> getTickers() {
        return tickers;
    }

    public void setTickers(List<KrakenFuturesTicker> tickers) {
        this.tickers = tickers;
    }
}
