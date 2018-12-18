package gyqx.spdherp.medicMgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.IBean;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.spdherp.medicMgr.dao.MedicHosGoodsInfoDao;
import gyqx.spdherp.medicMgr.service.IMedicHosGoodsInfoService;
import gyqx.spdherp.medicMgr.vo.MedicHosGoodsInfoVo;
import gyqx.spdherp.po.MedicalGoodsInfo;

@Service
public class MedicHosGoodsInfoService implements IMedicHosGoodsInfoService {

	@Resource
	private MedicHosGoodsInfoDao medicHosGoodsInfoDao;
	@Resource
	private SimpleDao simpleDao;

	/**
	 * 试剂产品信息主页面展示列表
	 */
	@Override
	public QueryResult<MedicHosGoodsInfoVo> listMedicHosGoods4MedicMgr(QueryInfo<MedicHosGoodsInfoVo> queryInfo)
			throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(medicHosGoodsInfoDao.listMedicHosGoods4MedicMgr(queryInfo.getQueryObject()));
	}

	/**
	 * 新增试剂产品信息
	 */
	@Override
	public MedicalGoodsInfo addMedicHosGood(MedicalGoodsInfo medicalGoodInfo) throws Exception {
		return simpleDao.insertAndGet(medicalGoodInfo);
	}

	/**
	 * 修改试剂产品信息
	 */
	@Override
	public int updateMedicHosGood(MedicalGoodsInfo medicalGoodInfo) throws Exception {
		return simpleDao.executeSql("update medical_goods_info set temperature_upper=?,temperature_lower=?,version=? where id=?",
				new Object[] {
						medicalGoodInfo.getTemperatureUpper(), 
						medicalGoodInfo.getTemperatureLower(),
						medicalGoodInfo.getVersion()+1,
						medicalGoodInfo.getId() 
				});
		//return update(medicalGoodInfo);
	}

	@Override
	public int deleteMedicHosGood(MedicalGoodsInfo medicalGoodInfo) throws Exception {
		return simpleDao.executeSql( "delete from medical_goods_info where id = ? ",medicalGoodInfo.getId());
		//return simpleDao.delete(medicalGoodInfo);
	}

}
