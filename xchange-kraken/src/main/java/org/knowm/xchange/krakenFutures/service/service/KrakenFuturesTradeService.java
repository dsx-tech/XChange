package org.knowm.xchange.krakenFutures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.krakenFutures.KrakenFuturesAdapters;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrders;
import org.knowm.xchange.service.trade.TradeService;

public class KrakenFuturesTradeService extends KrakenFuturesTradeServiceRaw
    implements TradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public KrakenFuturesTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() {
    KrakenFuturesOrders krakenOpenOrders = openOrders();
    return KrakenFuturesAdapters.adaptOrders(krakenOpenOrders);
  }
}
