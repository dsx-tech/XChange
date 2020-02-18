package org.knowm.xchange.utils.jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.currency.Currency;

public class CurrencyDeserializer extends KeyDeserializer {

  @Override
  public Object deserializeKey(
      String currencyString, DeserializationContext deserializationContext) {
    if (StringUtils.isBlank(currencyString)) {
      return null;
    }
    return Currency.getInstanceNoCreate(currencyString);
  }
}
