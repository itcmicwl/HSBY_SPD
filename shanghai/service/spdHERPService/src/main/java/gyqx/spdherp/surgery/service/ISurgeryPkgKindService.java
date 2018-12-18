package gyqx.spdherp.surgery.service;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SurgeryPkgKind;
import gyqx.spdherp.surgery.vo.ElTreeVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgKindVo;

import java.util.List;

public interface ISurgeryPkgKindService 
{
	SurgeryPkgKind add(SurgeryPkgKind surgeryPkgKind) throws Exception ;
	int update(SurgeryPkgKind surgeryPkgKind) throws Exception;

	int delete(SurgeryPkgKind surgeryPkgKind) throws Exception;

	List<SurgeryPkgKindVo> list(SurgeryPkgKindVo surgeryPkgKind) throws Exception;

	public ElTreeVo getSurgeryKindTreeByHos(SurgeryPkgKindVo params) throws Exception;

}


