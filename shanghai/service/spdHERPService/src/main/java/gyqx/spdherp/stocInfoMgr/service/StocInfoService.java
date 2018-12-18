package gyqx.spdherp.stocInfoMgr.service;

import java.util.List;
import java.util.Map;

import gyqx.platform.sys.vo.SysOrgVo;
import gyqx.spdherp.stocInfoMgr.vo.StocInfoVo;

public interface StocInfoService {
	
	/**
	 * 查询所有库房信息
	 * @param map 默认查询当前机构
	 * @return
	 */
	List<StocInfoVo> queryStocInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 查询当前机构科室信息
	 * @return
	 */
	public List<SysOrgVo> getOrgInfo();
	
	/**
	 * 根据库房ID查询当前库房详情
	 * @param id
	 * @return
	 */
	public StocInfoVo queryStocById(String id);
	
	/**
	 * 更新
	 * @param stocInfoVo
	 * @return
	 * @throws Exception
	 */
	public StocInfoVo updateStocInfo(StocInfoVo stocInfoVo) throws Exception;
	
	/**
	 * 插入
	 * @param stocInfoVo
	 * @return
	 * @throws Exception
	 */
	public StocInfoVo insertStocInfo(StocInfoVo stocInfoVo) throws Exception;
	
	/**
	 * 删除
	 * @param stocInfoVo
	 */
	void deleteStocInfo(StocInfoVo stocInfoVo);

}
