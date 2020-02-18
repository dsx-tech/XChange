package org.knowm.xchange.krakenFutures.dto.account;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesAccountType;
import org.knowm.xchange.utils.jackson.CurrencyDeserializer;

import java.math.BigDecimal;
import java.util.Map;

public class KrakenFuturesAccount {

    /**
     * The type of the account, either cashAccount or marginAccount
     */
    private KrakenFuturesAccountType type;

    /**
     * The currency of the account. All figures shown in
     * auxiliary and marginRequirements are in
     * this currency. Returned only for margin accounts
     */
    @JsonDeserialize(keyUsing = CurrencyDeserializer.class)
    private Currency currency;

    /**
     * A structure containing auxiliary account information.
     * Returned only for margin accounts
     */
    private KrakenFuturesAuxiliaryAccount auxiliary;

    /**
     * A structure containing the account’s margin requirements,
     * returned only for margin accounts
     */
    private KrakenFuturesAccountMarginRequirements marginRequirements;

    /**
     * A structure containing the account’s margin trigger estimates,
     * returned only for margin accounts
     */
    private KrakenFuturesAccountMarginRequirements triggerEstimates;

    /**
     * A structure containing account balances
     */
    @JsonDeserialize(keyUsing = CurrencyDeserializer.class)
    private Map<Currency, BigDecimal> balances;

    public KrakenFuturesAccountType getType() {
        return type;
    }

    public void setType(KrakenFuturesAccountType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public KrakenFuturesAuxiliaryAccount getAuxiliary() {
        return auxiliary;
    }

    public void setAuxiliary(KrakenFuturesAuxiliaryAccount auxiliary) {
        this.auxiliary = auxiliary;
    }

    public KrakenFuturesAccountMarginRequirements getMarginRequirements() {
        return marginRequirements;
    }

    public void setMarginRequirements(KrakenFuturesAccountMarginRequirements marginRequirements) {
        this.marginRequirements = marginRequirements;
    }

    public KrakenFuturesAccountMarginRequirements getTriggerEstimates() {
        return triggerEstimates;
    }

    public void setTriggerEstimates(KrakenFuturesAccountMarginRequirements triggerEstimates) {
        this.triggerEstimates = triggerEstimates;
    }

    public Map<Currency, BigDecimal> getBalances() {
        return balances;
    }

    public void setBalances(Map<Currency, BigDecimal> balances) {
        this.balances = balances;
    }
}
