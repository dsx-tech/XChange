package org.knowm.xchange.utils.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ISODateSerializer extends JsonSerializer<Date> {
  @Override
  public void serialize(
      Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeString(
        DateTimeFormatter.ISO_DATE_TIME.format(Instant.ofEpochMilli(date.getTime())));
  }
}
