package org.knowm.xchange.bequant.v2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bequant.v2.dto.BequantMetaData;
import org.knowm.xchange.bequant.v2.dto.BequantSymbol;
import org.knowm.xchange.bequant.v2.service.BequantAccountService;
import org.knowm.xchange.bequant.v2.service.BequantMarketDataService;
import org.knowm.xchange.bequant.v2.service.BequantMarketDataServiceRaw;
import org.knowm.xchange.bequant.v2.service.BequantTradeService;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.FeeTier;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class BequantExchange extends BaseExchange implements org.knowm.xchange.Exchange {

  private static final Logger LOGGER = LoggerFactory.getLogger(BequantExchange.class);

  static {
    setupPatchSupport();
  }

  private final SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();
  private BequantMetaData bequantMetaData;

  private static void setupPatchSupport() {

    try {
      Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
      methodsField.setAccessible(true);
      // get the methods field modifiers
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      // bypass the "private" modifier
      modifiersField.setAccessible(true);

      // remove the "final" modifier
      modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

      /* valid HTTP methods */
      String[] methods = {"GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE", "PATCH"};
      // set the new methods - including patch
      methodsField.set(null, methods);
    } catch (SecurityException
        | IllegalArgumentException
        | IllegalAccessException
        | NoSuchFieldException e) {
      LOGGER.error("Error while setting up PATCH support");
    }
  }

  @Override
  protected void initServices() {

    marketDataService = new BequantMarketDataService(this);
    tradeService = new BequantTradeService(this);
    accountService = new BequantAccountService(this);
  }

  @Override
  protected void loadExchangeMetaData(InputStream is) {

    bequantMetaData = loadMetaData(is, BequantMetaData.class);
    exchangeMetaData =
        BequantAdapters.adaptToExchangeMetaData(
            null, bequantMetaData.getCurrencies(), bequantMetaData.getCurrencyPairs());
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.bequant.io");
    exchangeSpecification.setHost("bequant.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Bequant");
    exchangeSpecification.setExchangeDescription("Bequant is a Bitcoin exchange.");

    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }

  @Override
  public void remoteInit() throws IOException {
    BequantMarketDataServiceRaw dataService = ((BequantMarketDataServiceRaw) marketDataService);
    List<BequantSymbol> bequantSymbols = dataService.getBequantSymbols();
    Map<Currency, CurrencyMetaData> currencies =
        dataService.getBequantCurrencies().stream()
            .collect(
                Collectors.toMap(
                    bequantCurrency -> new Currency(bequantCurrency.getId()),
                    bequantCurrency -> new CurrencyMetaData(null, bequantCurrency.getPayoutFee())));

    Map<CurrencyPair, CurrencyPairMetaData> currencyPairs =
        bequantSymbols.stream()
            .collect(
                Collectors.toMap(
                    bequantSymbol ->
                        new CurrencyPair(
                            new Currency(bequantSymbol.getBaseCurrency()),
                            new Currency(bequantSymbol.getQuoteCurrency())),
                    bequantSymbol ->
                        new CurrencyPairMetaData(
                            (BigDecimal) null,
                            bequantSymbol.getQuantityIncrement(),
                            (BigDecimal) null,
                            bequantSymbol.getTickSize().scale(),
                            (FeeTier[]) null)));
    exchangeMetaData =
        BequantAdapters.adaptToExchangeMetaData(bequantSymbols, currencies, currencyPairs);
  }
}
