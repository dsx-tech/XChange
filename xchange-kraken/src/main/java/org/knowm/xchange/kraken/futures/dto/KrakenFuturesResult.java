package org.knowm.xchange.kraken.futures.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.utils.jackson.ISO8601DateDeserializer;

import java.util.Date;

public class KrakenFuturesResult {

    /**
     * Always success
     */
    private String result;

    /**
     * The server date and time
     */
    @JsonDeserialize(using = ISO8601DateDeserializer.class)
    private Date serverTime;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }
}
