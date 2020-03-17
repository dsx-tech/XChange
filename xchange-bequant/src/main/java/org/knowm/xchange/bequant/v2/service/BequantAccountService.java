package org.knowm.xchange.bequant.v2.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.bequant.v2.BequantAdapters;
import org.knowm.xchange.bequant.v2.dto.BequantTransaction;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.DefaultWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.MoneroWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.RippleWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

public class BequantAccountService extends BequantAccountServiceRaw implements AccountService {

  public BequantAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {

    return new AccountInfo(
        BequantAdapters.adaptWallet("Main", getMainBalance()),
        BequantAdapters.adaptWallet("Trading", getTradingBalance()));
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address)
      throws IOException {
    return withdrawFunds(new DefaultWithdrawFundsParams(address, currency, amount));
  }

  @Override
  public String withdrawFunds(WithdrawFundsParams params) throws IOException {
    if (params instanceof MoneroWithdrawFundsParams) {
      MoneroWithdrawFundsParams moneroWithdrawFundsParams = (MoneroWithdrawFundsParams) params;
      return withdrawFundsRaw(
          moneroWithdrawFundsParams.getCurrency(),
          moneroWithdrawFundsParams.getAmount(),
          moneroWithdrawFundsParams.getAddress(),
          moneroWithdrawFundsParams.getPaymentId());
    } else if (params instanceof RippleWithdrawFundsParams) {
      RippleWithdrawFundsParams rippleWithdrawFundsParams = (RippleWithdrawFundsParams) params;
      return withdrawFundsRaw(
          rippleWithdrawFundsParams.getCurrency(),
          rippleWithdrawFundsParams.getAmount(),
          rippleWithdrawFundsParams.getAddress(),
          rippleWithdrawFundsParams.getTag());
    } else if (params instanceof DefaultWithdrawFundsParams) {
      DefaultWithdrawFundsParams defaultParams = (DefaultWithdrawFundsParams) params;
      return withdrawFundsRaw(
          defaultParams.getCurrency(), defaultParams.getAmount(), defaultParams.getAddress(), null);
    }

    throw new IllegalStateException("Don't know how to withdraw: " + params);
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args) throws IOException {
    return getDepositAddress(currency).getAddress();
  }

  @Override
  public TradeHistoryParams createFundingHistoryParams() {

    throw new NotAvailableFromExchangeException();
  }

  @Override
  public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws IOException {

    List<BequantTransaction> transactions;

    if (params instanceof BequantFundingHistoryParams) {
      BequantFundingHistoryParams bequantTradeHistoryParams = (BequantFundingHistoryParams) params;

      String currency =
          bequantTradeHistoryParams.getCurrency() != null
              ? bequantTradeHistoryParams.getCurrency().getCurrencyCode()
              : null;

      transactions =
          getTransactions(
              currency,
              bequantTradeHistoryParams.getLimit(),
              bequantTradeHistoryParams.getOffset());
    } else {
      transactions = getTransactions(null, null, null);
    }

    List<FundingRecord> records = new ArrayList<>();
    for (BequantTransaction transaction : transactions) {
      records.add(BequantAdapters.adapt(transaction));
    }
    return records;
  }
}
