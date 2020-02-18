package org.knowm.xchange.krakenFutures.dto.account;

import java.util.Map;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenFuturesAccounts extends KrakenFuturesResult {

  private Map<String, KrakenFuturesAccount> accounts;

  public Map<String, KrakenFuturesAccount> getAccounts() {
    return accounts;
  }

  public void setAccounts(Map<String, KrakenFuturesAccount> accounts) {
    this.accounts = accounts;
  }
}
