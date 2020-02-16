package org.knowm.xchange.kraken.futures.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesInstrumentType;

import java.io.IOException;

public class KrakenFuturesInstrumentTypeDeserializer extends JsonDeserializer<KrakenFuturesInstrumentType> {

    @Override
    public KrakenFuturesInstrumentType deserialize(JsonParser jp, final DeserializationContext ctxt) throws IOException {

        return KrakenFuturesInstrumentType.of(jp.getValueAsString());
    }

}
