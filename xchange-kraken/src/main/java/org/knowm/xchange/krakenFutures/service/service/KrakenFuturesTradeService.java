package org.knowm.xchange.krakenFutures.service.service;

import org.knowm.xchange.krakenFutures.KrakenFuturesExchange;
import org.knowm.xchange.service.trade.TradeService;

/** @author pchertalev */
public class KrakenFuturesTradeService extends KrakenFuturesTradeServiceRaw
    implements TradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public KrakenFuturesTradeService(KrakenFuturesExchange exchange) {
    super(exchange);
  }
}
