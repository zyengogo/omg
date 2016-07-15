package com.omg.xbase.struts;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


public class DateTimeDeserializer extends JsonDeserializer<org.joda.time.DateTime> {
	private DateTimeFormatter dtf = null;
	private DateTimeZone dtz = DateTimeZone.forID("Asia/Chongqing");
	public DateTimeDeserializer() {
		dtf = ISODateTimeFormat.dateTime();
		dtf.withZone(dtz);
	}
	
	public DateTimeDeserializer(String dateTimeFormat) {
		dtf = DateTimeFormat.forPattern(dateTimeFormat);
		dtf.withZone(dtz);
	}
	
	public org.joda.time.DateTime deserialize(JsonParser jp, DeserializationContext ctxt) {
		try {
			return dtf.parseDateTime(jp.getText());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
