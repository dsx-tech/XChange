package org.knowm.xchange.examples.bequant;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bequant.v2.BequantExchange;

public class BequantExampleUtils {

  public static Exchange createExchange() {

    ExchangeSpecification exSpec = new ExchangeSpecification(BequantExchange.class);
    exSpec.setApiKey("api key");
    exSpec.setSecretKey("api secret");

    return ExchangeFactory.INSTANCE.createExchange(exSpec);
  }
}
