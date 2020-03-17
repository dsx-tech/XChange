package org.knowm.xchange.bequant.v2.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.knowm.xchange.bequant.v2.BaseAuthenticatedServiceTest;
import org.knowm.xchange.bequant.v2.dto.BequantBalance;
import org.knowm.xchange.bequant.v2.dto.BequantSort;
import org.knowm.xchange.bequant.v2.dto.BequantTransaction;
import org.knowm.xchange.bequant.v2.dto.BequantTransferType;
import org.knowm.xchange.currency.Currency;
import si.mazi.rescu.HttpStatusIOException;

/**
 * Test ignored in default build because it requires production authentication credentials. See
 * {@link BaseAuthenticatedServiceTest}.
 */
@Ignore
public class BequantAccountServiceRawIntegration extends BaseAuthenticatedServiceTest {

  @Rule public final ExpectedException exception = ExpectedException.none();

  private BequantAccountServiceRaw service =
      (BequantAccountServiceRaw) exchange.getAccountService();

  @Test
  public void testGetMainBalance() throws IOException {

    List<BequantBalance> balance = service.getMainBalance();

    Map<Currency, BequantBalance> balanceMap = new HashMap<>();
    for (BequantBalance bequantBalance : balance) {
      balanceMap.put(Currency.getInstance(bequantBalance.getCurrency()), bequantBalance);
    }

    Assert.assertNotNull(balance);
    BigDecimal expected = new BigDecimal("0.00000000");
    Assert.assertTrue(expected.compareTo(balanceMap.get(Currency.BTC).getAvailable()) == 0);
  }

  @Test
  public void testGetTradingBalance() throws IOException {

    List<BequantBalance> balance = service.getTradingBalance();

    Map<Currency, BequantBalance> balanceMap = new HashMap<>();
    for (BequantBalance bequantBalance : balance) {
      balanceMap.put(Currency.getInstance(bequantBalance.getCurrency()), bequantBalance);
    }

    Assert.assertNotNull(balance);
    BigDecimal expected = new BigDecimal("0.000524159680");
    Assert.assertTrue(expected.equals(balanceMap.get(Currency.BTC).getAvailable()));
  }

  @Test
  public void testGetPaymentBalance() throws IOException {

    List<BequantBalance> response = service.getMainBalance();

    Assert.assertTrue(!response.isEmpty());
  }

  @Test
  public void testGetDepositAddress() throws IOException {

    String response = service.getDepositAddress(Currency.BTC).getAddress();

    Assert.assertTrue(StringUtils.isNotEmpty(response));
  }

  @Test
  public void testGetTransactions() throws IOException {
    List<BequantTransaction> transactions;

    transactions =
        service.getTransactions(
            null, BequantSort.SORT_ASCENDING, new Date(1520949577579L), new Date(), 100, null);
    Assert.assertTrue(!transactions.isEmpty());
    Assert.assertTrue(StringUtils.isNotEmpty(transactions.get(0).getId()));

    transactions =
        service.getTransactions(
            Currency.LTC.getCurrencyCode(),
            BequantSort.SORT_DESCENDING,
            new Date(0),
            new Date(),
            100,
            null);
    Assert.assertTrue(!transactions.isEmpty());
    Assert.assertTrue(StringUtils.isNotEmpty(transactions.get(0).getId()));

    transactions =
        service.getTransactions(
            Currency.LTC.getCurrencyCode(),
            BequantSort.SORT_DESCENDING,
            new Date(0),
            new Date(),
            100,
            null);
    Assert.assertTrue(!transactions.isEmpty());
    Assert.assertTrue(StringUtils.isNotEmpty(transactions.get(0).getId()));

    transactions = service.getTransactions(null, null, null);
    Assert.assertTrue(!transactions.isEmpty());
    Assert.assertTrue(StringUtils.isNotEmpty(transactions.get(0).getId()));

    transactions =
        service.getTransactions(
            Currency.LTC.getCurrencyCode(), null, new Date(0), new Date(), null, null);
    Assert.assertTrue(!transactions.isEmpty());
    Assert.assertTrue(StringUtils.isNotEmpty(transactions.get(0).getId()));

    transactions =
        service.getTransactions(
            Currency.LTC.getCurrencyCode(), null, 0L, Long.MAX_VALUE, null, null);
    Assert.assertTrue(!transactions.isEmpty());
    Assert.assertTrue(StringUtils.isNotEmpty(transactions.get(0).getId()));
  }

  // Should return {"error":{"code":20001,"message":"Insufficient funds","description":"Check that
  // the funds are sufficient, given commissions"}} --I'm poor
  @Test
  public void testTransferFunds() throws IOException {

    exception.expect(HttpStatusIOException.class);
    exception.expectMessage("HTTP status code was not OK: 400");
    service.transferFunds(
        Currency.USD, new BigDecimal("0.01"), BequantTransferType.BANK_TO_EXCHANGE);
  }
}
