package com.example.commutils.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtil {
	
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
	
	//https://howtodoinjava.com/java8/java-stream-distinct-examples/
	/**
	 * 根據bean裡面的屬性去掉重複
	 * @date : 2019年1月22日 下午4:01:53 </p>
	 * @author Daniel
	 * @param keyExtractor
	 * @return
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
	{
	    Map<Object, Boolean> map = new ConcurrentHashMap<>();
	    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
