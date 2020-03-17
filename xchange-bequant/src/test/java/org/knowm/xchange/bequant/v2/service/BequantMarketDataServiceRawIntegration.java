package org.knowm.xchange.bequant.v2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.knowm.xchange.bequant.v2.BaseServiceTest;
import org.knowm.xchange.bequant.v2.dto.BequantCurrency;
import org.knowm.xchange.bequant.v2.dto.BequantSymbol;
import org.knowm.xchange.bequant.v2.dto.BequantTicker;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class BequantMarketDataServiceRawIntegration extends BaseServiceTest {

  private MarketDataService marketDataService = exchange().getMarketDataService();
  private BequantMarketDataServiceRaw marketDataServiceRaw =
      (BequantMarketDataServiceRaw) marketDataService;

  @Test
  public void testGetBequantSymbols() throws IOException {

    List<BequantSymbol> symbols = marketDataServiceRaw.getBequantSymbols();

    assertNotNull(symbols);
    assertFalse(symbols.isEmpty());
  }

  @Test
  public void testGetBequantCurrencies() throws IOException {

    List<BequantCurrency> currencies = marketDataServiceRaw.getBequantCurrencies();
    assertNotNull(currencies);
    assertFalse(currencies.isEmpty());

    BequantCurrency currency = marketDataServiceRaw.getBequantCurrency("btc");
    assertNotNull(currency);
    assertEquals("BTC", currency.getId());
  }

  @Test
  public void testGetBequantTickers() throws IOException {

    Map<String, BequantTicker> tickers = marketDataServiceRaw.getBequantTickers();

    assertThat(tickers).isNotEmpty();
    assertThat(tickers.get("BTCUSD")).isNotNull();
  }
}
