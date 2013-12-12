package com.allyes.mtp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allyes.mtp.common.Config;
import com.allyes.mtp.common.error.AppException;
import com.allyes.mtp.service.TaskDealService;
import com.allyes.mtp.utils.MapBuilder;
import com.allyes.mtp.utils.dianping.ApiTool;
import com.allyes.mtp.utils.dianping.Apis;
import com.allyes.mtp.vo.TaskDeal;

@Service
public class DianpingTaskDealServiceImpl implements TaskDealService {
	@Autowired
	private Config config;

	@Override
	public TaskDeal[] getTransactionHistory(String beginTime, String endTime,
			int dealStatus) throws AppException {
		String result = ApiTool.requestApi(
				Apis.get_batch_deals_by_id,
				config.getDianpingAppKey(),
				config.getDianpingAppSecret(),
				MapBuilder.newInstance(HashMap.class)
						.put("begin_time", beginTime)
						.put("end_time", endTime)
						.put("transaction_status", dealStatus).map());
		JSONObject jsonObject = JSONObject.fromObject(result);
		if ("ok".equalsIgnoreCase(jsonObject.getString("status"))) {
			List<TaskDeal> taskDealList = new ArrayList<TaskDeal>();
			for (Object item : jsonObject.getJSONArray("transactions")) {
				TaskDeal taskDeal = new TaskDeal();
				taskDeal.setValue(item.toString());
				taskDealList.add(taskDeal);
			}
			return taskDealList.toArray(new TaskDeal[0]);
		} else {
			JSONObject error = jsonObject.getJSONObject("error");
			throw new AppException(error.getInt("errorCode"),
					error.getString("errorMessage"));
		}
	}
	
}
