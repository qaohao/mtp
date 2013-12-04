package com.allyes.mtp;

/**
 * Action定义
 * 
 * @author qaohao
 */
public interface Action {
	/**
	 * 请求资源。
	 * 
	 * @param actionContext
	 *            请求上下文。
	 * @return {@link Response}
	 */
	public Response request(ActionContext actionContext);
}