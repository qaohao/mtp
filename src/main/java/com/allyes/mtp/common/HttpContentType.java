package com.allyes.mtp.common;

import org.apache.commons.lang3.StringUtils;

import com.allyes.mtp.utils.Assert;

/**
 * 报表返回类型。
 * 
 * @author qaohao
 */
public enum HttpContentType {
	json("application/json"), xml("text/xml");
	private String format;
	private String contentType;

	private HttpContentType(String contentType) {
		this.format = this.name();
		this.contentType = contentType;
	}

	public static HttpContentType getInstance(String format) {
		Assert.isTrue(StringUtils.isBlank(format), "参数format不能为空！");
		if (json.format.equals(format)) {
			return json;
		} else if (xml.format.equals(format)) {
			return xml;
		} else {
			return json;
		}
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	public String getSuffix() {
		return "." + format;
	}
}
