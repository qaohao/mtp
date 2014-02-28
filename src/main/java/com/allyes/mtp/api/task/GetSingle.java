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
import com.allyes.mtp.vo.Task;

@Repository
public final class GetSingle extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(GetSingle.class);
	private static final DynaClass resultClazz = new BasicDynaClass(null, null,
			new DynaProperty[] { new DynaProperty("task", Task.class) });
	@Autowired
	private TaskService taskService;

	@Override
	public DynaBean execute(Map paramMap) throws AppException {
		Task task = taskService.getSingle(paramMap.get("task_id").toString());
		try {
			DynaBean resultBean = resultClazz.newInstance();
			resultBean.set("task", task);
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
