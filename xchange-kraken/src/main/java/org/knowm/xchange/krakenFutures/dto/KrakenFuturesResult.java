package org.knowm.xchange.krakenFutures.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class KrakenFuturesResult {

  private static final ToStringStyle TO_STRING_STYLE =
      new RecursiveToStringStyle() {
        {
          this.setUseClassName(false);
          this.setUseIdentityHashCode(false);
        }

        @Override
        protected boolean accept(Class<?> clazz) {
          if (clazz == BigDecimal.class || clazz == Date.class) return false;
          return super.accept(clazz);
        }
      };

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

  @Override
  public String toString() {
    return "KrakenFuturesResult: " + ToStringBuilder.reflectionToString(this, TO_STRING_STYLE);
  }
}
