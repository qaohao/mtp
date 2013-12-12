package com.allyes.mtp.utils;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 反射工具类
 * @author longlob
 */
public class ReflectUtils {
	
	/**
	 * 获得超类的参数类型，取第一个参数类型
	 * @param <T> 类型参数
	 * @param clazz 超类类型
	 */
	public static <T> Class<T> getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}
	
	/**
	 * 根据索引获得超类的参数类型
	 * @param clazz 超类类型
	 * @param index 索引
	 */
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 将pojo对象转换成一个Map，只针对含有get方法的属性，如果参数<code>pojo</code>为空，则返回
	 * <code>null</code>，如果并非pojo对象返回<code>null</code>。
	 * 
	 * @param pojo
	 *            普通java对象
	 * @return 含有get方法的属性转成的Map
	 */
	public static Map<String, Object> transPojo2Map(final Object pojo) {
		if (pojo == null) {
			return null;
		}

		if (pojo instanceof Map) {
			return (Map) pojo;
		}

		Class clazz = pojo.getClass();
		// 无效pojo对象。
		if (clazz.isArray() || clazz.isInterface() || clazz.isAnnotation()
				|| clazz.isAnonymousClass() || clazz.isEnum()
				|| clazz.isPrimitive() || clazz.isAnonymousClass()
				|| clazz.isSynthetic()) {
			return null;
		}

		// 取出含get方法的属性的值，将其存入map中。
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String methodName = "get" + StringUtils.capitalize(fieldName);
			Object value;
			try {
				value = clazz.getMethod(methodName).invoke(pojo);
				map.put(fieldName, value);
			} catch (Exception e) {
				continue;
			}
		}

		return map;
	}
	
}
