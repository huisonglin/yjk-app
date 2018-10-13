package com.yjk.app.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {


	/**
	 * 
	 * 将json形式字符串转换为java实体类
	 * 
	 */
	public static <T> T parse(String jsonStr, Class<T> clazz) {
	    ObjectMapper om = new ObjectMapper();
	    T readValue = null;
	    try {
	        readValue = om.readValue(jsonStr, clazz);
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return readValue;
	}
}
