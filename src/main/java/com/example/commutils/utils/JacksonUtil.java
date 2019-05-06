package com.example.commutils.utils;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json轉換工具
 * @author charles.chen
 * @date 2018年12月12日 下午4:03:08
 */
public final class JacksonUtil {
	
	public static ObjectMapper objectMapper = new ObjectMapper();
 
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
		try {
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
	public static String toJSon(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T readValue(InputStream fileInputStream, Class<T> valueType){
		try {
			return objectMapper.readValue(fileInputStream, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
}
