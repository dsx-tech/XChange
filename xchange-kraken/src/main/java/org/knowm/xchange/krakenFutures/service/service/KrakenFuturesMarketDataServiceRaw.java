package org.knowm.xchange.krakenFutures.service.service;

import java.time.LocalDate;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTicker;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTickers;
import org.knowm.xchange.krakenFutures.service.KrakenFuturesBaseService;

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

  public KrakenFuturesTicker getKrakenTicker(
      CurrencyPair currencyPair, KrakenFuturesProduct product, LocalDate maturityDate) {
    String productId = product.formatProductId(currencyPair, maturityDate);
    KrakenFuturesTickers tickers = kraken.getTickers();
    return tickers.getTickers().stream()
        .filter(ticker -> productId.equalsIgnoreCase(ticker.getSymbol()))
        .findFirst()
        .orElse(null);
  }

  public KrakenFuturesOrderBookResult getKrakenOrderbook(
      CurrencyPair currencyPair, KrakenFuturesProduct product, LocalDate maturityDate) {
    String productId = product.formatProductId(currencyPair, maturityDate);
    return kraken.getOrderbook(productId);
  }

  public KrakenFuturesInstruments getKrakenInstruments() {
    return kraken.getInstruments();
  }
}
