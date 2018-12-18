package gyqx.spdherp.stocInfoMgr.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import gyqx.platform.sys.vo.SysOrgVo;
import gyqx.spdherp.stocInfoMgr.vo.StocInfoVo;

public interface StocInfoMapper {
	/**
	 * 查询库存信息
	 * @param map
	 * @return
	 */
	List<StocInfoVo> queryStocInfo(Map<String, Object> param);
	
	/**
	 * 根据机构ID查询科室信息
	 * @param corpId 医院ID
	 * @return
	 */
	List<SysOrgVo> getOrgInfo(@Param("corpId")String corpId);
	
	/**
	 * 查询当前库房详情
	 * @param id
	 * @return
	 */
	StocInfoVo queryStocById(@Param("id") String id);
	
	/**
	 * 更新库房信息
	 * @param stocInfoVo
	 */
	void updateStocInfo(StocInfoVo stocInfoVo);
	
	/**
	 * 插入库房信息
	 * @param stocInfoVo
	 */
	void insertStocInfo(StocInfoVo stocInfoVo);
	
	
}
