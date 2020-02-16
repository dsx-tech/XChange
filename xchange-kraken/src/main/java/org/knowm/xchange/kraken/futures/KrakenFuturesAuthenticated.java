package org.knowm.xchange.kraken.futures;

import org.knowm.xchange.kraken.futures.dto.marketdata.KrakenFuturesTickers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface KrakenFuturesAuthenticated extends KrakenFutures {

    @GET
    @Path("openorders")
    KrakenFuturesTickers getOpenOrders();



}
