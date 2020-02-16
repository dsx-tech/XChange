package org.knowm.xchange.kraken.futures;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.kraken.futures.service.service.KrakenFuturesMarketDataService;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

/** @author pchertalev */
public class KrakenFuturesExchange extends BaseExchange implements Exchange {

  private final SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  protected void initServices() {
    this.marketDataService = new KrakenFuturesMarketDataService(this);
    this.tradeService = null;
    this.accountService = null;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://futures.kraken.com/derivatives/api/v3");
    exchangeSpecification.setHost("futures.kraken.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("KrakenFutures");
    exchangeSpecification.setExchangeDescription("Kraken Futures API");
    exchangeSpecification.setShouldLoadRemoteMetaData(false);
    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }

}
