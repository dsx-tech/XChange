package org.knowm.xchange.krakenFutures.dto.enums;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public enum KrakenFuturesProduct {

    FI("Inverse Futures", true),
    FV("Vanilla Futures", true),
    PI("Perpetual Inverse Futures", false),
    PV("Perpetual Vanilla Futures", false),
    IN("Real Time Index", false),
    RR("Reference Rate", false);

    public final static DateTimeFormatter MATURITY_DATE_FORMAT = DateTimeFormatter.ofPattern("yyMMdd");
    private final static String PRODUCT_DELIMITER_CHAR = "_";

    public final String codeName;
    public final boolean mustHaveMaturityDate;

    KrakenFuturesProduct(String codeName, boolean mustHaveMaturityDate) {
        this.codeName = codeName;
        this.mustHaveMaturityDate = mustHaveMaturityDate;
    }

    public static KrakenFuturesProduct getProduct(String productName) {
        return Arrays.stream(values())
                .filter(product -> product.name().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    /**
     * @param maturityDate 16:00 UTC
     * @return formatted productId string
     */
    public String formatProductId(CurrencyPair pair, LocalDate maturityDate) {
        if (maturityDate != null && mustHaveMaturityDate) {
            return formatProductId(pair) + PRODUCT_DELIMITER_CHAR + MATURITY_DATE_FORMAT.format(maturityDate);
        }
        return formatProductId(pair);
    }

    public String formatProductId(CurrencyPair pair) {
        return this.name() + PRODUCT_DELIMITER_CHAR + StringUtils.upperCase(StringUtils.upperCase(pair.base.toString() + pair.counter.toString()));
    }

    public static ImmutableTriple<KrakenFuturesProduct, CurrencyPair, LocalDate> parseProductId(String productId) {
        if (productId != null) {
            String [] productIdItems = productId.split(PRODUCT_DELIMITER_CHAR);
            if (productIdItems.length > 1) {
                CurrencyPair currencyPair = CurrencyPair.fromSymbol(productIdItems[1]);
                if (currencyPair != null) {
                    KrakenFuturesProduct product = getProduct(productIdItems[0]);
                    if (product != null) {
                        LocalDate maturityDate = null;
                        if (product.mustHaveMaturityDate && productIdItems.length == 3) {
                            try {
                                maturityDate = LocalDate.parse(productIdItems[2], MATURITY_DATE_FORMAT);
                                return ImmutableTriple.of(product, currencyPair, maturityDate);
                            } catch (DateTimeException e) {
                                throw new ExchangeException("invalid maturityDate format: " + productIdItems[2]);
                            }
                        } else if (!product.mustHaveMaturityDate && productIdItems.length == 2) {
                            return ImmutableTriple.of(product, currencyPair, null);
                        }
                        throw new ExchangeException("Invalid product prefix format: " + productId);
                    }
                }
                throw new ExchangeException("invalid currency pair format: " + productIdItems[1]);
            }
        }
        throw new ExchangeException("Invalid productId format: " + productId);
    }

}
