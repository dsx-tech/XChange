package org.knowm.xchange.krakenFutures.service;

import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.exceptions.NonceException;
import org.knowm.xchange.exceptions.RateLimitExceededException;
import org.knowm.xchange.krakenFutures.KrakenFuturesAuthenticated;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;
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

  protected <T extends KrakenFuturesResult> T checkResult(T krakenResult) {
    String error = krakenResult.getError();
    if (StringUtils.isNotBlank(error)) {
      if (StringUtils.containsIgnoreCase(error, "nonceBelowThreshold")
          || StringUtils.containsIgnoreCase(error, "nonceDuplicate")) {
        throw new NonceException(error);
      } else if (StringUtils.containsIgnoreCase(error, "insufficientFunds")) {
        throw new FundsExceededException(error);
      } else if (StringUtils.containsIgnoreCase(error, "apiLimitExceeded")) {
        throw new RateLimitExceededException(error);
      }
      throw new ExchangeException(error);
    }
    return krakenResult;
  }
}
