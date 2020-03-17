package org.knowm.xchange.bequant.v2.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.bequant.v2.dto.*;
import org.knowm.xchange.bequant.v2.dto.BequantAddress;
import org.knowm.xchange.bequant.v2.dto.BequantBalance;
import org.knowm.xchange.bequant.v2.dto.BequantInternalTransferResponse;
import org.knowm.xchange.bequant.v2.dto.BequantSort;
import org.knowm.xchange.bequant.v2.dto.BequantTransaction;
import org.knowm.xchange.bequant.v2.dto.BequantTransferType;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.exceptions.ExchangeException;
import si.mazi.rescu.HttpStatusIOException;

public class BequantAccountServiceRaw extends BequantBaseService {

  public BequantAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public String withdrawFundsRaw(
      Currency currency, BigDecimal amount, String address, String paymentId)
      throws HttpStatusIOException {

    return withdrawFundsRaw(currency, amount, address, paymentId, false);
  }

  public String withdrawFundsRaw(
      Currency currency, BigDecimal amount, String address, String paymentId, Boolean includeFee)
      throws HttpStatusIOException {
    Map response =
        bequant.payout(amount, currency.getCurrencyCode(), address, paymentId, includeFee);

    return response.get("id").toString();
  }

  public BequantInternalTransferResponse transferFunds(
      Currency currency, BigDecimal amount, BequantTransferType bequantTransferType)
      throws IOException {
    return bequant.transferToTrading(
        amount, currency.getCurrencyCode(), bequantTransferType.getType());
  }

  public String transferToTrading(Currency currency, BigDecimal amount) throws IOException {

    BequantInternalTransferResponse response =
        transferFunds(currency, amount, BequantTransferType.BANK_TO_EXCHANGE);

    if (response.id == null) {
      throw new ExchangeException("transfer failed: " + response);
    }
    return response.id;
  }

  public String transferToMain(Currency currency, BigDecimal amount) throws IOException {
    BequantInternalTransferResponse response =
        transferFunds(currency, amount, BequantTransferType.EXCHANGE_TO_BANK);

    if (response.id == null) {
      throw new ExchangeException("transfer failed: " + response);
    }
    return response.id;
  }

  public List<BequantBalance> getMainBalance() throws IOException {
    return bequant.getMainBalance();
  }

  public List<BequantBalance> getTradingBalance() throws IOException {
    return bequant.getTradingBalance();
  }

  public BequantAddress getDepositAddress(Currency currency) throws IOException {
    return bequant.getBequantDepositAddress(currency.toString());
  }

  public List<BequantTransaction> getTransactions(String currency, Integer limit, Integer offset)
      throws HttpStatusIOException {
    return bequant.transactions(currency, null, null, null, null, limit, offset);
  }

  public List<BequantTransaction> getTransactions(
      String currency, BequantSort sort, Date from, Date till, Integer limit, Integer offset)
      throws HttpStatusIOException {

    String sortValue = sort != null ? sort.toString().toUpperCase() : null;
    String fromValue = from != null ? Instant.ofEpochMilli(from.getTime()).toString() : null;
    String tillValue = till != null ? Instant.ofEpochMilli(till.getTime()).toString() : null;
    return bequant.transactions(
        currency, sortValue, "timestamp", fromValue, tillValue, limit, offset);
  }

  public List<BequantTransaction> getTransactions(
      String currency,
      BequantSort sort,
      Long fromIndex,
      Long tillIndex,
      Integer limit,
      Integer offset)
      throws HttpStatusIOException {

    String sortValue = sort != null ? sort.toString().toUpperCase() : null;
    String fromValue = fromIndex != null ? fromIndex.toString() : null;
    String tillValue = fromIndex != null ? tillIndex.toString() : null;
    return bequant.transactions(currency, sortValue, "index", fromValue, tillValue, limit, offset);
  }
}
