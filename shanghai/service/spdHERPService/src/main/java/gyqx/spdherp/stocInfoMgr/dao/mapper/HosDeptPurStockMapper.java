package gyqx.spdherp.stocInfoMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.stocInfoMgr.vo.HosDeptPurStockVo;

public interface HosDeptPurStockMapper {
	
	/**
	 * 查询科室请领关系
	 * @param hosDeptPurStockVo 医院id 部门id
	 * @return
	 */
	List<HosDeptPurStockVo> queryDeptRelation(HosDeptPurStockVo hosDeptPurStockVo);
	
	/**
	 * 更新
	 * @param hosDeptPurStockVo
	 */
	void update(HosDeptPurStockVo hosDeptPurStockVo);
}
