package gyqx.spdherp.deptMgr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.dao.mapper.IDeptGoodsApplyMapper;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;

@Repository
public class DeptGoodsApplyDao {

	@Autowired
	private IDeptGoodsApplyMapper deptGoodsApplyMapper;

	/**
	 * 科室商品申请，批量查询医院商品信息,且商品未加入到科室商品中
	 * 
	 * @param queryObject
	 *            <li>goodsName 商品名称模糊查询</li>
	 *            <li>hosId 医院id</li>
	 *            <li>deptId 医院科室id</li>
	 * @return
	 */
	public List<HosGoodsInfo> deptGoodsApplyBatchAddQuery(DeptGoodsApplyVo queryObject) {
		return deptGoodsApplyMapper.deptGoodsApplyBatchAddQuery(queryObject);
	}

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
	public List<DeptGoodsApplyVo> getDeptGoodsApplyList(DeptGoodsApplyVo queryObject) {
		return deptGoodsApplyMapper.getDeptGoodsApplyList(queryObject);
	}

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
	public void updateDeptGoodsApplySubmit(String hosId, String deptId, List<String> goodsApplyIds) {
		deptGoodsApplyMapper.updateDeptGoodsApplySubmit(hosId, deptId, goodsApplyIds);
	}

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
	public void deleteDeptGoodsApply(String hosId, String deptId, String id) {
		deptGoodsApplyMapper.deleteDeptGoodsApply(hosId, deptId, id);
	}

	/**
	 * 根据id更新科室商品申请申请状态为已阅
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
	public void updateDeptGoodsApplyRead(String hosId, String deptId, String id, Integer version) {
		deptGoodsApplyMapper.updateDeptGoodsApplyRead(hosId, deptId, id, version);
	}

	/**
	 * 根据id更新科室商品申请申请原由
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
	public void updateDeptGoodsApplyReason(String hosId, String deptId, String id, Integer version, String applyReason) {
		deptGoodsApplyMapper.updateDeptGoodsApplyReason(hosId, deptId, id, version, applyReason);
	}

}
