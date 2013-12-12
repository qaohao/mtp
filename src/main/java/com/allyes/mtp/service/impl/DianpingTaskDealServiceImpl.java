package com.allyes.mtp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allyes.mtp.service.TaskDealService;
import com.allyes.mtp.utils.spring.Properties;
import com.allyes.mtp.vo.TaskDeal;

@Service
public class DianpingTaskDealServiceImpl implements TaskDealService {
	@Properties(name="dianping.appkey")
	private String appKey;
	@Properties(name="dianping.app.secret")
	private String appSecret;
	
	@Override
	public List<TaskDeal> getTransactionHistory() {
		// TODO Auto-generated method stub
		return null;
	}
}
