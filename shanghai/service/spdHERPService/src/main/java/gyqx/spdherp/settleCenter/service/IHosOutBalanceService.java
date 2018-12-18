package gyqx.spdherp.settleCenter.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosOutBalance;
import gyqx.spdherp.settleCenter.vo.HosOutBalanceVo;

public interface IHosOutBalanceService 
{
	HosOutBalanceVo getById(String id) throws Exception ;
	HosOutBalanceVo getByBillId(String id) throws Exception ;
	List<String> createSettleBill(HosOutBalanceVo hosOutBalance) throws Exception;
	String add(HosOutBalanceVo hosOutBalance) throws Exception ;
	HosOutBalanceVo update(HosOutBalanceVo hosOutBalance) throws Exception;
	int delById(String id) throws Exception;

	List<HosOutBalanceVo> list(HosOutBalanceVo bean) throws Exception;
	
	QueryResult<HosOutBalanceVo> listByPage(QueryInfo<HosOutBalanceVo> queryInfo) throws Exception;

}


