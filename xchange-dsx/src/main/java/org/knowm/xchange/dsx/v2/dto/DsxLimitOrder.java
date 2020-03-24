package org.knowm.xchange.dsx.v2.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.LimitOrder;

public class DsxLimitOrder extends LimitOrder {
  public final String clientOrderId;

  public DsxLimitOrder(
      OrderType type,
      BigDecimal originalAmount,
      CurrencyPair currencyPair,
      String id,
      Date timestamp,
      BigDecimal limitPrice,
      String clientOrderId) {
    super(type, originalAmount, currencyPair, id, timestamp, limitPrice);
    this.clientOrderId = clientOrderId;
  }

  public DsxLimitOrder(
      OrderType type,
      BigDecimal originalAmount,
      BigDecimal cumulativeAmount,
      CurrencyPair currencyPair,
      String id,
      Date timestamp,
      BigDecimal limitPrice,
      String clientOrderId) {
    super(type, originalAmount, cumulativeAmount, currencyPair, id, timestamp, limitPrice);
    this.clientOrderId = clientOrderId;
  }

  public DsxLimitOrder(
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
      String clientOrderId) {
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
    this.clientOrderId = clientOrderId;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }
}
