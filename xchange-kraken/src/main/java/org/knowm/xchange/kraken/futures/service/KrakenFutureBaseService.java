package org.knowm.xchange.kraken.futures.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.FrequencyLimitExceededException;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.exceptions.NonceException;
import org.knowm.xchange.exceptions.RateLimitExceededException;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.futures.KrakenFuturesAuthenticated;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

import java.util.Arrays;

public class KrakenFutureBaseService extends BaseExchangeService implements BaseService {

  protected KrakenFuturesAuthenticated kraken;
  protected ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public KrakenFutureBaseService(Exchange exchange) {

    super(exchange);

    kraken =
        RestProxyFactory.createProxy(
                KrakenFuturesAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri(),
            getClientConfig());
    signatureCreator =
        KrakenFuturesDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  protected <R> R checkResult(KrakenResult<R> krakenResult) {

    if (!krakenResult.isSuccess()) {
      String[] errors = krakenResult.getError();
      if (errors.length == 0) {
        throw new ExchangeException("Missing error message");
      }
      String error = errors[0];

      if ("EAPI:Invalid nonce".equals(error)) {
        throw new NonceException(error);

      } else if ("EGeneral:Temporary lockout".equals(error)) {
        throw new FrequencyLimitExceededException(error);

      } else if ("EOrder:Insufficient funds".equals(error)) {
        throw new FundsExceededException(error);
      }
      if ("EAPI:Rate limit exceeded".equals(error)) {
        throw new RateLimitExceededException(error);
      }

      throw new ExchangeException(Arrays.toString(errors));
    }

    return krakenResult.getResult();
  }

}
