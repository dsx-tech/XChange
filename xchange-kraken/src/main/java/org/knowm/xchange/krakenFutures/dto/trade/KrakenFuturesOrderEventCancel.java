package org.knowm.xchange.krakenFutures.dto.trade;

public class KrakenFuturesOrderEventCancel extends KrakenFuturesOrderEvent {

    /**
     * The uid associated with the order
     */
    private String uid;

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
}
