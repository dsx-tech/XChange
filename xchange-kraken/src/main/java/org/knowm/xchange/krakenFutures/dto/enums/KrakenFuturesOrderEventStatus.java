package org.knowm.xchange.krakenFutures.dto.enums;

/** @author pchertalev */
public enum KrakenFuturesOrderEventStatus {
  // success statuses
  placed,
  cancelled,

  // failed statuses
  invalidOrderType("the order was not placed because orderType is invalid"),
  invalidSide("the order was not placed because side is invalid"),
  invalidSize("the order was not placed because size is invalid"),
  invalidPrice("the order was not placed because limitPrice and/or stopPrice are invalid"),
  insufficientAvailableFunds(" the order was not placed because available funds are insufficient"),
  selfFill(
      "the order was not placed because it would be filled against an existing order belonging to the same account"),
  tooManySmallOrders(
      "the order was not placed because the number of small open orders would exceed the permissible limit"),
  maxPositionViolation("Order would cause you to exceed your maximum position in this contract"),
  marketSuspended("the order was not placed because the market is suspended"),
  marketInactive("the order was not placed because the market is inactive"),
  clientOrderIdAlreadyExist("the specified client id already exist"),
  clientOrderIdTooLong("the client id is longer than the permissible limit"),
  outsidePriceCollar(
      "the limit order crosses the spread but is an order of magnitude away from the mark price - fat finger control"),
  postWouldExecute("the post-only order would be filled upon placement, thus is cancelled"),
  iocWouldNotExecute("the immediate-or-cancel order would not execute"),
  wouldNotReducePosition("the reduce only order would not reduce position"),
  wouldCauseLiquidation("wouldCauseLiquidation");

  private final String errorMessage;

  KrakenFuturesOrderEventStatus() {
    this(null);
  }

  KrakenFuturesOrderEventStatus(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
