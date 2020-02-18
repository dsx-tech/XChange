package org.knowm.xchange.krakenFutures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesOrderType;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesSide;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesTrigerSignal;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenCancelAllOrders;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesFills;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOpenPositions;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrderSendStatusResult;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrders;
import org.knowm.xchange.krakenFutures.service.KrakenFuturesBaseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class KrakenFuturesTradeServiceRaw extends KrakenFuturesBaseService {

    /**
     * Constructor
     *
     * @param exchange
     */
    public KrakenFuturesTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public KrakenFuturesOrders openOrders() {
        return checkResult(kraken.getOpenOrders(nonceFactory.createValue().toString(), apyKey, signatureCreator));
    }

    public KrakenFuturesOrderSendStatusResult sendOrder(KrakenFuturesOrderType orderType, CurrencyPair currencyPair, KrakenFuturesProduct product, LocalDate localDate,
                                                        KrakenFuturesSide side, Long size, BigDecimal limitPrice) {
        String symbol = product.formatProductId(currencyPair, localDate);
        return checkResult(kraken.sendOrder(nonceFactory.createValue().toString(), apyKey, signatureCreator, orderType, symbol,
                side, size, limitPrice, null, null, null, null));
    }

    public KrakenFuturesOrderSendStatusResult sendOrder(KrakenFuturesOrderType orderType, CurrencyPair currencyPair, KrakenFuturesProduct product, LocalDate localDate,
                                                        KrakenFuturesSide side, Long size, BigDecimal limitPrice, BigDecimal stopPrice,
                                                        KrakenFuturesTrigerSignal triggerSignal, String cliOrdId, Boolean reduceOnly) {
        String symbol = product.formatProductId(currencyPair, localDate);
        return checkResult(kraken.sendOrder(nonceFactory.createValue().toString(), apyKey, signatureCreator, orderType, symbol,
                side, size, limitPrice, stopPrice, triggerSignal, cliOrdId, reduceOnly));
    }

    /**
     * This endpoint returns information on filled orders for all futures contracts.
     *
     * @param lastFillTime If not provided, returns the last 100 fills in any futures contract. If provided, returns the 100 entries before lastFillTime.
     * @return fills
     */
    public KrakenFuturesFills fills(Date lastFillTime) {
        return checkResult(kraken.fills(nonceFactory.createValue().toString(), apyKey, signatureCreator, lastFillTime));
    }

    public KrakenFuturesOpenPositions openPositions() {
        return checkResult(kraken.openPositions(nonceFactory.createValue().toString(), apyKey, signatureCreator));
    }

    public KrakenCancelAllOrders cancelAllOrders() {
        return cancelAllOrders(null);
    }

    public KrakenCancelAllOrders cancelAllOrders(KrakenFuturesProduct product, CurrencyPair currencyPair, LocalDate maturityDate) {
        if (product == null || currencyPair == null) {
            return cancelAllOrders(null);
        }
        String symbol = product.formatProductId(currencyPair, maturityDate);
        return cancelAllOrders(symbol);
    }

    private KrakenCancelAllOrders cancelAllOrders(String symbol) {
        return checkResult(kraken.cancelAllOrders(nonceFactory.createValue().toString(), apyKey, signatureCreator, symbol));
    }
}
