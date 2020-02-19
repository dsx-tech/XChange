package org.knowm.xchange.krakenFutures.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.LimitOrder;

public class KrakenFuturesMarketOrder extends LimitOrder {

  public KrakenFuturesMarketOrder(
      OrderType type,
      BigDecimal originalAmount,
      CurrencyPair currencyPair,
      String id,
      Date timestamp,
      BigDecimal limitPrice) {
    super(type, originalAmount, currencyPair, id, timestamp, limitPrice);
  }

  public KrakenFuturesMarketOrder(
      OrderType type,
      BigDecimal originalAmount,
      BigDecimal cumulativeAmount,
      CurrencyPair currencyPair,
      String id,
      Date timestamp,
      BigDecimal limitPrice) {
    super(type, originalAmount, cumulativeAmount, currencyPair, id, timestamp, limitPrice);
  }

  public KrakenFuturesMarketOrder(
      OrderType type,
      BigDecimal originalAmount,
      CurrencyPair currencyPair,
      String id,
      Date timestamp,
      BigDecimal limitPrice,
      BigDecimal averagePrice,
      BigDecimal cumulativeAmount,
      BigDecimal fee,
      OrderStatus status) {
    super(
        type,
        originalAmount,
        currencyPair,
        id,
        timestamp,
        limitPrice,
        averagePrice,
        cumulativeAmount,
        fee,
        status);
  }

  public KrakenFuturesMarketOrder(
      OrderType type,
      BigDecimal originalAmount,
      CurrencyPair currencyPair,
      String id,
      Date timestamp,
      BigDecimal limitPrice,
      BigDecimal averagePrice,
      BigDecimal cumulativeAmount,
      BigDecimal fee,
      OrderStatus status,
      String userReference) {
    super(
        type,
        originalAmount,
        currencyPair,
        id,
        timestamp,
        limitPrice,
        averagePrice,
        cumulativeAmount,
        fee,
        status,
        userReference);
  }
}
