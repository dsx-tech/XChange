package org.knowm.xchange.examples.krakenFutures;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.krakenFutures.KrakenFuturesExchange;

public class KrakenFuturesExampleUtils {

  private KrakenFuturesExampleUtils() {}

  public static Exchange createTestExchange() {

    Exchange krakenFuturesExchange =
        ExchangeFactory.INSTANCE.createExchange(KrakenFuturesExchange.class.getName());
    krakenFuturesExchange.getExchangeSpecification().setApiKey("API Key");
    krakenFuturesExchange.getExchangeSpecification().setSecretKey("Secret==");
    krakenFuturesExchange.getExchangeSpecification().setUserName("user");
    krakenFuturesExchange
        .getExchangeSpecification()
        .setSslUri("https://demo-futures.kraken.com/derivatives");

    krakenFuturesExchange.applySpecification(krakenFuturesExchange.getExchangeSpecification());
    return krakenFuturesExchange;
  }
}
