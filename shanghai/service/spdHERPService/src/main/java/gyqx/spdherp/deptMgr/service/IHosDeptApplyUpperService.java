package gyqx.spdherp.deptMgr.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.deptMgr.vo.HosDeptApplyUperQueryVo;
import gyqx.spdherp.po.HosDeptApplyUpper;

public interface IHosDeptApplyUpperService 
{
	HosDeptApplyUpper get(String id) throws Exception ;
	HosDeptApplyUpper add(HosDeptApplyUpper hosDeptApplyUpper) throws Exception ;
	HosDeptApplyUpper update(HosDeptApplyUpper hosDeptApplyUpper) throws Exception;
	
	List<HosDeptApplyUpper> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<HosDeptApplyUpper> list(HosDeptApplyUpper bean) throws Exception;
	
	QueryResult<HosDeptApplyUpper> listByPage(QueryInfo<HosDeptApplyUpper> queryInfo) throws Exception;
	
	/**
	 * 查询科室产品请购数量上限数据
	 * @param queryInfo
	 * @return QueryResult<HosDeptApplyUperQueryVo>
	 */
	QueryResult<HosDeptApplyUperQueryVo> queryHosDeptApplyUpperList(QueryInfo<HosDeptApplyUpper> queryInfo);	

}


