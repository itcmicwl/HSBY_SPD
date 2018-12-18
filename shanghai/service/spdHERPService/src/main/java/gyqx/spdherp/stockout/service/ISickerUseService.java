package gyqx.spdherp.stockout.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SickerUse;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.Sickuse4print;

public interface ISickerUseService 
{
	SickerUserVo get(String id) throws Exception ;
	int add(SickerUserVo sickerUse) throws Exception ;
	int update(SickerUserVo sickerUse) throws Exception;
	
	QueryResult<SickerUserVo> list(QueryInfo<SickerUserVo> queryInfo) throws Exception;
	
	QueryResult<SickerUserVo> listByPage(QueryInfo<SickerUserVo> queryInfo) throws Exception;

	void setOutBillStatus(List<SickerUserListVo> lst) throws Exception;
	Sickuse4print getSickuserList4Print(String billId);
}


