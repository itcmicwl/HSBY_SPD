package gyqx.spdherp.medicMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdherp.medicMgr.dao.mapper.IMedicHosGoodsInfoMapper;
import gyqx.spdherp.medicMgr.vo.MedicHosGoodsInfoVo;

@Repository
public class MedicHosGoodsInfoDao {

	@Resource
	private IMedicHosGoodsInfoMapper mapper;
	/**
	 *  试剂产品信息主页面展示列表
	 * @param good
	 * @return
	 * @throws Exception
	 */
	public List<MedicHosGoodsInfoVo> listMedicHosGoods4MedicMgr(MedicHosGoodsInfoVo good)
			throws Exception {
		
		return mapper.listMedicHosGoods4MedicMgr(good);
	}
}
