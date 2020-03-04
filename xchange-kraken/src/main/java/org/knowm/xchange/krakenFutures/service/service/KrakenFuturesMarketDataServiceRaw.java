package org.knowm.xchange.krakenFutures.service.service;

import java.time.LocalDate;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTicker;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTickers;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTrades;
import org.knowm.xchange.krakenFutures.service.KrakenFuturesBaseService;

/** @author pchertalev */
public class KrakenFuturesMarketDataServiceRaw extends KrakenFuturesBaseService {

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

  public KrakenFuturesTrades getHistory(
      KrakenFuturesProduct product, CurrencyPair currencyPair, LocalDate maturityDate) {
    return getHistory(product, currencyPair, maturityDate, null);
  }

  public KrakenFuturesTrades getHistory(
      KrakenFuturesProduct product,
      CurrencyPair currencyPair,
      LocalDate maturityDate,
      Date lastTime) {
    if (product == null || currencyPair == null) {
      throw new ExchangeException("product and currency pair are mandatory");
    }
    return getHistory(product.formatProductId(currencyPair, maturityDate), lastTime);
  }

  public KrakenFuturesTrades getHistory(String symbol, Date lastTime) {
    if (StringUtils.isBlank(symbol)) {
      throw new ExchangeException("symbol is mandatory");
    }
    return kraken.history(symbol, lastTime);
  }
}
