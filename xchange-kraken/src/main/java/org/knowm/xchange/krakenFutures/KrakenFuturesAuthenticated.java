package org.knowm.xchange.krakenFutures;

import java.math.BigDecimal;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResult;
import org.knowm.xchange.krakenFutures.dto.KrakenFuturesResultException;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesAccounts;
import org.knowm.xchange.krakenFutures.dto.account.KrakenFuturesTransfers;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesOrderType;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesSide;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesTrigerSignal;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenCancelAllOrders;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesFills;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOpenPositions;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrderSendStatusResult;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesOrders;
import org.knowm.xchange.krakenFutures.dto.trade.KrakenFuturesRecentOrderEvents;
import org.knowm.xchange.krakenFutures.service.KrakenFuturesDigest;

@Path("/api/v3/")
@Produces(MediaType.APPLICATION_JSON)
public interface KrakenFuturesAuthenticated extends KrakenFutures {

  @GET
  @Path("accounts")
  KrakenFuturesAccounts accounts(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent)
      throws KrakenFuturesResultException;

  @GET
  @Path("openorders")
  KrakenFuturesOrders getOpenOrders(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent)
      throws KrakenFuturesResultException;

  @GET
  @Path("sendorder")
  KrakenFuturesOrderSendStatusResult sendOrder(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent,
      @QueryParam("orderType") KrakenFuturesOrderType orderType,
      @QueryParam("symbol") String symbol,
      @QueryParam("side") KrakenFuturesSide side,
      @QueryParam("size") Long size,
      @QueryParam("limitPrice") BigDecimal limitPrice,
      @QueryParam("stopPrice") BigDecimal stopPrice,
      @QueryParam("triggerSignal") KrakenFuturesTrigerSignal triggerSignal,
      @QueryParam("cliOrdId") String cliOrdId,
      @QueryParam("reduceOnly") Boolean reduceOnly)
      throws KrakenFuturesResultException;

  @GET
  @Path("fills")
  KrakenFuturesFills fills(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent,
      @QueryParam("lastFillTime") Date lastFillTime)
      throws KrakenFuturesResultException;

  @GET
  @Path("openpositions")
  KrakenFuturesOpenPositions openPositions(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent)
      throws KrakenFuturesResultException;

  @GET
  @Path("cancelallorders")
  KrakenCancelAllOrders cancelAllOrders(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent,
      @QueryParam("symbol") String symbol)
      throws KrakenFuturesResultException;

  @GET
  @Path("withdrawal")
  KrakenFuturesResult withdrawal(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent,
      @QueryParam("currency") Currency currency,
      @QueryParam("amount") BigDecimal amount)
      throws KrakenFuturesResultException;

  @GET
  @Path("transfer")
  KrakenFuturesResult transfer(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent,
      @QueryParam("fromAccount") String fromAccount,
      @QueryParam("toAccount") String toAccount,
      @QueryParam("unit") Currency currency,
      @QueryParam("amount") BigDecimal amount)
      throws KrakenFuturesResultException;

  @GET
  @Path("transfers")
  KrakenFuturesTransfers transfers(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent,
      @QueryParam("lastTransferTime") Date lastTransferTime)
      throws KrakenFuturesResultException;

  @GET
  @Path("recentorders")
  KrakenFuturesRecentOrderEvents recentOrders(
      @HeaderParam("Nonce") String nonce,
      @HeaderParam("APIKey") String apyKey,
      @HeaderParam("Authent") KrakenFuturesDigest authent,
      @HeaderParam("symbol") String symbol)
      throws KrakenFuturesResultException;
}