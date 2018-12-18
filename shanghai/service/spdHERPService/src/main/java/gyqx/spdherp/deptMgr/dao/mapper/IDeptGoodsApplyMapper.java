package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;

public interface IDeptGoodsApplyMapper {

	/**
	 * 科室商品申请，批量查询医院商品信息,且商品未加入到科室商品中
	 * 
	 * @param queryObject
	 *            <li>goodsName 商品名称模糊查询</li>
	 *            <li>hosId 医院id</li>
	 *            <li>deptId 医院科室id</li>
	 * @return
	 */
	List<HosGoodsInfo> deptGoodsApplyBatchAddQuery(DeptGoodsApplyVo queryObject);

	/**
	 * 查询科室产品申请集合信息，过滤数据状态为40（已阅）
	 * 
	 * @param queryObject
	 *            <li>goodsName 商品名称模糊查询</li>
	 *            <li>status 商品审核状态</li>
	 *            <li>hosId 医院id</li>
	 *            <li>deptId 医院科室id</li>
	 * @return
	 */
	List<DeptGoodsApplyVo> getDeptGoodsApplyList(DeptGoodsApplyVo queryObject);

	/**
	 * 科室商品申请提交
	 * 
	 * @param hosId
	 *            医院id
	 * @param deptId
	 *            科室id
	 * @param goodsApplyIds
	 *            商品申请id集合
	 */
	void updateDeptGoodsApplySubmit(@Param("hosId") String hosId, @Param("deptId") String deptId,
			@Param("goodsApplyIds") List<String> goodsApplyIds);

	/**
	 * 根据申请id删除科室商品申请数据
	 * 
	 * @param hosId
	 *            医院id
	 * @param deptId
	 *            科室id
	 * @param id
	 *            申请数据id
	 */
	void deleteDeptGoodsApply(@Param("hosId") String hosId, @Param("deptId") String deptId, @Param("id") String id);

	/**
	 * 根据id更新科室商品申请状态为已阅
	 * 
	 * @param hosId
	 *            医院id
	 * @param deptId
	 *            科室id
	 * @param id
	 *            申请id
	 * @param version
	 *            数据版本
	 */
	void updateDeptGoodsApplyRead(@Param("hosId") String hosId, @Param("deptId") String deptId, @Param("id") String id,
			@Param("version") Integer version);

	/**
	 * 根据id更新科室商品申请原由
	 * 
	 * @param hosId
	 *            医院id
	 * @param deptId
	 *            科室id
	 * @param id
	 *            申请id
	 * @param version
	 *            数据版本
	 * @param applyReason
	 *            申请原有
	 */
	void updateDeptGoodsApplyReason(@Param("hosId") String hosId, @Param("deptId") String deptId,
			@Param("id") String id, @Param("version") Integer version, @Param("applyReason") String applyReason);

}
