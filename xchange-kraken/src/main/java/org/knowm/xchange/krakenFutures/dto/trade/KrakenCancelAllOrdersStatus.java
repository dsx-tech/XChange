package org.knowm.xchange.krakenFutures.dto.trade;

import java.util.Date;
import java.util.List;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesCancelOrderStatus;

public class KrakenCancelAllOrdersStatus {

  /** The date and time the order cancellation was received */
  private Date receivedTime;

  /** The symbol of the futures or all */
  private String cancelOnly;

  /** The status of the order cancellation */
  private KrakenFuturesCancelOrderStatus status;

  /** A list of structures containing all the successfully cancelled orders */
  private List<KrakenFuturesCancelledOrderId> cancelledOrders;

  /** Order Events */
  private List<KrakenFuturesOrderEventCancel> orderEvents;

  public Date getReceivedTime() {
    return receivedTime;
  }

  public void setReceivedTime(Date receivedTime) {
    this.receivedTime = receivedTime;
  }

  public String getCancelOnly() {
    return cancelOnly;
  }

  public void setCancelOnly(String cancelOnly) {
    this.cancelOnly = cancelOnly;
  }

  public KrakenFuturesCancelOrderStatus getStatus() {
    return status;
  }

  public void setStatus(KrakenFuturesCancelOrderStatus status) {
    this.status = status;
  }

  public List<KrakenFuturesCancelledOrderId> getCancelledOrders() {
    return cancelledOrders;
  }

  public void setCancelledOrders(List<KrakenFuturesCancelledOrderId> cancelledOrders) {
    this.cancelledOrders = cancelledOrders;
  }

  public List<KrakenFuturesOrderEventCancel> getOrderEvents() {
    return orderEvents;
  }

  public void setOrderEvents(List<KrakenFuturesOrderEventCancel> orderEvents) {
    this.orderEvents = orderEvents;
  }
}
