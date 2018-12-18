package gyqx.spdherp.medicMgr.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.medicMgr.vo.MedicHosGoodsInfoVo;
import gyqx.spdherp.po.MedicalGoodsInfo;

public interface IMedicHosGoodsInfoService {

	/**
	 * 试剂产品信息主页面展示列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	public QueryResult<MedicHosGoodsInfoVo> listMedicHosGoods4MedicMgr(QueryInfo<MedicHosGoodsInfoVo> queryInfo) throws Exception;

	/**
	 * 新增试剂产品信息
	 * @param medicalGoodInfo
	 * @return
	 * @throws Exception
	 */
	public MedicalGoodsInfo addMedicHosGood(MedicalGoodsInfo medicalGoodInfo) throws Exception;

	/**
	 * 修改试剂产品信息
	 * @param medicalGoodInfo
	 * @return
	 */
	public int updateMedicHosGood(MedicalGoodsInfo medicalGoodInfo) throws Exception;

	/**
	 * 删除试剂产品信息
	 * @param medicalGoodInfo
	 */
	public int deleteMedicHosGood(MedicalGoodsInfo medicalGoodInfo) throws Exception;
}
