package gyqx.spdherp.applyMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdherp.applyMgr.dao.mapper.IDeptGoodsStVoMapper;
import gyqx.spdherp.applyMgr.vo.DeptGoodsStVo;

@Repository
public class DeptGoodsStVoDao 
{	
	@Resource
	private IDeptGoodsStVoMapper mapper;
	
	public List<DeptGoodsStVo> list(DeptGoodsStVo qbean) throws Exception{		

		return mapper.list(qbean);
	}
}


