package com.allyes.mtp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public abstract class WebUtil {

	public static Map parseUrlParameters(String paramStr) {
		if (StringUtils.isBlank(paramStr)) {
			return null;
		}

		int index = -1;
		String key, value;
		Map paramMap = new HashMap();
		for (String keyValue : paramStr.split("&")) {
			// 该参数无效直接忽略
			if ((index = keyValue.indexOf("=")) == -1) {
				continue;
			}

			key = keyValue.substring(0, index);
			value = keyValue.substring(index + 1);

			// 判断参数集合中是否已经包含该参数，如果包含将其对应值转化成list
			if (paramMap.containsKey(key)) {
				Object curValue = paramMap.get(key);
				if (curValue instanceof List) {
					((List) curValue).add(value);
				} else {
					List valueList = new ArrayList();
					valueList.add(curValue);
					valueList.add(value);
					paramMap.put(key, valueList);
				}
			} else {
				paramMap.put(key, value);
			}
		}

		return paramMap;
	}

}
