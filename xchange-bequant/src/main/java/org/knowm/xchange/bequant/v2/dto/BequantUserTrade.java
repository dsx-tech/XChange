package org.knowm.xchange.bequant.v2.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;

public class BequantUserTrade extends UserTrade {

  public final String clientOrderId;

  public BequantUserTrade(
      Order.OrderType type,
      BigDecimal originalAmount,
      CurrencyPair currencyPair,
      BigDecimal price,
      Date timestamp,
      String id,
      String orderId,
      BigDecimal feeAmount,
      Currency feeCurrency,
      String clientOrderId) {
    super(
        type, originalAmount, currencyPair, price, timestamp, id, orderId, feeAmount, feeCurrency);
    this.clientOrderId = clientOrderId;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }
}
