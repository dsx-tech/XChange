package org.knowm.xchange.krakenFutures;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTickers;

/** @author pchertalev */
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
