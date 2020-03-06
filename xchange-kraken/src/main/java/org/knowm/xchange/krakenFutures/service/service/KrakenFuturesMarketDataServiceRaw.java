package org.knowm.xchange.krakenFutures.service.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.krakenFutures.KrakenFuturesExchange;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTicker;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTickers;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTrades;
import org.knowm.xchange.krakenFutures.service.KrakenFuturesBaseService;

/** @author pchertalev */
public class KrakenFuturesMarketDataServiceRaw extends KrakenFuturesBaseService {

  public KrakenFuturesMarketDataServiceRaw(KrakenFuturesExchange exchange) {

    super(exchange);
  }

  public KrakenFuturesTickers getKrakenTickers() throws IOException {
    return check(kraken::getTickers);
  }

  public KrakenFuturesTicker getKrakenTicker(
      CurrencyPair currencyPair, KrakenFuturesProduct product, LocalDate maturityDate)
      throws IOException {
    String productId = product.formatProductId(currencyPair, maturityDate);
    KrakenFuturesTickers tickers = check(kraken::getTickers);
    return tickers.getTickers().stream()
        .filter(ticker -> productId.equalsIgnoreCase(ticker.getSymbol()))
        .findFirst()
        .orElse(null);
  }

  public KrakenFuturesOrderBookResult getKrakenOrderbook(
      CurrencyPair currencyPair, KrakenFuturesProduct product, LocalDate maturityDate)
      throws IOException {
    String productId = product.formatProductId(currencyPair, maturityDate);
    return check(() -> kraken.getOrderbook(productId));
  }

  public KrakenFuturesInstruments getKrakenInstruments() throws IOException {
    return check(kraken::getInstruments);
  }

  public KrakenFuturesTrades getHistory(
      KrakenFuturesProduct product, CurrencyPair currencyPair, LocalDate maturityDate)
      throws IOException {
    return getHistory(product, currencyPair, maturityDate, null);
  }

  public KrakenFuturesTrades getHistory(
      KrakenFuturesProduct product,
      CurrencyPair currencyPair,
      LocalDate maturityDate,
      Date lastTime)
      throws IOException {
    if (product == null || currencyPair == null) {
      throw new ExchangeException("product and currency pair are mandatory");
    }
    return getHistory(product.formatProductId(currencyPair, maturityDate), lastTime);
  }

  public KrakenFuturesTrades getHistory(String symbol, Date lastTime) throws IOException {
    if (StringUtils.isBlank(symbol)) {
      throw new ExchangeException("symbol is mandatory");
    }
    return check(() -> kraken.history(symbol, lastTime));
  }
}
