package org.knowm.xchange.kraken.futures;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Base64;

import static org.knowm.xchange.service.BaseParamsDigest.HMAC_SHA_512;

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

    public static String getAuthent(String postData, String nonce, String endpointPath, String secretKeyBase64)
    {
        Mac mac512;
        MessageDigest sha256;
        try {
            SecretKey secretKey = new SecretKeySpec
                    (Base64.getDecoder().decode(secretKeyBase64.getBytes()), HMAC_SHA_512);
            mac512 = Mac.getInstance(HMAC_SHA_512);
            mac512.init(secretKey);
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            throw new ExchangeException("Can't encoded signature: " + e.getMessage(), e);
        }

        sha256.update(postData.getBytes());
        sha256.update(nonce.getBytes());
        sha256.update(endpointPath.getBytes());
        mac512.update(sha256.digest());
        return Base64.getEncoder().encodeToString(mac512.doFinal()).trim();
    }
}
