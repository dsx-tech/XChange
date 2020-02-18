package org.knowm.xchange.krakenFutures.dto.enums;

import java.math.BigDecimal;
import java.util.function.Function;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrder;

public enum KrakenFuturesOrderType {

  /** for a limit order */
  lmt(KrakenFuturesOrder::getLimitPrice),

  /** for a post-only limit order */
  post(KrakenFuturesOrder::getLimitPrice),

  /** for a stop order */
  stp(KrakenFuturesOrder::getStopPrice),

  /** for a take profit order */
  take_profit(KrakenFuturesOrder::getStopPrice),

  /** for an immediate-or-cancel order */
  ioc(order -> null);

  public final Function<KrakenFuturesOrder, BigDecimal> priceRetriever;

  KrakenFuturesOrderType(Function<KrakenFuturesOrder, BigDecimal> priceRetriever) {
    this.priceRetriever = priceRetriever;
  }
}
