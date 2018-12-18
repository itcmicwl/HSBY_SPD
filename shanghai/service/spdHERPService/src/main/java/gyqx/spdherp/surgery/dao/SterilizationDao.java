package gyqx.spdherp.surgery.dao;


import common.db.SimpleDao;
import gyqx.spdherp.po.SurgeryPkg;
import gyqx.spdherp.surgery.dao.mapper.ISterilizationMapper;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SterilizationDao
{
	@Resource
	private SimpleDao dao;	

	@Resource
	private ISterilizationMapper mapper;

	public List<SurgeryPkgVo> listFirst(SurgeryPkgVo qbean) throws Exception{
		return mapper.listFirst(qbean);
	}

	public List<SurgeryPkgVo> listFirstExpire(SurgeryPkgVo qbean) throws Exception{
		return mapper.listFirstExpire(qbean);
	}

	public List<SurgeryPkgVo> listSec(SurgeryPkgVo queryInfo) throws Exception{
		return mapper.listSec(queryInfo);
	}

	public List<SurgeryPkgVo> listSecExpire(SurgeryPkgVo queryInfo){
		return mapper.listSecExpire(queryInfo);
	}

	public List<SurgeryPkgListVo> getNotFirstPkgListBySurCode(String sruCode){
		return mapper.getNotFirstPkgListBySurCode(sruCode);
	}

	public List<SurgeryPkgListVo> getNotSecPkgListBySurCode(String surCode){
		return  mapper.getNotSecPkgListBySurCode(surCode);
	}

	public int getCountNotSecPkgListBySurCode(String surCode){
		return mapper.getCountNotSecPkgListBySurCode(surCode);
	}

	public int updateFirstSterialization(List<SurgeryPkgListVo> list){
		return mapper.updateFirstSterialization(list);

	}

	public int updateSecSterialization(List<SurgeryPkgListVo> list){
		return mapper.updateSecSterialization(list);
	}

	public int updatePkgStatus(SurgeryPkgVo surgeryPkgVo){
		return mapper.updatePkgStatus(surgeryPkgVo);
	}


}


