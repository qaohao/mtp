package com.allyes.mtp.utils;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.allyes.mtp.common.Config;
import com.allyes.mtp.common.error.SystemException;
import com.allyes.mtp.utils.spring.Properties;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author qaohao
 */
@Repository
public class FreemarkerHelper implements
		ApplicationListener<ContextRefreshedEvent> {
	private static final Logger LOG = LoggerFactory.getLogger(FreemarkerHelper.class);
	@Autowired
	private Config config;
	@Properties(name="encoding")
	private String encoding;
	@Properties(name="number_format")
	private String numberFormat;
	@Properties(name="datetime_format")
	private String datetimeFormat;
	@Properties(name="date_format")
	private String dateFormat;
	@Properties(name="time_format")
	private String timeFormat;
	@Properties(name="template_exception_handler")
	private String templateExceptionHandler;

	private Configuration configure = null;

	@SuppressWarnings("rawtypes")
	public String merge(String template, Object model) {
		Writer output = null;
		try {
			Template freemarkerTemplate = configure.getTemplate(template);
			output = new StringWriter();
			freemarkerTemplate.process(model, output);
			return output.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(output);
		}
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			ApplicationContext appContext = event.getApplicationContext();
			if (appContext instanceof WebApplicationContext) {
				configure = new Configuration();
				configure.setServletContextForTemplateLoading(
						((WebApplicationContext)appContext).getServletContext(),
						config.getActionTemplateDir());
				configure.setDefaultEncoding(encoding);
				configure.setOutputEncoding(encoding);
				configure.setNumberFormat(numberFormat);
				configure.setDateFormat(dateFormat);
				configure.setTimeFormat(timeFormat);
				configure.setDateTimeFormat(datetimeFormat);
				if ("RETHROW".equals(templateExceptionHandler)) {
					configure.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
				} else if ("DEBUG".equals(templateExceptionHandler)) {
					configure.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
				} else if ("IGNORE".equals(templateExceptionHandler)) {
					configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
				} else if ("HTML_DEBUG".equals(templateExceptionHandler)) {
					configure.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
				} else {
					configure.setSharedVariable("ctx", SpringHelper.getServletContextPath((WebApplicationContext) appContext));
				}
			}
		} catch (Exception e) {
			throw new SystemException("Freemarker环境配置初始化中失败！", e);
		} finally {
			LOG.info("Freemarker环境配置初始化完毕。");
		}
	}
}
