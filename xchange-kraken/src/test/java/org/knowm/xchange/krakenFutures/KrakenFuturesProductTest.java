package org.knowm.xchange.krakenFutures;

import java.time.LocalDate;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.Assert;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesProduct;

public class KrakenFuturesProductTest {

  @Test
  public void parseProductValidFI() {
    ImmutableTriple<KrakenFuturesProduct, CurrencyPair, LocalDate> result =
        KrakenFuturesProduct.parseProductId("fi_ethbtc_200311");
    Assert.assertEquals(KrakenFuturesProduct.FI, result.left);
    Assert.assertEquals(CurrencyPair.ETH_BTC, result.middle);
    Assert.assertEquals(LocalDate.of(2020, 3, 11), result.right);
  }

  @Test
  public void parseProductValidPI() {
    ImmutableTriple<KrakenFuturesProduct, CurrencyPair, LocalDate> result =
        KrakenFuturesProduct.parseProductId("Pi_ethbtc");
    Assert.assertEquals(KrakenFuturesProduct.PI, result.left);
    Assert.assertEquals(CurrencyPair.ETH_BTC, result.middle);
    Assert.assertNull(result.right);
  }

  @Test(expected = ExchangeException.class)
  public void parseProductBadCurrencyPair() {
    KrakenFuturesProduct.parseProductId("fi_abuaaa_200311");
  }

  @Test(expected = ExchangeException.class)
  public void parseProductNoMandatoryMaturity() {
    KrakenFuturesProduct.parseProductId("fi_ethbtc");
  }

  @Test(expected = ExchangeException.class)
  public void parseProductInvalidProduct() {
    KrakenFuturesProduct.parseProductId("gg_ethbtc");
  }

  @Test(expected = ExchangeException.class)
  public void parseProductInvalidProductNull() {
    KrakenFuturesProduct.parseProductId(null);
  }

  @Test(expected = ExchangeException.class)
  public void parseProductInvalidProductId() {
    KrakenFuturesProduct.parseProductId("abracadabra");
  }
}
