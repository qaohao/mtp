package com.allyes.mtp.service;

import com.allyes.mtp.common.error.AppException;
import com.allyes.mtp.vo.TaskDeal;

public interface TaskDealService {
	public TaskDeal[] getTransactionHistory(String beginTime, String endTime,
			int dealStatus) throws AppException;
}