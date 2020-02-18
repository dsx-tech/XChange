package org.knowm.xchange.krakenFutures.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KrakenFuturesCancelledOrderId {

    /**
     * The cancelled order id
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 	The client order id. Shown only if client specified one
     */
    private String cliOrdId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCliOrdId() {
        return cliOrdId;
    }

    public void setCliOrdId(String cliOrdId) {
        this.cliOrdId = cliOrdId;
    }
}
