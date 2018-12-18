package gyqx.spdhdi.myGoods.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdhdi.po.HosGoodsInfo;

public interface IHosGoodsInfoMapper {
	List<HosGoodsInfoVo> getHosGoodsInfoList(HosGoodsInfo hosGoodsInfo);
	List<HosGoodsInfoVo> listHosGoods4CodeMgr(HosGoodsInfoVo hosGoodsInfo);
	HosGoodsInfoVo getHosGoodsInfoById(@Param("id") String id);

	/**
	 * 批量更新科室产品信息采购模式
	 * @param purMode 采购模式
	 * @param hosId 医院id
	 * @param goodsId 产品id
	 */
	void batchUpdateDeptGoodsPurMode(@Param("purMode") String purMode, @Param("hosId") String hosId,
			@Param("goodsId") String goodsId);

    Integer listHosGoodsCount4CodeMgr(HosGoodsInfoVo good);
}
