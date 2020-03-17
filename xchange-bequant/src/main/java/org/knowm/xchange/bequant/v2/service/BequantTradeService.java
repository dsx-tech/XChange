package org.knowm.xchange.bequant.v2.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.bequant.v2.BequantAdapters;
import org.knowm.xchange.bequant.v2.dto.BequantOrder;
import org.knowm.xchange.bequant.v2.dto.BequantOwnTrade;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.*;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;
import org.knowm.xchange.service.trade.params.orders.OrderQueryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OrderQueryParams;

public class BequantTradeService extends BequantTradeServiceRaw implements TradeService {

  public BequantTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    return getOpenOrders(createOpenOrdersParams());
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams params) throws IOException {
    List<BequantOrder> openOrdersRaw = getOpenOrdersRaw();
    return BequantAdapters.adaptOpenOrders(openOrdersRaw);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    return placeMarketOrderRaw(marketOrder).id;
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    return placeLimitOrderRaw(limitOrder).id;
  }

  @Override
  public boolean cancelOrder(CancelOrderParams orderParams) throws IOException {
    if (orderParams instanceof CancelOrderByIdParams) {
      String clientOrderId = ((CancelOrderByIdParams) orderParams).getOrderId();
      BequantOrder cancelOrderRaw = cancelOrderRaw(clientOrderId);
      return "canceled".equals(cancelOrderRaw.status);
    } else {
      return false;
    }
  }

  /** Required parameters: {@link TradeHistoryParamPaging} {@link TradeHistoryParamCurrencyPair} */
  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    Integer limit = 1000;
    long offset = 0;

    if (params instanceof TradeHistoryParamLimit) {
      limit = ((TradeHistoryParamLimit) params).getLimit();
    }

    if (params instanceof TradeHistoryParamOffset) {
      TradeHistoryParamOffset tradeHistoryParamOffset = (TradeHistoryParamOffset) params;
      offset = tradeHistoryParamOffset.getOffset();
    }

    String symbol = null;
    if (params instanceof TradeHistoryParamCurrencyPair) {
      CurrencyPair pair = ((TradeHistoryParamCurrencyPair) params).getCurrencyPair();
      symbol = BequantAdapters.adaptCurrencyPair(pair);
    }

    List<BequantOwnTrade> tradeHistoryRaw = getTradeHistoryRaw(symbol, limit, offset);
    return BequantAdapters.adaptTradeHistory(tradeHistoryRaw);
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return new BequantTradeHistoryParams(null, 100, 0L);
  }

  @Override
  public OpenOrdersParams createOpenOrdersParams() {
    return null;
  }

  @Override
  public Collection<Order> getOrder(OrderQueryParams... orderQueryParams) throws IOException {
    if (orderQueryParams == null) {
      return new ArrayList<>();
    }

    Collection<Order> orders = new ArrayList<>();
    for (OrderQueryParams param : orderQueryParams) {
      if (!(param instanceof OrderQueryParamCurrencyPair)) {
        throw new ExchangeException(
            "Parameters must be an instance of OrderQueryParamCurrencyPair");
      }
      BequantOrder rawOrder =
          getBequantOrder(
              BequantAdapters.adaptCurrencyPair(
                  ((OrderQueryParamCurrencyPair) param).getCurrencyPair()),
              param.getOrderId());

      if (rawOrder != null) orders.add(BequantAdapters.adaptOrder(rawOrder));
    }

    return orders;
  }
}
