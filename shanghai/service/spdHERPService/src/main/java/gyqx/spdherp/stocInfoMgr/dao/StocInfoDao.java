package gyqx.spdherp.stocInfoMgr.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.platform.sys.vo.SysOrgVo;
import gyqx.spdherp.stocInfoMgr.dao.mapper.StocInfoMapper;
import gyqx.spdherp.stocInfoMgr.vo.StocInfoVo;

@Repository
public class StocInfoDao {
	
	@Resource
	private StocInfoMapper mapper;

	public List<StocInfoVo> queryStocInfo(Map<String, Object> param) {
		return mapper.queryStocInfo(param);
	}
	
	public List<SysOrgVo> getOrgInfo(String corpId) {
		return mapper.getOrgInfo(corpId);
	}
	
	public StocInfoVo queryStocById(String id) {
		return mapper.queryStocById(id);
	}
	
	public void updateStocInfo(StocInfoVo stocInfoVo) {
		mapper.updateStocInfo(stocInfoVo);
	}

	public void insertStocInfo(StocInfoVo stocInfoVo) {
		mapper.insertStocInfo(stocInfoVo);
	}
}
