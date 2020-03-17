package org.knowm.xchange.bequant.v2;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.knowm.xchange.bequant.v2.dto.BequantCandle;
import org.knowm.xchange.bequant.v2.dto.BequantCurrency;
import org.knowm.xchange.bequant.v2.dto.BequantOrderBook;
import org.knowm.xchange.bequant.v2.dto.BequantSymbol;
import org.knowm.xchange.bequant.v2.dto.BequantTicker;
import org.knowm.xchange.bequant.v2.dto.BequantTrade;

/** Version 2 of Bequant API. See https://api.bequant.io/ */
@Path("/api/2/")
public interface Bequant {

  @GET
  @Path("public/symbol")
  List<BequantSymbol> getSymbols() throws IOException;

  @GET
  @Path("public/currency")
  List<BequantCurrency> getCurrencies() throws IOException;

  @GET
  @Path("public/currency/{currency}")
  BequantCurrency getCurrency(@PathParam("currency") String currency) throws IOException;

  @GET
  @Path("public/ticker")
  List<BequantTicker> getTickers() throws IOException;

  @GET
  @Path("public/ticker/{symbol}")
  BequantTicker getTicker(@PathParam("symbol") String symbol) throws IOException;

  @GET
  @Path("public/orderbook/{symbol}")
  BequantOrderBook getOrderBook(
      @PathParam("symbol") String symbol, @QueryParam("limit") Integer limit) throws IOException;

  @GET
  @Path("public/trades/{symbol}")
  List<BequantTrade> getTrades(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") long limit,
      @QueryParam("offset") long offset)
      throws IOException;

  @GET
  @Path("public/trades/{symbol}")
  List<BequantTrade> getTrades(
      @PathParam("symbol") String symbol,
      @QueryParam("sort") String sortDirection,
      @QueryParam("by") String sortBy,
      @QueryParam("from") String from,
      @QueryParam("limit") long limit)
      throws IOException;

  @GET
  @Path("public/ticker")
  List<BequantTicker> getBequantTickers() throws IOException;

  @GET
  @Path("public/candles/{symbol}")
  List<BequantCandle> getBequantOHLC(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") int limit,
      @QueryParam("period") String period)
      throws IOException;

  @GET
  @Path("public/candles/{symbol}")
  List<BequantCandle> getBequantOHLC(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") int limit,
      @QueryParam("period") String period,
      @QueryParam("sort") String sort)
      throws IOException;

  @GET
  @Path("public/candles/{symbol}")
  List<BequantCandle> getBequantOHLC(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") int limit,
      @QueryParam("period") String period,
      @QueryParam("from") Date from,
      @QueryParam("till") Date till,
      @QueryParam("sort") String sort)
      throws IOException;

  @GET
  @Path("public/candles/{symbol}")
  List<BequantCandle> getBequantOHLC(
      @PathParam("symbol") String symbol,
      @QueryParam("limit") int limit,
      @QueryParam("period") String period,
      @QueryParam("offset") int offset,
      @QueryParam("sort") String sort)
      throws IOException;
}
