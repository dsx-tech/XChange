package org.knowm.xchange.krakenFutures.dto;

import java.beans.ConstructorProperties;
import java.util.Date;
import java.util.List;
import org.knowm.xchange.exceptions.ExchangeException;

public class KrakenFuturesResultException extends ExchangeException {

  private final String result;

  private final Date serverTime;

  private final String error;

  private final List<KrakenFuturesDetailedError> errors;

  @ConstructorProperties({"result", "serverTime", "error", "errors"})
  public KrakenFuturesResultException(
      String result, Date serverTime, String error, List<KrakenFuturesDetailedError> errors) {
    super(
        String.format(
            "Operation failed: result: %s, errors: %s, serverTime: %s",
            result, error == null ? errors : error, serverTime));
    this.result = result;
    this.serverTime = serverTime;
    this.error = error;
    this.errors = errors;
  }

  public String getResult() {
    return result;
  }

  public Date getServerTime() {
    return serverTime;
  }

  public String getError() {
    return error;
  }

  public List<KrakenFuturesDetailedError> getErrors() {
    return errors;
  }
}
