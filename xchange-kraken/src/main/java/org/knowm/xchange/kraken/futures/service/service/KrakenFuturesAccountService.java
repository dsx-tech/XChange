package org.knowm.xchange.kraken.futures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.account.AccountService;

public class KrakenFuturesAccountService extends KrakenFuturesAccountServiceRaw implements AccountService {

  public KrakenFuturesAccountService(Exchange exchange) {
    super(exchange);
  }

}
