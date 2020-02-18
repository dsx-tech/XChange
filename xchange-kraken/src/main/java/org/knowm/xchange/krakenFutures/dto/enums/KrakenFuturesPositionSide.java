package org.knowm.xchange.krakenFutures.dto.enums;

import java.util.Arrays;

public enum KrakenFuturesPositionSide {
    LONG("long"),
    SHORT("short");

    private String name;

    KrakenFuturesPositionSide(String name) {
        this.name = name;
    }

    public static KrakenFuturesPositionSide of(String valueAsString) {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(valueAsString))
                .findFirst().orElse(null);
    }
}
