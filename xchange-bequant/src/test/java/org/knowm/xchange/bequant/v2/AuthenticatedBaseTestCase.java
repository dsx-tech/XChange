package org.knowm.xchange.bequant.v2;

import java.io.IOException;
import org.junit.BeforeClass;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;

/**
 * Base class for making test calls which require authentication to Bequant services. Since Bequant
 * has no test system, these credentials should be private to the person running them. Thus the
 * tests will be ignored for default suite runs. example -Dbequant_api_key=XXXXXXXXXX
 * -Dbequant_secret_key=YYYYYYYYY
 */
public class AuthenticatedBaseTestCase {

  private static final String API_KEY_LOOKUP = "bequant_api_key";
  private static final String SECRET_KEY_LOOKUP = "bequant_secret_key";
  protected static Exchange EXCHANGE = null;

  @BeforeClass
  public static void setUpClass() throws IOException {

    String apiKey = System.getProperty(API_KEY_LOOKUP);
    String secretValue = System.getProperty(SECRET_KEY_LOOKUP);

    EXCHANGE =
        ExchangeFactory.INSTANCE.createExchange(
            BequantExchange.class.getName(), apiKey, secretValue);
    EXCHANGE.remoteInit();
  }
}
