package org.knowm.xchange.krakenFutures.dto.account;

import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

import java.util.List;

public class KrakenFuturesTransfers extends KrakenFuturesResult {

    /**
     * A list containing structures with information on the account’s
     * transfer history, see below. The list is sorted descending by receivedTime
     */
    private List<KrakenFuturesTransfer> transfers;

    public List<KrakenFuturesTransfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<KrakenFuturesTransfer> transfers) {
        this.transfers = transfers;
    }
}
