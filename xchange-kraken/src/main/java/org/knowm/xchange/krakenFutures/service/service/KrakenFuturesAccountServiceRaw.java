package org.knowm.xchange.krakenFutures.service.service;

import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesAccounts;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesTransfers;
import org.knowm.xchange.krakenFutures.service.KrakenFuturesBaseService;

/** @author pchertalev */
public class KrakenFuturesAccountServiceRaw extends KrakenFuturesBaseService {

  public KrakenFuturesAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public KrakenFuturesAccounts accounts() {
    return checkResult(
        kraken.accounts(nonceFactory.createValue().toString(), apyKey, signatureCreator));
  }

  public KrakenFuturesResult withdrawalToSpotWallet(Currency currency, BigDecimal amount) {
    return checkResult(
        kraken.withdrawal(
            nonceFactory.createValue().toString(), apyKey, signatureCreator, currency, amount));
  }

  public KrakenFuturesResult transfer(
      String fromAccount, String toAccount, Currency currency, BigDecimal amount) {
    return checkResult(
        kraken.transfer(
            nonceFactory.createValue().toString(),
            apyKey,
            signatureCreator,
            fromAccount,
            toAccount,
            currency,
            amount));
  }

  public KrakenFuturesTransfers transfers() {
    return transfers(null);
  }

  public KrakenFuturesTransfers transfers(Date lastTransferTime) {
    return checkResult(
        kraken.transfers(
            nonceFactory.createValue().toString(), apyKey, signatureCreator, lastTransferTime));
  }
}
