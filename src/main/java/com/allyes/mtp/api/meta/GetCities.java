package com.allyes.mtp.api.meta;

import java.util.Map;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.allyes.mtp.api.BaseAction;
import com.allyes.mtp.common.error.AppException;
import com.allyes.mtp.common.error.SystemException;
import com.allyes.mtp.service.TaskService;

@Repository
public class GetCities extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(GetCities.class);
	@Autowired
	private TaskService taskService;
	private static final DynaClass resultClazz = new BasicDynaClass(null, null,
			new DynaProperty[] {
					new DynaProperty("cityList", String[].class) });
	
	@Override
	public DynaBean execute(Map paramMap) throws AppException {
		String[] cityList = taskService.getCities();
		try {
			DynaBean resultBean = resultClazz.newInstance();
			resultBean.set("cityList", cityList);
			return resultBean;
		} catch (IllegalAccessException e) {
			LOG.error("创建对象失败！", e);
			throw new SystemException("创建对象失败！");
		} catch( InstantiationException e) {
			LOG.error("创建对象失败！", e);
			throw new SystemException("创建对象失败！");
		}
	}
}