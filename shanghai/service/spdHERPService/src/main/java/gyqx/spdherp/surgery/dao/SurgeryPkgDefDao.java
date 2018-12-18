package gyqx.spdherp.surgery.dao;


import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdherp.po.SurgeryPkgDef;
import gyqx.spdherp.surgery.dao.mapper.ISurgeryPkgDefMapper;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SurgeryPkgDefDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISurgeryPkgDefMapper mapper;

	@Resource
	private SimpleDao simpleDao;

	public SurgeryPkgDef insertAndGet(SurgeryPkgDef surgeryPkgDef) throws Exception 
	{

		return dao.insertAndGet(surgeryPkgDef);
	}

	public int update(SurgeryPkgDef surgeryPkgDef) throws Exception {
		return mapper.update(surgeryPkgDef);
	}

	public List<SurgeryPkgDefVo> list(SurgeryPkgDefVo qbean) throws Exception{
		
		return mapper.list(qbean);
	}
}


