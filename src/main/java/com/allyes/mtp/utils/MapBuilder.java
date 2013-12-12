package com.allyes.mtp.utils;

import java.util.Map;


public final class MapBuilder {
	Map innerMap;
	private <T extends Map> MapBuilder(Class<T> clazz) {
		innerMap = (Map)ReflectUtils.newInstance(clazz);
	}
	public static <T extends Map> MapBuilder newInstance(Class<T> clazz) {
		return new MapBuilder(clazz);
	}
	public MapBuilder put(Object key, Object value) {
		innerMap.put(key, value);
		return this;
	}
	public MapBuilder putAll(Map map) {
		innerMap.putAll(map);
		return this;
	}
	public Map map() {
		return innerMap;
	}
}
