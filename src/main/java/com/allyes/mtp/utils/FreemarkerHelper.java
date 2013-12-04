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

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author qaohao
 */
@Repository
public class FreemarkerHelper implements
		ApplicationListener<ContextRefreshedEvent> {
	private Logger LOG = LoggerFactory.getLogger(FreemarkerHelper.class);
	@Autowired
	private Config config;
	private Configuration configure = null;
	private String DEFAULT_ENCODING = "UTF-8";

	@SuppressWarnings("rawtypes")
	public String merge(String template, Object model) {
		Writer output = null;
		try {
			Template freemarkerTemplate = configure.getTemplate(template,
					DEFAULT_ENCODING);
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
		ApplicationContext applicationContext = event.getApplicationContext();
		if (applicationContext instanceof WebApplicationContext) {
			configure = new Configuration();
			configure.setServletContextForTemplateLoading(
					((WebApplicationContext)applicationContext).getServletContext(),
					config.getActionTemplateDir());
			configure.setDefaultEncoding(DEFAULT_ENCODING);
			LOG.info("Freemarker环境配置初始化完毕。");
		}
	}
}
