package org.knowm.xchange.krakenFutures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.trade.TradeService;

/** @author pchertalev */
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
}
