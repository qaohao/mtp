package com.allyes.mtp.service;

import java.util.List;

import com.allyes.mtp.common.error.AppException;
import com.allyes.mtp.vo.TaskDeal;

public interface TaskDealService {
	public List<TaskDeal> getTransactionHistory() throws AppException;
}