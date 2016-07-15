package com.omg.xbase.struts.plug.json;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author LZ
 *
 */
public class JsonUtil {
	private static Log log = LogFactory.getLog(JsonUtil.class);  
	
	
	/**
	 * 将对象转换成JSON字符串
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj){
		return JSONSerializer.toJSON(obj).toString();
	}
	
//	public static String toJSONString(Object obj,String dateFormat){
//		return JSONSerializer.toJSON(obj, configJson(dateFormat)).toString();
//	}
	
	/**
	 * 将JSON字符串转换为对象(数组型JSON)
	 * @param jsonString
	 * @param cla
	 * @return
	 */
	public static Object[] toArray(String jsonString,Class cla){
		JSON json=JSONSerializer.toJSON(jsonString);
		if(json instanceof JSONArray){
			return (Object[]) JSONArray.toArray((JSONArray) json,cla);
		}else{
			return null;
		}
	}
//	public static Object[] toArray2(String jsonString){
//		JSON json=JSONSerializer.toJSON(jsonString);
//		if(json instanceof JSONArray){
//			return (Object[]) JSONArray.toArray((JSONArray) json,CptDocument.class);
//		}else{
//			return null;
//		}
//	}
	
	/**
	 * 将JSON字符串转换为对象(数组型JSON)
	 * @param jsonString
	 * @param cla
	 * @return
	 */
//	public static String array2json2(CptDocument[] array){
//	    JSONArray jsonArray1 = JSONArray.fromObject( array );    
//	    return jsonArray1.toString();
//	}
//	
	/**
	 * 将JSON字符串转换为对象(非数组型JSON)
	 * @param jsonString
	 * @param cla
	 * @return
	 */
	public static Object toObject(String jsonString,Class cla){
		JSON json=JSONSerializer.toJSON(jsonString);
		if(json instanceof JSONObject){
			return JSONObject.toBean((JSONObject) json,cla);
		}else{
			return null;
		}
	}
	public static String object2json(Object obj) {  
        StringBuilder json = new StringBuilder();  
        if (obj == null) {  
          json.append("\"\"");  
        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float  
            || obj instanceof Boolean || obj instanceof Short || obj instanceof Double  
            || obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger  
            || obj instanceof Byte) {  
          json.append("\"").append(string2json(obj.toString())).append("\"");  
        } else if (obj instanceof Object[]) {  
          json.append(array2json((Object[]) obj));  
        } else if (obj instanceof List) {  
          json.append(list2json((List<?>) obj));  
        } else if (obj instanceof Map) {  
          json.append(map2json((Map<?, ?>) obj));  
        } else if (obj instanceof Set) {  
          json.append(set2json((Set<?>) obj));  
        } else {  
          json.append(bean2json(obj));  
        }  
        return json.toString();  
}  
public static String bean2json(Object bean) {  
        StringBuilder json = new StringBuilder();  
        json.append("{");  
        PropertyDescriptor[] props = null;  
        try {  
          props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();  
        } catch (IntrospectionException e) {}  
        if (props != null) {  
          for (int i = 0; i < props.length; i++) {  
            try {  
              String name = object2json(props[i].getName());  
              String value = object2json(props[i].getReadMethod().invoke(bean));  
              json.append(name);  
              json.append(":");  
              json.append(value);  
              json.append(",");  
            } catch (Exception e) {}  
          }  
          json.setCharAt(json.length() - 1, '}');  
        } else {  
          json.append("}");  
        }  
        return json.toString();  
}  
public static String list2json(List<?> list) {  
        StringBuilder json = new StringBuilder();  
        json.append("[");  
        if (list != null && list.size() > 0) {  
          for (Object obj : list) {  
            json.append(object2json(obj));  
            json.append(",");  
          }  
          json.setCharAt(json.length() - 1, ']');  
        } else {  
          json.append("]");  
        }  
        return json.toString();  
}  
public static String list2json2(List<?> list) {  
	StringBuilder json = new StringBuilder();  
	json.append("[");  
	if (list != null && list.size() > 0) {  
		for (Object obj : list) {  
			json.append(toJSONString(obj));  
			json.append(",");  
		}  
		json.setCharAt(json.length() - 1, ']');  
	} else {  
		json.append("]");  
	}  
	return json.toString();  
}  
public static String array2json(Object[] array) {  
        StringBuilder json = new StringBuilder();  
        json.append("[");  
        if (array != null && array.length > 0) {  
          for (Object obj : array) {  
            json.append(object2json(obj));  
            json.append(",");  
          }  
          json.setCharAt(json.length() - 1, ']');  
        } else {  
          json.append("]");  
        }  
        return json.toString();  
}  
public static String map2json(Map<?, ?> map) {  
        StringBuilder json = new StringBuilder();  
        json.append("{");  
        if (map != null && map.size() > 0) {  
          for (Object key : map.keySet()) {  
            json.append(object2json(key));  
            json.append(":");  
            json.append(object2json(map.get(key)));  
            json.append(",");  
          }  
          json.setCharAt(json.length() - 1, '}');  
        } else {  
          json.append("}");  
        }  
        return json.toString();  
}  
public static String set2json(Set<?> set) {  
        StringBuilder json = new StringBuilder();  
        json.append("[");  
        if (set != null && set.size() > 0) {  
          for (Object obj : set) {  
            json.append(object2json(obj));  
            json.append(",");  
          }  
          json.setCharAt(json.length() - 1, ']');  
        } else {  
          json.append("]");  
        }  
        return json.toString();  
}  
public static String string2json(String s) {  
        if (s == null)  
          return "";  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < s.length(); i++) {  
          char ch = s.charAt(i);  
          switch (ch) {  
          case '"':  
            sb.append("\\\"");  
            break;  
          case '\\':  
            sb.append("\\\\");  
            break;  
          case '\b':  
            sb.append("\\b");  
            break;  
          case '\f':  
            sb.append("\\f");  
            break;  
          case '\n':  
            sb.append("\\n");  
            break;  
          case '\r':  
            sb.append("\\r");  
            break;  
          case '\t':  
            sb.append("\\t");  
            break;  
          case '/':  
            sb.append("\\/");  
            break;  
          default:  
            if (ch >= '\u0000' && ch <= '\u001F') {  
              String ss = Integer.toHexString(ch);  
              sb.append("\\u");  
              for (int k = 0; k < 4 - ss.length(); k++) {  
                sb.append('0');  
              }  
              sb.append(ss.toUpperCase());  
            } else {  
              sb.append(ch);  
            }  
          }  
        }  
        return sb.toString();  
} 


///**
// * JSON 时间解析器具
// * 
// * @param datePattern
// * @return
// */
//private static JsonConfig configJson(String datePattern) {
//    JsonConfig jsonConfig = new JsonConfig();
//    jsonConfig.setExcludes(new String[] { "" });
//    jsonConfig.setIgnoreDefaultExcludes(false);
//    jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//    jsonConfig.registerJsonValueProcessor(Date.class,
//            new DateJsonValueProcessor(datePattern));
//    return jsonConfig;
//}

}   
