package com.allyes.mtp.utils.dianping;

public interface Apis {
	/** 根据多个团购ID批量获取指定团购单的详细信息 */
	String get_batch_deals_by_id = "http://api.dianping.com/v1/deal/get_batch_deals_by_id";
	/** 获取指定城市当前在线的全部团购ID列表 */
	String get_all_id_list = "http://api.dianping.com/v1/deal/get_all_id_list";
	/** 根据团购ID获取指定团购单的详细信息 */
	String get_single_deal = "http://api.dianping.com/v1/deal/get_single_deal";
	/** 获取应用导入的团购交易的历史记录 */
	String get_deal_transaction_history = "http://api.dianping.com/v1/stats/get_deal_transaction_history";
}