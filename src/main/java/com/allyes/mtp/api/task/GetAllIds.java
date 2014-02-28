package com.allyes.mtp.api.task;

import java.util.Map;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.allyes.mtp.api.BaseAction;
import com.allyes.mtp.common.error.AppException;
import com.allyes.mtp.common.error.SystemException;
import com.allyes.mtp.service.TaskService;

/**
 * 获取所有任务ID api接口。
 * 
 * @author qaohao
 */
@Repository
public final class GetAllIds extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(GetAllIds.class);
	@Autowired
	private TaskService taskService;

	private static final DynaClass resultClazz = new BasicDynaClass(null, null,
			new DynaProperty[] {
					new DynaProperty("count", Integer.class),
					new DynaProperty("taskIdList", String[].class) });

	@Override
	public DynaBean execute(Map paramMap) throws AppException {
		String city = paramMap.get("city").toString();
		String[] idList = taskService.getAllIds(city);
		try {
			DynaBean resultBean = resultClazz.newInstance();
			resultBean.set("count", idList.length);
			resultBean.set("taskIdList", idList);
			return resultBean;
		} catch (IllegalAccessException e) {
			LOG.error("创建对象失败！", e);
			throw new SystemException("创建对象失败！");
		} catch (InstantiationException e) {
			LOG.error("创建对象失败！", e);
			throw new SystemException("创建对象失败！");
		}
	}
}