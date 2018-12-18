package gyqx.spdherp.stockout.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SickerUseList;
import gyqx.spdherp.stockout.vo.SickerUserListVo;

public interface ISickerUseListService 
{
	SickerUserListVo get(String id) throws Exception ;
	int add(SickerUserListVo sickerUseList) throws Exception ;
	int update(SickerUserListVo sickerUseList) throws Exception;
	List<SickerUserListVo> list(SickerUserListVo bean) throws Exception;
	List<SickerUserListVo> getListByBillId(String billId) throws Exception;
	QueryResult<SickerUserListVo> listByPage(QueryInfo<SickerUserListVo> queryInfo) throws Exception;
	int update4Return(List<SickerUserListVo> lst) throws Exception;
}


