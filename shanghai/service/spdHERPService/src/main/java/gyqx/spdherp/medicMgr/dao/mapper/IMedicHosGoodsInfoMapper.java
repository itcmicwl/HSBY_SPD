package gyqx.spdherp.medicMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.medicMgr.vo.MedicHosGoodsInfoVo;

public interface IMedicHosGoodsInfoMapper {
	/**
	 * 查询试剂管理模块，试剂产品信息维护主页面列表。
	 * 
	 * @param hosGoodsInfo  传递osGoodsInfoVo实体，通过hos_goods_info表中的,id、hos_Id分别
	 *   与medical_goods_info表中的goods_id、hos_id对应来关联查询出 MedicHosGoodsInfoVo实体列表表
	 * @return
	 */
	List<MedicHosGoodsInfoVo> listMedicHosGoods4MedicMgr(MedicHosGoodsInfoVo medicHosGoodsInfo);
}
