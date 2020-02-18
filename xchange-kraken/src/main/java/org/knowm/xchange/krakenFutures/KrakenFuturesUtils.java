package org.knowm.xchange.krakenFutures;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * @author pchertalev
 */
public class KrakenFuturesUtils {

    private final static Logger log = LoggerFactory.getLogger(KrakenFuturesUtils.class);

    public static String getProductId(CurrencyPair currencyPair, Object [] args) {
        validateCurrencyPair(currencyPair);
        KrakenFuturesProduct product = getIndexedValue("product", 0, KrakenFuturesProduct.class, null, true, args);

        LocalDate maturityDate = null;
        if (product.mustHaveMaturityDate) {
            maturityDate = getIndexedValue("maturityDate", 1, LocalDate.class, null, true, args);
        }
        return product.formatProductId(currencyPair, maturityDate);
    }

    private static void validateCurrencyPair(CurrencyPair currencyPair) {
        if (currencyPair == null) {
            throw new ExchangeException("Currency pair is mandatory");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getIndexedValue(String name, int index, Class<T> expectedClass, T defaultValue, boolean failIfMissing, Object... args) {
        if (args.length >= index + 1 && expectedClass != null) {
            if (expectedClass.isAssignableFrom(args[index].getClass())) {
                return (T) args[index];
            } else {
                throw new ExchangeException(
                        String.format("Invalid type of parameter #%d (%s): expected %s but actual %s",
                                index, name, expectedClass.getName(), args[index].getClass()));
            }
        }
        if (defaultValue != null) {
            log.warn("Parameter '{}' was not correctly specified, so the default value {} is used", name, defaultValue);
            return defaultValue;
        }
        if (failIfMissing) {
            throw new ExchangeException(String.format("Parameter #%d (%s) is not specified", index, name));
        }
        return null;
    }

}
