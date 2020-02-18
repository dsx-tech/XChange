package org.knowm.xchange.krakenFutures.dto.trade;

import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;

public class KrakenFuturesOrderSendStatusResult extends KrakenFuturesResult {

  /** A structure containing information on the send order request, see below */
  private KrakenFuturesOrderSendStatus sendStatus;

  public KrakenFuturesOrderSendStatus getSendStatus() {
    return sendStatus;
  }

  public void setSendStatus(KrakenFuturesOrderSendStatus sendStatus) {
    this.sendStatus = sendStatus;
  }
}
