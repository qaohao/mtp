package com.allyes.mtp.api.stats;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.allyes.mtp.api.BaseAction;
import com.allyes.mtp.vo.Deal;

/**
 * 获取交易历史记录api接口。
 * 
 * @author qaohao
 */
@Repository
public class GetDealHistory extends BaseAction<List<Deal>> {
	@Override
	public List<Deal> execute(Map paramMap) {
		// TODO
		return null;
	}
}