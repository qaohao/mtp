package com.allyes.mtp.api.widget;

import java.util.Arrays;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.lang.ArrayUtils;
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
public class ListView extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(ListView.class);
	
	@Autowired
	private TaskService taskService;

	private static final DynaClass resultClazz = new BasicDynaClass(null, null,
			new DynaProperty[] {
			new DynaProperty("taskList", JSONObject[].class) });
	
	@Override
	public DynaBean execute(Map paramMap) throws AppException {
		String[] idList = taskService.getAllIds("西安");
		if (ArrayUtils.isEmpty(idList)) {
			return null;
		}
		if (idList.length > 40) {
			idList = Arrays.copyOf(idList, 40);
		}
		Task[] taskList = taskService.getBatch(idList);
		JSONObject[] taskJsonList = new JSONObject[taskList.length];
		for (int i = 0; i < taskList.length; i++) {
			taskJsonList[i] = JSONObject.fromObject(taskList[i].getValue());
		}
		try {
			DynaBean resultBean = resultClazz.newInstance();
			resultBean.set("taskList", taskJsonList);
			return resultBean;
		} catch (IllegalAccessException | InstantiationException e) {
			LOG.error("创建对象失败！", e);
			throw new SystemException("创建对象失败！", e);
		}
	}
}
