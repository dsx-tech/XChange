package org.knowm.xchange.kraken.futures.dto.trade;

import org.knowm.xchange.kraken.futures.dto.KrakenFuturesResult;

public class KrakenFuturesOrderSendStatusResult extends KrakenFuturesResult {

    /**
     * A structure containing information on the send order request, see below
     */
    private KrakenFuturesOrderSendStatus sendStatus;

    public KrakenFuturesOrderSendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(KrakenFuturesOrderSendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }
}
