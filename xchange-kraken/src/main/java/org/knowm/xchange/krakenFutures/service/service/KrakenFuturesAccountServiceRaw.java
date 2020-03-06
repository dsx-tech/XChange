package org.knowm.xchange.krakenFutures.service.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.krakenFutures.KrakenFuturesExchange;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesAccounts;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesTransfers;
import org.knowm.xchange.krakenFutures.service.KrakenFuturesBaseService;

/** @author pchertalev */
public class KrakenFuturesAccountServiceRaw extends KrakenFuturesBaseService {

  public KrakenFuturesAccountServiceRaw(KrakenFuturesExchange exchange) {
    super(exchange);
  }

  public KrakenFuturesAccounts accounts() throws IOException {
    return kraken.accounts(nonceFactory.createValue().toString(), apyKey, signatureCreator);
  }

  public KrakenFuturesResult withdrawalToSpotWallet(Currency currency, BigDecimal amount)
      throws IOException {
    return check(
        () ->
            kraken.withdrawal(
                nonceFactory.createValue().toString(),
                apyKey,
                signatureCreator,
                currency.getIso4217Currency(),
                amount));
  }

  public KrakenFuturesResult transfer(
      String fromAccount, String toAccount, Currency currency, BigDecimal amount)
      throws IOException {
    return check(
        () ->
            kraken.transfer(
                nonceFactory.createValue().toString(),
                apyKey,
                signatureCreator,
                fromAccount,
                toAccount,
                currency.getIso4217Currency(),
                amount));
  }

  public KrakenFuturesTransfers transfers() throws IOException {
    return transfers(null);
  }

  public KrakenFuturesTransfers transfers(Date lastTransferTime) throws IOException {
    return check(
        () ->
            kraken.transfers(
                nonceFactory.createValue().toString(), apyKey, signatureCreator, lastTransferTime));
  }
}
