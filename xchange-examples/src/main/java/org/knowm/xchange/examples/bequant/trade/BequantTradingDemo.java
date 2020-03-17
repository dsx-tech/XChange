package org.knowm.xchange.examples.bequant.trade;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.examples.bequant.BequantExampleUtils;
import org.knowm.xchange.bequant.v2.dto.BequantOwnTrade;
import org.knowm.xchange.bequant.v2.dto.BequantSort;
import org.knowm.xchange.bequant.v2.service.BequantTradeServiceRaw;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.DefaultTradeHistoryParamPaging;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BequantTradingDemo {

  public static void main(String[] args) throws IOException {

    Exchange exchange = BequantExampleUtils.createExchange();
    TradeService tradeService = exchange.getTradeService();

    generic(tradeService);
    raw((BequantTradeServiceRaw) tradeService);
  }

  private static void generic(TradeService tradeService) throws IOException {

    DefaultTradeHistoryParamPaging params = new DefaultTradeHistoryParamPaging();
    UserTrades accountInfo = tradeService.getTradeHistory(params);
    System.out.println(accountInfo);
  }

  private static void raw(BequantTradeServiceRaw tradeService) throws IOException {

    List<BequantOwnTrade> trades = tradeService.getTradeHistoryRaw("TRXBTC", 100, 0L);
    System.out.println(Arrays.toString(trades.toArray()));

    trades =
        tradeService.getTradeHistoryRaw(
            "LTCBTC", BequantSort.SORT_ASCENDING, new Date(0), null, 1000, 0L);
    System.out.println(Arrays.toString(trades.toArray()));

    trades =
        tradeService.getTradeHistoryRaw(
            "LTCBTC", BequantSort.SORT_DESCENDING, 0L, null, 1000, 0L);
    System.out.println(Arrays.toString(trades.toArray()));
  }
}
