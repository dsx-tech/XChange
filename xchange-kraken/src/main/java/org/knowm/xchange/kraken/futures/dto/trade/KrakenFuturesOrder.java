package org.knowm.xchange.kraken.futures.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesOrderStatus;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesOrderType;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesSide;

import java.math.BigDecimal;
import java.util.Date;

public class KrakenFuturesOrder {

    /**
     * The date and time the order was received
     */
    private Date receivedTime;

    /**
     * The date and time the order was last updated
     */
    private Date lastUpdateTime;

    /**
     * The status of the order, either of:
     * untouched: the entire size of the order is unfilled
     * partiallyFilled: the size of the order is partially but not entirely filled
     */
    private KrakenFuturesOrderStatus status;

    /**
     * The unique identifier of the order
      */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * The unique client order identifier. This field is returned only if the order has a client order id
     */
    private String cliOrdId;

    /**
     * The order type, either lmt for a limit order or stp for a stop order
     */
    private KrakenFuturesOrderType orderType;

    /**
     * 	The symbol of the futures the order refers to.
     */
    private String symbol;

    /**
     * 	The direction of the order, either buy for a buy order or sell for a sell order
     */
    private KrakenFuturesSide side;

    /**
     * The unfilled size associated with the order
     */
    private Long unfilledSize;

    /**
     * The filled size associated with the order
     */
    private Long filledSize;

    /**
     * The limit price associated with the order
     */
    private BigDecimal limitPrice;

    /**
     * If orderType is stp: The stop price associated with the order
     * If orderType is lmt: Not returned because N/A
     */
    private BigDecimal stopPrice;

    /**
     * The date and time the order was placed
     */
    private Date timestamp;

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public KrakenFuturesOrderStatus getStatus() {
        return status;
    }

    public void setStatus(KrakenFuturesOrderStatus status) {
        this.status = status;
    }

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

    public KrakenFuturesOrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(KrakenFuturesOrderType orderType) {
        this.orderType = orderType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public KrakenFuturesSide getSide() {
        return side;
    }

    public void setSide(KrakenFuturesSide side) {
        this.side = side;
    }

    public Long getUnfilledSize() {
        return unfilledSize;
    }

    public void setUnfilledSize(Long unfilledSize) {
        this.unfilledSize = unfilledSize;
    }

    public Long getFilledSize() {
        return filledSize;
    }

    public void setFilledSize(Long filledSize) {
        this.filledSize = filledSize;
    }

    public BigDecimal getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(BigDecimal limitPrice) {
        this.limitPrice = limitPrice;
    }

    public BigDecimal getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(BigDecimal stopPrice) {
        this.stopPrice = stopPrice;
    }
}
