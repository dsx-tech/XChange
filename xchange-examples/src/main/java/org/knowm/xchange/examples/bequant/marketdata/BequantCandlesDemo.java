package org.knowm.xchange.examples.bequant.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.examples.bequant.BequantExampleUtils;
import org.knowm.xchange.bequant.v2.dto.BequantCandle;
import org.knowm.xchange.bequant.v2.service.BequantMarketDataServiceRaw;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class BequantCandlesDemo {

  public static void main(String[] args) throws Exception {

    Exchange bequantExchange = BequantExampleUtils.createExchange();

    bequantExchange.remoteInit();
    System.out.println(
        "Market metadata: " + bequantExchange.getExchangeMetaData().getCurrencyPairs().toString());

    MarketDataService marketDataService = bequantExchange.getMarketDataService();
    BequantMarketDataServiceRaw bequantMarketDataService =
        (BequantMarketDataServiceRaw) bequantExchange.getMarketDataService();

    getCandles(bequantMarketDataService);
  }

  private static void getCandles(BequantMarketDataServiceRaw bequantMarketDataService)
      throws IOException, ParseException {
    CurrencyPair currencyPair = new CurrencyPair("BTC/USD");
    int limit = 10;
    String sort = "ASC";
    String period = "M15";
    int offset = 10;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime end = LocalDateTime.parse("2019-01-24 00:00", formatter);
    LocalDateTime start = LocalDateTime.parse("2019-01-23 00:00", formatter);

    Date from = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
    Date till = Date.from(end.atZone(ZoneId.systemDefault()).toInstant());

    // default is latest candles sorted ASC
    System.out.println("Default");
    List<BequantCandle> candles =
        bequantMarketDataService.getBequantCandles(currencyPair, limit, period);
    printCandles(candles);

    // sorted
    sort = "ASC";
    System.out.println("Sorted " + sort);
    candles = bequantMarketDataService.getBequantCandles(currencyPair, limit, period, sort);
    printCandles(candles);

    sort = "DESC";
    System.out.println("Sorted " + sort);
    candles = bequantMarketDataService.getBequantCandles(currencyPair, limit, period, sort);
    printCandles(candles);

    // sorted with date range
    System.out.println("Filtered from " + from + " to " + till + " and sort " + sort);
    candles =
        bequantMarketDataService.getBequantCandles(currencyPair, limit, period, from, till, "ASC");

    printCandles(candles);

    // using offset
    System.out.println("Using offset " + offset + " and sort " + sort);
    candles = bequantMarketDataService.getBequantCandles(currencyPair, limit, period, offset, sort);
    printCandles(candles);
  }

  private static void printCandles(List<BequantCandle> candles) {
    System.out.println(
        "----------------------------------------------------------------------------------------");
    System.out.printf(
        "%-30s %-15s %-15s %-15s %-15s \n", "Timestamp", "Open", "Max", "Min", "Close");
    System.out.println(
        "----------------------------------------------------------------------------------------");
    for (BequantCandle candle : candles) {
      System.out.printf(
          "%-30s %-15s %-15s %-15s %-15s \n",
          candle.getTimestamp(),
          candle.getOpen(),
          candle.getMax(),
          candle.getMin(),
          candle.getClose());
    }
    System.out.println(
        "----------------------------------------------------------------------------------------");

    System.out.println();
    System.out.println();
  }
}
