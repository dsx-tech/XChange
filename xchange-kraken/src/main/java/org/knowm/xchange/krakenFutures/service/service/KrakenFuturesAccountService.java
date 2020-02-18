package org.knowm.xchange.krakenFutures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.account.AccountService;

public class KrakenFuturesAccountService extends KrakenFuturesAccountServiceRaw implements AccountService {

  public KrakenFuturesAccountService(Exchange exchange) {
    super(exchange);
  }

}
