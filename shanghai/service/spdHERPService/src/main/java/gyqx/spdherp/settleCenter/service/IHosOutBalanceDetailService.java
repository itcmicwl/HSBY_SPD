package gyqx.spdherp.settleCenter.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosOutBalanceDetail;
import gyqx.spdherp.settleCenter.vo.HosOutBalanceDetailVo;

public interface IHosOutBalanceDetailService 
{
	HosOutBalanceDetailVo get(String id) throws Exception ;
	HosOutBalanceDetailVo add(HosOutBalanceDetailVo hosOutBalanceDetail) throws Exception ;
	HosOutBalanceDetailVo update(HosOutBalanceDetailVo hosOutBalanceDetail) throws Exception;
	int insertByBatch(List<HosOutBalanceDetailVo> lst)throws Exception;
	int updateByBatch(List<HosOutBalanceDetailVo> lst)throws Exception;
	int delById(String id)throws Exception;
	int delByBillId(String billId)throws Exception;
	List<HosOutBalanceDetailVo> list(HosOutBalanceDetailVo bean) throws Exception;
	QueryResult<HosOutBalanceDetailVo> listByPage(QueryInfo<HosOutBalanceDetailVo> queryInfo) throws Exception;

}


