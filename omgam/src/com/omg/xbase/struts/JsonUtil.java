package com.omg.xbase.struts;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.type.TypeReference;


public class JsonUtil {
	public static <T>T getObjectFromJson(String json,Class<T> c) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return (T)mapper.readValue(json, c);
	}
	
	public static ArrayList<?> getObjectArrayListFromJson(String json,TypeReference<?> t) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return (ArrayList<?>)mapper.readValue(json, t);
	}

	public static <T>T getObjectFromJson(String json,String jodaDateTimeFormat, Class<T> c) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		CustomDeserializerFactory sf = new CustomDeserializerFactory();
		if (jodaDateTimeFormat == null || "".equals(jodaDateTimeFormat)) {
			sf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeDeserializer());
		}else{
			sf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeDeserializer(jodaDateTimeFormat));
		}
		mapper.setDeserializerProvider(new StdDeserializerProvider(sf));
		return (T)mapper.readValue(json, c);
	}
	
	public static String getJsonFromObject(Object value,String filter) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		FilterSerializerFactory csf = new FilterSerializerFactory();
		csf.addExcludeFilters(filter);
		csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer());
		mapper.setSerializerFactory(csf);
		return mapper.writeValueAsString(value);
	}
	
	public static void getJsonFromObject(Object value,OutputStream out,String filter) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		FilterSerializerFactory csf = new FilterSerializerFactory();
		csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer());
		mapper.setSerializerFactory(csf);
		csf.addExcludeFilters(filter);
		mapper.writeValue(out, value);
	}
}
