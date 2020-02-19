package org.knowm.xchange.krakenFutures.dto.enums;

public enum KrakenFuturesOrderType {

  /** for a limit order */
  lmt,

  /** for a post-only limit order */
  post,

  /** for a stop order */
  stp,

  /** for a take profit stop order */
  take_profit,

  /** for an immediate-or-cancel order */
  ioc
}
