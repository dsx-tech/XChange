package org.knowm.xchange.examples.krakenFutures.account;

import java.io.IOException;
import java.math.BigDecimal;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.examples.krakenFutures.KrakenFuturesExampleUtils;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesAccounts;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesTransfers;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesAccountServiceRaw;

public class KrakenFuturesAccountRawSpecificDemo {

  public static void main(String[] args) throws IOException {

    Exchange krakenExchange = KrakenFuturesExampleUtils.createTestExchange();

    KrakenFuturesAccountServiceRaw rawKrakenFuturesAcctService =
        (KrakenFuturesAccountServiceRaw) krakenExchange.getAccountService();

    KrakenFuturesAccounts accounts = rawKrakenFuturesAcctService.accounts();
    System.out.println(accounts);

    KrakenFuturesResult transfer =
        rawKrakenFuturesAcctService.transfer(
            "cash", "fi_xbtusd", Currency.XBT, new BigDecimal("0.01"));
    System.out.println(transfer);

    KrakenFuturesTransfers transfers = rawKrakenFuturesAcctService.transfers();
    System.out.println(transfers);

    KrakenFuturesResult withdrawalResult =
        rawKrakenFuturesAcctService.withdrawalToSpotWallet(Currency.XBT, new BigDecimal("0.0001"));
    System.out.println(withdrawalResult);
  }
}
