package com.allyes.mtp.api.stats;

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
import com.allyes.mtp.service.TaskDealService;
import com.allyes.mtp.vo.TaskDeal;

/**
 * 获取交易历史记录api接口。
 * 
 * @author qaohao
 */
@Repository
public final class GetDealHistory extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(GetDealHistory.class);
	private static final DynaClass resultClazz = new BasicDynaClass(null, null,
			new DynaProperty[] {
			new DynaProperty("count", Integer.class),
			new DynaProperty("total", Integer.class),
			new DynaProperty("taskDealList", TaskDeal[].class) });

	@Autowired
	private TaskDealService taskDealService;
	
	@Override
	public DynaBean execute(Map paramMap) {
		return null;
	}
}