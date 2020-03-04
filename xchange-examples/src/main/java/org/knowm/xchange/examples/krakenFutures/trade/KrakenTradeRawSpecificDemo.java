package org.knowm.xchange.examples.krakenFutures.trade;

import static org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct.PI;

import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.examples.krakenFutures.KrakenFuturesExampleUtils;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesOrderType;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesSide;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenCancelAllOrders;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesFills;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOpenPositions;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrderSendStatusResult;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrders;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesRecentOrderEvents;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesTradeServiceRaw;

public class KrakenTradeRawSpecificDemo {

  public static void main(String[] args) {

    Exchange krakenExchange = KrakenFuturesExampleUtils.createTestExchange();

    // Interested in the private trading functionality (authentication)
    KrakenFuturesTradeServiceRaw tradeService =
        (KrakenFuturesTradeServiceRaw) krakenExchange.getTradeService();

    KrakenFuturesRecentOrderEvents recentOrders =
        tradeService.recentOrders(PI, CurrencyPair.ETH_USD, null);
    System.out.println(recentOrders);

    KrakenFuturesOrders openOrders = tradeService.openOrders();
    System.out.println(openOrders);

    KrakenFuturesOrderSendStatusResult sendStatus =
        tradeService.sendOrder(
            KrakenFuturesOrderType.lmt,
            CurrencyPair.LTC_USD,
            PI,
            null,
            KrakenFuturesSide.sell,
            5L,
            new BigDecimal("71.01"));
    System.out.println(sendStatus);

    KrakenFuturesFills fills = tradeService.fills(new Date(System.currentTimeMillis()));
    System.out.println(fills);

    KrakenFuturesOpenPositions openPositions = tradeService.openPositions();
    System.out.println(openPositions);

    KrakenCancelAllOrders cancelOrdersResult = tradeService.cancelAllOrders();
    System.out.println(cancelOrdersResult);

    cancelOrdersResult = tradeService.cancelAllOrders(PI, CurrencyPair.XBT_USD, null);
    System.out.println(cancelOrdersResult);
  }
}
