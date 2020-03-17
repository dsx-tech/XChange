package org.knowm.xchange.examples.bequant.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.examples.bequant.BequantExampleUtils;
import org.knowm.xchange.bequant.v2.dto.BequantOrderBook;
import org.knowm.xchange.bequant.v2.dto.BequantTicker;
import org.knowm.xchange.bequant.v2.service.BequantMarketDataServiceRaw;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.Map;

public class BequantMarketDataDemo {

  public static void main(String[] args) throws Exception {

    Exchange bequantExchange = BequantExampleUtils.createExchange();

    bequantExchange.remoteInit();
    System.out.println(
        "Market metadata: " + bequantExchange.getExchangeMetaData().getCurrencyPairs().toString());

    MarketDataService marketDataService = bequantExchange.getMarketDataService();

    generic(marketDataService);
    raw((BequantMarketDataServiceRaw) marketDataService);
  }

  private static void generic(MarketDataService marketDataService) throws IOException {

    Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USD);
    System.out.println("BTC / USD Ticker: " + ticker.toString());

    // Get the latest order book data for BTC/USD
    OrderBook orderBook = marketDataService.getOrderBook(CurrencyPair.BTC_USD);

    System.out.println(
        "Current Order Book size for BTC/USD: "
            + (orderBook.getAsks().size() + orderBook.getBids().size()));

    System.out.println("First Ask: " + orderBook.getAsks().get(0).toString());

    System.out.println("First Bid: " + orderBook.getBids().get(0).toString());

    System.out.println(orderBook.toString());

    // Get the latest trade data for BTC/USD
    Trades trades = marketDataService.getTrades(CurrencyPair.BTC_USD);
    System.out.println("Trades, default. Size=" + trades.getTrades().size());
  }

  private static void raw(BequantMarketDataServiceRaw marketDataService) throws IOException {

    BequantTicker ticker = marketDataService.getBequantTicker(CurrencyPair.BTC_USD);
    System.out.println("BTC/USD Ticker: " + ticker.toString());

    Map<String, BequantTicker> tickers = marketDataService.getBequantTickers();
    System.out.println("All Tickers: " + tickers.toString());

    // Get the latest order book data for BTC/USD
    BequantOrderBook orderBook = marketDataService.getBequantOrderBook(CurrencyPair.BTC_USD);

    System.out.println(
        "Current Order Book size for BTC/USD: "
            + (orderBook.getAsks().length + orderBook.getBids().length));

    System.out.println(orderBook);
  }
}
