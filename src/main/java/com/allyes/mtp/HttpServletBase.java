package com.allyes.mtp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allyes.mtp.utils.SpringHelper;
import com.allyes.mtp.utils.WebUtil;

public class HttpServletBase extends HttpServlet implements ActionContext {
	private static final Logger LOG = LoggerFactory
			.getLogger(HttpServletBase.class);

	private String reqUri;
	private Map reqParamMap;
	private static ActionDispatcher actionDispatcher;
	private static String uriPrefix;
	
	@Override
	public void init() throws ServletException {
		uriPrefix = getInitParameter("uri_prefix");
	}
	
	@Override
	public final void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		SpringHelper.bindSessionContext(getServletContext());
		actionDispatcher = (ActionDispatcher) SpringHelper
				.getBean("actionDispatcher");

		HttpServletRequest htpReq = (HttpServletRequest) req;
		LOG.info("完整的请求url：" + htpReq.getRequestURL());
		reqUri = htpReq.getRequestURI().replaceFirst(htpReq.getContextPath(),
				StringUtils.EMPTY);
		reqUri = reqUri.startsWith("/") ? reqUri.substring(1) : reqUri;
		reqUri = reqUri.replaceFirst(uriPrefix, "");
		LOG.info("请求uri：" + reqUri);
		super.service(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		reqParamMap = req.getParameterMap();
		actionDispatcher.dispatch(this);
		doResponse(resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		InputStream is = req.getInputStream();
		String paramStr = IOUtils.toString(is);
		IOUtils.closeQuietly(is);
		LOG.info("post方式原始请求参数串：" + paramStr);
		
		reqParamMap = WebUtil.parseUrlParameters(paramStr);
		doResponse(resp);
	}
	
	protected void doResponse(HttpServletResponse httpResponse) throws IOException {
		Response response = actionDispatcher.dispatch(this);
		httpResponse.setStatus(response.getStatus());
		httpResponse.setContentType(response.getType());
		ServletOutputStream output = httpResponse.getOutputStream();
		IOUtils.write(response.getContent(), output);
		IOUtils.closeQuietly(output);
	}

	@Override
	public Map getParamMap() {
		return reqParamMap;
	}

	@Override
	public String getRequstUri() {
		return reqUri;
	}

	@Override
	public HttpServlet getHttpServlet() {
		return this;
	}

}
