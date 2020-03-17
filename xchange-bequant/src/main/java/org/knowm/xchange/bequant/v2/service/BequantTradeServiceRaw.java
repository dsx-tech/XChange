package org.knowm.xchange.bequant.v2.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.bequant.v2.BequantAdapters;
import org.knowm.xchange.bequant.v2.dto.BequantBalance;
import org.knowm.xchange.bequant.v2.dto.BequantLimitOrder;
import org.knowm.xchange.bequant.v2.dto.BequantMarketOrder;
import org.knowm.xchange.bequant.v2.dto.BequantOrder;
import org.knowm.xchange.bequant.v2.dto.BequantOwnTrade;
import org.knowm.xchange.bequant.v2.dto.BequantSort;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;

public class BequantTradeServiceRaw extends BequantBaseService {

  public BequantTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<BequantOrder> getOpenOrdersRaw() throws IOException {
    return bequant.getBequantActiveOrders();
  }

  public BequantOrder placeMarketOrderRaw(MarketOrder marketOrder) throws IOException {

    String symbol = BequantAdapters.adaptCurrencyPair(marketOrder.getCurrencyPair());
    String side = BequantAdapters.getSide(marketOrder.getType()).toString();

    String clientOrderId = null;
    if (marketOrder instanceof BequantMarketOrder) {
      clientOrderId = ((BequantMarketOrder) marketOrder).getClientOrderId();
    }

    return bequant.postBequantNewOrder(
        clientOrderId,
        symbol,
        side,
        null,
        marketOrder.getOriginalAmount(),
        BequantOrderType.market,
        BequantTimeInForce.IOC);
  }

  public BequantOrder placeLimitOrderRaw(LimitOrder limitOrder, BequantTimeInForce timeInForce)
      throws IOException {
    String symbol = BequantAdapters.adaptCurrencyPair(limitOrder.getCurrencyPair());
    String side = BequantAdapters.getSide(limitOrder.getType()).toString();

    String clientOrderId = null;
    if (limitOrder instanceof BequantLimitOrder) {
      BequantLimitOrder order = (BequantLimitOrder) limitOrder;
      clientOrderId = order.getClientOrderId();
    }

    return bequant.postBequantNewOrder(
        clientOrderId,
        symbol,
        side,
        limitOrder.getLimitPrice(),
        limitOrder.getOriginalAmount(),
        BequantOrderType.limit,
        timeInForce);
  }

  public BequantOrder placeLimitOrderRaw(LimitOrder limitOrder) throws IOException {
    return placeLimitOrderRaw(limitOrder, BequantTimeInForce.GTC);
  }

  public BequantOrder updateMarketOrderRaw(
      String clientOrderId, BigDecimal quantity, String requestClientId, Optional<BigDecimal> price)
      throws IOException {

    return bequant.updateBequantOrder(clientOrderId, quantity, requestClientId, price.orElse(null));
  }

  public BequantOrder cancelOrderRaw(String clientOrderId) throws IOException {
    return bequant.cancelSingleOrder(clientOrderId);
  }

  public List<BequantOrder> cancelAllOrdersRaw(String symbol) throws IOException {
    return bequant.cancelAllOrders(symbol);
  }

  public List<BequantOwnTrade> getHistorialTradesByOrder(String orderId) throws IOException {
    return bequant.getHistorialTradesByOrder(orderId);
  }

  public List<BequantOrder> getBequantRecentOrders() throws IOException {
    return bequant.getBequantRecentOrders();
  }

  public List<BequantOwnTrade> getTradeHistoryRaw(String symbol, Integer limit, long offset)
      throws IOException {
    return bequant.getBequantTrades(symbol, null, null, null, null, limit, offset);
  }

  public List<BequantOwnTrade> getTradeHistoryRaw(
      String symbol, BequantSort sort, Date from, Date till, Integer limit, long offset)
      throws IOException {
    String sortValue = sort != null ? sort.toString().toUpperCase() : null;
    String fromValue = from != null ? Instant.ofEpochMilli(from.getTime()).toString() : null;
    String tillValue = till != null ? Instant.ofEpochMilli(till.getTime()).toString() : null;
    return bequant.getBequantTrades(
        symbol, sortValue, "timestamp", fromValue, tillValue, limit, offset);
  }

  public List<BequantOwnTrade> getTradeHistoryRaw(
      String symbol, BequantSort sort, Long fromId, Long tillId, Integer limit, long offset)
      throws IOException {
    String sortValue = sort != null ? sort.toString().toUpperCase() : null;
    String fromValue = fromId != null ? fromId.toString() : null;
    String tillValue = tillId != null ? tillId.toString() : null;
    return bequant.getBequantTrades(symbol, sortValue, "id", fromValue, tillValue, limit, offset);
  }

  public BequantOrder getBequantOrder(String symbol, String clientOrderId) throws IOException {
    List<BequantOrder> orders = bequant.getBequantOrder(symbol, clientOrderId);

    if (orders == null || orders.isEmpty()) {
      return null;
    } else {
      return orders.iterator().next();
    }
  }

  public List<BequantBalance> getTradingBalance() throws IOException {

    return bequant.getTradingBalance();
  }
}
