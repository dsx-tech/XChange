package org.knowm.xchange.krakenFutures.dto.trade;

public class KrakenFuturesOrderEventPlace extends KrakenFuturesOrderEvent {

    /**
     * The amount of quantity that was removed before placement or null if the order is not a reduce only
     */
    private Long reducedQuantity;

    /**
     * The placed order
     */
    private KrakenFuturesOrder order;

    public Long getReducedQuantity() {
        return reducedQuantity;
    }

    public void setReducedQuantity(Long reducedQuantity) {
        this.reducedQuantity = reducedQuantity;
    }

    public KrakenFuturesOrder getOrder() {
        return order;
    }

    public void setOrder(KrakenFuturesOrder order) {
        this.order = order;
    }
}
