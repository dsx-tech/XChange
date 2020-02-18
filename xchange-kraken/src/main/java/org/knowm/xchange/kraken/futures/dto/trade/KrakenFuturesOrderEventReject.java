package org.knowm.xchange.kraken.futures.dto.trade;

public class KrakenFuturesOrderEventReject extends KrakenFuturesOrderEvent {

    /**
     * The uid associated with the order
     */
    private String uid;

    /**
     * postWouldExecute: the post-only order would be filled upon placement, thus is cancelled
     * iocWouldNotExecute: the immediate-or-cancel order would not execute.
     * wouldNotReducePosition: the reduce only order would not reduce position.
     * orderForEditNotFound: the order for edit was not found. The order field will be null in this case
     */
    private String reason;

    /**
     * The cancelled order. (See Place Event for the description)
     */
    private KrakenFuturesOrder order;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public KrakenFuturesOrder getOrder() {
        return order;
    }

    public void setOrder(KrakenFuturesOrder order) {
        this.order = order;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
