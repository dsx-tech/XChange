package org.knowm.xchange.bequant.v2.dto;

public enum BequantTransferType {
  BANK_TO_EXCHANGE("bankToExchange"),
  EXCHANGE_TO_BANK("exchangeToBank");

  private final String type;

  BequantTransferType(String type) {

    this.type = type;
  }

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return "BequantTransferType{" + "type='" + type + '\'' + '}';
  }
}
