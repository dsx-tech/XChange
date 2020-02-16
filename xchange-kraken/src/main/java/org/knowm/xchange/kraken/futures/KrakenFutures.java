package org.knowm.xchange.kraken.futures;

import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesTickers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author pchertalev
 **/
@Produces(MediaType.APPLICATION_JSON)
public interface KrakenFutures {

    @GET
    @Path("tickers")
    KrakenFuturesTickers getTickers();

    @GET
    @Path("instruments")
    KrakenFuturesInstruments getInstruments();

    @GET
    @Path("orderbook")
    KrakenFuturesOrderBookResult getOrderbook(@QueryParam("symbol") String symbol);

}
