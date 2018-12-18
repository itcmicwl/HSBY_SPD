package gyqx.spdherp.productsInfos.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosCostCode;
import gyqx.spdherp.productsInfos.vo.HosCostCodeVo;

public interface IHosCostCodeService 
{
	HosCostCode get(String id) throws Exception ;
	HosCostCode add(HosCostCode hosCostCode) throws Exception ;
	HosCostCode update(HosCostCode hosCostCode) throws Exception;
	
	List<HosCostCode> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<HosCostCode> list(HosCostCode bean) throws Exception;
	
	QueryResult<HosCostCode> listByPage(QueryInfo<HosCostCode> queryInfo) throws Exception;	
	QueryResult<HosCostCodeVo> listByPage_HosCostCodeGoods(QueryInfo<HosCostCodeVo> queryInfo) throws Exception;	

}


