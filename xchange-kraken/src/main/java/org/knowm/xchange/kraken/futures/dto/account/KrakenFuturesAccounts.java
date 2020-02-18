package org.knowm.xchange.kraken.futures.dto.account;

import org.knowm.xchange.kraken.futures.dto.KrakenFuturesResult;

import java.util.Map;

public class KrakenFuturesAccounts extends KrakenFuturesResult {

    private Map<String, KrakenFuturesAccount> accounts;

    public Map<String, KrakenFuturesAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, KrakenFuturesAccount> accounts) {
        this.accounts = accounts;
    }
}
