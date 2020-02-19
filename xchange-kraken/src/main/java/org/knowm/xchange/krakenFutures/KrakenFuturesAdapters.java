package org.knowm.xchange.krakenFutures;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesSide;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTicker;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTickers;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTrade;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTrades;

/** @author pchertalev */
public class KrakenFuturesAdapters {

  public static Ticker adaptTicker(KrakenFuturesTicker krakenTicker) {
    ImmutableTriple<KrakenFuturesProduct, CurrencyPair, LocalDate> productItems =
        KrakenFuturesProduct.parseProductId(krakenTicker.getSymbol());
    Ticker.Builder builder = new Ticker.Builder();
    builder.open(krakenTicker.getOpen24h());
    builder.ask(krakenTicker.getAsk());
    builder.bid(krakenTicker.getBid());
    builder.askSize(toBD(krakenTicker.getAskSize()));
    builder.bidSize(toBD(krakenTicker.getBidSize()));
    builder.high(krakenTicker.getHigh24h());
    builder.low(krakenTicker.getLow24h());
    builder.volume(toBD(krakenTicker.getVol24h()));
    builder.last(krakenTicker.getLast());
    builder.currencyPair(productItems.middle);
    return builder.build();
  }

  public static List<Ticker> adaptTickers(KrakenFuturesTickers krakenTickers) {
    return krakenTickers.getTickers().stream()
        .map(KrakenFuturesAdapters::adaptTicker)
        .collect(Collectors.toList());
  }

  private static BigDecimal toBD(Long val) {
    return val == null ? null : new BigDecimal(val);
  }

  public static OrderBook adaptOrderbook(
      KrakenFuturesOrderBookResult krakenOrderbook, CurrencyPair currencyPair) {
    Stream<LimitOrder> askStream =
        krakenOrderbook.getOrderBook().getAsks().stream()
            .map(
                ask ->
                    new LimitOrder(Order.OrderType.ASK, ask[1], currencyPair, null, null, ask[0]));
    Stream<LimitOrder> bidStream =
        krakenOrderbook.getOrderBook().getBids().stream()
            .map(
                ask ->
                    new LimitOrder(Order.OrderType.BID, ask[1], currencyPair, null, null, ask[0]));
    return new OrderBook(krakenOrderbook.getServerTime(), askStream, bidStream);
  }

  public static Trades adaptTrades(KrakenFuturesTrades history, CurrencyPair currencyPair) {
    return new Trades(
        history.getHistory().stream()
            .map(trade -> adaptTrade(trade, currencyPair))
            .collect(Collectors.toList()));
  }

  private static Trade adaptTrade(KrakenFuturesTrade trade, CurrencyPair currencyPair) {
    return new Trade(
        trade.getSide() == KrakenFuturesSide.sell ? Order.OrderType.ASK : Order.OrderType.BID,
        toBD(trade.getSize()),
        currencyPair,
        trade.getPrice(),
        trade.getTime(),
        trade.getTradeId() == null ? null : trade.getTradeId().toString());
  }
}
