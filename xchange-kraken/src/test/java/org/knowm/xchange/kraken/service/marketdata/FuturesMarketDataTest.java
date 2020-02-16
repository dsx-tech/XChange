package org.knowm.xchange.kraken.service.marketdata;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.kraken.futures.KrakenFuturesExchange;
import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.kraken.futures.service.service.KrakenFuturesMarketDataServiceRaw;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesProduct.FI;

public class FuturesMarketDataTest {
    @Test
    public void tickerFetchTest() throws Exception {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(KrakenFuturesExchange.class.getName());
        MarketDataService marketDataService = exchange.getMarketDataService();
        Ticker ticker = marketDataService.getTicker(CurrencyPair.BCH_USD, FI, LocalDate.of(20,3,27));
        System.out.println(ticker.toString());
        assertThat(ticker).isNotNull();

        List<Ticker> tickers = marketDataService.getTickers(null);
        assertThat(ticker).isNotNull();

        OrderBook orderbook = marketDataService.getOrderBook(CurrencyPair.BCH_USD, FI, LocalDate.of(20, 3, 27));
        assertThat(ticker).isNotNull();

        KrakenFuturesMarketDataServiceRaw marketDataServiceRaw = (KrakenFuturesMarketDataServiceRaw) marketDataService;
        KrakenFuturesInstruments instuments = marketDataServiceRaw.getKrakenInstruments();
        assertThat(instuments).isNotNull();
    }
}
