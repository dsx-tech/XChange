package org.knowm.xchange.krakenFutures.dto;

import java.util.Date;

public class KrakenFuturesResult {

  /** Always success */
  private String result;

  /** The server date and time */
  private Date serverTime;

  private String error;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Date getServerTime() {
    return serverTime;
  }

  public void setServerTime(Date serverTime) {
    this.serverTime = serverTime;
  }

  public String getError() {
    return error;
  }
}
