package org.knowm.xchange.examples.bequant.account;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.examples.bequant.BequantExampleUtils;
import org.knowm.xchange.bequant.v2.dto.BequantBalance;
import org.knowm.xchange.bequant.v2.service.BequantAccountServiceRaw;
import org.knowm.xchange.service.account.AccountService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BequantAccountDemo {

  public static void main(String[] args) throws IOException {

    Exchange exchange = BequantExampleUtils.createExchange();
    AccountService accountService = exchange.getAccountService();

    generic(accountService);
    raw((BequantAccountServiceRaw) accountService);
  }

  private static void generic(AccountService accountService) throws IOException {

    AccountInfo accountInfo = accountService.getAccountInfo();
    System.out.println(accountInfo);
  }

  private static void raw(BequantAccountServiceRaw accountService) throws IOException {

    List<BequantBalance> accountInfo = accountService.getMainBalance();
    System.out.println(Arrays.toString(accountInfo.toArray()));
  }
}
