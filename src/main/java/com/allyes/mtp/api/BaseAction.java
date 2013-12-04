package com.allyes.mtp.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.allyes.mtp.Action;
import com.allyes.mtp.ActionContext;
import com.allyes.mtp.Response;
import com.allyes.mtp.common.Config;
import com.allyes.mtp.common.HttpContentType;
import com.allyes.mtp.common.HttpStatus;
import com.allyes.mtp.utils.FreemarkerHelper;

/**
 * Action基类，定义请求整个处理流程：
 * <ul>
 * authenticate -> validate -> execute
 * </ul>
 * 最终其返回值插入到对应模板中。
 * 
 * @author qaohao
 * 
 * @param <T>
 *            结果VO类
 */
public abstract class BaseAction<T> implements Action {
	private static final Logger LOG = LoggerFactory.getLogger(BaseAction.class);

	@Autowired
	private Config config;

	@Autowired
	protected FreemarkerHelper freemarkerHelper;

	@Override
	public Response request(ActionContext actionContext) {
		// 参数认证通过且参数合法，则继续响应请求。
		Map paramMap = actionContext.getParamMap();
		if (authenticate(paramMap) && validate(paramMap)) {
			LOG.info("认证通过且请求参数合法。");
			// 获取请求内容类型，默认是json
			HttpContentType htpContentType = HttpContentType.json;
			if (paramMap.containsKey("format")) {
				htpContentType = HttpContentType.getInstance(paramMap.get(
						"format").toString());
			}
			LOG.info("请求内容格式为："+htpContentType.getFormat());
			
			// 处理请求并将处理结果插入到模板中。
			T t = execute(paramMap);
			String template = actionContext.getRequstUri()
					+ htpContentType.getSuffix();
			LOG.info("请求响应的模板：" + template);
			String content = freemarkerHelper.merge(template, t);

			// 组装Response对象。
			Response response = new Response();
			response.setStatus(HttpStatus.SC_OK);
			response.setType(htpContentType.getContentType());
			response.setContent(content);
			return response;
		} else {
			LOG.error("认证不通过或者参数中包含无效值！");
			// TODO
			return null;
		}
	}

	/**
	 * 验证参数。
	 * 
	 * @param paramMap
	 *            参数Map
	 * @return 不合法返回false，否则返回true
	 */
	public boolean validate(Map paramMap) {
		// TODO
		return true;
	}

	/**
	 * 执行请求。
	 * 
	 * @param paramMap
	 *            参数Map
	 * @return 处理后的结果。
	 */
	public abstract T execute(Map paramMap);

	/**
	 * 根据规则验证token有效性。
	 * 
	 * @param paramMap
	 *            参数Map
	 * @return 无效返回false，否则返回true
	 */
	final boolean authenticate(Map paramMap) {
		return true;
	}
}