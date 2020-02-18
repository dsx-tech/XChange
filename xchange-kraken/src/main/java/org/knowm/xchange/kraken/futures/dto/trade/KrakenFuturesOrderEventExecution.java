package org.knowm.xchange.kraken.futures.dto.trade;

import java.math.BigDecimal;

public class KrakenFuturesOrderEventExecution extends KrakenFuturesOrderEvent {

    /**
     * The executed amount
     */
    private Long amount;

    /**
     * The price of the execution
     */
    private BigDecimal price;

    /**
     * The UID associated with the execution
     */
    private String executionId;

    /**
     * The amount of quantity that was removed from the order before execution or null if the order is not a reduce only
     */
    private Long takerReducedQuantity;

    /**
     * The order before the edit was applied (if the execution is a result of an order edit) or null
     */
    private KrakenFuturesOrder orderPriorEdit;

    /**
     * The order before it executes
     */
    private KrakenFuturesOrder orderPriorExecution;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public Long getTakerReducedQuantity() {
        return takerReducedQuantity;
    }

    public void setTakerReducedQuantity(Long takerReducedQuantity) {
        this.takerReducedQuantity = takerReducedQuantity;
    }

    public KrakenFuturesOrder getOrderPriorEdit() {
        return orderPriorEdit;
    }

    public void setOrderPriorEdit(KrakenFuturesOrder orderPriorEdit) {
        this.orderPriorEdit = orderPriorEdit;
    }

    public KrakenFuturesOrder getOrderPriorExecution() {
        return orderPriorExecution;
    }

    public void setOrderPriorExecution(KrakenFuturesOrder orderPriorExecution) {
        this.orderPriorExecution = orderPriorExecution;
    }
}
