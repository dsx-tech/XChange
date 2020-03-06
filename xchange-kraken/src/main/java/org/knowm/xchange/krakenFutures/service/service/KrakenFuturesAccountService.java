package org.knowm.xchange.krakenFutures.service.service;

import org.knowm.xchange.krakenFutures.KrakenFuturesExchange;
import org.knowm.xchange.service.account.AccountService;

/** @author pchertalev */
public class KrakenFuturesAccountService extends KrakenFuturesAccountServiceRaw
    implements AccountService {

  public KrakenFuturesAccountService(KrakenFuturesExchange exchange) {
    super(exchange);
  }
}
