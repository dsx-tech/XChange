package org.knowm.xchange.krakenFutures.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesPositionSide;

import java.io.IOException;

public class KrakenFuturesPositionSideDeserializer extends JsonDeserializer<KrakenFuturesPositionSide> {

    @Override
    public KrakenFuturesPositionSide deserialize(JsonParser jp, final DeserializationContext ctxt) throws IOException {
        return KrakenFuturesPositionSide.of(jp.getValueAsString());
    }

}
