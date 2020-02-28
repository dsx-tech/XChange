package org.knowm.xchange.examples.krakenFutures.marketdata;

import static org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct.FI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.examples.krakenFutures.KrakenFuturesExampleUtils;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class KrakenFuturesMarketDataDemo {

  public static void main(String[] args) throws IOException {

    Exchange krakenFuturesExchange = KrakenFuturesExampleUtils.createTestExchange();

    MarketDataService marketDataService = krakenFuturesExchange.getMarketDataService();

    Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USD, FI, LocalDate.of(20, 3, 27));
    System.out.println(ticker);

    List<Ticker> tickers = marketDataService.getTickers(null);
    System.out.println(tickers);

    OrderBook orderbook =
        marketDataService.getOrderBook(CurrencyPair.BTC_USD, FI, LocalDate.of(20, 3, 27));
    System.out.println(orderbook);

    Trades trades = marketDataService.getTrades(CurrencyPair.BTC_USD, FI, LocalDate.of(20, 3, 27));
    System.out.println(trades);
  }
}
