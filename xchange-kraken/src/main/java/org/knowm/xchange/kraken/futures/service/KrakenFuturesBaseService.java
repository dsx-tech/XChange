package org.knowm.xchange.kraken.futures.service;

import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.FrequencyLimitExceededException;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.exceptions.NonceException;
import org.knowm.xchange.exceptions.RateLimitExceededException;
import org.knowm.xchange.kraken.futures.KrakenFuturesAuthenticated;
import org.knowm.xchange.kraken.futures.dto.KrakenFuturesResult;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

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
            if (StringUtils.containsIgnoreCase(error, "nonceBelowThreshold") || StringUtils.containsIgnoreCase(error, "nonceDuplicate") ) {
                throw new NonceException(error);
            } else if (StringUtils.containsIgnoreCase(error, "apiLimitExceeded")) {
                throw new RateLimitExceededException(error);
            }
            throw new ExchangeException(error);
        }
        return krakenResult;
    }

}
