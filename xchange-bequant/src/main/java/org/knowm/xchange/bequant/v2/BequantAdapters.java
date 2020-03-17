package org.knowm.xchange.bequant.v2;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bequant.v2.dto.*;
import org.knowm.xchange.bequant.v2.dto.BequantBalance;
import org.knowm.xchange.bequant.v2.dto.BequantOrderBook;
import org.knowm.xchange.bequant.v2.dto.BequantOrderLimit;
import org.knowm.xchange.bequant.v2.dto.BequantOwnTrade;
import org.knowm.xchange.bequant.v2.dto.BequantSide;
import org.knowm.xchange.bequant.v2.dto.BequantSymbol;
import org.knowm.xchange.bequant.v2.dto.BequantTicker;
import org.knowm.xchange.bequant.v2.dto.BequantTrade;
import org.knowm.xchange.bequant.v2.dto.BequantTransaction;
import org.knowm.xchange.bequant.v2.dto.BequantUserTrade;
import org.knowm.xchange.bequant.v2.service.BequantMarketDataServiceRaw;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.FundingRecord.Type;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.FeeTier;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;

public class BequantAdapters {

  private static Map<String, CurrencyPair> symbols = new HashMap<>();

  public static CurrencyPair adaptSymbol(String symbol) {
    if (symbols.isEmpty()) {
      try {
        BequantExchange exchange = ExchangeFactory.INSTANCE.createExchange(BequantExchange.class);
        symbols =
            new BequantMarketDataServiceRaw(exchange)
                .getBequantSymbols().stream()
                    .collect(
                        Collectors.toMap(
                            bequantSymbol ->
                                bequantSymbol.getBaseCurrency() + bequantSymbol.getQuoteCurrency(),
                            bequantSymbol ->
                                new CurrencyPair(
                                    bequantSymbol.getBaseCurrency(),
                                    bequantSymbol.getQuoteCurrency())));
      } catch (Exception ignored) {
      }
    }

    return symbols.containsKey(symbol) ? symbols.get(symbol) : guessSymbol(symbol);
  }

  static CurrencyPair guessSymbol(String symbol) {
    int splitIndex = symbol.endsWith("USDT") ? symbol.lastIndexOf("USDT") : symbol.length() - 3;
    return new CurrencyPair(symbol.substring(0, splitIndex), symbol.substring(splitIndex));
  }

  public static CurrencyPair adaptSymbol(BequantSymbol bequantSymbol) {

    return new CurrencyPair(bequantSymbol.getBaseCurrency(), bequantSymbol.getQuoteCurrency());
  }

  public static Ticker adaptTicker(BequantTicker bequantTicker, CurrencyPair currencyPair) {

    BigDecimal bid = bequantTicker.getBid();
    BigDecimal ask = bequantTicker.getAsk();
    BigDecimal high = bequantTicker.getHigh();
    BigDecimal low = bequantTicker.getLow();
    BigDecimal last = bequantTicker.getLast();
    BigDecimal volume = bequantTicker.getVolume();
    Date timestamp = bequantTicker.getTimestamp();

    return new Ticker.Builder()
        .currencyPair(currencyPair)
        .last(last)
        .bid(bid)
        .ask(ask)
        .high(high)
        .low(low)
        .volume(volume)
        .timestamp(timestamp)
        .build();
  }

  public static List<Ticker> adaptTickers(Map<String, BequantTicker> bequantTickers) {

    List<Ticker> tickers = new ArrayList<>(bequantTickers.size());

    for (Map.Entry<String, BequantTicker> ticker : bequantTickers.entrySet()) {

      tickers.add(adaptTicker(ticker.getValue(), adaptSymbol(ticker.getKey())));
    }

    return tickers;
  }

  public static OrderBook adaptOrderBook(
      BequantOrderBook bequantOrderBook, CurrencyPair currencyPair) {

    List<LimitOrder> asks =
        adaptMarketOrderToLimitOrder(bequantOrderBook.getAsks(), OrderType.ASK, currencyPair);
    List<LimitOrder> bids =
        adaptMarketOrderToLimitOrder(bequantOrderBook.getBids(), OrderType.BID, currencyPair);

    return new OrderBook(null, asks, bids);
  }

  private static List<LimitOrder> adaptMarketOrderToLimitOrder(
      BequantOrderLimit[] bequantOrders, OrderType orderType, CurrencyPair currencyPair) {

    List<LimitOrder> orders = new ArrayList<>(bequantOrders.length);

    for (BequantOrderLimit bequantOrderLimit : bequantOrders) {
      LimitOrder limitOrder =
          new LimitOrder(
              orderType,
              bequantOrderLimit.getSize(),
              currencyPair,
              null,
              null,
              bequantOrderLimit.getPrice());
      orders.add(limitOrder);
    }

    return orders;
  }

  public static OrderType adaptSide(BequantSide side) {

    switch (side) {
      case BUY:
        return OrderType.BID;
      case SELL:
        return OrderType.ASK;
      default:
        return null;
    }
  }

  public static Trades adaptTrades(
      List<? extends BequantTrade> allBequantTrades, CurrencyPair currencyPair) {

    List<Trade> trades = new ArrayList<>(allBequantTrades.size());
    long lastTradeId = 0;
    for (int i = 0; i < allBequantTrades.size(); i++) {
      BequantTrade bequantTrade = allBequantTrades.get(i);

      Date timestamp = bequantTrade.getTimestamp();
      BigDecimal price = bequantTrade.getPrice();
      BigDecimal amount = bequantTrade.getQuantity();
      String tid = bequantTrade.getId();
      long longTradeId = tid == null ? 0 : Long.parseLong(tid);
      if (longTradeId > lastTradeId) {
        lastTradeId = longTradeId;
      }
      OrderType orderType = adaptSide(bequantTrade.getSide());
      Trade trade = new Trade(orderType, amount, currencyPair, price, timestamp, tid);
      trades.add(trade);
    }

    return new Trades(trades, lastTradeId, Trades.TradeSortType.SortByTimestamp);
  }

  public static LimitOrder adaptOrder(BequantOrder bequantOrder) {
    OrderType type = adaptOrderType(bequantOrder.side);

    return new BequantLimitOrder(
        type,
        bequantOrder.quantity,
        adaptSymbol(bequantOrder.symbol),
        bequantOrder.id,
        bequantOrder.getCreatedAt(),
        bequantOrder.price,
        null, // exchange does not provide average price
        bequantOrder.cumQuantity,
        null,
        convertOrderStatus(bequantOrder.status),
        bequantOrder.clientOrderId);
  }

  public static List<LimitOrder> adaptOrders(List<BequantOrder> openOrdersRaw) {
    List<LimitOrder> openOrders = new ArrayList<>(openOrdersRaw.size());

    for (BequantOrder bequantOrder : openOrdersRaw) {
      openOrders.add(adaptOrder(bequantOrder));
    }

    return openOrders;
  }

  public static OpenOrders adaptOpenOrders(List<BequantOrder> openOrdersRaw) {
    return new OpenOrders(adaptOrders(openOrdersRaw));
  }

  public static OrderType adaptOrderType(String side) {

    return side.equals("buy") ? OrderType.BID : OrderType.ASK;
  }

  public static UserTrades adaptTradeHistory(List<BequantOwnTrade> tradeHistoryRaw) {

    List<UserTrade> trades = new ArrayList<>(tradeHistoryRaw.size());
    for (BequantOwnTrade bequantOwnTrade : tradeHistoryRaw) {

      OrderType type = adaptOrderType(bequantOwnTrade.getSide().getValue());
      CurrencyPair pair = adaptSymbol(bequantOwnTrade.symbol);
      BigDecimal originalAmount = bequantOwnTrade.getQuantity();
      Date timestamp = bequantOwnTrade.getTimestamp();
      String id = Long.toString(bequantOwnTrade.getId());
      String orderId = String.valueOf(bequantOwnTrade.getOrderId());
      String clientOrderId = bequantOwnTrade.getClientOrderId();

      UserTrade trade =
          new BequantUserTrade(
              type,
              originalAmount,
              pair,
              bequantOwnTrade.getPrice(),
              timestamp,
              id,
              orderId,
              bequantOwnTrade.getFee(),
              pair.counter,
              clientOrderId);

      trades.add(trade);
    }

    return new UserTrades(trades, Trades.TradeSortType.SortByTimestamp);
  }

  public static Wallet adaptWallet(String name, List<BequantBalance> bequantBalances) {

    List<Balance> balances = new ArrayList<>(bequantBalances.size());

    for (BequantBalance balanceRaw : bequantBalances) {
      Currency currency = Currency.getInstance(balanceRaw.getCurrency());
      Balance balance =
          new Balance(currency, null, balanceRaw.getAvailable(), balanceRaw.getReserved());
      balances.add(balance);
    }
    return Wallet.Builder.from(balances).id(name).name(name).build();
  }

  public static String adaptCurrencyPair(CurrencyPair pair) {

    return pair == null ? null : pair.base.getCurrencyCode() + pair.counter.getCurrencyCode();
  }

  public static BequantSide getSide(OrderType type) {

    return type == OrderType.BID ? BequantSide.BUY : BequantSide.SELL;
  }

  public static ExchangeMetaData adaptToExchangeMetaData(
      List<BequantSymbol> symbols,
      Map<Currency, CurrencyMetaData> currencies,
      Map<CurrencyPair, CurrencyPairMetaData> currencyPairs) {
    if (symbols != null) {
      for (BequantSymbol symbol : symbols) {
        CurrencyPair pair = adaptSymbol(symbol);
        BigDecimal tickSize = symbol.getTickSize();
        int priceScale = tickSize.scale(); // not 100% sure this is correct

        BigDecimal tradingFee = symbol.getTakeLiquidityRate();
        BigDecimal minimumAmount = symbol.getQuantityIncrement();
        BigDecimal maximumAmount = null;

        FeeTier[] feeTiers = null;
        if (currencyPairs.containsKey(pair)) {
          CurrencyPairMetaData existing = currencyPairs.get(pair);
          minimumAmount = existing.getMinimumAmount();
          maximumAmount = existing.getMaximumAmount();
          feeTiers = existing.getFeeTiers();
        }

        CurrencyPairMetaData meta =
            new CurrencyPairMetaData(
                tradingFee, minimumAmount, maximumAmount, priceScale, feeTiers);

        currencyPairs.put(pair, meta);
      }
    }

    return new ExchangeMetaData(currencyPairs, currencies, null, null, null);
  }

  public static FundingRecord adapt(BequantTransaction transaction) {

    String description = transaction.getType() + " " + transaction.getStatus();
    if (transaction.getIndex() != null) {
      description += ", index: " + transaction.getIndex();
    }
    if (transaction.getPaymentId() != null) {
      description += ", paymentId: " + transaction.getPaymentId();
    }

    return new FundingRecord.Builder()
        .setAddress(transaction.getAddress())
        .setCurrency(Currency.getInstance(transaction.getCurrency()))
        .setAmount(transaction.getAmount())
        .setType(convertType(transaction.getType()))
        .setFee(transaction.getFee())
        .setDescription(description)
        .setStatus(convertStatus(transaction.getStatus()))
        .setInternalId(transaction.getId())
        .setBlockchainTransactionHash(transaction.getHash())
        .setDate(transaction.getCreatedAt())
        .build();
  }

  /**
   * @param type
   * @return
   * @see https://api.bequant.io/ Transaction Model possible types: payout, payin, deposit,
   *     withdraw, bankToExchange, exchangeToBank
   */
  private static Type convertType(String type) {
    switch (type) {
      case "payout":
      case "withdraw":
      case "exchangeToBank":
        return Type.WITHDRAWAL;
      case "payin":
      case "deposit":
      case "bankToExchange":
        return Type.DEPOSIT;
      default:
        throw new RuntimeException("Unknown Bequant transaction type: " + type);
    }
  }

  /**
   * @return
   * @see https://api.bequant.io/ Transaction Model possible statusses: created, pending, failed,
   *     success
   */
  private static FundingRecord.Status convertStatus(String status) {
    switch (status) {
      case "created":
      case "pending":
        return FundingRecord.Status.PROCESSING;
      case "failed":
        return FundingRecord.Status.FAILED;
      case "success":
        return FundingRecord.Status.COMPLETE;
      default:
        throw new RuntimeException("Unknown Bequant transaction status: " + status);
    }
  }

  /**
   * Decodes Bequant Order status.
   *
   * @return
   * @see https://api.bequant.com/#order-model Order Model possible statuses: new, suspended,
   *     partiallyFilled, filled, canceled, expired
   */
  private static Order.OrderStatus convertOrderStatus(String status) {
    switch (status) {
      case "new":
        return Order.OrderStatus.NEW;
      case "suspended":
        return Order.OrderStatus.STOPPED;
      case "partiallyFilled":
        return Order.OrderStatus.PARTIALLY_FILLED;
      case "filled":
        return Order.OrderStatus.FILLED;
      case "canceled":
        return Order.OrderStatus.CANCELED;
      case "expired":
        return Order.OrderStatus.EXPIRED;
      default:
        throw new RuntimeException("Unknown Bequant transaction status: " + status);
    }
  }
}
