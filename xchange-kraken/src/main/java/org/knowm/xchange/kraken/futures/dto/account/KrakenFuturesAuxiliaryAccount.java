package org.knowm.xchange.kraken.futures.dto.account;

import java.math.BigDecimal;

public class KrakenFuturesAuxiliaryAccount {

    private BigDecimal usd;

    /**
     * The portfolio value of the account, in currency
     */
    private BigDecimal pv;

    /**
     * The PnL of current open positions of the account, in currency
     */
    private BigDecimal pnl;

    /**
     * The available funds of the account, in currency
     */
    private BigDecimal af;

    private BigDecimal funding;

    public BigDecimal getUsd() {
        return usd;
    }

    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }

    public BigDecimal getPv() {
        return pv;
    }

    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }

    public BigDecimal getPnl() {
        return pnl;
    }

    public void setPnl(BigDecimal pnl) {
        this.pnl = pnl;
    }

    public BigDecimal getAf() {
        return af;
    }

    public void setAf(BigDecimal af) {
        this.af = af;
    }

    public BigDecimal getFunding() {
        return funding;
    }

    public void setFunding(BigDecimal funding) {
        this.funding = funding;
    }
}
