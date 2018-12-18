package gyqx.spdhdi.orderMgr.dao;


import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.orderMgr.dao.mapper.IPurconfirmListMapper;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdhdi.orderMgr.vo.PurconfirmListVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PurconfirmListDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IPurconfirmListMapper mapper;

	public int insertByBatch(List<PurconfirmListVo> lst) throws Exception 
	{
		return mapper.insertByBatch(lst);
	}
	public int updateByBatch(List<PurconfirmListVo> lst) throws Exception {
		return mapper.updateByBatch(lst);
	}
	public int update(PurconfirmListVo purconfirmList) throws Exception {
		return mapper.update(purconfirmList);
	}	

	public void delById(String id) throws Exception {
		mapper.delById(id);
	}
	public void delByBillid(String billid) throws Exception {
		mapper.delByBillid(billid);
	}
	public List<PurconfirmListVo> list(PurconfirmListVo qbean) throws Exception{		
		return mapper.list(qbean);
	}
	public List<PurconfirmListVo> getListByDistrBillList(List<DistrBillListVo> lst) throws Exception{		
		return mapper.getListByDistrBillList(lst);
	}
}


