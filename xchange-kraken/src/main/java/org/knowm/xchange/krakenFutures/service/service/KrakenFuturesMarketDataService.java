package org.knowm.xchange.krakenFutures.service.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.krakenFutures.KrakenFuturesAdapters;
import org.knowm.xchange.krakenFutures.KrakenFuturesUtils;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTicker;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTickers;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTrades;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

/** @author pchertalev */
public class KrakenFuturesMarketDataService extends KrakenFuturesMarketDataServiceRaw
    implements MarketDataService {

  public KrakenFuturesMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) {
    KrakenFuturesProduct product =
        KrakenFuturesUtils.getIndexedValue(
            "product", 0, KrakenFuturesProduct.class, null, true, args);
    LocalDate maturityDate =
        KrakenFuturesUtils.getIndexedValue(
            "maturityDate", 1, LocalDate.class, null, product.mustHaveMaturityDate, args);
    KrakenFuturesTicker krakenTicker = getKrakenTicker(currencyPair, product, maturityDate);

    return KrakenFuturesAdapters.adaptTicker(krakenTicker, currencyPair);
  }

  @Override
  public List<Ticker> getTickers(Params params) {
    KrakenFuturesTickers krakenTickers = getKrakenTickers();
    return KrakenFuturesAdapters.adaptTickers(krakenTickers);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) {
    KrakenFuturesProduct product =
        KrakenFuturesUtils.getIndexedValue(
            "product", 0, KrakenFuturesProduct.class, null, true, args);
    LocalDate maturityDate =
        KrakenFuturesUtils.getIndexedValue(
            "maturityDate", 1, LocalDate.class, null, product.mustHaveMaturityDate, args);
    KrakenFuturesOrderBookResult krakenOrderbook =
        getKrakenOrderbook(currencyPair, product, maturityDate);
    return KrakenFuturesAdapters.adaptOrderbook(krakenOrderbook, currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    KrakenFuturesProduct product =
        KrakenFuturesUtils.getIndexedValue(
            "product", 0, KrakenFuturesProduct.class, null, true, args);
    LocalDate maturityDate =
        KrakenFuturesUtils.getIndexedValue(
            "maturityDate", 1, LocalDate.class, null, product.mustHaveMaturityDate, args);
    Date lastDate =
        KrakenFuturesUtils.getIndexedValue("lastTime", 2, Date.class, null, false, args);

    KrakenFuturesTrades history = getHistory(product, currencyPair, maturityDate, lastDate);
    return KrakenFuturesAdapters.adaptTrades(history, currencyPair);
  }
}
