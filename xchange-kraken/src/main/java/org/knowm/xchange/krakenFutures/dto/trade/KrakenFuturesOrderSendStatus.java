package org.knowm.xchange.krakenFutures.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesOrderEventStatus;

public class KrakenFuturesOrderSendStatus {

  /** The unique identifier of the order */
  @JsonProperty("order_id")
  private String orderId;

  /**
   * The unique client order identifier. This field is returned only if the order has a client order
   * id.
   */
  private String cliOrdId;

  /** The date and time the order was received */
  private Date receivedTime;

  /** The status of the order */
  private KrakenFuturesOrderEventStatus status;

  /** Order Events */
  private List<KrakenFuturesOrderEvent> orderEvents;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getCliOrdId() {
    return cliOrdId;
  }

  public void setCliOrdId(String cliOrdId) {
    this.cliOrdId = cliOrdId;
  }

  public Date getReceivedTime() {
    return receivedTime;
  }

  public void setReceivedTime(Date receivedTime) {
    this.receivedTime = receivedTime;
  }

  public List<KrakenFuturesOrderEvent> getOrderEvents() {
    return orderEvents;
  }

  public void setOrderEvents(List<KrakenFuturesOrderEvent> orderEvents) {
    this.orderEvents = orderEvents;
  }

  public KrakenFuturesOrderEventStatus getStatus() {
    return status;
  }

  public void setStatus(KrakenFuturesOrderEventStatus status) {
    this.status = status;
  }
}
