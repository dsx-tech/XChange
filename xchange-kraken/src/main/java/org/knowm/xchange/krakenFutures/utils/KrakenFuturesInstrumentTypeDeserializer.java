package org.knowm.xchange.krakenFutures.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesInstrumentType;

public class KrakenFuturesInstrumentTypeDeserializer
    extends JsonDeserializer<KrakenFuturesInstrumentType> {

  @Override
  public KrakenFuturesInstrumentType deserialize(JsonParser jp, final DeserializationContext ctxt)
      throws IOException {

    return KrakenFuturesInstrumentType.of(jp.getValueAsString());
  }
}
