package org.knowm.xchange.kraken.futures.dto.marketdata;

import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesSide;
import org.knowm.xchange.kraken.futures.dto.enums.KrakenFuturesTradeType;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

/**
 * @author pchertalev
 */
public class KrakenFuturesTrade {

    /**
     * 	The classification of the taker side in the matched trade: "buy" if the taker is a buyer, "sell" if the taker is a seller.
     */
    private final KrakenFuturesSide side;

    /**
     * The classification of the matched trade in an orderbook: "fill" if it is a normal buyer and seller, "liquidation"
     * if it is a result of a user being liquidated from their position, or "termination" if it is a result of a user being terminated.
     */
    private final KrakenFuturesTradeType type;


    /**
     * The subscription message sequence number
     */
    private final Long seq;

    /**
     * The UTC or GMT time of the trade in milliseconds
     */
    private final Long time;

    /**
     * The quantity of the traded product
     */
    private final BigDecimal qty;

    /**
     * The price that the product got traded
     */
    private final BigDecimal price;

    @ConstructorProperties({"side", "type", "seq", "time", "qty", "price"})
    public KrakenFuturesTrade(KrakenFuturesSide side, KrakenFuturesTradeType type, Long seq, Long time, BigDecimal qty, BigDecimal price) {
        this.side = side;
        this.type = type;
        this.seq = seq;
        this.time = time;
        this.qty = qty;
        this.price = price;
    }

    public KrakenFuturesSide getSide() {
        return side;
    }

    public KrakenFuturesTradeType getType() {
        return type;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getTime() {
        return time;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
