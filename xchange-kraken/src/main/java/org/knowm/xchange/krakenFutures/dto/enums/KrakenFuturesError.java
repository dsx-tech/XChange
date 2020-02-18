package org.knowm.xchange.krakenFutures.dto.enums;

import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.ExchangeSecurityException;
import org.knowm.xchange.exceptions.FrequencyLimitExceededException;

import java.util.Arrays;
import java.util.function.Function;

public enum KrakenFuturesError {
    apiLimitExceeded(FrequencyLimitExceededException::new),
    authenticationError(ExchangeSecurityException::new),

    accountInactive,
    requiredArgumentMissing,
    invalidArgument,
    nonceBelowThreshold,
    nonceDuplicate
    ;

    private final Function<String, ExchangeException> exceptionFactory;

    KrakenFuturesError() {
        exceptionFactory = null;
    }

    KrakenFuturesError(Function<String, ExchangeException> exceptionFactory) {
        this.exceptionFactory = exceptionFactory;
    }

    public ExchangeException getException(String errorMessage) {
        return Arrays.stream(values())
                .filter(error -> StringUtils.containsIgnoreCase(errorMessage, error.name()))
                .filter(error -> error.exceptionFactory != null)
                .map(error -> error.exceptionFactory.apply(errorMessage))
                .findFirst()
                .orElse(new ExchangeException(errorMessage));

    }
}
