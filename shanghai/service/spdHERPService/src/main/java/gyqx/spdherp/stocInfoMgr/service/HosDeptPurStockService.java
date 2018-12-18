package gyqx.spdherp.stocInfoMgr.service;

import java.util.List;

import gyqx.spdherp.stocInfoMgr.vo.HosDeptPurStockVo;

/**
 * 科室请领关系
 * 
 * @date 2017年10月30日 下午3:13:40
 */
public interface HosDeptPurStockService {

	/**
	 * 通过医院和科室查询科室可以请领的对象
	 * @param hosDeptPurStockVo
	 * @return
	 */
	List<HosDeptPurStockVo> queryDeptRelation(HosDeptPurStockVo hosDeptPurStockVo) throws Exception;

	/**
	 * 保存科室请领关系
	 * @param hosDeptPurStockVo
	 * @throws Exception 
	 */
	void deptRelationSave(HosDeptPurStockVo hosDeptPurStockVo) throws Exception;
	
	/**
	 * 设置默认请购科室
	 * @param hosDeptPurStockVo
	 * @throws Exception 
	 */
	void setDefaultDept(HosDeptPurStockVo hosDeptPurStockVo) throws Exception;

}
