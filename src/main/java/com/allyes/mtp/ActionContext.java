package com.allyes.mtp;

import java.util.Map;

import javax.servlet.http.HttpServlet;

/**
 * 请求Action上下文。
 * 
 * @author qaohao
 */
public interface ActionContext {
	/**
	 * @return 请求参数映射
	 */
	public Map getParamMap();

	/**
	 * @return 请求uri（不包含context）
	 */
	public String getRequstUri();
	
	/**
	 * @return 请求HttpServlet
	 * @see HttpServlet
	 */
	public HttpServlet getHttpServlet();
}
