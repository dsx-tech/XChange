package org.knowm.xchange.dsx.v2;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.knowm.xchange.dsx.v2.dto.DsxCandle;
import org.knowm.xchange.dsx.v2.dto.DsxCurrency;
import org.knowm.xchange.dsx.v2.dto.DsxOrderBook;
import org.knowm.xchange.dsx.v2.dto.DsxSymbol;
import org.knowm.xchange.dsx.v2.dto.DsxTicker;
import org.knowm.xchange.dsx.v2.dto.DsxTrade;

/** Version 2 of DSX API. See https://api.dsxglobal.com/api/2/explore/ */
@Path("/api/2/")
public interface Dsx {

  @GET
  @Path("public/symbol")
  List<DsxSymbol> getSymbols() throws IOException;

  @GET
  @Path("public/currency")
  List<DsxCurrency> getCurrencies() throws IOException;

  @GET
  @Path("public/currency/{currency}")
  DsxCurrency getCurrency(@PathParam("currency") String currency) throws IOException;

  @GET
  @Path("public/ticker")
  List<DsxTicker> getTickers() throws IOException;

  @GET
  @Path("public/ticker/{symbol}")
  DsxTicker getTicker(@PathParam("symbol") String symbol) throws IOException;

  @GET
  @Path("public/orderbook/{symbol}")
  DsxOrderBook getOrderBook(@PathParam("symbol") String symbol, @QueryParam("limit") Integer limit)
      throws IOException;

  @GET
  @Path("public/trades/{symbol}")
  List<DsxTrade> getTrades(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") long limit,
      @QueryParam("offset") long offset)
      throws IOException;

  @GET
  @Path("public/trades/{symbol}")
  List<DsxTrade> getTrades(
      @PathParam("symbol") String symbol,
      @QueryParam("sort") String sortDirection,
      @QueryParam("by") String sortBy,
      @QueryParam("from") String from,
      @QueryParam("limit") long limit)
      throws IOException;

  @GET
  @Path("public/ticker")
  List<DsxTicker> getDsxTickers() throws IOException;

  @GET
  @Path("public/candles/{symbol}")
  List<DsxCandle> getDsxOHLC(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") int limit,
      @QueryParam("period") String period)
      throws IOException;

  @GET
  @Path("public/candles/{symbol}")
  List<DsxCandle> getDsxOHLC(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") int limit,
      @QueryParam("period") String period,
      @QueryParam("sort") String sort)
      throws IOException;

  @GET
  @Path("public/candles/{symbol}")
  List<DsxCandle> getDsxOHLC(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") int limit,
      @QueryParam("period") String period,
      @QueryParam("from") Date from,
      @QueryParam("till") Date till,
      @QueryParam("sort") String sort)
      throws IOException;

  @GET
  @Path("public/candles/{symbol}")
  List<DsxCandle> getDsxOHLC(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") int limit,
      @QueryParam("period") String period,
      @QueryParam("offset") int offset,
      @QueryParam("sort") String sort)
      throws IOException;
}
