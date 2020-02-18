package org.knowm.xchange.kraken.futures;

import org.knowm.xchange.kraken.futures.dto.account.KrakenFuturesAccounts;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesOrderType;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesSide;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesTrigerSignal;
import org.knowm.xchange.kraken.futures.dto.trade.KrakenFuturesFills;
import org.knowm.xchange.kraken.futures.dto.trade.KrakenFuturesOpenPositions;
import org.knowm.xchange.kraken.futures.dto.trade.KrakenFuturesOrderSendStatusResult;
import org.knowm.xchange.kraken.futures.dto.trade.KrakenFuturesOrders;
import org.knowm.xchange.kraken.futures.service.KrakenFuturesDigest;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Date;

@Path("/api/v3/")
@Produces(MediaType.APPLICATION_JSON)
public interface KrakenFuturesAuthenticated extends KrakenFutures {


    @GET
    @Path("accounts")
    KrakenFuturesAccounts accounts(@HeaderParam("Nonce") String nonce, @HeaderParam("APIKey") String apyKey,
                                   @HeaderParam("Authent") KrakenFuturesDigest authent);

    @GET
    @Path("openorders")
    KrakenFuturesOrders getOpenOrders(@HeaderParam("Nonce") String nonce, @HeaderParam("APIKey") String apyKey,
                                      @HeaderParam("Authent") KrakenFuturesDigest authent);

    @GET
    @Path("sendorder")
    KrakenFuturesOrderSendStatusResult sendOrder(@HeaderParam("Nonce") String nonce, @HeaderParam("APIKey") String apyKey, @HeaderParam("Authent") KrakenFuturesDigest authent,
                                                 @QueryParam("orderType") KrakenFuturesOrderType orderType, @QueryParam("symbol") String symbol,
                                                 @QueryParam("side") KrakenFuturesSide side, @QueryParam("size") Long size, @QueryParam("limitPrice") BigDecimal limitPrice,
                                                 @QueryParam("stopPrice") BigDecimal stopPrice, @QueryParam("triggerSignal") KrakenFuturesTrigerSignal triggerSignal,
                                                 @QueryParam("cliOrdId") String cliOrdId, @QueryParam("reduceOnly") Boolean reduceOnly);

    @GET
    @Path("fills")
    KrakenFuturesFills fills(@HeaderParam("Nonce") String nonce, @HeaderParam("APIKey") String apyKey, @HeaderParam("Authent") KrakenFuturesDigest authent,
                             @QueryParam("lastFillTime") Date lastFillTime);

    @GET
    @Path("openpositions")
    KrakenFuturesOpenPositions openPositions(@HeaderParam("Nonce") String nonce, @HeaderParam("APIKey") String apyKey, @HeaderParam("Authent") KrakenFuturesDigest authent);
}
