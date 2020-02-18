package org.knowm.xchange.kraken.futures.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KrakenFuturesOrderEventEdit extends KrakenFuturesOrderEvent {

    /**
     * The amount of quantity that was removed from the edited order or null if the order is not a reduce only
     */
    private Long reducedQuantity;

    /**
     * 	The order before the edit was applied. (See Place Event for the description)
     */
    private KrakenFuturesOrder old;

    /**
     * The order after the edit was applied. (See Place Event for the description)
     */
    @JsonProperty("new")
    private KrakenFuturesOrder newOrder;

    public Long getReducedQuantity() {
        return reducedQuantity;
    }

    public void setReducedQuantity(Long reducedQuantity) {
        this.reducedQuantity = reducedQuantity;
    }

    public KrakenFuturesOrder getOld() {
        return old;
    }

    public void setOld(KrakenFuturesOrder old) {
        this.old = old;
    }

    public KrakenFuturesOrder getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(KrakenFuturesOrder newOrder) {
        this.newOrder = newOrder;
    }
}
