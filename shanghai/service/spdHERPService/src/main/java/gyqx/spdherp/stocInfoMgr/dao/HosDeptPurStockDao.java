package gyqx.spdherp.stocInfoMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdherp.stocInfoMgr.dao.mapper.HosDeptPurStockMapper;
import gyqx.spdherp.stocInfoMgr.vo.HosDeptPurStockVo;

@Repository
public class HosDeptPurStockDao {
	
	@Resource
	private HosDeptPurStockMapper mapper;

	public List<HosDeptPurStockVo> queryDeptRelation(HosDeptPurStockVo hosDeptPurStockVo) {
		return mapper.queryDeptRelation(hosDeptPurStockVo);
	}
	
	public void update(HosDeptPurStockVo hosDeptPurStockVo){
		mapper.update(hosDeptPurStockVo);
	}

}
