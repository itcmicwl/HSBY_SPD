package gyqx.spdhdi.myInfos.service;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myInfos.vo.SaleCodeInfoVo;
import gyqx.spdhdi.myInfos.vo.ProvHosDeptInfoCodeVo;
import gyqx.spdhdi.po.ProvHosDeptInfoCode;

import java.util.List;

public interface IProvHosDeptInfoCodeService 
{
	ProvHosDeptInfoCode get(String id) throws Exception ;
	ProvHosDeptInfoCode add(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception ;
	ProvHosDeptInfoCode update(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception;
	ProvHosDeptInfoCodeVo getThePHDeptCodeVoByProvIdAndOrgId(String provId, String orgId)  throws  Exception;
	List<ProvHosDeptInfoCode> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<ProvHosDeptInfoCode> list(ProvHosDeptInfoCode bean) throws Exception;
	
	QueryResult<ProvHosDeptInfoCode> listByPage(QueryInfo<ProvHosDeptInfoCode> queryInfo) throws Exception;	
	QueryResult<ProvHosDeptInfoCodeVo> list_PH_dept_code_voByPage(QueryInfo<ProvHosDeptInfoCodeVo> queryInfo) throws Exception;	
	
	/**
	 * 查询业务员信息和部门
	 * @param vo 医院id, 要货科室ID, 供应商ID
	 * @return
	 */
	SaleCodeInfoVo getSaleCodeInfo(SaleCodeInfoVo vo);
}


