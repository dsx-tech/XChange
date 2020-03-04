package org.knowm.xchange.krakenFutures.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.krakenFutures.KrakenFuturesAuthenticated;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

/** @author pchertalev */
public class KrakenFuturesBaseService extends BaseExchangeService implements BaseService {

  protected final KrakenFuturesAuthenticated kraken;
  protected final KrakenFuturesDigest signatureCreator;
  protected final String apyKey;
  protected final SynchronizedValueFactory<Long> nonceFactory;

  public KrakenFuturesBaseService(Exchange exchange) {
    super(exchange);
    kraken =
        RestProxyFactory.createProxy(
            KrakenFuturesAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri(),
            getClientConfig());

    apyKey = exchange.getExchangeSpecification().getApiKey();
    signatureCreator = new KrakenFuturesDigest(exchange.getExchangeSpecification().getSecretKey());
    nonceFactory = exchange.getNonceFactory();
  }
}
