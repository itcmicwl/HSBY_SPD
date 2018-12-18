package gyqx.spdherp.surgery.dao;


import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.SurgeryPkgDefList;
import gyqx.spdherp.surgery.dao.mapper.ISurgeryPkgDefListMapper;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefListVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SurgeryPkgDefListDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISurgeryPkgDefListMapper mapper;

	public SurgeryPkgDefList insertAndGet(SurgeryPkgDefList surgeryPkgDefList) throws Exception
	{
		return dao.insertAndGet(surgeryPkgDefList);
	}

	public int insertByBatch(List<SurgeryPkgDefListVo> list) throws Exception {
		return mapper.insertByBatch(list);
	}

	public int delete(String id){
		return mapper.delete(id);
	}

	public int deleteByBatch(List<SurgeryPkgDefListVo> list){
		return  mapper.deleteByBatch(list);
	}

	public SurgeryPkgDefList updateAndGet(SurgeryPkgDefList surgeryPkgDefList) throws Exception {
		return dao.updateAndGet(surgeryPkgDefList);
	}

	public List<SurgeryPkgDefListVo> list(SurgeryPkgDefListVo qbean) throws Exception{
		return mapper.list(qbean);
	}
	public List<SurgeryPkgDefListVo> list4QG(SurgeryPkgDefListVo queryInfo)throws Exception{
		return mapper.list4QG(queryInfo);
	}
	public int update(SurgeryPkgDefList qbean) throws Exception{
		return mapper.update(qbean);
	}

	public List<SurgeryPkgDefListVo> listNotImportGoods(SurgeryPkgDefListVo surgeryPkgDefListVo){
		return mapper.listNotImportGoods(surgeryPkgDefListVo);
	}

	public 	List<HosGoodsInfoVo> getHosGoodsInfoById(String goodsId){
		return mapper.getHosGoodsInfoById(goodsId);
	}
}


