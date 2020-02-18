package org.knowm.xchange.kraken.futures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesProduct;
import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesTicker;
import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesTickers;
import org.knowm.xchange.kraken.futures.service.KrakenFuturesBaseService;

import java.time.LocalDate;

public class KrakenFuturesMarketDataServiceRaw extends KrakenFuturesBaseService {

    /**
     * Constructor
     *
     * @param exchange
     */
    public KrakenFuturesMarketDataServiceRaw(Exchange exchange) {

        super(exchange);
    }

    public KrakenFuturesTickers getKrakenTickers() {
        return kraken.getTickers();
    }

    public KrakenFuturesTicker getKrakenTicker(CurrencyPair currencyPair, KrakenFuturesProduct product, LocalDate maturityDate) {
        String productId = product.formatProductId(currencyPair, maturityDate);
        KrakenFuturesTickers tickers = kraken.getTickers();
        return tickers.getTickers().stream()
                .filter(ticker -> productId.equalsIgnoreCase(ticker.getSymbol()))
                .findFirst()
                .orElse(null);
    }

    public KrakenFuturesOrderBookResult getKrakenOrderbook(CurrencyPair currencyPair, KrakenFuturesProduct product, LocalDate maturityDate) {
        String productId = product.formatProductId(currencyPair, maturityDate);
        return kraken.getOrderbook(productId);
    }
    public KrakenFuturesInstruments getKrakenInstruments() {
        return kraken.getInstruments();
    }

}
