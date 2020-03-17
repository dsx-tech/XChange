package org.knowm.xchange.bequant.v2.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.bequant.v2.BequantAdapters;
import org.knowm.xchange.bequant.v2.dto.BequantCandle;
import org.knowm.xchange.bequant.v2.dto.BequantCurrency;
import org.knowm.xchange.bequant.v2.dto.BequantOrderBook;
import org.knowm.xchange.bequant.v2.dto.BequantSort;
import org.knowm.xchange.bequant.v2.dto.BequantSymbol;
import org.knowm.xchange.bequant.v2.dto.BequantTicker;
import org.knowm.xchange.bequant.v2.dto.BequantTrade;
import org.knowm.xchange.currency.CurrencyPair;

public class BequantMarketDataServiceRaw extends BequantBaseService {

  public BequantMarketDataServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<BequantSymbol> getBequantSymbols() throws IOException {

    return bequant.getSymbols();
  }

  public List<BequantCurrency> getBequantCurrencies() throws IOException {

    return bequant.getCurrencies();
  }

  public BequantCurrency getBequantCurrency(String currency) throws IOException {

    return bequant.getCurrency(currency);
  }

  public Map<String, BequantTicker> getBequantTickers() throws IOException {

    return bequant.getBequantTickers().stream()
        .collect(
            Collectors.toMap(
                bequantTicker -> bequantTicker.getSymbol(), bequantTicker -> bequantTicker));
  }

  public BequantTicker getBequantTicker(CurrencyPair currencyPair) throws IOException {

    return bequant.getTicker(BequantAdapters.adaptCurrencyPair(currencyPair));
  }

  public BequantOrderBook getBequantOrderBook(CurrencyPair currencyPair) throws IOException {

    return bequant.getOrderBook(BequantAdapters.adaptCurrencyPair(currencyPair), null);
  }

  public BequantOrderBook getBequantOrderBook(CurrencyPair currencyPair, Integer limit)
      throws IOException {

    return bequant.getOrderBook(BequantAdapters.adaptCurrencyPair(currencyPair), limit);
  }

  public List<BequantTrade> getBequantTrades(CurrencyPair currencyPair) throws IOException {

    return getBequantTrades(
        currencyPair,
        100,
        BequantTrade.BequantTradesSortField.SORT_BY_TRADE_ID,
        BequantSort.SORT_ASCENDING,
        0,
        100,
        0);
  }

  // TODO add extra params in API
  public List<BequantTrade> getBequantTrades(
      CurrencyPair currencyPair,
      long from,
      BequantTrade.BequantTradesSortField sortBy,
      BequantSort sortDirection,
      long startIndex,
      long maxResults,
      long offset)
      throws IOException {

    return bequant.getTrades(BequantAdapters.adaptCurrencyPair(currencyPair), maxResults, offset);
  }

  public List<BequantTrade> getBequantTrades(
      CurrencyPair currencyPair,
      long from,
      BequantTrade.BequantTradesSortField sortBy,
      BequantSort sortDirection,
      long maxResults)
      throws IOException {

    return bequant.getTrades(
        BequantAdapters.adaptCurrencyPair(currencyPair),
        sortDirection.toString(),
        sortBy.toString(),
        String.valueOf(from),
        maxResults);
  }

  public List<BequantCandle> getBequantCandles(CurrencyPair currencyPair, int limit, String period)
      throws IOException {

    return bequant.getBequantOHLC(BequantAdapters.adaptCurrencyPair(currencyPair), limit, period);
  }

  public List<BequantCandle> getBequantCandles(
      CurrencyPair currencyPair, int limit, String period, String sort) throws IOException {

    return bequant.getBequantOHLC(
        BequantAdapters.adaptCurrencyPair(currencyPair), limit, period, sort);
  }

  public List<BequantCandle> getBequantCandles(
      CurrencyPair currencyPair, int limit, String period, Date from, Date till, String sort)
      throws IOException {

    return bequant.getBequantOHLC(
        BequantAdapters.adaptCurrencyPair(currencyPair), limit, period, from, till, sort);
  }

  public List<BequantCandle> getBequantCandles(
      CurrencyPair currencyPair, int limit, String period, int offset, String sort)
      throws IOException {

    return bequant.getBequantOHLC(
        BequantAdapters.adaptCurrencyPair(currencyPair), limit, period, offset, sort);
  }
}
