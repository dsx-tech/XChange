package org.knowm.xchange.examples.krakenFutures.marketdata;

import static org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct.FI;

import java.time.LocalDate;
import java.util.Date;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.examples.krakenFutures.KrakenFuturesExampleUtils;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTicker;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTrades;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesMarketDataServiceRaw;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class KrakenFuturesMarketDataRawDemo {

  public static void main(String[] args) {

    Exchange krakenFuturesExchange = KrakenFuturesExampleUtils.createTestExchange();

    MarketDataService marketDataService = krakenFuturesExchange.getMarketDataService();

    KrakenFuturesMarketDataServiceRaw marketDataServiceRaw =
        (KrakenFuturesMarketDataServiceRaw) marketDataService;

    KrakenFuturesInstruments instruments = marketDataServiceRaw.getKrakenInstruments();
    System.out.println(instruments);

    KrakenFuturesTicker ticker =
        marketDataServiceRaw.getKrakenTicker(CurrencyPair.XBT_USD, FI, LocalDate.of(20, 3, 27));
    System.out.println(ticker);

    KrakenFuturesOrderBookResult orderBook =
        marketDataServiceRaw.getKrakenOrderbook(CurrencyPair.XBT_USD, FI, LocalDate.of(20, 3, 27));
    System.out.println(orderBook);

    KrakenFuturesTrades trades =
        marketDataServiceRaw.getHistory(
            FI, CurrencyPair.XBT_USD, LocalDate.of(20, 3, 27), new Date());
    System.out.println(trades);
  }
}
