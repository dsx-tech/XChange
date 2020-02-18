package org.knowm.xchange.kraken.futures.service.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.kraken.futures.dto.account.KrakenFuturesAccounts;
import org.knowm.xchange.kraken.futures.service.KrakenFuturesBaseService;

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
