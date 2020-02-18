package org.knowm.xchange.krakenFutures.dto.trade;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.knowm.xchange.krakenFutures.dto.enums.KrakenFuturesOrderEventType;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
    property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = KrakenFuturesOrderEventPlace.class, name = "PLACE"),
  @JsonSubTypes.Type(value = KrakenFuturesOrderEventCancel.class, name = "CANCEL"),
  @JsonSubTypes.Type(value = KrakenFuturesOrderEventEdit.class, name = "EDIT"),
  @JsonSubTypes.Type(value = KrakenFuturesOrderEventReject.class, name = "REJECT"),
  @JsonSubTypes.Type(value = KrakenFuturesOrderEventExecution.class, name = "EXECUTION"),
})
public class KrakenFuturesOrderEvent {

  private KrakenFuturesOrderEventType type;

  public KrakenFuturesOrderEventType getType() {
    return type;
  }

  public void setType(KrakenFuturesOrderEventType type) {
    this.type = type;
  }
}
