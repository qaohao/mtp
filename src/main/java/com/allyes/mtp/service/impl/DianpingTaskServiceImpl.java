package com.allyes.mtp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allyes.mtp.common.Config;
import com.allyes.mtp.common.error.AppException;
import com.allyes.mtp.service.TaskService;
import com.allyes.mtp.utils.MapBuilder;
import com.allyes.mtp.utils.dianping.ApiTool;
import com.allyes.mtp.utils.dianping.Apis;
import com.allyes.mtp.vo.Task;

@Service
public class DianpingTaskServiceImpl implements TaskService {
	@Autowired
	private Config config;

	@Override
	public String[] getAllIds(String city) throws AppException {
		String result = ApiTool.requestApi(Apis.get_all_id_list,
				config.getDianpingAppKey(), config.getDianpingAppSecret(),
				MapBuilder.newInstance(HashMap.class).put("city", city).map());

		JSONObject jsonObject = JSONObject.fromObject(result);
		if ("ok".equalsIgnoreCase(jsonObject.getString("status"))) {
			return (String[]) JSONArray.toArray(
					jsonObject.getJSONArray("id_list"), String.class);
		} else {
			JSONObject error = jsonObject.getJSONObject("error");
			throw new AppException(error.getInt("errorCode"),
					error.getString("errorMessage"));
		}
	}

	@Override
	public Task getSingle(String id) throws AppException {
		String result = ApiTool.requestApi(Apis.get_single_deal,
				config.getDianpingAppKey(), config.getDianpingAppSecret(),
				MapBuilder.newInstance(HashMap.class).put("deal_id", id).map());
		JSONObject jsonObject = JSONObject.fromObject(result);
		if ("ok".equalsIgnoreCase(jsonObject.getString("status"))) {
			JSONArray jsonArray = jsonObject.getJSONArray("deals");
			if (jsonArray != null && !jsonArray.isEmpty()) {
				Task task = new Task();
				task.setValue(jsonArray.get(0).toString());
				return task;
			}
			return null;
		} else {
			JSONObject error = jsonObject.getJSONObject("error");
			throw new AppException(error.getInt("errorCode"),
					error.getString("errorMessage"));
		}
	}

	@Override
	public Task[] getBatch(String[] idList) throws AppException {
		String result = ApiTool.requestApi(
				Apis.get_batch_deals_by_id,
				config.getDianpingAppKey(),
				config.getDianpingAppSecret(),
				MapBuilder.newInstance(HashMap.class)
						.put("deal_ids", StringUtils.join(idList, ",")).map());
		JSONObject jsonObject = JSONObject.fromObject(result);
		if ("ok".equalsIgnoreCase(jsonObject.getString("status"))) {
			List<Task> taskList = new ArrayList<Task>();
			for (Object item : jsonObject.getJSONArray("deals")) {
				Task task = new Task();
				task.setValue(item.toString());
				taskList.add(task);
			}
			return taskList.toArray(new Task[0]);
		} else {
			JSONObject error = jsonObject.getJSONObject("error");
			throw new AppException(error.getInt("errorCode"),
					error.getString("errorMessage"));
		}
	}

	@Override
	public String[] getCities() throws AppException {
		String result = ApiTool.requestApi(Apis.get_cities_with_businesses,
				config.getDianpingAppKey(), config.getDianpingAppSecret(), null);

		JSONObject jsonObject = JSONObject.fromObject(result);
		if ("ok".equalsIgnoreCase(jsonObject.getString("status"))) {
			return (String[]) JSONArray.toArray(
					jsonObject.getJSONArray("cities"), String.class);
		} else {
			JSONObject error = jsonObject.getJSONObject("error");
			throw new AppException(error.getInt("errorCode"),
					error.getString("errorMessage"));
		}
	}

}
