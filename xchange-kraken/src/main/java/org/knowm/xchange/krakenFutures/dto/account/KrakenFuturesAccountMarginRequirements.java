package org.knowm.xchange.krakenFutures.dto.account;

import java.math.BigDecimal;

public class KrakenFuturesAccountMarginRequirements {

  /** The initial margin requirement of the account */
  private BigDecimal im;

  /** The maintenance margin requirement of the account */
  private BigDecimal mm;

  /** The liquidation threshold of the account */
  private BigDecimal lt;

  /** The termination threshold of the account */
  private BigDecimal tt;

  public BigDecimal getIm() {
    return im;
  }

  public void setIm(BigDecimal im) {
    this.im = im;
  }

  public BigDecimal getMm() {
    return mm;
  }

  public void setMm(BigDecimal mm) {
    this.mm = mm;
  }

  public BigDecimal getLt() {
    return lt;
  }

  public void setLt(BigDecimal lt) {
    this.lt = lt;
  }

  public BigDecimal getTt() {
    return tt;
  }

  public void setTt(BigDecimal tt) {
    this.tt = tt;
  }
}
