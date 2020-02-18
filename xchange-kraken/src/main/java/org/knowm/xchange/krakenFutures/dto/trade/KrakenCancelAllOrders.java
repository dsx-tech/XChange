package org.knowm.xchange.krakenFutures.dto.trade;

import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenCancelAllOrders extends KrakenFuturesResult {

  /** A structure containing information on the cancellation request, see below */
  private KrakenCancelAllOrdersStatus cancelStatus;

  public KrakenCancelAllOrdersStatus getCancelStatus() {
    return cancelStatus;
  }

  public void setCancelStatus(KrakenCancelAllOrdersStatus cancelStatus) {
    this.cancelStatus = cancelStatus;
  }
}
