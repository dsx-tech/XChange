package org.knowm.xchange.krakenFutures.dto.marketdata;

import java.math.BigDecimal;

public class KrakenFuturesMarginLevel {

    /**
     * For Futures: The lower limit of the number of contracts that this margin level applies
     * For indices: Not returned because N/A
     */
    private Long contracts;

    /**
     * For Futures: The initial margin requirement for this level
     * For indices: Not returned because N/A
     */
    private BigDecimal initialMargin;

    /**
     * For Futures: The maintenance margin requirement for this level
     * For indices: Not returned because N/A
     */
    private BigDecimal maintenanceMargin;

    public Long getContracts() {
        return contracts;
    }

    public void setContracts(Long contracts) {
        this.contracts = contracts;
    }

    public BigDecimal getInitialMargin() {
        return initialMargin;
    }

    public void setInitialMargin(BigDecimal initialMargin) {
        this.initialMargin = initialMargin;
    }

    public BigDecimal getMaintenanceMargin() {
        return maintenanceMargin;
    }

    public void setMaintenanceMargin(BigDecimal maintenanceMargin) {
        this.maintenanceMargin = maintenanceMargin;
    }
}
