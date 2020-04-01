package org.knowm.xchange.dsx.v2.service;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dsx.v2.DsxAdapters;
import org.knowm.xchange.dsx.v2.dto.DsxSort;
import org.knowm.xchange.dsx.v2.dto.DsxTrade;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

public class DsxMarketDataService extends DsxMarketDataServiceRaw implements MarketDataService {

  public DsxMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    return DsxAdapters.adaptTicker(getDsxTicker(currencyPair), currencyPair);
  }

  @Override
  public List<Ticker> getTickers(Params params) throws IOException {
    return DsxAdapters.adaptTickers(getDsxTickers());
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
    if (args == null || args.length == 0) {
      return DsxAdapters.adaptOrderBook(getDsxOrderBook(currencyPair), currencyPair);
    } else {
      Integer limit = ArrayUtils.getElement(0, args, Integer.class);
      return DsxAdapters.adaptOrderBook(getDsxOrderBook(currencyPair, limit), currencyPair);
    }
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {

    Long from = ArrayUtils.getElement(0, args, Long.class); // <trade_id> or <timestamp>
    Long till = ArrayUtils.getElement(1, args, Long.class); // <trade_id> or <timestamp>
    DsxTradesSortBy sortBy =
        ArrayUtils.getElement(
            2, args, DsxTradesSortBy.class, DsxTradesSortBy.timestamp); // "id" or "timestamp"
    DsxSort sortDirection = ArrayUtils.getElement(3, args, DsxSort.class); // "ASC" or "DESC"
    Integer maxResults = ArrayUtils.getElement(4, args, Integer.class); // max is 1000
    Integer offset = ArrayUtils.getElement(5, args, Integer.class); // max is 100000

    return DsxAdapters.adaptTrades(
        getDsxTrades(currencyPair, sortDirection, sortBy, from, till, maxResults, offset),
        currencyPair,
        sortBy);
  }
}
