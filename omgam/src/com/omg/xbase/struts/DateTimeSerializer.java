package com.omg.xbase.struts;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeSerializer extends JsonSerializer<DateTime> {
	private DateTimeFormatter dtf = null;
	private DateTimeZone dtz = DateTimeZone.forID("Asia/Chongqing");

	public DateTimeSerializer() {
		dtf = ISODateTimeFormat.dateTime();
		dtf.withZone(dtz);
	}
	
	public DateTimeSerializer(String dateTimeFormat) {
		dtf = DateTimeFormat.forPattern(dateTimeFormat);
		dtf.withZone(dtz);
	}
	public void serialize(org.joda.time.DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(dtf.print(value));
	}

}
