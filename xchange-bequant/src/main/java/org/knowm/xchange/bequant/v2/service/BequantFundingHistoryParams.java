package org.knowm.xchange.bequant.v2.service;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

public class BequantFundingHistoryParams implements TradeHistoryParams {

  private Currency currency;
  private Integer offset;
  private Integer limit;

  private BequantFundingHistoryParams(Currency currency, Integer offset, Integer limit) {
    this.currency = currency;
    this.offset = offset;
    this.limit = limit;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Currency getCurrency() {
    return currency;
  }

  public Integer getOffset() {
    return offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public static class Builder {

    private Currency currency;

    private Integer offset;

    private Integer limit;

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public Builder offset(Integer offset) {
      this.offset = offset;
      return this;
    }

    public Builder limit(Integer limit) {
      this.limit = limit;
      return this;
    }

    public BequantFundingHistoryParams build() {
      return new BequantFundingHistoryParams(currency, offset, limit);
    }
  }
}
