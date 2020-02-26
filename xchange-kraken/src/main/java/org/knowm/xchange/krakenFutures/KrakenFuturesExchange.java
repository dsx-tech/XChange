package org.knowm.xchange.krakenFutures;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesAccountService;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesMarketDataService;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesTradeService;
import org.knowm.xchange.utils.nonce.TimestampIncrementingNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

/** @author pchertalev */
public class KrakenFuturesExchange extends BaseExchange implements Exchange {

  private final SynchronizedValueFactory<Long> nonceFactory =
      new TimestampIncrementingNonceFactory();

  @Override
  protected void initServices() {
    this.marketDataService = new KrakenFuturesMarketDataService(this);
    this.tradeService = new KrakenFuturesTradeService(this);
    this.accountService = new KrakenFuturesAccountService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://futures.kraken.com/derivatives");
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
