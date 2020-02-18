package org.knowm.xchange.krakenFutures;

import org.junit.Ignore;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesAccounts;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesTransfers;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesOrderType;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesSide;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenCancelAllOrders;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesFills;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOpenPositions;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrderSendStatusResult;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrders;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesAccountServiceRaw;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesMarketDataServiceRaw;
import org.knowm.xchange.krakenFutures.service.service.KrakenFuturesTradeServiceRaw;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct.FI;
import static org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct.PI;

@Ignore
public class FuturesMarketDataTest {
    /**
     * account:
     * go858ujj@futures-demo.com
     * ac9gypodsbqmse3413x1hs
     * <p>
     * Demo Api Key
     * SEpLe3MiVCPWQqVIXObDzhCKQIh3IQcfiM/OeUr+9JS5hykZQw1jl6UF
     * cosfOtLxeSHGrAFIo1zD3lCI1Qj4lfia0ezIlHzbT+nd2vNKP4JE2l6t00P/SN+nWnAwEtAbvDS+siPgHTZhXwfy
     */
    @Test
    public void tickerFetchTest() throws Exception {

        ExchangeSpecification exchangeSpecification = new ExchangeSpecification(KrakenFuturesExchange.class);
        exchangeSpecification.setApiKey("SEpLe3MiVCPWQqVIXObDzhCKQIh3IQcfiM/OeUr+9JS5hykZQw1jl6UF");
        exchangeSpecification.setSecretKey("cosfOtLxeSHGrAFIo1zD3lCI1Qj4lfia0ezIlHzbT+nd2vNKP4JE2l6t00P/SN+nWnAwEtAbvDS+siPgHTZhXwfy");
        exchangeSpecification.setSslUri("https://demo-futures.kraken.com/derivatives");

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(exchangeSpecification);
        KrakenFuturesTradeServiceRaw tradeService = (KrakenFuturesTradeServiceRaw) exchange.getTradeService();
        KrakenFuturesAccountServiceRaw accountService = (KrakenFuturesAccountServiceRaw) exchange.getAccountService();
        MarketDataService marketDataService = exchange.getMarketDataService();
        KrakenFuturesMarketDataServiceRaw marketDataServiceRaw = (KrakenFuturesMarketDataServiceRaw) marketDataService;

        Ticker ticker = marketDataService.getTicker(CurrencyPair.BCH_USD, FI, LocalDate.of(20, 3, 27));
        System.out.println(ticker.toString());
        assertThat(ticker).isNotNull();

        List<Ticker> tickers = marketDataService.getTickers(null);
        assertThat(tickers).isNotNull();

        OrderBook orderbook = marketDataService.getOrderBook(CurrencyPair.BCH_USD, FI, LocalDate.of(20, 3, 27));
        assertThat(orderbook).isNotNull();

        KrakenFuturesInstruments instuments = marketDataServiceRaw.getKrakenInstruments();
        assertThat(instuments).isNotNull();

        KrakenFuturesOrders openOrders = tradeService.openOrders();
        assertThat(openOrders).isNotNull();

        KrakenFuturesOrderSendStatusResult sendStatus = tradeService.sendOrder(
                KrakenFuturesOrderType.lmt, CurrencyPair.LTC_USD, PI, null,
                KrakenFuturesSide.sell, 5L, new BigDecimal("71.01"));

        assertThat(sendStatus).isNotNull();

        KrakenFuturesAccounts accounts = accountService.accounts();
        assertThat(accounts).isNotNull();

        KrakenFuturesFills fills = tradeService.fills(new Date(System.currentTimeMillis()));
        assertThat(fills).isNotNull();

        KrakenFuturesOpenPositions openPositions = tradeService.openPositions();
        assertThat(openPositions).isNotNull();

        KrakenCancelAllOrders cancelOrdersResult = tradeService.cancelAllOrders();
        assertThat(cancelOrdersResult).isNotNull();

        cancelOrdersResult = tradeService.cancelAllOrders(PI, CurrencyPair.XBT_USD, null);
        assertThat(cancelOrdersResult).isNotNull();

        KrakenFuturesResult withdrawalResult = accountService.withdrawalToSpotWallet(Currency.XBT, new BigDecimal("0.0001"));
        assertThat(withdrawalResult).isNotNull();

        KrakenFuturesResult transfer = accountService.transfer("cash", "fi_xrpusd", Currency.XRP, new BigDecimal("0.01"));
        assertThat(transfer).isNotNull();

        KrakenFuturesTransfers transfers = accountService.transfers();
        assertThat(transfers).isNotNull();
    }
}
