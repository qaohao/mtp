package com.allyes.mtp.common;

import org.apache.commons.lang.StringUtils;

import com.allyes.mtp.utils.Assert;

/**
 * 请求内容格式。
 * 
 * @author qaohao
 */
public enum HttpFormat {
	json("application/json"), xml("text/xml"),html("text/html");
	private String format;
	private String contentType;

	private HttpFormat(String contentType) {
		this.format = this.name();
		this.contentType = contentType;
	}

	public static HttpFormat getInstance(String format) {
		Assert.isTrue(StringUtils.isBlank(format), "参数format不能为空！");
		if (json.format.equals(format)) {
			return json;
		} else if (xml.format.equals(format)) {
			return xml;
		} else if (html.format.equals(format)) {
			return html;
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
