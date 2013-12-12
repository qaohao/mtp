package com.allyes.mtp;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class HttpFilter
 */
public class HttpEncodingFilter implements Filter {
	private static final Logger LOG = LoggerFactory.getLogger(HttpEncodingFilter.class);
	
	private String encoding;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOG.debug("进入过滤器处理。");
		// place your code here
		request.setCharacterEncoding(this.encoding);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter("encoding");
		LOG.debug("过滤器设置的参数编码是：" + encoding);
	}

}
