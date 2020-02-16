package org.knowm.xchange.kraken.futures.dto.marketdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesInstrumentType;
import org.knowm.xchange.kraken.futures.utils.KrakenFuturesInstrumentTypeDeserializer;
import org.knowm.xchange.utils.jackson.ISO8601DateDeserializer;

import java.math.BigDecimal;
import java.util.Date;

public class KrakenFuturesInstrument {

    /**
     * The symbol of the Futures or index, see Section 3.4.4
     */
    private String symbol;

    /**
     * 	The type of the instrument, either futures_inverse, futures_vanilla, turbo_inverse, spot index or volatility index
     */
    @JsonDeserialize(using = KrakenFuturesInstrumentTypeDeserializer.class)
    private KrakenFuturesInstrumentType type;

    /**
     * 	True if the instrument can be traded, false otherwise
     */
    private boolean tradeable;

    /**
     * For Futures: The underlying of the Futures
     * For indices: Not returned because N/A
     */
    private String underlying;

    /**
     * For Futures: The date and time at which the Futures stops trading
     * For indices: Not returned because N/A
     */
    @JsonDeserialize(using = ISO8601DateDeserializer.class)
    private Date lastTradingTime;

    /**
     * For Futures: The tick size increment of the Futures
     * For indices: Not returned because N/A
     */
    private BigDecimal tickSize;

    /**
     * For Futures: For Futures: The contract size of the Futures
     * For indices: Not returned because N/
     */
    private Long contractSize;

    /**
     * For Futures: A list containing the margin schedules
     * For indices: Not returned because N/A
     */
    private KrakenFuturesMarginLevel [] marginLevels;
    private KrakenFuturesMarginLevel [] retailMarginLevels;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public KrakenFuturesInstrumentType getType() {
        return type;
    }

    public void setType(KrakenFuturesInstrumentType type) {
        this.type = type;
    }

    public boolean isTradeable() {
        return tradeable;
    }

    public void setTradeable(boolean tradeable) {
        this.tradeable = tradeable;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public Date getLastTradingTime() {
        return lastTradingTime;
    }

    public void setLastTradingTime(Date lastTradingTime) {
        this.lastTradingTime = lastTradingTime;
    }

    public BigDecimal getTickSize() {
        return tickSize;
    }

    public void setTickSize(BigDecimal tickSize) {
        this.tickSize = tickSize;
    }

    public Long getContractSize() {
        return contractSize;
    }

    public void setContractSize(Long contractSize) {
        this.contractSize = contractSize;
    }

    public KrakenFuturesMarginLevel[] getMarginLevels() {
        return marginLevels;
    }

    public void setMarginLevels(KrakenFuturesMarginLevel[] marginLevels) {
        this.marginLevels = marginLevels;
    }

    public KrakenFuturesMarginLevel[] getRetailMarginLevels() {
        return retailMarginLevels;
    }

    public void setRetailMarginLevels(KrakenFuturesMarginLevel[] retailMarginLevels) {
        this.retailMarginLevels = retailMarginLevels;
    }
}
