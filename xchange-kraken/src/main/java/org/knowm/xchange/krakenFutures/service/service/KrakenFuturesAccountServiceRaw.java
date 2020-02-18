package org.knowm.xchange.krakenFutures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesAccounts;
import org.knowm.xchange.krakenFutures.service.KrakenFuturesBaseService;

/**
 * @author pchertalev
 */
public class KrakenFuturesAccountServiceRaw extends KrakenFuturesBaseService {

  public KrakenFuturesAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public KrakenFuturesAccounts accounts() {
    return kraken.accounts(nonceFactory.createValue().toString(), apyKey, signatureCreator);
  }

}
