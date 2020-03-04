package org.knowm.xchange.krakenFutures;

import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResultException;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesInstruments;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesOrderBookResult;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTickers;
import org.knowm.xchange.krakenFutures.dto.marketdata.KrakenFuturesTrades;

/** @author pchertalev */
@Produces(MediaType.APPLICATION_JSON)
public interface KrakenFutures {

  @GET
  @Path("tickers")
  KrakenFuturesTickers getTickers() throws KrakenFuturesResultException;

  @GET
  @Path("instruments")
  KrakenFuturesInstruments getInstruments() throws KrakenFuturesResultException;

  @GET
  @Path("orderbook")
  KrakenFuturesOrderBookResult getOrderbook(@QueryParam("symbol") String symbol)
      throws KrakenFuturesResultException;

  @GET
  @Path("history")
  KrakenFuturesTrades history(
      @QueryParam("symbol") String symbol, @QueryParam("lastTime") Date lastTime)
      throws KrakenFuturesResultException;
}
