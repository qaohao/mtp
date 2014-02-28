package com.allyes.mtp.service;

import com.allyes.mtp.common.error.AppException;
import com.allyes.mtp.vo.Task;

public interface TaskService {
	public String[] getAllIds(String city) throws AppException;

	public Task getSingle(String id) throws AppException;

	public Task[] getBatch(String[] idArr) throws AppException;

	public String[] getCities() throws AppException;
}