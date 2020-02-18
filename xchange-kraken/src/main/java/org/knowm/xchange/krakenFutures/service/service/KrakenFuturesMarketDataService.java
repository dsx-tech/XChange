package org.knowm.xchange.krakenFutures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.krakenFutures.KrakenFuturesAdapters;
import org.knowm.xchange.krakenFutures.KrakenFuturesUtils;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTicker;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTickers;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class KrakenFuturesMarketDataService extends KrakenFuturesMarketDataServiceRaw implements MarketDataService {

    /**
     * Constructor
     *
     * @param exchange
     */
    public KrakenFuturesMarketDataService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public Ticker getTicker(CurrencyPair currencyPair, Object... args) {
        KrakenFuturesProduct product = KrakenFuturesUtils.getIndexedValue("product", 0, KrakenFuturesProduct.class, null, true, args);
        LocalDate maturityDate = KrakenFuturesUtils.getIndexedValue("maturityDate", 1, LocalDate.class, null, product.mustHaveMaturityDate, args);
        KrakenFuturesTicker krakenTicker = getKrakenTicker(currencyPair, product, maturityDate);

        return KrakenFuturesAdapters.adaptTicker(krakenTicker);
    }

    @Override
    public List<Ticker> getTickers(Params params) {
        KrakenFuturesTickers krakenTickers = getKrakenTickers();
        return KrakenFuturesAdapters.adaptTickers(krakenTickers);
    }

    @Override
    public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
        KrakenFuturesProduct product = KrakenFuturesUtils.getIndexedValue("product", 0, KrakenFuturesProduct.class, null, true, args);
        LocalDate maturityDate = KrakenFuturesUtils.getIndexedValue("maturityDate", 1, LocalDate.class, null, product.mustHaveMaturityDate, args);
        KrakenFuturesOrderBookResult krakenOrderbook = getKrakenOrderbook(currencyPair, product, maturityDate);
        return KrakenFuturesAdapters.adaptOrderbook(krakenOrderbook, currencyPair);
    }
}
