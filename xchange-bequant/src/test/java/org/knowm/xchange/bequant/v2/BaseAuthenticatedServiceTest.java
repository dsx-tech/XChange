package org.knowm.xchange.bequant.v2;

import org.junit.BeforeClass;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;

public class BaseAuthenticatedServiceTest extends BaseServiceTest {

  private static final String API_KEY = "bequant_api_key";
  private static final String SECRET_KEY = "bequant_secret_key";

  public static String getApiKey() {
    return System.getProperty(API_KEY);
  }

  public static String getSecret() {
    return System.getProperty(SECRET_KEY);
  }

  @BeforeClass
  public static void setUpBaseClass() {

    exchangeSpecification = new ExchangeSpecification(BequantExchange.class);
    exchangeSpecification.setApiKey(getApiKey());
    exchangeSpecification.setSecretKey(getSecret());

    exchange = ExchangeFactory.INSTANCE.createExchange(exchangeSpecification);
  }
}
