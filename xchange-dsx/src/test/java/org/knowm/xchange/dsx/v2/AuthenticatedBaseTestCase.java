package org.knowm.xchange.dsx.v2;

import java.io.IOException;
import org.junit.BeforeClass;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;

/**
 * Base class for making test calls which require authentication to DSX services. Since DSX has no
 * test system, these credentials should be private to the person running them. Thus the tests will
 * be ignored for default suite runs. example -Ddsx_api_key=XXXXXXXXXX -Ddsx_secret_key=YYYYYYYYY
 */
public class AuthenticatedBaseTestCase {

  private static final String API_KEY_LOOKUP = "dsx_api_key";
  private static final String SECRET_KEY_LOOKUP = "dsx_secret_key";
  protected static Exchange EXCHANGE = null;

  @BeforeClass
  public static void setUpClass() throws IOException {

    String apiKey = System.getProperty(API_KEY_LOOKUP);
    String secretValue = System.getProperty(SECRET_KEY_LOOKUP);

    EXCHANGE =
        ExchangeFactory.INSTANCE.createExchange(DsxExchange.class.getName(), apiKey, secretValue);
    EXCHANGE.remoteInit();
  }
}
