package org.knowm.xchange.krakenFutures.dto.enums;

public enum KrakenFuturesTransferStatus {

    /**
     * the withdrawal request was accepted and will be processed soon
     */
    accepted,

    /**
     * the withdrawal request was not accepted because available funds are insufficient
     */
    insufficientAvailableFunds
}
