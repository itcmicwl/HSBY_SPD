package gyqx.spdherp.surgery.service;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SurgeryPkgDef;
import gyqx.spdherp.surgery.vo.ElTreeVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefVo;

import java.util.List;
import java.util.Map;

public interface ISurgeryPkgDefService 
{
	SurgeryPkgDef add(SurgeryPkgDefVo surgeryPkgDef) throws Exception ;
	int update(SurgeryPkgDefVo surgeryPkgDef) throws Exception;
	
	List<SurgeryPkgDefVo> list(SurgeryPkgDefVo bean) throws Exception;
	
	QueryResult<SurgeryPkgDefVo> listByPage(QueryInfo<SurgeryPkgDefVo> queryInfo) throws Exception;

	ElTreeVo getSurgeryKindDefTreeByHos(Map params) throws Exception;

}


