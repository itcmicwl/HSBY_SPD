package gyqx.spdherp.spdOutPtl.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SpdOutPtl;
import gyqx.spdherp.spdOutPtl.vo.DeptBuyForSpdPtl;
import gyqx.spdherp.spdOutPtl.vo.OutStockForSpdPtl;

public interface ISpdOutPtlService 
{
	SpdOutPtl get(String id) throws Exception ;
	SpdOutPtl add(SpdOutPtl spdOutPtl) throws Exception ;
	SpdOutPtl update(SpdOutPtl spdOutPtl) throws Exception;
	
	List<SpdOutPtl> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<SpdOutPtl> list(SpdOutPtl bean) throws Exception;
	
	QueryResult<SpdOutPtl> listByPage(QueryInfo<SpdOutPtl> queryInfo) throws Exception;

	boolean insertSpdOutPtlLists(List<SpdOutPtl> list) throws Exception;

	//根据请购单写中间表
	List<DeptBuyForSpdPtl> getPurListInfo(String billId) throws Exception;

	boolean forPurSpdOutPtl(String billId) throws Exception;


}


