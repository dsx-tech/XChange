package org.knowm.xchange.kraken.futures.dto.enums;

import java.util.Arrays;

public enum KrakenFuturesInstrumentType {
    futures_inverse,
    futures_vanilla,
    turbo_inverse,
    spot_index("spot index"),
    volatility_index("volatility index");

    private final String name;

    KrakenFuturesInstrumentType() {
        this.name = this.name();
    }

    KrakenFuturesInstrumentType(String name) {
        this.name = name;
    }

    public static KrakenFuturesInstrumentType of(String typeString) {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(typeString))
                .findFirst().orElse(null);
    }
}
