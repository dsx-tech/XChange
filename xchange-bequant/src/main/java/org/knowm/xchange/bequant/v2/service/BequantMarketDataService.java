package org.knowm.xchange.bequant.v2.service;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.bequant.v2.BequantAdapters;
import org.knowm.xchange.bequant.v2.dto.BequantSort;
import org.knowm.xchange.bequant.v2.dto.BequantTrade;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

public class BequantMarketDataService extends BequantMarketDataServiceRaw
    implements MarketDataService {

  public BequantMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    return BequantAdapters.adaptTicker(getBequantTicker(currencyPair), currencyPair);
  }

  @Override
  public List<Ticker> getTickers(Params params) throws IOException {
    return BequantAdapters.adaptTickers(getBequantTickers());
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
    if (args == null || args.length == 0) {
      return BequantAdapters.adaptOrderBook(getBequantOrderBook(currencyPair), currencyPair);
    } else {
      Integer limit = (Integer) args[0];
      return BequantAdapters.adaptOrderBook(getBequantOrderBook(currencyPair, limit), currencyPair);
    }
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {

    if (args == null || args.length == 0) {
      return BequantAdapters.adaptTrades(getBequantTrades(currencyPair), currencyPair);
    }

    long from = (Long) args[0]; // <trade_id> or <timestamp>
    BequantTrade.BequantTradesSortField sortBy =
        (BequantTrade.BequantTradesSortField) args[1]; // "trade_id" or "timestamp"
    BequantSort sortDirection = (BequantSort) args[2]; // "asc" or "desc"
    long startIndex = (Long) args[3]; // 0
    long max_results = (Long) args[4]; // max is 1000
    long offset = (Long) args[5]; // max is 100000

    return BequantAdapters.adaptTrades(
        getBequantTrades(
            currencyPair, from, sortBy, sortDirection, startIndex, max_results, offset),
        currencyPair);
  }

  public Trades getTradesCustom(
      CurrencyPair currencyPair,
      long fromTradeId,
      BequantTrade.BequantTradesSortField sortBy,
      BequantSort sortDirection,
      long limit)
      throws IOException {

    return BequantAdapters.adaptTrades(
        getBequantTrades(currencyPair, fromTradeId, sortBy, sortDirection, limit), currencyPair);
  }
}
