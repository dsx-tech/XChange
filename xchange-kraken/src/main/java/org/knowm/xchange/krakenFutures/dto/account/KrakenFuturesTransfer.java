package org.knowm.xchange.krakenFutures.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesTransferStatus;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesTransferType;

public class KrakenFuturesTransfer {

  /**
   * If transferType is deposit: The date and time the deposit was first detected If transferType is
   * withdrawal: The date and time the withdrawal request was received
   */
  private Date receivedTime;

  /**
   * If status is processed: The date and time the transfer has been processed and booked If status
   * is pending: Not returned because N/A
   */
  private Date completedTime;

  /**
   * accepted: the withdrawal request was accepted and will be processed soon
   * insufficientAvailableFunds : the withdrawal request was not accepted because available funds
   * are insufficient
   */
  private KrakenFuturesTransferStatus status;

  /** The unique identifier of the transfer */
  @JsonProperty("transfer_id")
  private String transferId;

  /**
   * If status is processed: The blockchain transaction id of the transfer if the transfer involves
   * an external digital asset address and Internal Transaction if the transaction is sent to an
   * address controlled by Crypto Facilities If status is pending: Not returned because N/A
   */
  @JsonProperty("transaction_id")
  private String transactionId;

  /** The digital asset address to which the transfer is sent */
  private String targetAddress;

  /** The type of the transfer, either deposit or withdrawal */
  private KrakenFuturesTransferType transferType;

  /**
   * The digital asset amount that was transferred. Positive for deposits and negative for
   * withdrawals
   */
  private BigDecimal amount;

  public Date getReceivedTime() {
    return receivedTime;
  }

  public void setReceivedTime(Date receivedTime) {
    this.receivedTime = receivedTime;
  }

  public Date getCompletedTime() {
    return completedTime;
  }

  public void setCompletedTime(Date completedTime) {
    this.completedTime = completedTime;
  }

  public KrakenFuturesTransferStatus getStatus() {
    return status;
  }

  public void setStatus(KrakenFuturesTransferStatus status) {
    this.status = status;
  }

  public String getTransferId() {
    return transferId;
  }

  public void setTransferId(String transferId) {
    this.transferId = transferId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getTargetAddress() {
    return targetAddress;
  }

  public void setTargetAddress(String targetAddress) {
    this.targetAddress = targetAddress;
  }

  public KrakenFuturesTransferType getTransferType() {
    return transferType;
  }

  public void setTransferType(KrakenFuturesTransferType transferType) {
    this.transferType = transferType;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
