package org.knowm.xchange.krakenFutures.service;

import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.exceptions.NonceException;
import org.knowm.xchange.exceptions.RateLimitExceededException;
import org.knowm.xchange.krakenFutures.KrakenFuturesAuthenticated;
import org.knowm.xchange.krakenFutures.KrakenFuturesExchange;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResultException;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesError;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;

/** @author pchertalev */
public class KrakenFuturesBaseService extends BaseExchangeService<KrakenFuturesExchange>
    implements BaseService {

  protected final KrakenFuturesAuthenticated kraken;
  protected final KrakenFuturesDigest signatureCreator;
  protected final String apyKey;
  protected final SynchronizedValueFactory<Long> nonceFactory;

  public KrakenFuturesBaseService(KrakenFuturesExchange exchange) {
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

  @FunctionalInterface
  public interface Supplier<T> {
    T get() throws IOException;
  }

  protected <T> T check(Supplier<T> result) throws IOException {
    try {
      return result.get();
    } catch (KrakenFuturesResultException ke) {
      if (StringUtils.containsIgnoreCase(
          ke.getMessage(), KrakenFuturesError.apiLimitExceeded.name())) {
        throw new RateLimitExceededException(ke.getMessage());
      } else if (StringUtils.containsIgnoreCase(ke.getMessage(), "nonce")) {
        throw new NonceException(ke.getMessage());
      } else {
        throw ke;
      }
    }
  }
}
