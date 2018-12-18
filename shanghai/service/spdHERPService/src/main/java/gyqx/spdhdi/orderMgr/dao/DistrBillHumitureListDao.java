package gyqx.spdhdi.orderMgr.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.orderMgr.dao.mapper.IDistrBillHumitureListMapper;
import gyqx.spdhdi.orderMgr.vo.DistrBillHumitureListVo;

@Repository
public class DistrBillHumitureListDao 
{
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDistrBillHumitureListMapper mapper;

	public DistrBillHumitureListVo get(String id)
	{
		return mapper.get(id);	
	}	

	public int insertBatch(List<DistrBillHumitureListVo> distrBillHumitureList) throws Exception 
	{
		return mapper.insertBatch(distrBillHumitureList);
	}

	public int update(DistrBillHumitureListVo distrBillHumitureList) throws Exception {
		return mapper.update(distrBillHumitureList);
	}
	
	public void delById(String id) throws Exception {
		mapper.delById(id);
	}
	public void delByPid(String pid) throws Exception {
		mapper.delByPid(pid);
	}
	
	public List<DistrBillHumitureListVo> list(DistrBillHumitureListVo qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


