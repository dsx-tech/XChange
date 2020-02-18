package org.knowm.xchange.kraken.futures.dto.enums;

import org.knowm.xchange.kraken.futures.dto.trade.KrakenFuturesOrder;

import java.math.BigDecimal;
import java.util.function.Function;

public enum  KrakenFuturesOrderType {

    /**
     * for a limit order
     */
    lmt(KrakenFuturesOrder::getLimitPrice),

    /**
     *  for a post-only limit order
     */
    post(KrakenFuturesOrder::getLimitPrice),

    /**
     *  for a stop order
     */
    stp(KrakenFuturesOrder::getStopPrice),

    /**
     * for a take profit order
     */
    take_profit(KrakenFuturesOrder::getStopPrice),

    /**
     * for an immediate-or-cancel order
     */
    ioc(order -> null);

    public final Function<KrakenFuturesOrder, BigDecimal> priceRetriever;

    KrakenFuturesOrderType(Function<KrakenFuturesOrder, BigDecimal> priceRetriever) {
        this.priceRetriever = priceRetriever;
    }
}
