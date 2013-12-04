package com.allyes.mtp.api.task;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.allyes.mtp.api.BaseAction;
import com.allyes.mtp.vo.Detail;

@Repository
public class GetDetails extends BaseAction<List<Detail>> {
	@Override
	public List<Detail> execute(Map paramMap) {
		return null;
	}
}
