package org.knowm.xchange.kraken.futures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.kraken.KrakenAdapters;
import org.knowm.xchange.kraken.futures.KrakenFuturesAdapters;
import org.knowm.xchange.kraken.futures.dto.trade.KrakenFuturesOrders;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;

public class KrakenFuturesTradeService extends KrakenFuturesTradeServiceRaw implements TradeService {

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
    KrakenFuturesOrders krakenOpenOrders = getKrakenOpenOrders();
    return KrakenFuturesAdapters.adaptOrders(krakenOpenOrders);
  }

}
