package gyqx.spdherp.stocInfoMgr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.base.PinYin;
import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.platform.sys.vo.SysOrgVo;
import gyqx.spdherp.stocInfoMgr.dao.StocInfoDao;
import gyqx.spdherp.stocInfoMgr.service.StocInfoService;
import gyqx.spdherp.stocInfoMgr.vo.StocInfoVo;
import gyqx.spdherp.stocInfoMgr.vo.StocLevel;

@Service
public class StocInfoServiceImpl implements StocInfoService {
	
	@Resource
	private StocInfoDao stocInfoDao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SimpleDao dao;
	
	@Override
	public List<StocInfoVo> queryStocInfo(Map<String, Object> param) {
		param.put("hosId", userOnlineStateUtils.getCurrent().getCorpId());
		return stocInfoDao.queryStocInfo(param);
	}
	@Override
	public List<SysOrgVo> getOrgInfo() {
		return stocInfoDao.getOrgInfo(userOnlineStateUtils.getCurrent().getCorpId());
	}
	@Override
	public StocInfoVo queryStocById(String id) {
		return stocInfoDao.queryStocById(id);
	}
	@Override
	public StocInfoVo updateStocInfo(StocInfoVo stocInfoVo) throws Exception {
		stocInfoVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		stocInfoVo.setShortPinyin(PinYin.getShortPinyin(stocInfoVo.getStocName()));
		// code
		for (StocLevel stocLevel : StocLevel.values()) {
			if(stocLevel.getName().equals(stocInfoVo.getStocLevel())){
				int index = stocLevel.getIndex();
				stocInfoVo.setCode(String.valueOf(index) + "." + stocInfoVo.getId());
				break;
			}
		}
		stocInfoDao.updateStocInfo(stocInfoVo);
		return stocInfoVo;
	}
	@Override
	public StocInfoVo insertStocInfo(StocInfoVo stocInfoVo) throws Exception {
		String stocId = atomUtil.newValue("stoc-id");
		stocInfoVo.setId(stocId); // sys_atom
		// code
		for (StocLevel stocLevel : StocLevel.values()) {
			if(stocLevel.getName().equals(stocInfoVo.getStocLevel())){
				int index = stocLevel.getIndex();
				stocInfoVo.setCode(String.valueOf(index) + "." + stocId);
				break;
			}
		}
		stocInfoVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		stocInfoVo.setShortPinyin(PinYin.getShortPinyin(stocInfoVo.getStocName()));
		stocInfoDao.insertStocInfo(stocInfoVo);
		return stocInfoVo;
	}
	@Override
	public void deleteStocInfo(StocInfoVo stocInfoVo) {
		dao.executeSql("delete from stoc_info where id = ?", stocInfoVo.getId());
	}
	
}
