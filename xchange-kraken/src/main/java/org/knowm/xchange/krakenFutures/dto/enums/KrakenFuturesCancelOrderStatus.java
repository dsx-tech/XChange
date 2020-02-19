package org.knowm.xchange.krakenFutures.dto.enums;

/** @author pchertalev */
public enum KrakenFuturesCancelOrderStatus {

  /**
   * the order has been cancelled. This may only be part of the order as part may have been filled.
   */
  cancelled,

  /** no open orders for cancellation */
  noOrdersToCancel;
}
