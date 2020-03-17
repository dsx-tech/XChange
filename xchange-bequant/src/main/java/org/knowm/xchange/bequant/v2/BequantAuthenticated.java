package org.knowm.xchange.bequant.v2;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.bequant.v2.dto.BequantAddress;
import org.knowm.xchange.bequant.v2.dto.BequantBalance;
import org.knowm.xchange.bequant.v2.dto.BequantException;
import org.knowm.xchange.bequant.v2.dto.BequantInternalTransferResponse;
import org.knowm.xchange.bequant.v2.dto.BequantOrder;
import org.knowm.xchange.bequant.v2.dto.BequantOwnTrade;
import org.knowm.xchange.bequant.v2.dto.BequantTransaction;
import org.knowm.xchange.bequant.v2.service.BequantOrderType;
import org.knowm.xchange.bequant.v2.service.BequantTimeInForce;
import si.mazi.rescu.HttpStatusIOException;

/** Version 2 of Bequant API. See https://api.bequant.io/ */
@Path("/api/2/")
public interface BequantAuthenticated extends Bequant {

  /** ************************* Account APIs ***************************** */
  @GET
  @Path("account/balance")
  List<BequantBalance> getMainBalance() throws IOException, BequantException;

  @GET
  @Path("account/crypto/address/{currency}")
  BequantAddress getBequantDepositAddress(@PathParam("currency") String currency)
      throws IOException, BequantException;

  @GET
  @Path("account/transactions")
  List<BequantTransaction> transactions(
      @QueryParam("currency") String currency,
      @QueryParam("sort") String sort,
      @QueryParam("by") String by,
      @QueryParam("from") String from,
      @QueryParam("till") String till,
      @QueryParam("limit") Integer limit,
      @QueryParam("offset") Integer offset)
      throws BequantException, HttpStatusIOException;

  @POST
  @Path("account/transfer")
  BequantInternalTransferResponse transferToTrading(
      @FormParam("amount") BigDecimal amount,
      @FormParam("currency") String currency,
      @FormParam("type") String type)
      throws IOException, BequantException;

  @POST
  @Path("account/crypto/withdraw")
  Map payout(
      @FormParam("amount") BigDecimal amount,
      @FormParam("currency") String currency,
      @FormParam("address") String address,
      @FormParam("paymentId") String paymentId,
      @FormParam("includeFee") Boolean includeFee)
      throws BequantException, HttpStatusIOException;

  /** ********************** Tradding & Order APIs *********************** */

  // TODO add query params
  @GET
  @Path("order")
  List<BequantOrder> getBequantActiveOrders() throws IOException, BequantException;

  @POST
  @Path("order")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  BequantOrder postBequantNewOrder(
      @FormParam("clientOrderId") String clientOrderId,
      @FormParam("symbol") String symbol,
      @FormParam("side") String side,
      @FormParam("price") BigDecimal price,
      @FormParam("quantity") BigDecimal quantity,
      @FormParam("type") BequantOrderType type,
      @FormParam("timeInForce") BequantTimeInForce timeInForce)
      throws IOException, BequantException;

  @PATCH
  @Path("order/{clientOrderId}")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  BequantOrder updateBequantOrder(
      @PathParam("clientOrderId") String clientOrderId,
      @FormParam("quantity") BigDecimal quantity,
      @FormParam("requestClientId") String requestClientId,
      @FormParam("price") BigDecimal price)
      throws IOException, BequantException;

  @DELETE
  @Path("order")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  List<BequantOrder> cancelAllOrders(@FormParam("symbol") String symbol)
      throws IOException, BequantException;

  @DELETE
  @Path("order/{clientOrderId}")
  BequantOrder cancelSingleOrder(@PathParam("clientOrderId") String clientOrderId)
      throws IOException, BequantException;

  @GET
  @Path("trading/balance")
  List<BequantBalance> getTradingBalance() throws IOException, BequantException;

  /** ******************* Trading History APIs ***************************** */
  @GET
  @Path("history/trades")
  List<BequantOwnTrade> getBequantTrades(
      @QueryParam("symbol") String symbol,
      @QueryParam("sort") String sort,
      @QueryParam("by") String sortBy,
      @QueryParam("from") String from,
      @QueryParam("till") String till,
      @QueryParam("limit") Integer limit,
      @QueryParam("offset") long offset)
      throws IOException, BequantException;

  // TODO add query params

  /**
   * Get historical orders
   *
   * @return historical orders
   * @throws IOException
   * @throws BequantException
   */
  @GET
  @Path("history/order")
  List<BequantOrder> getBequantRecentOrders() throws IOException, BequantException;

  /**
   * Get an old order. The returning collection contains, at most, 1 element.
   *
   * @param symbol symbol
   * @param clientOrderId client order id
   * @return list of orders
   * @throws IOException throw in case IO problems
   * @throws BequantException throw in case internal Bequant problems
   */
  @GET
  @Path("history/order")
  List<BequantOrder> getBequantOrder(
      @QueryParam("symbol") String symbol, @QueryParam("clientOrderId") String clientOrderId)
      throws IOException, BequantException;

  @GET
  @Path("/history/order/{id}/trades")
  List<BequantOwnTrade> getHistorialTradesByOrder(@PathParam("id") String orderId)
      throws IOException, BequantException;
}
